package edu.bada.samochodex.api;

import edu.bada.samochodex.model.Poczta;
import edu.bada.samochodex.service.PocztaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ApplicationController {

    private final PocztaService pocztaService;

    @Autowired
    public ApplicationController(PocztaService pocztaService) {
        this.pocztaService = pocztaService;
    }

    @RequestMapping("/poczty")
    public String viewPocztyPage(Model model) {
        List<Poczta> poczty = pocztaService.getAll();

        model.addAttribute("poczty", poczty);
        return "index";
    }

    @RequestMapping("/poczty/{id}")
    public String viewPocztyPage(Model model, @PathVariable("id") int id) {
        Poczta poczta = pocztaService.getById(id)
                .orElse(null);

        model.addAttribute("pocztaById", poczta);
        return "index";
    }

    @GetMapping("/")
    @ResponseBody
    public String greetings() {
        return "<b>*** Witamy na Samochodex ***</b> </br></br>" +
                "Przejdz do /poczty aby sprawdzić aktualne poczty";
    }
}

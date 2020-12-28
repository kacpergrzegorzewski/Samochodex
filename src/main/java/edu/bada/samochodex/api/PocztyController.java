package edu.bada.samochodex.api;

import edu.bada.samochodex.model.Poczta;
import edu.bada.samochodex.service.PocztaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/poczty")
public class PocztyController {

    private final PocztaService pocztaService;

    @Autowired
    public PocztyController(PocztaService pocztaService) {
        this.pocztaService = pocztaService;
    }

    @RequestMapping("/")
    public String viewPocztyPage(Model model) {
        List<Poczta> poczty = pocztaService.getAll();

        model.addAttribute("poczty", poczty);
        return "poczty";
    }

    // TODO: Not implemented yet
    @RequestMapping("/{id}")
    public String viewPocztyPage(Model model, @PathVariable("id") int id) {
        Poczta poczta = pocztaService.getById(id)
                .orElse(null);

        model.addAttribute("pocztaById", poczta);
        return "poczty";
    }
}

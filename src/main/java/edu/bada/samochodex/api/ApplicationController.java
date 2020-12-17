package edu.bada.samochodex.api;

import edu.bada.samochodex.dao.PocztaDao;
import edu.bada.samochodex.model.Poczta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ApplicationController {

    private final PocztaDao pocztaDao;

    @Autowired
    public ApplicationController(PocztaDao dao) {
        this.pocztaDao = dao;
    }

    @RequestMapping("/poczty")
    public String viewHomePage(Model model) {
        List<Poczta> poczty = pocztaDao.getAll();

        model.addAttribute("poczty", poczty);
        return "index";
    }

    @RequestMapping("/")
    @ResponseBody
    public String greetings() {
        return "<b>*** Witamy na Samochodex ***</b> </br></br>" +
                "Przejdz do /poczty aby sprawdzić aktualne poczty";
    }
}

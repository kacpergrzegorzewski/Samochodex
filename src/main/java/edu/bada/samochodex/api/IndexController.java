package edu.bada.samochodex.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String getHomeView() {
        return "index";
    }

    @GetMapping("/logowanie")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("/kontakt")
    public String getContactView() {
        return "contact";
    }

    @GetMapping("/historia")
    public String getHistoryView() {
        return "history";
    }
}

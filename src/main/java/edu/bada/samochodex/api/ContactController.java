package edu.bada.samochodex.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kontakt")
public class ContactController {

    @GetMapping
    public String getLoginView() {
        return "contact";
    }

}

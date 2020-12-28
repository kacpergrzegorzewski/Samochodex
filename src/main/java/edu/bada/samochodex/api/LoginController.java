package edu.bada.samochodex.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logowanie")
public class LoginController {

    @GetMapping
    public String getLoginView() {
        return "login";
    }

    @GetMapping("/salony")
    public String getSalony() {
        return "salony";
    }
}

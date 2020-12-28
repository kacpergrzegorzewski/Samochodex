package edu.bada.samochodex.api;

import edu.bada.samochodex.security.auth.ApplicationUserService;
import edu.bada.samochodex.security.auth.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {

    private final ApplicationUserService applicationUserService;

    public TemplateController(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @GetMapping("/login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("/register-user")
    public String getRegistrationView(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register-user")
    public String registerUser (@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        applicationUserService.signUpUser(user);

        return "home";
    }
}

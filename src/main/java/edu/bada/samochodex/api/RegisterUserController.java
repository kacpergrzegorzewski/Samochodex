package edu.bada.samochodex.api;

import edu.bada.samochodex.security.auth.ApplicationUserService;
import edu.bada.samochodex.security.auth.ApplicationUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rejestracja")
public class RegisterUserController {

    private final ApplicationUserService applicationUserService;

    public RegisterUserController(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @GetMapping
    public String getRegistrationView(Model model) {
        model.addAttribute("user", new ApplicationUser());
        return "registration";
    }

    @PostMapping
    public String registerUser (@ModelAttribute ApplicationUser user, Model model) {
        model.addAttribute("user", user);
        applicationUserService.registerUser(user);

        return "index";
    }
}

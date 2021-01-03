package edu.bada.samochodex.api;

import edu.bada.samochodex.model.Address;
import edu.bada.samochodex.model.Client;
import edu.bada.samochodex.security.auth.ApplicationUser;
import edu.bada.samochodex.security.auth.ApplicationUserService;
import edu.bada.samochodex.service.AddressService;
import edu.bada.samochodex.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/rejestracja")
public class RegisterUserController {

    private final ApplicationUserService applicationUserService;
    private final ClientService clientService;
    private final AddressService addressService;

    public RegisterUserController(ApplicationUserService applicationUserService, ClientService clientService, AddressService addressService) {
        this.applicationUserService = applicationUserService;
        this.clientService = clientService;
        this.addressService = addressService;
    }

    @GetMapping
    public String getRegistrationView(Model model) {
        List<Address> addresses = addressService.getAll();

        model.addAttribute("user", new ApplicationUser());
        model.addAttribute("client", new Client());
        model.addAttribute("addresses", addresses);

        return "registration";
    }

    @PostMapping
    public String registerUser (ApplicationUser user, Client client, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("client", client);

        applicationUserService.registerUser(user);
        clientService.save(client);

        return "index";
    }
}

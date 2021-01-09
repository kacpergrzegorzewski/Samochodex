package edu.bada.samochodex.api;

import edu.bada.samochodex.model.Address;
import edu.bada.samochodex.model.Client;
import edu.bada.samochodex.model.Post;
import edu.bada.samochodex.security.auth.ApplicationUser;
import edu.bada.samochodex.security.auth.ApplicationUserService;
import edu.bada.samochodex.service.AddressService;
import edu.bada.samochodex.service.ClientService;
import edu.bada.samochodex.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rejestracja")
public class RegisterUserController {

    private final ApplicationUserService applicationUserService;
    private final ClientService clientService;
    private final AddressService addressService;
    private final PostService postService;

    @Autowired
    public RegisterUserController(ApplicationUserService applicationUserService, ClientService clientService,
                                  AddressService addressService, PostService postService) {
        this.applicationUserService = applicationUserService;
        this.clientService = clientService;
        this.addressService = addressService;
        this.postService = postService;
    }

    @GetMapping
    public String getRegistrationView(Model model) {
        model.addAttribute("user", new ApplicationUser());
        model.addAttribute("client", new Client());
        model.addAttribute("address", new Address());
        model.addAttribute("post", new Post());

        return "registration/client_registration";
    }

    @PostMapping
    public String registerUser (ApplicationUser user, Client client, Address address, Post post, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("client", client);
        model.addAttribute("address", address);
        model.addAttribute("post", post);

        Post savedPost = postService.save(post);

        address.setPost(savedPost);
        addressService.save(address);

        applicationUserService.registerUser(user);

        client.setAddress(address);
        client.setEmail(user.getUsername());
        clientService.save(client);

        return "redirect:/?zarejestrowano=true";
    }
}

package edu.bada.samochodex.api;

import edu.bada.samochodex.model.Address;
import edu.bada.samochodex.model.Post;
import edu.bada.samochodex.service.AddressService;
import edu.bada.samochodex.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/adresy")
public class AddressController {

    private final AddressService addressService;
    private final PostService postService;

    @Autowired
    public AddressController(AddressService service, PostService postService) {
        this.addressService = service;
        this.postService = postService;
    }

    @GetMapping
    public String showAddressesPage(Model model) {
        List<Address> addresses = addressService.getAll();
        List<Post> posts = postService.getAll();

        model.addAttribute("addresses", addresses);
        model.addAttribute("posts", posts);

        return "addresses/addresses";
    }

    @GetMapping("/dodaj")
    public String showAddressAddForm(Model model) {
        Address address = new Address();
        List<Post> posts = postService.getAll();

        model.addAttribute("address", address);
        model.addAttribute("posts", posts);

        return "addresses/add_address";
    }

    @PostMapping("/zapisz")
    public String saveAddress(@ModelAttribute("address") Address address) {
        addressService.save(address);

        return "redirect:/adresy";
    }

    @GetMapping("/edytuj/{id}")
    public String showAddressEditForm(@PathVariable("id") long id, Model model) {
        Address address = addressService.getById(id);
        List<Post> posts = postService.getAll();

        model.addAttribute("address", address);
        model.addAttribute("posts", posts);

        return "addresses/edit_address";
    }
}

package edu.bada.samochodex.api;

import edu.bada.samochodex.model.*;
import edu.bada.samochodex.security.ApplicationUserRole;
import edu.bada.samochodex.security.auth.ApplicationUser;
import edu.bada.samochodex.security.auth.ApplicationUserService;
import edu.bada.samochodex.service.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/rejestracja")
public class RegisterUserController {

    private final ApplicationUserService applicationUserService;
    private final ClientService clientService;
    private final EmployeeService employeeService;
    private final AddressService addressService;
    private final PostService postService;
    private final PositionService positionService;
    private final CarDealerService carDealerService;

    public RegisterUserController(ApplicationUserService applicationUserService, ClientService clientService,
                                  EmployeeService employeeService, AddressService addressService, PostService postService,
                                  PositionService positionService, CarDealerService carDealerService) {
        this.applicationUserService = applicationUserService;
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.addressService = addressService;
        this.postService = postService;
        this.positionService = positionService;
        this.carDealerService = carDealerService;
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

        return "redirect:/";
    }

    /* ------ ADMIN ------- */

    @GetMapping("/pracownik")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getEmployeeRegistrationView(Model model) {
        List<CarDealer> carDealers = carDealerService.getAll();
        List<Position> positions = positionService.getAll();

        model.addAttribute("user", new ApplicationUser());
        model.addAttribute("employee", new Employee());
        model.addAttribute("address", new Address());
        model.addAttribute("post", new Post());
        model.addAttribute("carDealers", carDealers);
        model.addAttribute("positions", positions);

        return "registration/employee_registration";
    }

    @PostMapping("/pracownik")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String registerUserEmployee (ApplicationUser user, Employee employee, Address address, Post post, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("employee", employee);
        model.addAttribute("address", address);
        model.addAttribute("post", post);

        Post savedPost = postService.save(post);

        address.setPost(savedPost);
        addressService.save(address);

        user.setRole(ApplicationUserRole.EMPLOYEE.name());
        applicationUserService.registerUser(user);

        employee.setAddress(address);
        employee.setEmail(user.getUsername());
        employee.setDataZatrudniena(new Date(System.currentTimeMillis()));
        employeeService.save(employee);

        return "redirect:/";
    }
}

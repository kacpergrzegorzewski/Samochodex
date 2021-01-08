package edu.bada.samochodex.api;

import edu.bada.samochodex.model.*;
import edu.bada.samochodex.security.ApplicationUserRole;
import edu.bada.samochodex.security.auth.ApplicationUser;
import edu.bada.samochodex.security.auth.ApplicationUserService;
import edu.bada.samochodex.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private final CarDealerService carDealerService;
    private final CarService carService;
    private final PositionService positionService;
    private final PostService postService;
    private final AddressService addressService;
    private final ApplicationUserService applicationUserService;
    private final EmployeeService employeeService;
    private final OrderService orderService;

    @Autowired
    public AdminController(CarDealerService carDealerService, CarService carService, PositionService positionService,
                           PostService postService, AddressService addressService, ApplicationUserService applicationUserService,
                           EmployeeService employeeService, OrderService orderService) {
        this.carDealerService = carDealerService;
        this.carService = carService;
        this.positionService = positionService;
        this.postService = postService;
        this.addressService = addressService;
        this.applicationUserService = applicationUserService;
        this.employeeService = employeeService;
        this.orderService = orderService;
    }

    @GetMapping
    public String getAdminPage() {
        return "admin/admin";
    }

    //region ORDER MANAGEMENT
    @GetMapping("/zarzadzanie-zamowieniami")
    public String getOrdersPage(Model model) {
        List<Order> orders = orderService.getAll();

        model.addAttribute("orders", orders);

        return "admin/order_management";

    }

    @GetMapping("/zrealizuj-zamowienie/{id}")
    public String realizeOrder(@PathVariable("id") Long id) {
        Order realizedOrder = orderService.getById(id);

        realizedOrder.setDone(true);
        orderService.save(realizedOrder);

        return "redirect:/admin/zarzadzanie-zamowieniami";
    }

    @GetMapping("/anuluj-zamowienie/{id}")
    public String rejectOrder(@PathVariable("id") Long id) {
        Order realizedOrder = orderService.getById(id);
        Car orderedCar = carService.getById(realizedOrder.getCar().getId());

        orderedCar.setNaSprzedaz(true);
        carService.save(orderedCar);

        orderService.delete(realizedOrder);

        return "redirect:/admin/zarzadzanie-zamowieniami";
    }
    //endregion

    //region EMPLOYEE REGISTRATION
    @GetMapping("/zarejestruj-pracownika")
    public String getEmployeeRegistrationView(Model model) {
        List<CarDealer> carDealers = carDealerService.getAll();
        List<Position> positions = positionService.getAll();

        model.addAttribute("user", new ApplicationUser());
        model.addAttribute("employee", new Employee());
        model.addAttribute("address", new Address());
        model.addAttribute("post", new Post());
        model.addAttribute("carDealers", carDealers);
        model.addAttribute("positions", positions);

        return "admin/employee_registration";
    }

    @PostMapping("/zarejestruj-pracownika")
    public String registerEmployee (ApplicationUser user, Employee employee, Address address, Post post, Model model) {
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

        return "redirect:/admin";
    }
    //endregion

    //region EMPLOYEE MANAGEMENT
    @GetMapping("/zarzadzanie-pracownikami")
    public String getEmployeesPage(Model model) {
        List<Employee> employees = employeeService.getAll();

        model.addAttribute("employees", employees);

        return "admin/employee_management";
    }

    @GetMapping("/zwolnij-pracownika/{id}")
    public String fireEmployee(@PathVariable("id") Long id, Model model) {
        employeeService.deleteById(id);

        return "redirect:/admin/zarzadzanie-pracownikami";
    }
    //endregion

}

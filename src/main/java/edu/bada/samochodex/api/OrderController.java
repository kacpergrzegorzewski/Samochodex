package edu.bada.samochodex.api;

import edu.bada.samochodex.model.Car;
import edu.bada.samochodex.model.Client;
import edu.bada.samochodex.model.Employee;
import edu.bada.samochodex.model.Order;
import edu.bada.samochodex.service.CarService;
import edu.bada.samochodex.service.ClientService;
import edu.bada.samochodex.service.EmployeeService;
import edu.bada.samochodex.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/zamowienia")
public class OrderController {

    private final OrderService orderService;
    private final ClientService clientService;
    private final EmployeeService employeeService;
    private final CarService carService;

    @Autowired
    public OrderController(OrderService orderService, ClientService clientService, EmployeeService employeeService,
                           CarService carService) {
        this.orderService = orderService;
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.carService = carService;
    }

    @GetMapping
    public String showOrderPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client currClient = clientService.getByEmail(authentication.getName());

        List<Order> orders = orderService.getAllByClient(currClient);
        model.addAttribute("orders", orders);

        return "orders/orders";
    }

    @GetMapping("/anuluj/{id}")
    public String clientRejectOrder(@PathVariable("id") Long id) {
        Order orderToReject = orderService.getById(id);
        // Samochód z odrzucanego zamówienia
        Car carInOrder = carService.getById(orderToReject.getCar().getId());

        carInOrder.setNaSprzedaz(true);
        carService.save(carInOrder);

        orderService.delete(orderToReject);

        return "redirect:/zamowienia?anulowano=true";
    }

    /* ------ EMPLOYEE ------- */

    @GetMapping("/zarzadzanie")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public String showOrderManagementPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee currEmployee = employeeService.getByEmail(authentication.getName());

        List<Order> orders = orderService.getAllByEmployee(currEmployee);
        model.addAttribute("orders", orders);

        return "orders/order_management";
    }

    @GetMapping("/zarzadzanie/zrealizuj/{id}")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public String realizeOrder(@PathVariable("id") Long id) {
        Order relizedOrderd = orderService.getById(id);

        relizedOrderd.setDone(true);
        orderService.save(relizedOrderd);

        return "redirect:/zamowienia/zarzadzanie?zrealizowano=true";
    }

    @GetMapping("/zarzadzanie/anuluj/{id}")
    public String employeeRejectOrder(@PathVariable("id") Long id) {
        Order orderToReject = orderService.getById(id);
        // Samochód z odrzucanego zamówienia
        Car carInOrder = carService.getById(orderToReject.getCar().getId());

        carInOrder.setNaSprzedaz(true);
        carService.save(carInOrder);

        orderService.delete(orderToReject);

        return "redirect:/zamowienia/zarzadzanie?anulowano=true";
    }
}

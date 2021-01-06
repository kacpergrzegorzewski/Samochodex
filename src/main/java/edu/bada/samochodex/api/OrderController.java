package edu.bada.samochodex.api;

import edu.bada.samochodex.model.Client;
import edu.bada.samochodex.model.Employee;
import edu.bada.samochodex.model.Order;
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

    @Autowired
    public OrderController(OrderService orderService, ClientService clientService, EmployeeService employeeService) {
        this.orderService = orderService;
        this.clientService = clientService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String showOrderPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client currClient = clientService.getByEmail(authentication.getName());

        List<Order> orders = orderService.getAllByClient(currClient);
        model.addAttribute("orders", orders);

        return "orders/orders";
    }

    /* ------ EMPLOYEE ------- */

    @GetMapping("/zarzadzanie")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    public String showOrderManagementPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee currEmployee = employeeService.getByEmail(authentication.getName());

        List<Order> orders = orderService.getAllByEmployee(currEmployee);
        model.addAttribute("orders", orders);

        return "orders/order_management";
    }

    @GetMapping("/zarzadzanie/zrealizuj/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    public String realizeOrder(@PathVariable("id") Long id) {
        Order realizedOrder = orderService.getById(id);

        // Realizacja zamówienia sprawia, że samochód z zamówienia nie jest już na sprzedaż
        realizedOrder.getCar().setNaSprzedaz(false);

        orderService.delete(realizedOrder);

        return "redirect:/zamowienia";
    }
}

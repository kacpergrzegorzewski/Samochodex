package edu.bada.samochodex.api;

import edu.bada.samochodex.model.Client;
import edu.bada.samochodex.model.Order;
import edu.bada.samochodex.service.ClientService;
import edu.bada.samochodex.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/zamowienia")
public class OrderController {

    private final OrderService orderService;
    private final ClientService clientService;

    @Autowired
    public OrderController(OrderService orderService, ClientService clientService) {
        this.orderService = orderService;
        this.clientService = clientService;
    }

    @GetMapping
    public String showOrderPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client currClient = clientService.getByEmail(authentication.getName());

        List<Order> orders = orderService.getAllOfClient(currClient);
        model.addAttribute("orders", orders);

        return "orders/orders";
    }
}

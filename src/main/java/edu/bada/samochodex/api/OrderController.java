package edu.bada.samochodex.api;

import edu.bada.samochodex.model.Order;
import edu.bada.samochodex.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/zamówienia")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String showOrderPage(Model model) {
        List<Order> orders = orderService.getAll();
        model.addAttribute("orders", orders);

        return "orders/orders";
    }
}

package edu.bada.samochodex.service;

import edu.bada.samochodex.dao.OrderDao;
import edu.bada.samochodex.model.Client;
import edu.bada.samochodex.model.Employee;
import edu.bada.samochodex.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderDao orderDao;

    @Autowired
    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Order getById(Long id) {
        return orderDao.findById(id).orElse(null);
    }

    public List<Order> getAll() {
        return orderDao.findAll();
    }

    public List<Order> getAllByClient(Client client) {
        List<Order> orders = orderDao.findAll();

        return orders.stream()
                // Pokazuje niezrealizowane zamówenia
                .filter(order -> !order.isDone())
                .filter((order) -> order.getClient().getId().equals(client.getId()))
                .collect(Collectors.toList());
    }

    public List<Order> getAllByEmployee(Employee employee) {
        List<Order> orders = orderDao.findAll();

        return orders.stream()
                // Pokazuje niezrealizowane zamówenia
                .filter(order -> !order.isDone())
                .filter((order) -> order.getEmployee().getId().equals(employee.getId()))
                .collect(Collectors.toList());
    }

    public void save(Order order) {
        orderDao.save(order);
    }

    public void saveAll(List<Order> orders) {
        orderDao.saveAll(orders);
    }

    public void deleteById(Long id) {
        orderDao.deleteById(id);
    }

    public void delete(Order order) {
        orderDao.delete(order);
    }

    public void deleteAll() {
        orderDao.deleteAll();
    }
}

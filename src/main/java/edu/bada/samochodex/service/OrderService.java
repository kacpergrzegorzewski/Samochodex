package edu.bada.samochodex.service;

import edu.bada.samochodex.dao.OrderDao;
import edu.bada.samochodex.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void save(Order order) {
        orderDao.save(order);
    }

    public void deleteById(Long id) {
        orderDao.deleteById(id);
    }
}

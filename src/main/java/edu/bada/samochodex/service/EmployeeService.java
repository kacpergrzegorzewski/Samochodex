package edu.bada.samochodex.service;

import edu.bada.samochodex.dao.EmployeeDao;
import edu.bada.samochodex.model.Car;
import edu.bada.samochodex.model.Employee;
import edu.bada.samochodex.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class EmployeeService {

    private final EmployeeDao employeeDao;
    private final OrderService orderService;
    private final CarService carService;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao, OrderService orderService, CarService carService) {
        this.employeeDao = employeeDao;
        this.orderService = orderService;
        this.carService = carService;
    }

    public Employee getById(Long id) {
        return employeeDao.findById(id).orElse(null);
    }

    public Employee getByEmail(String email) {
        List<Employee> employees = employeeDao.findAll();

        return employees.stream()
                .filter(employee -> employee.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("Employee with email " + email + " not found"));
    }

    public List<Employee> getAll() {
        return employeeDao.findAll();
    }

    public void save(Employee employee) {
        employeeDao.save(employee);
    }

    public void delete(Employee employee) throws NullPointerException {
        Random random = new Random();
        List<Employee> employees = employeeDao.findAll();
        employees.remove(employee);

        // Zamówienia pracownika, który będzie zwalniany
        List<Order> orders = orderService.getAllByEmployee(employee);

        // Jeżeli zwolniono wszystkich pracowników to wszystkie zamówienia są anulowane
        if (employees.isEmpty()) {
            // Samochody, są znowu wystawiane na sprzedaż
            List<Car> cars = carService.getAllInOrders(orders);
            cars.forEach(car -> car.setNaSprzedaz(true));
            carService.saveAll(cars);

            orderService.deleteAll();
        } else {
            // Każdemu zamówieniu przypisuje nowego losowo wybranego pracownika
            orders.forEach(order -> order.setEmployee(employees.get(random.nextInt(employees.size()))));
            orderService.saveAll(orders);
        }

        employeeDao.delete(employee);
    }

    public void deleteById(Long id) {
        delete(getById(id));
    }
}

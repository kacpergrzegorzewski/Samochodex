package edu.bada.samochodex.service;

import edu.bada.samochodex.dao.EmployeeDao;
import edu.bada.samochodex.model.Client;
import edu.bada.samochodex.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
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

    public void deleteById(Long id) {
        employeeDao.deleteById(id);
    }
}

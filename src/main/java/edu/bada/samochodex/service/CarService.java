package edu.bada.samochodex.service;

import edu.bada.samochodex.dao.CarDao;
import edu.bada.samochodex.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarDao carDao;

    @Autowired
    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    public Car getById(Long id) {
        return carDao.findById(id).orElse(null);
    }

    public List<Car> getAll() {
        return carDao.findAll();
    }

    public void save(Car car) {
        carDao.save(car);
    }

    public void deleteById(Long id) {
        carDao.deleteById(id);
    }
}
package edu.bada.samochodex.service;

import edu.bada.samochodex.dao.CarModelDao;
import edu.bada.samochodex.model.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarModelService {

    private final CarModelDao carModelDao;

    @Autowired
    public CarModelService(CarModelDao carModelDao) {
        this.carModelDao = carModelDao;
    }

    public CarModel getById(Long id) {
        return carModelDao.findById(id).orElse(null);
    }

    public List<CarModel> getAll() {
        return carModelDao.findAll();
    }

    public void save(CarModel carModel) {
        carModelDao.save(carModel);
    }

    public void deleteById(Long id) {
        carModelDao.deleteById(id);
    }
}

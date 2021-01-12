package edu.bada.samochodex.service;

import edu.bada.samochodex.dao.CarBrandDao;
import edu.bada.samochodex.model.CarBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarBrandService {

    private final CarBrandDao carBrandDao;

    @Autowired
    public CarBrandService(CarBrandDao carBrandDao) {
        this.carBrandDao = carBrandDao;
    }

    public CarBrand getById(Long id) {
        return carBrandDao.findById(id).orElse(null);
    }

    public List<CarBrand> getAll() {
        return carBrandDao.findAll();
    }

    public void save(CarBrand carBrand) {
        carBrandDao.save(carBrand);
    }

    public void deleteById(Long id) {
        carBrandDao.deleteById(id);
    }
}

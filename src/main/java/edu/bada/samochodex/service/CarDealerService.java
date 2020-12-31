package edu.bada.samochodex.service;

import edu.bada.samochodex.dao.CarDealerDao;
import edu.bada.samochodex.model.CarDealer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarDealerService {

    private final CarDealerDao carDealerDao;

    @Autowired
    public CarDealerService(CarDealerDao carDealerDao) {
        this.carDealerDao = carDealerDao;
    }

    public CarDealer getById(Long id) {
        return carDealerDao.findById(id).orElse(null);
    }

    public List<CarDealer> getAll() {
        return carDealerDao.findAll();
    }

    public void save(CarDealer carDealer) {
        carDealerDao.save(carDealer);
    }

    public void deleteById(Long id) {
        carDealerDao.deleteById(id);
    }
}

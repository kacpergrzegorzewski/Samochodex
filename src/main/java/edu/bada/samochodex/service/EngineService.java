package edu.bada.samochodex.service;

import edu.bada.samochodex.dao.EngineDao;
import edu.bada.samochodex.model.CarModel;
import edu.bada.samochodex.model.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineService {

    private final EngineDao engineDao;

    @Autowired
    public EngineService(EngineDao engineDao) {
        this.engineDao = engineDao;
    }

    public Engine getById(Long id) {
        return engineDao.findById(id).orElse(null);
    }

    public List<Engine> getAll() {
        return engineDao.findAll();
    }

    public void save(Engine engine) {
        engineDao.save(engine);
    }

    public void deleteById(Long id) {
        engineDao.deleteById(id);
    }
}

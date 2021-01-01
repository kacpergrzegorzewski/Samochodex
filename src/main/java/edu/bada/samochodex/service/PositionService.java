package edu.bada.samochodex.service;

import edu.bada.samochodex.dao.PositionDao;
import edu.bada.samochodex.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    private final PositionDao positionDao;

    @Autowired
    public PositionService(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public Position getById(Long id) {
        return positionDao.findById(id).orElse(null);
    }

    public List<Position> getAll() {
        return positionDao.findAll();
    }

    public void save(Position position) {
        positionDao.save(position);
    }

    public void deleteById(Long id) {
        positionDao.deleteById(id);
    }
}

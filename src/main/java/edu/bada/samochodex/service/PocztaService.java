package edu.bada.samochodex.service;

import edu.bada.samochodex.dao.Dao;
import edu.bada.samochodex.model.Poczta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PocztaService {

    private final Dao<Poczta> pocztaDao;

    @Autowired
    public PocztaService(@Qualifier("oracle") Dao<Poczta> pocztaDao) {
        this.pocztaDao = pocztaDao;
    }

    public Optional<Poczta> getById(int id) {
        return pocztaDao.getById(id);
    }

    public List<Poczta> getAll() {
        return pocztaDao.getAll();
    }

    public void save(Poczta poczta) {}

    public Poczta get(int id) {
        return null;
    }

    public void update(Poczta poczta) {}

    public void delete(int id) {}
}

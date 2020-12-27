package edu.bada.samochodex.dao;

import edu.bada.samochodex.model.Poczta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("oracle")
public class PocztaDao implements Dao<Poczta> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PocztaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Poczta> getById(int id) {
        String sql = String.format("SELECT * FROM POCZTY WHERE ID_POCZTY=%s", id);
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Poczta.class))
                .stream()
                .findFirst();
    }

    public List<Poczta> getAll() {
        String sql = "SELECT * FROM POCZTY";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Poczta.class));
    }

    // CRUD methods
    public void save(Poczta poczta) {}

    public Poczta get(int id) {
        return null;
    }

    public void update(Poczta poczta) {}

    public void delete(int id) {}
}

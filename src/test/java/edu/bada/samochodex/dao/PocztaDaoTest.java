package edu.bada.samochodex.dao;

import edu.bada.samochodex.model.Poczta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PocztaDaoTest {

    private PocztaDao pocztaDao;

    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf");
        dataSource.setUsername("msuchoc1");
        dataSource.setPassword("msuchoc1");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");

        pocztaDao = new PocztaDao(new JdbcTemplate(dataSource));
    }

    @Test
    void getAll() {
        List<Poczta> poczty = pocztaDao.getAll();
        assertFalse(poczty.isEmpty());
    }

    @Test
    void save() {
    }

    @Test
    void get() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}
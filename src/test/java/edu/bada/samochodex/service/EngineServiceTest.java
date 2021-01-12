package edu.bada.samochodex.service;

import edu.bada.samochodex.dao.EngineDao;
import edu.bada.samochodex.model.Engine;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@Disabled
class EngineServiceTest {

    @Autowired
    private EngineService engineService;

    @MockBean
    private EngineDao engineDao;

    static Long id = 1L;

    @Test
    void getById() {
        Engine engine = new Engine("Silnik", 300, 300, "Benzyna", 1000);
        engine.setId(id);

        when(engineDao.findById(id)).thenReturn(java.util.Optional.of(engine));
        assertThat(engineService.getById(id)).isEqualTo(engine);
    }

    @Test
    void getAll() {
        Engine engine1 = new Engine("Silnik", 300, 300, "Benzyna", 1000);
        engine1.setId(id);

        Engine engine2 = new Engine("Silnik2", 300, 300, "Benzyna", 1000);
        engine2.setId(id+1);

        List<Engine> engines = Arrays.asList(engine1, engine2);

        when(engineDao.findAll()).thenReturn(engines);
        assertThat(engineService.getAll()).isEqualTo(engines);
    }

    @Test
    void save() {
        Engine engine = new Engine("Silnik", 300, 300, "Benzyna", 1000);
        engine.setId(id);

        when(engineDao.save(engine)).thenReturn(engine);
        assertThat(engineService.save(engine)).isEqualTo(engine);
    }
}
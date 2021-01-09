package edu.bada.samochodex.service;

import edu.bada.samochodex.dao.PositionDao;
import edu.bada.samochodex.model.Position;
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
class PositionServiceTest {

    @Autowired
    private PositionService positionService;

    @MockBean
    private PositionDao positionDao;

    static Long id = 1L;

    @Test
    void getById() {
        Position position = new Position("Pracownik");
        position.setId(id);

        when(positionDao.findById(id)).thenReturn(java.util.Optional.of(position));
        assertThat(positionService.getById(id)).isEqualTo(position);
    }

    @Test
    void getAll() {
        Position position1 = new Position("Pracownik");
        position1.setId(id);

        Position position2 = new Position("Sprzątaczka");
        position2.setId(id+1);

        List<Position> positions = Arrays.asList(position1, position2);

        when(positionDao.findAll()).thenReturn(positions);
        assertThat(positionService.getAll()).isEqualTo(positions);
    }

    @Test
    void save() {
        Position position = new Position("Pracownik");
        position.setId(id);

        when(positionDao.save(position)).thenReturn(position);
        assertThat(positionService.save(position)).isEqualTo(position);
    }
}
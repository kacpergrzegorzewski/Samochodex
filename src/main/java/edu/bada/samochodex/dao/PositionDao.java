package edu.bada.samochodex.dao;

import edu.bada.samochodex.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionDao extends JpaRepository<Position, Long> {
}

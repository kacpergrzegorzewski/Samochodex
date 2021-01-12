package edu.bada.samochodex.dao;

import edu.bada.samochodex.model.Engine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineDao extends JpaRepository<Engine, Long> {
}

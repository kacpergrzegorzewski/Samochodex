package edu.bada.samochodex.dao;

import edu.bada.samochodex.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarModelDao extends JpaRepository<CarModel, Long> {
}

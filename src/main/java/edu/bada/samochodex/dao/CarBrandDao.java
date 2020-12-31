package edu.bada.samochodex.dao;

import edu.bada.samochodex.model.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarBrandDao extends JpaRepository<CarBrand, Long> {
}

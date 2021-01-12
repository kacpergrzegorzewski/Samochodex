package edu.bada.samochodex.dao;

import edu.bada.samochodex.model.CarDealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDealerDao extends JpaRepository<CarDealer, Long> {
}

package edu.bada.samochodex.dao;

import edu.bada.samochodex.model.EquipmentVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentVersionDao extends JpaRepository<EquipmentVersion, Long> {
}

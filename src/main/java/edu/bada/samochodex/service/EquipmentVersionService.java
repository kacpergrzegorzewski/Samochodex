package edu.bada.samochodex.service;

import edu.bada.samochodex.dao.EquipmentVersionDao;
import edu.bada.samochodex.model.EquipmentVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentVersionService {

    private final EquipmentVersionDao equipmentVersionDao;

    @Autowired
    public EquipmentVersionService(EquipmentVersionDao equipmentVersionDao) {
        this.equipmentVersionDao = equipmentVersionDao;
    }

    public EquipmentVersion getById(Long id) {
        return equipmentVersionDao.findById(id).orElse(null);
    }

    public List<EquipmentVersion> getAll() {
        return equipmentVersionDao.findAll();
    }

    public void save(EquipmentVersion equipmentVersion) {
        equipmentVersionDao.save(equipmentVersion);
    }

    public void deleteById(Long id) {
        equipmentVersionDao.deleteById(id);
    }
}

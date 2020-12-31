package edu.bada.samochodex.service;

import edu.bada.samochodex.dao.AddressDao;
import edu.bada.samochodex.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressDao addressDao;

    @Autowired
    public AddressService(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    public Address getById(Long id) {
        return addressDao.findById(id).orElse(null);
    }

    public List<Address> getAll() {
        return addressDao.findAll();
    }

    public void save(Address address) {
        addressDao.save(address);
    }

    public void update(Address address) {}

    public void deleteById(Long id) {
        addressDao.deleteById(id);
    }
}

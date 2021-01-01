package edu.bada.samochodex.service;

import edu.bada.samochodex.dao.ClientDao;
import edu.bada.samochodex.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientDao clientDao;

    @Autowired
    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public Client getById(Long id) {
        return clientDao.findById(id).orElse(null);
    }

    public List<Client> getAll() {
        return clientDao.findAll();
    }

    public void save(Client client) {
        clientDao.save(client);
    }

    public void deleteById(Long id) {
        clientDao.deleteById(id);
    }
}

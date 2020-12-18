package edu.bada.samochodex.dao;

import edu.bada.samochodex.model.Poczta;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {

    Optional<Poczta> getById(int id);

    List<T> getAll();

    void save(T t);

    T get(int id);

    void update(T t);

    void delete(int id);
}

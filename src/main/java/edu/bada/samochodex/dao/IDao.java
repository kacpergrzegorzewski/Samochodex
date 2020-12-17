package edu.bada.samochodex.dao;

import java.util.List;

public interface IDao<T> {

    List<T> getAll();

    void save(T t);

    T get(int id);

    void update(T t);

    void delete(int id);
}

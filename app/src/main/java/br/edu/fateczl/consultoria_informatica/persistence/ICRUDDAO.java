package br.edu.fateczl.consultoria_informatica.persistence;

import java.sql.SQLException;
import java.util.List;

public interface ICRUDDAO<T> {
    void insert(T entity) throws SQLException;
    void update(T entity) throws SQLException;
    void delete(T entity) throws SQLException;
    T findById(int id) throws SQLException;
    List<T> findAll() throws SQLException;
}

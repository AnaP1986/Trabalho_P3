package br.edu.fateczl.consultoria_informatica.controller;

import java.sql.SQLException;
import java.util.List;

public interface IController<T> {
    void add(T entity) throws SQLException;
    void update(T entity) throws SQLException;
    void remove(T entity) throws SQLException;
    T findById(int id) throws SQLException;
    List<T> findAll() throws SQLException;
}

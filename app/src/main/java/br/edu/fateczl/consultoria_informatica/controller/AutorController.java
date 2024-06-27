package br.edu.fateczl.consultoria_informatica.controller;

import android.content.Context;
import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.consultoria_informatica.model.Autor;
import br.edu.fateczl.consultoria_informatica.persistence.AutorDAO;
import br.edu.fateczl.consultoria_informatica.persistence.IAutorDAO;

public class AutorController implements IController<Autor> {
    private IAutorDAO autorDAO;

    public AutorController(Context context) {
        this.autorDAO = new AutorDAO(context);
    }

    @Override
    public void add(Autor autor) throws SQLException {
        autorDAO.insert(autor);
    }

    @Override
    public void update(Autor autor) throws SQLException {
        autorDAO.update(autor);
    }

    @Override
    public void remove(Autor autor) throws SQLException {
        autorDAO.delete(autor);
    }

    @Override
    public Autor findById(int id) throws SQLException {
        return autorDAO.findById(id);
    }

    @Override
    public List<Autor> findAll() throws SQLException {
        return autorDAO.findAll();
    }
}

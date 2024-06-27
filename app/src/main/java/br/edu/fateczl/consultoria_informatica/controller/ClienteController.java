package br.edu.fateczl.consultoria_informatica.controller;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.consultoria_informatica.model.Cliente;
import br.edu.fateczl.consultoria_informatica.persistence.ClienteDao;

public class ClienteController {
    private ClienteDao clienteDao;

    public ClienteController(Context context) {
        clienteDao = new ClienteDao(context);
    }

    public void add(Cliente cliente) throws SQLException {
        clienteDao.insert(cliente);
    }

    public void update(Cliente cliente) throws SQLException {
        clienteDao.update(cliente);
    }

    public void remove(Cliente cliente) throws SQLException {
        clienteDao.delete(cliente);
    }

    public Cliente findById(String cpf) throws SQLException { // Modificado para aceitar String
        return clienteDao.findById(cpf);
    }

    public List<Cliente> findAll() throws SQLException {
        return clienteDao.findAll();
    }
}

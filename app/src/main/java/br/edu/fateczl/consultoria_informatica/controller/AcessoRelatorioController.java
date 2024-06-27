package br.edu.fateczl.consultoria_informatica.controllers;

import android.content.Context;
import java.sql.SQLException;
import java.util.List;
import br.edu.fateczl.consultoria_informatica.model.AcessoRelatorio;
import br.edu.fateczl.consultoria_informatica.persistence.AcessoRelatorioDAO;

public class AcessoRelatorioController {
    private AcessoRelatorioDAO acessoRelatorioDAO;

    public AcessoRelatorioController(Context context) {
        acessoRelatorioDAO = new AcessoRelatorioDAO(context);
    }

    public void add(AcessoRelatorio acessoRelatorio) throws SQLException {
        acessoRelatorioDAO.insert(acessoRelatorio);
    }

    public void update(AcessoRelatorio acessoRelatorio) throws SQLException {
        acessoRelatorioDAO.update(acessoRelatorio);
    }

    public void remove(AcessoRelatorio acessoRelatorio) throws SQLException {
        acessoRelatorioDAO.delete(acessoRelatorio);
    }

    public List<AcessoRelatorio> list() throws SQLException {
        return acessoRelatorioDAO.selectAll();
    }
}

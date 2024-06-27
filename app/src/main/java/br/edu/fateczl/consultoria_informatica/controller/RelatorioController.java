package br.edu.fateczl.consultoria_informatica.controllers;

import android.content.Context;
import java.sql.SQLException;
import java.util.List;
import br.edu.fateczl.consultoria_informatica.model.Relatorio;
import br.edu.fateczl.consultoria_informatica.persistence.RelatorioDAO;

public class RelatorioController {
    private RelatorioDAO relatorioDAO;

    public RelatorioController(Context context) {
        relatorioDAO = new RelatorioDAO(context);
    }

    public void add(Relatorio relatorio) throws SQLException {
        relatorioDAO.insert(relatorio);
    }

    public void update(Relatorio relatorio) throws SQLException {
        relatorioDAO.update(relatorio);
    }

    public void remove(Relatorio relatorio) throws SQLException {
        relatorioDAO.delete(relatorio);
    }

    public List<Relatorio> list() throws SQLException {
        return relatorioDAO.selectAll();
    }
}

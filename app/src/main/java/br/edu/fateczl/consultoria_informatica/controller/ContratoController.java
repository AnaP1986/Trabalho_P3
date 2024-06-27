package br.edu.fateczl.consultoria_informatica.controller;

import android.content.Context;
import java.util.List;  // Importação do List
import br.edu.fateczl.consultoria_informatica.model.Contrato;
import br.edu.fateczl.consultoria_informatica.persistence.ContratoDao;

public class ContratoController {
    private final ContratoDao contratoDao;

    public ContratoController(Context context) {
        this.contratoDao = new ContratoDao(context);
    }

    public void insertContrato(Contrato contrato) {
        contratoDao.insert(contrato);
    }

    public void updateContrato(Contrato contrato) {
        contratoDao.update(contrato);
    }

    public void deleteContrato(Contrato contrato) {
        contratoDao.delete(contrato);
    }

    public Contrato findContratoById(int id) {
        return contratoDao.findById(id);
    }

    public List<Contrato> findAllContratos() {
        return contratoDao.findAll();
    }
}

package br.edu.fateczl.consultoria_informatica.model;

public class Contrato {
    private int id;
    private int idCliente;
    private String dataInicio;
    private String dataFim;

    // Construtor
    public Contrato(int id, int idCliente, String dataInicio, String dataFim) {
        this.id = id;
        this.idCliente = idCliente;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }
}

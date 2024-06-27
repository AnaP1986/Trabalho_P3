package br.edu.fateczl.consultoria_informatica.model;

public class AcessoRelatorio {
    private int numeroAcesso;
    private int id;
    private String dataAcesso;
    private Cliente cliente;
    private Relatorio relatorio;

    public AcessoRelatorio(int numeroAcesso, int id, String dataAcesso, Cliente cliente, Relatorio relatorio) {
        this.numeroAcesso = numeroAcesso;
        this.id = id;
        this.dataAcesso = dataAcesso;
        this.cliente = cliente;
        this.relatorio = relatorio;
    }

    public int getNumeroAcesso() {
        return numeroAcesso;
    }

    public void setNumeroAcesso(int numeroAcesso) {
        this.numeroAcesso = numeroAcesso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataAcesso() {
        return dataAcesso;
    }

    public void setDataAcesso(String dataAcesso) {
        this.dataAcesso = dataAcesso;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Relatorio getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(Relatorio relatorio) {
        this.relatorio = relatorio;
    }
}

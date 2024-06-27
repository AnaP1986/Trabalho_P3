package br.edu.fateczl.consultoria_informatica.model;

public class Autor {
    private int idAutor;
    private String nome;
    private String sobrenome;
    private boolean anonimo;

    // Construtor padrão
    public Autor() {
        this.idAutor = 0;
        this.nome = "";
        this.sobrenome = "";
        this.anonimo = false;
    }

    // Construtor com parâmetros
    public Autor(int idAutor, String nome, String sobrenome, boolean anonimo) {
        this.idAutor = idAutor;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.anonimo = anonimo;
    }

    // Getters e Setters
    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public boolean isAnonimo() {
        return anonimo;
    }

    public void setAnonimo(boolean anonimo) {
        this.anonimo = anonimo;
    }

    // Método getId() adicionado
    public int getId() {
        return idAutor;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "idAutor=" + idAutor +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", anonimo=" + anonimo +
                '}';
    }
}

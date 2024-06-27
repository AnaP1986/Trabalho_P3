package br.edu.fateczl.consultoria_informatica.model;

public class Relatorio {
    private int idRelatorio;
    private String titulo;
    private String resumo;
    private String texto;
    private boolean isPublico;
    private Autor autor;

    // Construtor
    public Relatorio(int idRelatorio, String titulo, String resumo, String texto, boolean isPublico, Autor autor) {
        this.idRelatorio = idRelatorio;
        this.titulo = titulo;
        this.resumo = resumo;
        this.texto = texto;
        this.isPublico = isPublico;
        this.autor = autor;
    }

    // Getters e Setters
    public int getIdRelatorio() {
        return idRelatorio;
    }

    public void setIdRelatorio(int idRelatorio) {
        this.idRelatorio = idRelatorio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isPublico() {
        return isPublico;
    }

    public void setPublico(boolean publico) {
        isPublico = publico;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    // Adicionando o método getId()
    public int getId() {
        return idRelatorio;
    }

    @Override
    public String toString() {
        return "Relatorio{" +
                "idRelatorio=" + idRelatorio +
                ", titulo='" + titulo + '\'' +
                ", resumo='" + resumo + '\'' +
                ", texto='" + texto + '\'' +
                ", isPublico=" + isPublico +
                ", autor=" + autor +
                '}';
    }
}

package br.com.mrkt.model;

/**
 * Classe responsável por conter os atributos e métodos de acesso do objeto Administrador.
 * @author Lenno Sousa
 * 
 */
public class Administrador {
    
    private int idAdministrador;
    private String nome;
    private String sobrenome;
    private Usuario usuario;

    public Administrador() {
    }

    public Administrador(int idAdministrador, String nome, String sobrenome, Usuario usuario) {
        this.idAdministrador = idAdministrador;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.usuario = usuario;
    }

    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

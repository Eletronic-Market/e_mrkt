package br.com.mrkt.model;

/**
 * Classe responsável por conter os atributos e métodos de acesso do objeto Usuario.
 * @author Lenno Sousa
 */
public class Usuario {
    
    private int status;
    private int idUsuario;
    private int tipoUsuario;
    private String email;
    private String senha;

    public Usuario() {
    }

    public Usuario(int status, int idUsuario, int tipoUsuario, String email, String senha) {
        this.status = status;
        this.idUsuario = idUsuario;
        this.tipoUsuario = tipoUsuario;
        this.email = email;
        this.senha = senha;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

package br.com.mrkt.model;

import br.com.mrkt.model.notification.NotificacoesPendentes;

/**
 * Classe responsável por conter os atributos e métodos de acesso do objeto Supermercado.
 * @author Lenno Sousa
 */
public class Supermercado {
    
    private int idSupermercado;
    private String razaoSocial;
    private String telefone;
    private DocumentoPJ documentoPJ;
    private Endereco endereco;
    private Usuario usuario;
    private NotificacoesPendentes notificacoesPendentes;

    public Supermercado() {
    }

    public Supermercado(int idSupermercado, String razaoSocial, String telefone, DocumentoPJ documentoPJ, Endereco endereco, Usuario usuario, NotificacoesPendentes notificacoesPendentes) {
        this.idSupermercado = idSupermercado;
        this.razaoSocial = razaoSocial;
        this.telefone = telefone;
        this.documentoPJ = documentoPJ;
        this.endereco = endereco;
        this.usuario = usuario;
        this.notificacoesPendentes = notificacoesPendentes;
    }

    public int getIdSupermercado() {
        return idSupermercado;
    }

    public void setIdSupermercado(int idSupermercado) {
        this.idSupermercado = idSupermercado;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public DocumentoPJ getDocumentoPJ() {
        return documentoPJ;
    }

    public void setDocumentoPJ(DocumentoPJ documentoPJ) {
        this.documentoPJ = documentoPJ;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public NotificacoesPendentes getNotificacoesPendentes() {
        return notificacoesPendentes;
    }

    public void setNotificacoesPendentes(NotificacoesPendentes notificacoesPendentes) {
        this.notificacoesPendentes = notificacoesPendentes;
    }
    
}

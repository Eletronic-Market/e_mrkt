package br.com.mrkt.model;

import br.com.mrkt.model.notification.NotificacoesPendentes;
import java.util.ArrayList;

/**
 * Classe responsável por conter os atributos e métodos de acesso do objeto Consumidor.
 * @author Lenno Sousa
 */
public class Consumidor {
    
    private int idConsumidor;
    private String nome;
    private String sobrenome;
    private Usuario usuario;
    private Endereco endereco;
    private ArrayList enderecos = new ArrayList();
    private DocumentoPF documentoPF;
    private NotificacoesPendentes notificacoesPendentes;
    private PreferenciasConsumidor preferenciasConsumidor;

    public Consumidor() {
    }

    public Consumidor(int idConsumidor, String nome, String sobrenome, Usuario usuario, Endereco endereco, DocumentoPF documentoPF, NotificacoesPendentes notificacoesPendentes, PreferenciasConsumidor preferenciasConsumidor) {
        this.idConsumidor = idConsumidor;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.usuario = usuario;
        this.endereco = endereco;
        this.documentoPF = documentoPF;
        this.notificacoesPendentes = notificacoesPendentes;
        this.preferenciasConsumidor = preferenciasConsumidor;
    }

    public int getIdConsumidor() {
        return idConsumidor;
    }

    public void setIdConsumidor(int idConsumidor) {
        this.idConsumidor = idConsumidor;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public ArrayList getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(ArrayList<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public DocumentoPF getDocumentoPF() {
        return documentoPF;
    }

    public void setDocumentoPF(DocumentoPF documentoPF) {
        this.documentoPF = documentoPF;
    }

    public NotificacoesPendentes getNotificacoesPendentes() {
        return notificacoesPendentes;
    }

    public void setNotificacoesPendentes(NotificacoesPendentes notificacoesPendentes) {
        this.notificacoesPendentes = notificacoesPendentes;
    }

    public PreferenciasConsumidor getPreferenciasConsumidor() {
        return preferenciasConsumidor;
    }

    public void setPreferenciasConsumidor(PreferenciasConsumidor preferenciasConsumidor) {
        this.preferenciasConsumidor = preferenciasConsumidor;
    }
    
}
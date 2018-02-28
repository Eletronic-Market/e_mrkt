package br.com.mrkt.model.notification;

/**
 * Classe responsável por conter os atributos e métodos de acesso do objeto Notificações Pendentes.
 * @author Lenno Sousa
 */
public class NotificacoesPendentes {
    
    private int id_notificacao_pendente;
    private String tipo_notificacao;

    public NotificacoesPendentes() {
    }

    public NotificacoesPendentes(int id_notificacao_pendente, String tipo_notificacao) {
        this.id_notificacao_pendente = id_notificacao_pendente;
        this.tipo_notificacao = tipo_notificacao;
    }

    public int getId_notificacao_pendente() {
        return id_notificacao_pendente;
    }

    public void setId_notificacao_pendente(int id_notificacao_pendente) {
        this.id_notificacao_pendente = id_notificacao_pendente;
    }

    public String getTipo_notificacao() {
        return tipo_notificacao;
    }

    public void setTipo_notificacao(String tipo_notificacao) {
        this.tipo_notificacao = tipo_notificacao;
    }



    //Tipos

    //cadastro_consumidor
    //alteracao_email_consumidor
    
}

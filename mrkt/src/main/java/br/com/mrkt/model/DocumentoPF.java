package br.com.mrkt.model;

/**
 * Classe responsável por conter os atributos e métodos de acesso do objeto Documento Pessoa Física.
 * @author Lenno Sousa
 */
public class DocumentoPF {
    
    public int idDocumentoPF;
    private String cpf;

    public DocumentoPF() {
    }

    public DocumentoPF(int idDocumentoPF, String cpf) {
        this.idDocumentoPF = idDocumentoPF;
        this.cpf = cpf;
    }

    public int getIdDocumentoPF() {
        return idDocumentoPF;
    }

    public void setIdDocumentoPF(int idDocumentoPF) {
        this.idDocumentoPF = idDocumentoPF;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}

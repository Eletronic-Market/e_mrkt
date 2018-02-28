package br.com.mrkt.model;

/**
 * Classe responsável por conter os atributos e métodos de acesso do objeto Documento Pessoa Jurídica.
 * @author Lenno Sousa
 */
public class DocumentoPJ {
    
    private int idDocumentoPJ;
    private String cnpj;

    public DocumentoPJ() {
    }

    public DocumentoPJ(int idDocumentoPJ, String cnpj) {
        this.idDocumentoPJ = idDocumentoPJ;
        this.cnpj = cnpj;
    }

    public int getIdDocumentoPJ() {
        return idDocumentoPJ;
    }

    public void setIdDocumentoPJ(int idDocumentoPJ) {
        this.idDocumentoPJ = idDocumentoPJ;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
}

package br.com.mrkt.model;

/**
 * Classe responsável por conter os atributos e métodos de acesso do objeto Setor Produto.
 * @author Lenno Sousa
 */
public class SetorProduto {
    
    private int idSetor;
    private String nomeSetor;

    public SetorProduto() {
    }

    public SetorProduto(int idSetor, String nomeSetor) {
        this.idSetor = idSetor;
        this.nomeSetor = nomeSetor;
    }

    public int getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(int idSetor) {
        this.idSetor = idSetor;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }
    
}

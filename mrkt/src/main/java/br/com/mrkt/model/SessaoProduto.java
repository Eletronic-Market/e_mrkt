package br.com.mrkt.model;

/**
 * Classe responsável por conter os atributos e métodos de acesso do objeto Sessão do Produto.
 * @author Lenno Sousa
 */
public class SessaoProduto {
    
    private int idSessaoProduto;
    private String sessao;

    public SessaoProduto() {
    }

    public SessaoProduto(int idSessaoProduto, String sessao) {
        this.idSessaoProduto = idSessaoProduto;
        this.sessao = sessao;
    }

    public int getIdSessaoProduto() {
        return idSessaoProduto;
    }

    public void setIdSessaoProduto(int idSessaoProduto) {
        this.idSessaoProduto = idSessaoProduto;
    }

    public String getSessao() {
        return sessao;
    }

    public void setSessao(String sessao) {
        this.sessao = sessao;
    }
    
}

package br.com.mrkt.model;

/**
 * Classe responsável por conter os atributos e métodos de acesso do objeto Produtos do Consumidor.
 * @author Lenno Sousa
 */
public class ProdutosConsumidor {
    
    private int idProdutoConsumidor;
    private String codigoDeBarraProdutoConsumidor;
    private String nome;
    private Double valor;
    private int sessaoProduto;

    public ProdutosConsumidor() {
    }

    public ProdutosConsumidor(int idProdutoConsumidor, String codigoDeBarraProdutoConsumidor, String nome, Double valor, int sessaoProduto) {
        this.idProdutoConsumidor = idProdutoConsumidor;
        this.codigoDeBarraProdutoConsumidor = codigoDeBarraProdutoConsumidor;
        this.nome = nome;
        this.valor = valor;
        this.sessaoProduto = sessaoProduto;
    }

    public int getIdProdutoConsumidor() {
        return idProdutoConsumidor;
    }

    public void setIdProdutoConsumidor(int idProdutoConsumidor) {
        this.idProdutoConsumidor = idProdutoConsumidor;
    }

    public String getCodigoDeBarraProdutoConsumidor() {
        return codigoDeBarraProdutoConsumidor;
    }

    public void setCodigoDeBarraProdutoConsumidor(String codigoDeBarraProdutoConsumidor) {
        this.codigoDeBarraProdutoConsumidor = codigoDeBarraProdutoConsumidor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getSessao() {
        return sessaoProduto;
    }

    public void setSessao(int sessaoProduto) {
        this.sessaoProduto = sessaoProduto;
    }
    
}

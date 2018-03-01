package br.com.mrkt.model;

/**
 * Classe responsável por conter os atributos e métodos de acesso do objeto Produto.
 * @author Lenno Sousa
 */
public class Produto {
    
    private int idProduto;
    private String codigoDeBarra;
    private String nome;
    private Double valor;
    private int sessaoProduto;
    private String tipo_sessao;
    private int familiaProduto;
    private String tipo_familia;
    private int quantidade;
    private Supermercado supermercado;

    public Produto() {
    }

    public Produto(int idProduto, String codigoDeBarra, String nome, Double valor, int sessaoProduto, String tipo_sessao, int familiaProduto, String tipo_familia, int quantidade, Supermercado supermercado) {
        this.idProduto = idProduto;
        this.codigoDeBarra = codigoDeBarra;
        this.nome = nome;
        this.valor = valor;
        this.sessaoProduto = sessaoProduto;
        this.tipo_sessao = tipo_sessao;
        this.familiaProduto = familiaProduto;
        this.tipo_familia = tipo_familia;
        this.quantidade = quantidade;
        this.supermercado = supermercado;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getCodigoDeBarra() {
        return codigoDeBarra;
    }

    public void setCodigoDeBarra(String codigoDeBarra) {
        this.codigoDeBarra = codigoDeBarra;
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

    public int getSessaoProduto() {
        return sessaoProduto;
    }

    public void setSessaoProduto(int sessaoProduto) {
        this.sessaoProduto = sessaoProduto;
    }

    public String getTipo_sessao() {
        return tipo_sessao;
    }

    public void setTipo_sessao(String tipo_sessao) {
        this.tipo_sessao = tipo_sessao;
    }

    public int getFamiliaProduto() {
        return familiaProduto;
    }

    public void setFamiliaProduto(int familiaProduto) {
        this.familiaProduto = familiaProduto;
    }

    public String getTipo_familia() {
        return tipo_familia;
    }

    public void setTipo_familia(String tipo_familia) {
        this.tipo_familia = tipo_familia;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Supermercado getSupermercado() {
        return supermercado;
    }

    public void setSupermercado(Supermercado supermercado) {
        this.supermercado = supermercado;
    }
    
}

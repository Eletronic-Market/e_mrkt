package br.com.mrkt.model;

/**
 * Classe responsável por conter os atributos e métodos de acesso do objeto Listas do Dia.
 * @author Lenno Sousa
 */
public class ListasDoDia {
    
    private String valorLista;
    private Produto produto;
    private Usuario usuario;
    private Consumidor consumidor;
    private ListaConsumidor listaConsumidor;
    private Supermercado supermercado;

    public ListasDoDia() {
    }

    public ListasDoDia(String valorLista, Produto produto, Usuario usuario, Consumidor consumidor, ListaConsumidor listaConsumidor, Supermercado supermercado) {
        this.valorLista = valorLista;
        this.produto = produto;
        this.usuario = usuario;
        this.consumidor = consumidor;
        this.listaConsumidor = listaConsumidor;
        this.supermercado = supermercado;
    }

    public String getValorLista() {
        return valorLista;
    }

    public void setValorLista(String valorLista) {
        this.valorLista = valorLista;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Consumidor getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(Consumidor consumidor) {
        this.consumidor = consumidor;
    }

    public ListaConsumidor getListaConsumidor() {
        return listaConsumidor;
    }

    public void setListaConsumidor(ListaConsumidor listaConsumidor) {
        this.listaConsumidor = listaConsumidor;
    }

    public Supermercado getSupermercado() {
        return supermercado;
    }

    public void setSupermercado(Supermercado supermercado) {
        this.supermercado = supermercado;
    }
    
}

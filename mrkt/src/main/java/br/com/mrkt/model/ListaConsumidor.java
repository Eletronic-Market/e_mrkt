package br.com.mrkt.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe responsável por conter os atributos e métodos de acesso do objeto Lista do Consumidor.
 * @author Lenno Sousa
 */
public class ListaConsumidor {
    
    private int idLista;
    private String nomeLista;
    private int quantidade;
    private Date dataCadastroLista;
    private Date dataCompraLista;
    private Consumidor consumidor;
    private Supermercado supermercado;
    private List<ItemLista> itens = new ArrayList<>();

    public ListaConsumidor() {
    }

    public ListaConsumidor(int idLista, String nomeLista, int quantidade, Date dataCadastroLista, Date dataCompraLista, Consumidor consumidor, Supermercado supermercado) {
        this.idLista = idLista;
        this.nomeLista = nomeLista;
        this.quantidade = quantidade;
        this.dataCadastroLista = dataCadastroLista;
        this.dataCompraLista = dataCompraLista;
        this.consumidor = consumidor;
        this.supermercado = supermercado;
    }

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public String getNomeLista() {
        return nomeLista;
    }

    public void setNomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDataCadastroLista() {
        return dataCadastroLista;
    }

    public void setDataCadastroLista(Date dataCadastroLista) {
        this.dataCadastroLista = dataCadastroLista;
    }

    public Date getDataCompraLista() {
        return dataCompraLista;
    }

    public void setDataCompraLista(Date dataCompraLista) {
        this.dataCompraLista = dataCompraLista;
    }

    public Consumidor getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(Consumidor consumidor) {
        this.consumidor = consumidor;
    }

    public Supermercado getSupermercado() {
        return supermercado;
    }

    public void setSupermercado(Supermercado supermercado) {
        this.supermercado = supermercado;
    }

    public List<ItemLista> getItens() {
        return itens;
    }

    public void setItens(List<ItemLista> itens) {
        this.itens = itens;
    }
    
}

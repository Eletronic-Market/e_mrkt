package br.com.mrkt.model;

import java.util.Objects;

/**
 * Classe responsável por conter os atributos e métodos de acesso do objeto Item Lista.
 * @author Lenno Sousa
 */
public class ItemLista {
    
    private int idItemLista;
    private int quantidade;
    private int contador;
    private ProdutosConsumidor produtosCliente;

    public ItemLista() {
    }

    public ItemLista(int idItemLista, int quantidade, int contador, ProdutosConsumidor produtosCliente) {
        this.idItemLista = idItemLista;
        this.quantidade = quantidade;
        this.contador = contador;
        this.produtosCliente = produtosCliente;
    }

    public int getIdItemLista() {
        return idItemLista;
    }

    public void setIdItemLista(int idItemLista) {
        this.idItemLista = idItemLista;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public ProdutosConsumidor getProdutosCliente() {
        return produtosCliente;
    }

    public void setProdutosCliente(ProdutosConsumidor produtosCliente) {
        this.produtosCliente = produtosCliente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.produtosCliente);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemLista other = (ItemLista) obj;
        if (this.idItemLista != other.idItemLista) {
            return false;
        }
        if (this.quantidade != other.quantidade) {
            return false;
        }
        if (this.contador != other.contador) {
            return false;
        }
        if (!Objects.equals(this.produtosCliente, other.produtosCliente)) {
            return false;
        }
        return true;
    }
    
}

package br.com.mrkt.model;

/**
 * Classe responsável por conter os atributos e métodos de acesso do objeto Informações.
 * @author Lenno Sousa
 */
public class Informacoes {
    
    private int quantidadeUsuariosConsumidores;
    private int quantidadeUsuariosSupermercado;
    private int quantidadeUsuariosTransportadora;

    public Informacoes() {
    }

    public Informacoes(int quantidadeUsuariosConsumidores, int quantidadeUsuariosSupermercado, int quantidadeUsuariosTransportadora) {
        this.quantidadeUsuariosConsumidores = quantidadeUsuariosConsumidores;
        this.quantidadeUsuariosSupermercado = quantidadeUsuariosSupermercado;
        this.quantidadeUsuariosTransportadora = quantidadeUsuariosTransportadora;
    }

    public int getQuantidadeUsuariosConsumidores() {
        return quantidadeUsuariosConsumidores;
    }

    public void setQuantidadeUsuariosConsumidores(int quantidadeUsuariosConsumidores) {
        this.quantidadeUsuariosConsumidores = quantidadeUsuariosConsumidores;
    }

    public int getQuantidadeUsuariosSupermercado() {
        return quantidadeUsuariosSupermercado;
    }

    public void setQuantidadeUsuariosSupermercado(int quantidadeUsuariosSupermercado) {
        this.quantidadeUsuariosSupermercado = quantidadeUsuariosSupermercado;
    }

    public int getQuantidadeUsuariosTransportadora() {
        return quantidadeUsuariosTransportadora;
    }

    public void setQuantidadeUsuariosTransportadora(int quantidadeUsuariosTransportadora) {
        this.quantidadeUsuariosTransportadora = quantidadeUsuariosTransportadora;
    }
    
}

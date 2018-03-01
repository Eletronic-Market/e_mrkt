package br.com.mrkt.model;

import java.util.Date;

/**
 * Classe responsável por conter os atributos e métodos de acesso do objeto Anuncio.
 * @author Lenno Sousa
 */
public class Anuncio {
    
    private int idAnuncio;
    private String anuncio;
    private Date dataInicio;
    private Date dataFinal;
    private int situacao;
    private int contador;
    private int quantidade;
    private Supermercado supermercado;
    private FamiliaProduto familiaProduto;

    public Anuncio() {
    }

    public Anuncio(int idAnuncio, String anuncio, Date dataInicio, Date dataFinal, int situacao, int contador, int quantidade, Supermercado supermercado, FamiliaProduto familiaProduto) {
        this.idAnuncio = idAnuncio;
        this.anuncio = anuncio;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.situacao = situacao;
        this.contador = contador;
        this.quantidade = quantidade;
        this.supermercado = supermercado;
        this.familiaProduto = familiaProduto;
    }

    public int getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(int idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public String getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(String anuncio) {
        this.anuncio = anuncio;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
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

    public FamiliaProduto getFamiliaProduto() {
        return familiaProduto;
    }

    public void setFamiliaProduto(FamiliaProduto familiaProduto) {
        this.familiaProduto = familiaProduto;
    }
    
}

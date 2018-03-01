package br.com.mrkt.model;

/**
 * Classe responsável por conter os atributos e métodos de acesso do objeto Familia Produto.
 * @author Lenno Sousa
 */
public class FamiliaProduto {
    
    private int idFamiliaProduto;
    private String tipoFamilia;

    public FamiliaProduto() {
    }

    public FamiliaProduto(int idFamiliaProduto, String tipoFamilia) {
        this.idFamiliaProduto = idFamiliaProduto;
        this.tipoFamilia = tipoFamilia;
    }

    public int getIdFamiliaProduto() {
        return idFamiliaProduto;
    }

    public void setIdFamiliaProduto(int idFamiliaProduto) {
        this.idFamiliaProduto = idFamiliaProduto;
    }

    public String getTipoFamilia() {
        return tipoFamilia;
    }

    public void setTipoFamilia(String tipoFamilia) {
        this.tipoFamilia = tipoFamilia;
    }
    
}

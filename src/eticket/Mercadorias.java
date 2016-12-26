/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eticket;

/**
 *
 * @author Jonis
 */
public class Mercadorias {
    
    private String codBarras, descricao;
    private double precoCusto, lucro, precoVenda;

    public Mercadorias(String descricao, String codBarras, double precoCusto, double lucro, double precoVenda) {
        this.descricao = descricao;
        this.lucro = lucro;
        this.codBarras = codBarras;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
    }

    public Mercadorias() {
    }

  public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getLucro(){
        return lucro;
    }
    
    public void setLucro(double lucro){
        this.lucro = lucro;        
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

}

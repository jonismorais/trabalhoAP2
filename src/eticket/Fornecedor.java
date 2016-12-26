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
public class Fornecedor extends Pessoa {
    
    private String cnpj, ie;

    private Fornecedor(String cnpj, String ie, String nome, String endereco, String telefone, 
                        String email, String bairro, String cidade, String uf, String cep) {
        super(nome, endereco, telefone, email, bairro, cidade, uf, cep);
        this.cnpj = cnpj;
        this.ie = ie;
    }

    public Fornecedor() {
       
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

     
    
}

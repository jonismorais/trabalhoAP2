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
public class Cliente extends Pessoa {
    
    private String cpf, rg;
   
    private Cliente(String cpf, String rg, String nome, String endereco, String telefone, String email, 
                                               String bairro, String cidade, String uf, String cep) {
        super(nome, endereco, telefone, email, bairro, cidade, uf, cep);
        this.cpf = cpf;
        this.rg = rg;
      
    }

    public Cliente(){
        
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    
}

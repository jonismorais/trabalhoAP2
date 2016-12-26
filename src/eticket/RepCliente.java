 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eticket;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Jonis
 */
public class RepCliente extends Conexao{
    
    private static RepCliente instanciaRep;

    private RepCliente(){

    }
    
    public static RepCliente obterInstancia(){
        if (instanciaRep == null){
            instanciaRep = new RepCliente();
        }
        return instanciaRep;
    }
    
    public void atualizar(Cliente clientes) throws Exception{
            
    Statement connect = conectar();
        String nome = "UPDATE CLIENTES SET NOME = '"+clientes.getNome()+"' WHERE CLIENTES.CPF = '"+clientes.getCpf()+"';";
        String rg = "UPDATE CLIENTES SET RG = '"+clientes.getRg()+"' WHERE CLIENTES.CPF = '"+clientes.getCpf()+"';";
        String endereco = "UPDATE CLIENTES SET ENDERECO = '"+clientes.getEndereco()+"' WHERE CLIENTES.CPF = '"+clientes.getCpf()+"';";
        String bairro = "UPDATE CLIENTES SET BAIRRO = '"+clientes.getBairro()+"' WHERE CLIENTES.CPF = '"+clientes.getCpf()+"';";
        String cidade = "UPDATE CLIENTES SET CIDADE = '"+clientes.getCidade()+"' WHERE CLIENTES.CPF = '"+clientes.getCpf()+"';";
        String uf = "UPDATE CLIENTES SET UF = '"+clientes.getUf()+"' WHERE CLIENTES.CPF = '"+clientes.getCpf()+"';";
        String cep = "UPDATE CLIENTES SET CEP = '"+clientes.getCep()+"' WHERE CLIENTES.CPF = '"+clientes.getCpf()+"';";
        String telefone = "UPDATE CLIENTES SET TELEFONE = '"+clientes.getTelefone()+"' WHERE CLIENTES.CPF = '"+clientes.getCpf()+"';";
        String email = "UPDATE CLIENTES SET EMAIL = '"+clientes.getEmail()+"' WHERE CLIENTES.CPF = '"+clientes.getCpf()+"';";


                
                //String sql = "UPDATE Clientes SETE Nome = '"+clientes.getNome()+"', SET RG =, Rg, Endereco, Bairro, Cidade, Uf, Cep, Telefone, Email) WHERE Clientes.CPF = '"+clientes.getCpf()+"' VALUES ('"+clientes.getNome()+"','"+clientes.getRg()+"','"+clientes.getEndereco()+"','"+clientes.getBairro()+"','"+clientes.getCidade()+"','"+clientes.getUf()+"','"+clientes.getCep()+"','"+clientes.getTelefone()+"','"+ clientes.getEmail()+"')";
            //System.out.println(sql);
               
        try{
            connect.execute(nome);
            connect.execute(rg);
            connect.execute(endereco);
            connect.execute(bairro);
            connect.execute(cidade);
            connect.execute(uf);
            connect.execute(cep);
            connect.execute(telefone);
            connect.execute(email);
            
     }
        catch (SQLException e) {
           throw new Exception ("Erro ao atualizar os dados. Tente novamente!"+e);
        }
    }
    public void inserir (Cliente clientes) throws Exception {
        if(clientes == null){
            throw new Exception("O cliente não foi instanciado");
        }
        if(clientes.getNome() == null){
            throw new Exception("Digite o Nome do cliente");
        }
        if(clientes.getCpf() == null){
            throw new Exception("Digite o CPF do cliente");
        }
        if(clientes.getNome().trim().equals("")){
            throw new Exception("Digite o Nome do cliente");
        }
     //   if(this.consulta(clientes) >= 0){
       //     throw new Exception("Esse cliente já está cadastrado");
       // }
        
        Statement connect = conectar();
        String sql = "INSERT INTO Clientes (Nome, Cpf, Rg, Endereco, Bairro, Cidade, Uf, Cep, Telefone, Email)";
        sql += "VALUES ('"+clientes.getNome()+"','"+clientes.getCpf()+"','"+clientes.getRg()+"','"+clientes.getEndereco()+"','"+clientes.getBairro()+"','"+clientes.getCidade()+"','"+clientes.getUf()+"','"+clientes.getCep()+"','"+clientes.getTelefone()+"','"+ clientes.getEmail()+"')";    
               
        try{
            connect.execute(sql);
     }
        catch (SQLException e) {
           throw new Exception ("Erro ao inserir os dados: CPF já está cadastrado");
        }
    }

    public void remover (String cpf) throws Exception {
        
        Statement connect = conectar();
        
        String prov = cpf;
        boolean teste;
        
        ResultSet r = connect.executeQuery("SELECT * FROM Clientes WHERE Clientes.Cpf = '"+prov+"';");
        
        teste = r.next();
        
        if(!teste){
            JOptionPane.showMessageDialog(null, "Cliente não encontrado");
        }
        else{
            String sql = "DELETE FROM Clientes WHERE Clientes.Cpf = '" +cpf+"';";
            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso");

            try{
                connect.execute(sql);
                
            }catch (SQLException e){
                throw new Exception ("Erro ao excluir os dados"+e.getMessage());
            }
         }
}

/*
    public void atualizar (Cliente clientes) throws Exception {
        if(clientes == null){
            throw new Exception("O cliente não foi instanciado");
        }
        if(clientes.getNome() == null){
            throw new Exception("Digite o Nome do cliente");
        }
        if(clientes.getCpf() == null){
            throw new Exception("Digite o CPF do cliente");
        }
        if(clientes.getCpf().trim().equals("")){
            throw new Exception("Digite o CPF do cliente");
        }
        if(clientes.getNome().trim().equals("")){
            throw new Exception("Digite o Nome do cliente");
        }
        if(this.consulta(clientes) == -1){
            throw new Exception("Esse cliente não está cadastrado");
        }
        //this.lista.set(this.consulta(clientes), clientes);
        }
  */

        
    
}
    


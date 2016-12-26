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
public class RepVendedor extends Conexao{
    
    private static RepVendedor instanciaRep;

    private RepVendedor(){

    }
            
    public static RepVendedor obterInstancia(){
        if (instanciaRep == null){
            instanciaRep = new RepVendedor();
        }
        return instanciaRep;
    }
   
    public void inserir (Vendedor vendedor) throws Exception {
        if(vendedor == null){
            throw new Exception("O vendedor não foi instanciado");
        }
        if(vendedor.getNome() == null){
            throw new Exception("Digite o Nome do cliente");
        }
        if(vendedor.getCpf() == null){
            throw new Exception("Digite o CPF do cliente");
        }
        if(vendedor.getNome().trim().equals("")){
            throw new Exception("Digite o Nome do cliente");
        }
     
        
        Statement connect = conectar();
        String sql = "INSERT INTO Funcionarios (Cpf, Nome)";
        sql += "VALUES ('"+vendedor.getCpf()+"','"+vendedor.getNome()+"')";    
               
        try{
            connect.execute(sql);
     }
        catch (SQLException e) {
           throw new Exception ("Erro ao inserir os dados: " + e.getMessage());
        }
    }

    public void remover (String cpf) throws Exception {
        
        Statement connect = conectar();
        
        String prov = cpf;
        boolean teste;
        
        ResultSet r = connect.executeQuery("SELECT * FROM Funcionarios WHERE Funcionarios.Cpf = '"+prov+"';");
        
        teste = r.next();
        
        if(!teste){
            JOptionPane.showMessageDialog(null, "Funcionário não encontrado");
        }
        else{
            String sql = "DELETE FROM Funcionarios WHERE Funcionarios.Cpf = '" +cpf+"';";
            JOptionPane.showMessageDialog(null, "Funcionário excluído com sucesso");

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
    


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
public class RepFornecedor extends Conexao{
    
    private static RepFornecedor instanciaRep;

    private RepFornecedor(){

    }
            
    public static RepFornecedor obterInstancia(){
        if (instanciaRep == null){
            instanciaRep = new RepFornecedor();
        }
        return instanciaRep;
    }
    
    public void atualizar(Fornecedor fornecedor) throws Exception{
            
    Statement connect = conectar();
        String nome = "UPDATE FORNECEDORES SET RAZAOSOCIAL = '"+fornecedor.getNome()+"' WHERE FORNECEDORES.CNPJ = '"+fornecedor.getCnpj()+"';";
        String rg = "UPDATE FORNECEDORES SET INSCEST = '"+fornecedor.getIe()+"' WHERE FORNECEDORES.CNPJ = '"+fornecedor.getCnpj()+"';";
        String endereco = "UPDATE FORNECEDORES SET ENDERECO = '"+fornecedor.getEndereco()+"' WHERE FORNECEDORES.CNPJ = '"+fornecedor.getCnpj()+"';";
        String bairro = "UPDATE FORNECEDORES SET BAIRRO = '"+fornecedor.getBairro()+"' WHERE FORNECEDORES.CNPJ = '"+fornecedor.getCnpj()+"';";
        String cidade = "UPDATE FORNECEDORES SET CIDADE = '"+fornecedor.getCidade()+"' WHERE FORNECEDORES.CNPJ = '"+fornecedor.getCnpj()+"';";
        String uf = "UPDATE FORNECEDORES SET UF = '"+fornecedor.getUf()+"' WHERE FORNECEDORES.CNPJ = '"+fornecedor.getCnpj()+"';";
        String cep = "UPDATE FORNECEDORES SET CEP = '"+fornecedor.getCep()+"' WHERE FORNECEDORES.CNPJ = '"+fornecedor.getCnpj()+"';";
        String telefone = "UPDATE FORNECEDORES SET TELEFONE = '"+fornecedor.getTelefone()+"' WHERE FORNECEDORES.CNPJ = '"+fornecedor.getCnpj()+"';";
        String email = "UPDATE FORNECEDORES SET EMAIL = '"+fornecedor.getEmail()+"' WHERE FORNECEDORES.CNPJ = '"+fornecedor.getCnpj()+"';";


                
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
    
    
    public void inserir (Fornecedor fornecedor) throws Exception {
        if(fornecedor == null){
            throw new Exception("O fornecedor não foi instanciado");
        }
        if(fornecedor.getNome() == null){
            throw new Exception("Digite a Razão Social do fornecedor");
        }
        if(fornecedor.getCnpj() == null){
            throw new Exception("Digite o CNPJ do fornecedor");
        }
        if(fornecedor.getNome().trim().equals("")){
            throw new Exception("Digite a Razão Social do fornecedor");
        }
     //   if(this.consulta(clientes) >= 0){
       //     throw new Exception("Esse cliente já está cadastrado");
       // }
        Statement connect = conectar();
        String sql = "INSERT INTO Fornecedores (RazaoSocial, Cnpj, InscEst, Endereco, Bairro, Cidade, Uf, Cep, Telefone, Email)";
        sql += "VALUES ('"+fornecedor.getNome()+"','"+ fornecedor.getCnpj()+"','"+ fornecedor.getIe()+"','"+ fornecedor.getEndereco()+"','"+fornecedor.getBairro()+"','"+fornecedor.getCidade()+"','"+fornecedor.getUf()+"','"+ fornecedor.getCep()+"','"+fornecedor.getTelefone()+"','"+ fornecedor.getEmail()+"')";    
        
        try{
            connect.execute(sql);
        }
        catch (SQLException e) {
            throw new Exception ("Erro ao inserir os dados: CNPJ já está cadastrado");
        }
    }
    
    public void remover (String cnpj) throws Exception {
        
        Statement connect = conectar();
        
        String prov = cnpj;
        boolean teste;
        
        ResultSet r = connect.executeQuery("SELECT * FROM Fornecedores WHERE Fornecedores.Cnpj = '"+prov+"';");
        
        teste = r.next();
        
        if(!teste){
            JOptionPane.showMessageDialog(null, "Fornecedor não encontrado");
        }
        else{
            String sql = "DELETE FROM Fornecedores WHERE Fornecedores.Cnpj = '" +cnpj+"';";
            JOptionPane.showMessageDialog(null, "Fornecedor excluído com sucesso");

            try{
                connect.execute(sql);
            }catch (SQLException e){
                throw new Exception ("Erro ao excluir os dados"+e.getMessage());
            }
         }
    }
}
        //this.lista.add(clientes);
  //  }
    
   /* public void remover (Cliente clientes) throws Exception {
        if(clientes == null){
            throw new Exception("O cliente não foi instanciado");
        }
        if(clientes.getCpf() == null){
            throw new Exception("Digite o CPF do cliente");
        }
        if(clientes.getCpf().trim().equals("")){
            throw new Exception("Digite o CPF do cliente");
        }
   /*     if(this.consulta(clientes) == -1){
            throw new Exception("Esse cliente não está cadastrado");
        }
        //this.lista.remove(this.consulta(clientes));
    }
    
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
    
/*    public int consulta (Cliente clientes) throws Exception {
        int retorno = -1;
  //      for (int i=0; i<this.lista.size(); i++){
    //        if(clientes.getCpf().trim().equals(this.lista.get(i).getCpf().trim())){
                retorno = i;
                break;
            }
        }
        return retorno;    
    }
    
}*/
    


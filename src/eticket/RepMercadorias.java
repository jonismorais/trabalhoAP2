/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eticket;

import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jonis
 */
public class RepMercadorias extends Conexao {
    
    private static RepMercadorias instanciaRep;
    
    
    public RepMercadorias(){
        
    }
    
    public static RepMercadorias obterInstancia(){
        if(instanciaRep == null){
            instanciaRep = new RepMercadorias();
        }
        return instanciaRep;
    }
    
    public void inserir(Mercadorias mercadorias) throws Exception {
        if(mercadorias == null){
            throw new Exception("A mercadoria não foi instanciada");
        }
        
        if(mercadorias.getCodBarras() == null){
            throw new Exception("Informe o Código de Barras");       
        }
        if(mercadorias.getDescricao() == null){
            throw new Exception("Informe a Descrição");
        }
        
        Statement connect = conectar();
        String sql = "INSERT INTO Mercadorias (CodBarras, Descricao, PrecoCusto, Lucro, PrecoVenda)";
        sql += "VALUES ('"+mercadorias.getCodBarras()+"','"+mercadorias.getDescricao()+"','"+mercadorias.getPrecoCusto()+"','"+mercadorias.getLucro()+"','"+ mercadorias.getPrecoVenda()+"')";    
        
        try{
            connect.execute(sql);
        }
        catch (SQLException e) {
            throw new Exception ("Erro ao inserir os dados: Código de Barras já está cadastrado");
        }
    
}
    
}

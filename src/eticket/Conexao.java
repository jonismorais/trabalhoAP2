/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eticket;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
/**
 *
 * @author Jonis
 */
public class Conexao {
   static java.sql.Connection con;
   static java.sql.Statement stm;   
 
   
    public static Statement conectar() throws Exception{
        try{
            Class.forName("org.firebirdsql.jdbc.FBDriver");
        }catch(java.lang.ClassNotFoundException e) {
                    System.out.println("Firebird JCA-JDBC driver not found in class path");
                    System.out.println(e.getMessage());
                 
        }
         try{
            InputStream is = new FileInputStream("config.txt");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
             
             String caminho = br.readLine();
             String[] temp = new String[3];
                temp = caminho.split(";");
                caminho = br.readLine();
             
                
             
            con = DriverManager.getConnection(temp[0],temp[1],temp[2]);
            br.close();
            stm = con.createStatement();
         }catch (SQLException sqle){
          throw new Exception("Erro 2: ao conectar ao Banco de Dados: " + sqle.getMessage());
        }
        return stm;
    }
 
    public void desconectar(){
        try{
            con.close();
        }catch (SQLException sqle){
        }
    
    }
}

 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eticket;

import com.itextpdf.text.BaseColor;
import java.io.FileOutputStream;
import java.io.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReciboVenda {
    
    public ReciboVenda(){
    
    }
    
    public void pegarCodigo(){
        
    }
    
    
    String dataVenda, cpfVendedor, nomeVendedor, cpfCliente, nomeCliente = null, subtotal, desconto, valorTotal;
     int codigo = 0;
     String codBarras, descricao, quantidade, vlrUnit, vlrTotal;    
    public void show() throws IOException {
    
    try{
            Statement stm = Conexao.conectar();
                ResultSet rs = stm.executeQuery("SELECT MAX(CODIGO) AS CODIGO FROM DADOS_VENDAS");
                while(rs.next()){
                int cod = rs.getInt("CODIGO");
            stm.close();
    try{
            Statement st = Conexao.conectar();
                ResultSet rscod = st.executeQuery("SELECT * FROM DADOS_VENDAS WHERE DADOS_VENDAS.CODIGO = '"+cod+"';");
                
                while(rscod.next()){
                        codigo = rscod.getInt("CODIGO");
                        dataVenda = rscod.getString("DATAVENDA");
                        cpfVendedor = rscod.getString("CPFVENDEDOR");
                        nomeVendedor = rscod.getString("NOMEVENDEDOR");
                        cpfCliente = rscod.getString("CPFCLIENTE");
                        nomeCliente = rscod.getString("NOMECLIENTE");
                        subtotal = rscod.getString("SUBTOTAL");
                        desconto = rscod.getString("DESCONTO");
                        valorTotal = rscod.getString("VALOR_VENDA");
        Document doc = null;
        OutputStream os = null;
        try {
        doc = new Document(PageSize.A4, 56, 56, 56, 56);
        os = new FileOutputStream("recibo/RECIBO_"+nomeCliente+"_"+codigo+".pdf");
            //associa a stream de saída ao 
        PdfWriter.getInstance(doc, os);
            //abre o documento  
        doc.open();
            //adiciona o texto ao PDF
        Paragraph p1 = new Paragraph("##### RECIBO DE VENDA DE MERCADORIA Nº: "+codigo+" #####");
        p1.setAlignment(Element.ALIGN_CENTER);
        p1.setSpacingAfter(20);
        doc.add(p1);
        
        PdfPTable table = new PdfPTable(new float[] {0.2f, 0.5f, 0.3f});
        PdfPCell header = new PdfPCell(new Paragraph("DADOS DO CLIENTE"));
        PdfPCell cel1 = new PdfPCell(new Paragraph ("Data Venda"));
        PdfPCell cel2 = new PdfPCell(new Paragraph ("Nome Cliente"));
        PdfPCell cel3 = new PdfPCell(new Paragraph ("CPF"));
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cel1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel3.setHorizontalAlignment(Element.ALIGN_CENTER);
        header.setColspan(3);
        table.addCell(header);
        table.addCell(cel1);
        table.addCell(cel2);
        table.addCell(cel3);
        doc.add(table);
        
        PdfPTable table1 = new PdfPTable(new float[] {0.2f, 0.5f, 0.3f});
        table1.addCell(dataVenda);
        table1.addCell(nomeCliente);
        table1.addCell(cpfCliente);
        doc.add(table1);
        Paragraph p2 = new Paragraph("\n\n");
        doc.add(p2);
        
        
        try{
            Statement stitens = Conexao.conectar();
            ResultSet rsitens = stitens.executeQuery("SELECT COUNT(ID_VENDA) FROM ITENS_VENDA WHERE ID_VENDA = '"+cod+"';");
            while(rsitens.next()){
                
                 try{
            Statement st2 = Conexao.conectar();
                ResultSet rs2 = st2.executeQuery("SELECT * FROM ITENS_VENDA WHERE ITENS_VENDA.ID_VENDA = '"+cod+"';");
        PdfPTable table2 = new PdfPTable(new float[] {0.4f, 0.1f, 0.25f, 0.25f});
        PdfPCell header2 = new PdfPCell(new Paragraph("INFORMAÇÕES SOBRE OS ITENS COMPRADOS"));
        PdfPCell cel4 = new PdfPCell(new Paragraph ("Descrição"));
        PdfPCell cel5 = new PdfPCell(new Paragraph ("Qtd"));
        PdfPCell cel6 = new PdfPCell(new Paragraph ("R$ Unid"));
        PdfPCell cel7 = new PdfPCell(new Paragraph ("R$ Total"));
        header2.setHorizontalAlignment(Element.ALIGN_CENTER);
        header2.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cel4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel5.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel6.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel7.setHorizontalAlignment(Element.ALIGN_CENTER);
        header2.setColspan(4);
        header2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(header2);
        table2.addCell(cel4);
        table2.addCell(cel5);
        table2.addCell(cel6);
        table2.addCell(cel7);
        doc.add(table2);
                while(rs2.next()){
                        descricao = rs2.getString("DESCRICAO");
                        quantidade = rs2.getString("QUANTIDADE");
                        vlrUnit = rs2.getString("VALOR_UNIT");
                        vlrTotal = rs2.getString("VALOR_TOTAL");
                
        PdfPTable table3 = new PdfPTable(new float[] {0.4f, 0.1f, 0.25f, 0.25f});
        table3.addCell(descricao);
        table3.addCell(quantidade);
        table3.addCell(vlrUnit);
        table3.addCell(vlrTotal);
        doc.add(table3);
        }
        
        Paragraph p3 = new Paragraph("\n\n");
        doc.add(p3);
        
        PdfPTable table4 = new PdfPTable(new float[] {0.35f, 0.3f, 0.35f});
        PdfPCell header4 = new PdfPCell(new Paragraph("VALORES DA COMPRA"));
        PdfPCell cel8 = new PdfPCell(new Paragraph ("Subtotal - R$"));
        PdfPCell cel9 = new PdfPCell(new Paragraph ("% Desconto"));
        PdfPCell cel10 = new PdfPCell(new Paragraph ("Valor Total - R$"));
        header4.setHorizontalAlignment(Element.ALIGN_CENTER);
        header4.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cel8.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel9.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel10.setHorizontalAlignment(Element.ALIGN_CENTER);
        header4.setColspan(3);
        table4.addCell(header4);
        table4.addCell(cel8);
        table4.addCell(cel9);
        table4.addCell(cel10);
        doc.add(table4);
        
        PdfPTable table5 = new PdfPTable(new float[] {0.35f, 0.3f, 0.35f});
        table5.addCell(subtotal);
        table5.addCell(desconto);
        table5.addCell(valorTotal);
        doc.add(table5);
        
            
        }finally {
        if (doc != null) {
        //fechamento do documento
        doc.close();
        if (os != null) {
        //fechamento da stream de saída
        os.close();

        
        
        }
        }
        }
        }
        

        }catch(Exception e){}
                }            

       catch(Exception e){}
                
        } 
        
     }catch(Exception e){}
                }
     }catch(Exception e){}

}
public void abreRecibo() throws IOException{
  try{
            Statement stm = Conexao.conectar();
                ResultSet rs = stm.executeQuery("SELECT MAX(CODIGO) AS CODIGO FROM DADOS_VENDAS");
                while(rs.next()){
                int cod = rs.getInt("CODIGO");
            stm.close();
    try{
            Statement st = Conexao.conectar();
                ResultSet rscod = st.executeQuery("SELECT * FROM DADOS_VENDAS WHERE DADOS_VENDAS.CODIGO = '"+cod+"';");
                
                while(rscod.next()){
                        codigo = rscod.getInt("CODIGO");
                        nomeCliente = rscod.getString("NOMECLIENTE");
                        
    } 
}catch(Exception e){
    
}
                }
  }catch(Exception e){
      
  }
     java.awt.Desktop.getDesktop().open( new File( "recibo/RECIBO_"+nomeCliente+"_"+codigo+".pdf" ) );

}
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eticket;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jonismar, Felipe, Arthur
 */
public class cadVendas extends javax.swing.JFrame {
    private int itensAdd = 0 ;
    private formMain telaPrincipal;
    ReciboVenda recibo = new ReciboVenda();
    
    private void atualizaQuantidade() throws Exception{
        try{
          String sqlqtd, codBarras;
            int quantidade;

         for(int i=0; i<jTableItensVenda.getRowCount(); i++){   
            Statement st = Conexao.conectar();
        
                    codBarras = (String.valueOf(jTableItensVenda.getValueAt(i, 0)));
                    ResultSet rs = st.executeQuery("SELECT QTD FROM MERCADORIAS WHERE MERCADORIAS.CODBARRAS = '"+codBarras+"'");
                    while (rs.next())
                        {
                    int qtd = rs.getInt("QTD");
                    quantidade = qtd - Integer.parseInt((String)jTableItensVenda.getValueAt(i, 2));          
                    sqlqtd = ("UPDATE MERCADORIAS SET QTD = '"+quantidade+"' WHERE MERCADORIAS.CODBARRAS = '"+codBarras+"';");
                    st.executeUpdate(sqlqtd);
                    }
            }
        }catch (SQLException ex) {
            
        }
    }
    
    private void gravaItens(){
        
        try  {
            Statement st = Conexao.conectar();
            
            ResultSet rs = st.executeQuery("SELECT MAX(CODIGO)AS CODIGO FROM DADOS_VENDAS");
                int codigo;
                while (rs.next()){
                    codigo = rs.getInt("CODIGO");
                        for(int i=0; i<jTableItensVenda.getRowCount(); i++){  
                            String codBarras, descricao, quantidade, valorUnidade, valorTotal, sql;
                            codBarras = (String.valueOf(jTableItensVenda.getValueAt(i, 0)));    
                            descricao = (String.valueOf(jTableItensVenda.getValueAt(i, 1)));        
                            quantidade = (String.valueOf(jTableItensVenda.getValueAt(i, 2)));          
                            valorUnidade = (String.valueOf(jTableItensVenda.getValueAt(i, 3)));
                            valorTotal = (String.valueOf(jTableItensVenda.getValueAt(i, 4)));
                            sql = ("INSERT INTO ITENS_VENDA (ID_VENDA,CODBARRAS,DESCRICAO,QUANTIDADE,VALOR_UNIT,VALOR_TOTAL) VALUES ('"+codigo+"','"+codBarras+"','"+descricao+"','"+quantidade+"','"+valorUnidade+"','"+valorTotal+"');");
                            st.execute(sql);
                            recibo.show();
                        }
                       int i = JOptionPane.showConfirmDialog(null, "Venda Finalizada. Deseja Imprimir?");
                       if(i == JOptionPane.YES_OPTION) {
                       jButtonSair.doClick();
                      BarraProgresso barra = new BarraProgresso();
                      barra.show();
                       
                       }        
                }
                st.close();
            }catch(Exception e){
        
            }
            
        }
    private void calculaTotalProdutos() {
        Double soma = 0.0;
            try{ 
                for (int i = 0 ; i < jTableItensVenda.getRowCount(); i++) {
                    Double valorAux = Double.parseDouble((String) jTableItensVenda.getValueAt(i,4));
                    soma+= valorAux; 
                }     
                jTextFieldSubTotal.setText(String.valueOf(soma));
                }
            catch(Exception e){ JOptionPane.showMessageDialog(null,"Erro ao calcular Total Produtos: "+e.getMessage()); 
            } 
        }
    
    private void carregaVendedor() {
     
        try  {
            Statement st = Conexao.conectar();
                ResultSet rs = st.executeQuery("SELECT Funcionarios.Nome FROM Funcionarios WHERE Funcionarios.Cpf = '"+jTextFieldCpfVendedor.getText()+"';");
                    while(rs.next()) {
                        jTextFieldNomeVendedor.setText(rs.getString("Nome"));
                        jTextFieldNomeVendedor.setOpaque(true);
                        jTextFieldNomeVendedor.enable(false);
                        jTextFieldCpfCliente.requestFocus();
                    }
                st.close();
         }catch(Exception e){
              JOptionPane.showMessageDialog(null,"Erro ao carregar dados do vendedor: "+e.getMessage());
         }
    }
    
    private void geraData(){
        jTextFieldDataVenda.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));

    }
    
    private void carregaClientes(){
                try  {
                Statement st = Conexao.conectar();
                   ResultSet rs = st.executeQuery("SELECT Clientes.Nome FROM Clientes WHERE Clientes.Cpf = '"+jTextFieldCpfCliente.getText()+"';");
                        while(rs.next()) {
                            jTextFieldNomeCliente.setText(rs.getString("Nome"));
                            jTextFieldNomeCliente.enable(false);
                            jTextFieldCodBarras.requestFocus();
                        }
                    st.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Erro ao carregar dados do cliente: "+e.getMessage());
            }
    }
    
    private boolean verificaExistencia(String codBarras) throws Exception{
        
        String codB = null;
            Statement st = Conexao.conectar();
                ResultSet rs = st.executeQuery("SELECT CODBARRAS FROM MERCADORIAS WHERE CODBARRAS = '"+codBarras+"';");
                    while(rs.next()){
                        codB = rs.getString("CODBARRAS");
                    }
             if (!jTextFieldCodBarras.getText().equals(codB)){
                if(!jTextFieldCodBarras.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Mercadoria não cadastrada!");
                }
            if (itensAdd<1){
               jTextFieldCodBarras.requestFocus();
            }
            return false;
        }
        else {
            return true;
        }
    }
    
    private boolean verificaCliente (String cpfCliente) throws Exception{
        
        String cpfC = null;
            Statement st = Conexao.conectar();
                ResultSet rs = st.executeQuery("SELECT CPF FROM CLIENTES WHERE CPF = '"+cpfCliente+"';");
                while(rs.next()){
                    cpfC = rs.getString("CPF");
                }
        if (!jTextFieldCpfCliente.getText().equals(cpfC)){
            JOptionPane.showMessageDialog(null, "Cliente não cadastrado!");
            return false;
        }
        else {
            return true;
        }
    }
    
    private void carregaProdutos(){
                try  {
                Statement st = Conexao.conectar();
                ResultSet rs = st.executeQuery("SELECT * FROM Mercadorias WHERE Mercadorias.CodBarras = '"+jTextFieldCodBarras.getText()+"';");
                    while(rs.next()) {
                            jTextFieldDescricao.setText(rs.getString("Descricao"));
                            jTextFieldPrecoUnitario.setText(rs.getString("PrecoVenda"));
                            jTextFieldDescricao.enable(false);
                            jTextFieldPrecoUnitario.enable(false);
                            jTextFieldQuantidade.requestFocus();
                }
                
                st.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Erro ao carregar dados do produto: "+e.getMessage());
            }
    }
    
    public void recebeTela(formMain telaPrincipal){
        this.telaPrincipal = telaPrincipal;
    }
    
    /**
     * Creates new form cadVendas
     */
    public cadVendas(){
        initComponents();
        geraData();
      //  new BarraProgresso();//.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldCodBarras = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldQuantidade = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldPrecoUnitario = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldPrecoTotal = new javax.swing.JTextField();
        jTextFieldNomeVendedor = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldDataVenda = new javax.swing.JTextField();
        jTextFieldNomeCliente = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jTextFieldDescricao = new javax.swing.JTextField();
        jTextFieldCpfVendedor = new javax.swing.JFormattedTextField();
        jTextFieldCpfCliente = new javax.swing.JFormattedTextField();
        jButtonDeletar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButtonSalvar = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldValorTotal = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldSubTotal = new javax.swing.JTextField();
        jTextFieldDesconto = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableItensVenda = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("frmVendas"); // NOI18N
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastro de Vendas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel1.setName(""); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("CPF Vendedor:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Nome do Vendedor:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("CPF Cliente:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Nome Cliente:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Código de Barras:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Descrição da Mercadoria:");

        jTextFieldCodBarras.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldCodBarrasFocusLost(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Quantidade:");

        jTextFieldQuantidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldQuantidadeFocusLost(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Preço Unitário - R$");

        jTextFieldPrecoUnitario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldPrecoUnitarioFocusLost(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Preço Total - R$");

        jTextFieldPrecoTotal.setEnabled(false);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Data:");

        jTextFieldDataVenda.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextFieldDataVenda.setEnabled(false);

        jButton1.setText("Adicionar Item");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        try {
            jTextFieldCpfVendedor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTextFieldCpfVendedor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldCpfVendedorFocusLost(evt);
            }
        });

        try {
            jTextFieldCpfCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTextFieldCpfCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldCpfClienteFocusLost(evt);
            }
        });

        jButtonDeletar.setText("Excluir Item");
        jButtonDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeletarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextFieldCodBarras)
                                .addComponent(jTextFieldDataVenda, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldCpfCliente))
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNomeCliente, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jTextFieldCpfVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jTextFieldNomeVendedor)))
                            .addComponent(jTextFieldDescricao)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(48, 48, 48)
                                .addComponent(jLabel8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldPrecoUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldPrecoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonDeletar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNomeVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCpfVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCpfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCodBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPrecoUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPrecoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButtonDeletar)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pagamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jButtonSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eticket/Save-icon.png"))); // NOI18N
        jButtonSalvar.setToolTipText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eticket/Clear-icon.png"))); // NOI18N
        jButtonLimpar.setToolTipText("Limpar");
        jButtonLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparActionPerformed(evt);
            }
        });

        jButtonSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eticket/Log-Out-icon.png"))); // NOI18N
        jButtonSair.setToolTipText("Sair");
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Total da Venda - R$");

        jTextFieldValorTotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextFieldValorTotal.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Subtotal - R$:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("% Desconto");

        jTextFieldSubTotal.setEnabled(false);

        jTextFieldDesconto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldDescontoFocusLost(evt);
            }
        });
        jTextFieldDesconto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDescontoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jTextFieldDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jTextFieldValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(jButtonLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(jButtonSair, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonSair)
                    .addComponent(jButtonSalvar)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButtonLimpar))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTableItensVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código de Barras:", "Descrição", "Quantidade", "Preço Unitário", "Preço Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableItensVenda.setName("jTableItensVenda"); // NOI18N
        jScrollPane2.setViewportView(jTableItensVenda);
        if (jTableItensVenda.getColumnModel().getColumnCount() > 0) {
            jTableItensVenda.getColumnModel().getColumn(0).setResizable(false);
            jTableItensVenda.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTableItensVenda.getColumnModel().getColumn(1).setResizable(false);
            jTableItensVenda.getColumnModel().getColumn(1).setPreferredWidth(60);
            jTableItensVenda.getColumnModel().getColumn(2).setResizable(false);
            jTableItensVenda.getColumnModel().getColumn(2).setPreferredWidth(10);
            jTableItensVenda.getColumnModel().getColumn(3).setResizable(false);
            jTableItensVenda.getColumnModel().getColumn(3).setPreferredWidth(20);
            jTableItensVenda.getColumnModel().getColumn(4).setResizable(false);
            jTableItensVenda.getColumnModel().getColumn(4).setPreferredWidth(20);
        }

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("ITENS DA VENDA:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        
        try {
            Statement stm = Conexao.conectar();
            String cpfVendedor = (""+jTextFieldCpfVendedor.getText());
            String nomeVendedor = (""+jTextFieldNomeVendedor.getText());    
            String cpfCliente = (""+jTextFieldCpfCliente.getText());        
            String nomeCliente = (""+jTextFieldNomeCliente.getText());          
            String dataVenda = (""+jTextFieldDataVenda.getText());
            String totalVenda = (""+jTextFieldValorTotal.getText());
            String subTotal = (""+jTextFieldSubTotal.getText());
            String desconto = (""+jTextFieldDesconto.getText());
            String sql1 = "INSERT INTO DADOS_VENDAS (DATAVENDA,CPFVENDEDOR,NOMEVENDEDOR,CPFCLIENTE,NOMECLIENTE,SUBTOTAL,DESCONTO,VALOR_VENDA) VALUES ('"+dataVenda+"','"+cpfVendedor+"','"+nomeVendedor+"','"+cpfCliente+"','"+nomeCliente+"','"+subTotal+"','"+desconto+"','"+totalVenda+"');";
            stm.execute(sql1);
            stm.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao carregar dados do cliente: "+e.getMessage());
        }
        gravaItens();
        try {
            atualizaQuantidade();
            
        } catch (Exception ex) {
        }
        jButtonLimparActionPerformed(evt);
                 
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparActionPerformed
        jTextFieldCpfVendedor.setText("");
        jTextFieldCpfCliente.setText("");
        jTextFieldNomeCliente.setText("");
        jTextFieldQuantidade.setText("");
        jTextFieldPrecoUnitario.setText("");
        jTextFieldPrecoTotal.setText("");
        jTextFieldCodBarras.setText("");
        jTextFieldNomeVendedor.setText("");
        jTextFieldSubTotal.setText("");
        jTextFieldDesconto.setText("");
        jTextFieldValorTotal.setText("");
        jTextFieldCpfVendedor.enable(true);
        jTextFieldCpfCliente.enable(true);
        jTextFieldCpfVendedor.requestFocus();

        DefaultTableModel dtm = (DefaultTableModel) jTableItensVenda.getModel();
            int qtd = dtm.getRowCount();
                for(int i=0; i<qtd; i++){
                    dtm.removeRow(0);
                }
        
    }//GEN-LAST:event_jButtonLimparActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        telaPrincipal.enable(true);
        this.dispose();
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jTextFieldPrecoUnitarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPrecoUnitarioFocusLost
        
            
    }//GEN-LAST:event_jTextFieldPrecoUnitarioFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            
        try {
            Statement stm = Conexao.conectar();
                String codBarras = jTextFieldCodBarras.getText();
                ResultSet rs = stm.executeQuery("SELECT QTD FROM MERCADORIAS WHERE MERCADORIAS.CODBARRAS = '"+codBarras+"'");
                    while (rs.next())
                        {
                    int qtd = rs.getInt("QTD");
                    
                    if(Integer.parseInt(jTextFieldQuantidade.getText())<qtd){
                    DefaultTableModel dtm = (DefaultTableModel) jTableItensVenda.getModel();
                        dtm.addRow(new Object[]{jTextFieldCodBarras.getText(),jTextFieldDescricao.getText(),jTextFieldQuantidade.getText(),jTextFieldPrecoUnitario.getText(),jTextFieldPrecoTotal.getText()});
                            calculaTotalProdutos();

                    }else{
                     JOptionPane.showMessageDialog(null,"Quantidade Inexistente!");
                    }
                    }
        
        } catch (SQLException ex) {
        } catch (Exception ex) {
            Logger.getLogger(cadVendas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        jTextFieldCpfVendedor.enable(false);
        jTextFieldCpfCliente.enable(false);
        jTextFieldCodBarras.setText("");
        jTextFieldDescricao.setText("");
        jTextFieldDescricao.enable(true);
        jTextFieldQuantidade.setText("");
        jTextFieldPrecoUnitario.setText("");
        jTextFieldPrecoTotal.setText("");
        jTextFieldCodBarras.requestFocus();
        itensAdd++;
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private boolean verificaVendedor (String cpfVendedor) throws Exception{
        
        String cpfV = null;
        
            Statement st = Conexao.conectar();
                ResultSet rs = st.executeQuery("SELECT CPF FROM FUNCIONARIOS WHERE CPF = '"+cpfVendedor+"';");
                    while(rs.next()){
                        cpfV = rs.getString("CPF");
                }
       
        if (!jTextFieldCpfVendedor.getText().equals(cpfV)){
            if(!jTextFieldCpfVendedor.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Vendedor não cadrastado");}
            return false;
        }
        else {
            return true;
        }
        
        
    }
    
    
    private void jTextFieldCodBarrasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCodBarrasFocusLost
        try {
            if(verificaExistencia(jTextFieldCodBarras.getText())){
                carregaProdutos();}
            else{
            }
        } catch (Exception ex) {
            Logger.getLogger(cadEntradas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextFieldCodBarrasFocusLost
    
    private void jTextFieldQuantidadeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldQuantidadeFocusLost
            double x, y, z;
                x = Double.parseDouble(jTextFieldQuantidade.getText());
                y = Double.parseDouble(jTextFieldPrecoUnitario.getText());
                z = (x*y);
                jTextFieldPrecoTotal.setText(String.valueOf(z));
                
    }//GEN-LAST:event_jTextFieldQuantidadeFocusLost

    private void jTextFieldDescontoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDescontoFocusLost
        if(jTextFieldDesconto.getText().equals("")){
            try {
                throw new ExcessaoDesconto();
            } catch (ExcessaoDesconto ex) {
               JOptionPane.showMessageDialog(null, ex);
               jTextFieldDesconto.requestFocus();
            }
        }
        
        
        double x, y, z, a=0;
        x = (Double.parseDouble(jTextFieldDesconto.getText())/100);
        y = (Double.parseDouble(jTextFieldSubTotal.getText()));
        //z = (Double.parseDouble(jTextFieldSubTotal.getText())-y);
        y = (1 - x)*y;
        String resultado = String.format("%.2f", y);
        jTextFieldValorTotal.setText(resultado);
        
    }//GEN-LAST:event_jTextFieldDescontoFocusLost

    private void jTextFieldDescontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDescontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDescontoActionPerformed

    private void jTextFieldCpfVendedorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCpfVendedorFocusLost
        try {
            if(verificaVendedor(jTextFieldCpfVendedor.getText())){
                carregaVendedor();}
            else{
                jTextFieldCpfVendedor.requestFocus();
            }
        } catch (Exception ex) {
            Logger.getLogger(cadEntradas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextFieldCpfVendedorFocusLost

    private void jTextFieldCpfClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCpfClienteFocusLost
        try {
            if(verificaCliente(jTextFieldCpfCliente.getText())){
                carregaClientes();}
            else{
                jTextFieldCpfCliente.requestFocus();
            }
        } catch (Exception ex) {
            Logger.getLogger(cadEntradas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextFieldCpfClienteFocusLost

    private void jButtonDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeletarActionPerformed
        DefaultTableModel dtm = (DefaultTableModel)jTableItensVenda.getModel();
        if (jTableItensVenda.getSelectedRow() >= 0){
            dtm.removeRow(jTableItensVenda.getSelectedRow());
            jTableItensVenda.setModel(dtm);
        }else{
            JOptionPane.showMessageDialog(null, "Selecione um item para excluir");
        }

    }//GEN-LAST:event_jButtonDeletarActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonDeletar;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableItensVenda;
    private javax.swing.JTextField jTextFieldCodBarras;
    private javax.swing.JFormattedTextField jTextFieldCpfCliente;
    private javax.swing.JFormattedTextField jTextFieldCpfVendedor;
    private javax.swing.JTextField jTextFieldDataVenda;
    private javax.swing.JTextField jTextFieldDesconto;
    private javax.swing.JTextField jTextFieldDescricao;
    private javax.swing.JTextField jTextFieldNomeCliente;
    private javax.swing.JTextField jTextFieldNomeVendedor;
    private javax.swing.JTextField jTextFieldPrecoTotal;
    private javax.swing.JTextField jTextFieldPrecoUnitario;
    private javax.swing.JTextField jTextFieldQuantidade;
    private javax.swing.JTextField jTextFieldSubTotal;
    private javax.swing.JTextField jTextFieldValorTotal;
    // End of variables declaration//GEN-END:variables

   
}

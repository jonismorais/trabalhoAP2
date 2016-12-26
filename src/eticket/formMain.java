


package eticket;

import java.sql.Date;
import java.text.DateFormat;
import java.util.Locale;

/**
 *
 * @author Jonis
 */
public class formMain extends javax.swing.JFrame {
    
    public static String getDataFormatada(){
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL, new Locale("pt", "BR"));        

        // DATA ATUAL DO SISTEMA : new Date(System.currentTimeMillis())
        String dataExtenso = formatador.format(new Date(System.currentTimeMillis()));
        int index  = dataExtenso.indexOf(",") + 2;
        int lenght = dataExtenso.length();
        return dataExtenso.substring(index, lenght).toLowerCase();
    }
    
    public void mostrarHora() {
        DataHora dh = new DataHora(jLabelhora);
        Thread thHora = dh;
        thHora.start();
    }
    
    

    /**
     * Creates new form formMain
     */
    public formMain() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        mostrarHora();
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jToolBar1 = new javax.swing.JToolBar();
        jButtonClientes = new javax.swing.JButton();
        jButtonFornecedor = new javax.swing.JButton();
        jButtonEmpregado = new javax.swing.JButton();
        jButtonCadMercadorias = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButtonMercadorias = new javax.swing.JButton();
        jButtonVender = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButtonSair = new javax.swing.JButton();
        jLabel123 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        jLabelData = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        jLabelhora = new javax.swing.JLabel();
        jLabelImg = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemEntrada = new javax.swing.JMenuItem();
        jMenuItemSaida = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("e-Market - Automação Comercial - v. 2016.12.1.0.1");
        setName("frmMain"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

        jButtonClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eticket/cad_clientes.png"))); // NOI18N
        jButtonClientes.setToolTipText("Cadastro de Clientes");
        jButtonClientes.setFocusable(false);
        jButtonClientes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonClientes.setName("btnCadClientes"); // NOI18N
        jButtonClientes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClientesActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonClientes);

        jButtonFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eticket/truck-icon.png"))); // NOI18N
        jButtonFornecedor.setToolTipText("Cadastro de Fornecedores");
        jButtonFornecedor.setFocusable(false);
        jButtonFornecedor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonFornecedor.setName("btnCadEventos"); // NOI18N
        jButtonFornecedor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFornecedorActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonFornecedor);

        jButtonEmpregado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eticket/empregado.png"))); // NOI18N
        jButtonEmpregado.setToolTipText("Cadastro de Funcionário");
        jButtonEmpregado.setFocusable(false);
        jButtonEmpregado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEmpregado.setName("btnFuncionario"); // NOI18N
        jButtonEmpregado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonEmpregado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEmpregadoActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonEmpregado);

        jButtonCadMercadorias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eticket/Drive-PRODUCT-Red-icon.png"))); // NOI18N
        jButtonCadMercadorias.setToolTipText("Cadastro de Mercadorias");
        jButtonCadMercadorias.setFocusable(false);
        jButtonCadMercadorias.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCadMercadorias.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonCadMercadorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadMercadoriasActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonCadMercadorias);
        jToolBar1.add(jSeparator4);

        jButtonMercadorias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eticket/product-icon.png"))); // NOI18N
        jButtonMercadorias.setToolTipText("Entrada de Mercadorias");
        jButtonMercadorias.setFocusable(false);
        jButtonMercadorias.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonMercadorias.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonMercadorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMercadoriasActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonMercadorias);

        jButtonVender.setIcon(new javax.swing.ImageIcon("C:\\Users\\Jonis\\Documents\\NetBeansProjects\\eTicket\\img\\shop-cart-add-icon.png")); // NOI18N
        jButtonVender.setToolTipText("Saída de Mercadorias");
        jButtonVender.setFocusable(false);
        jButtonVender.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonVender.setName("btnVender"); // NOI18N
        jButtonVender.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVenderActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonVender);
        jToolBar1.add(jSeparator3);

        jButtonSair.setIcon(new javax.swing.ImageIcon("C:\\Users\\Jonis\\Documents\\NetBeansProjects\\eTicket\\img\\door-in-icon.png")); // NOI18N
        jButtonSair.setToolTipText("Sair");
        jButtonSair.setFocusable(false);
        jButtonSair.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSair.setName("btnSair"); // NOI18N
        jButtonSair.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonSair);

        jLabel123.setText("                                                                                                    ");
        jToolBar1.add(jLabel123);
        jToolBar1.add(jSeparator7);

        jLabelData.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelData.setText(getDataFormatada());
        jToolBar1.add(jLabelData);
        jToolBar1.add(jSeparator8);

        jLabelhora.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelhora.setText("<hora>");
        jToolBar1.add(jLabelhora);

        jLabelImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eticket/7ceed119d0b849eba3cbb948e79519bb.png"))); // NOI18N

        jMenu1.setText("Cadastros");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Clientes");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Fornecedores");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Mercadorias");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);
        jMenu1.add(jSeparator6);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Vendedores");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Movimento");

        jMenuItemEntrada.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItemEntrada.setText("Entrada ");
        jMenuItemEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEntradaActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemEntrada);

        jMenuItemSaida.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItemSaida.setText("Saída");
        jMenuItemSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSaidaActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemSaida);

        jMenuBar1.add(jMenu2);

        jMenu5.setText("Ajuda");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem3.setText("Sobre");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem3);
        jMenu5.add(jSeparator5);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem4.setText("Sair");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem4);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelImg)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 207, Short.MAX_VALUE)
                .addComponent(jLabelImg)
                .addGap(30, 30, 30))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClientesActionPerformed
        cadClientes frmClientes = new cadClientes();
        frmClientes.recebeTela(this);
        frmClientes.show();
        this.enable(false);
    }//GEN-LAST:event_jButtonClientesActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        cadClientes frmClientes = new cadClientes();
        frmClientes.recebeTela(this);
        frmClientes.show();
        this.enable(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       cadFornecedor fornecedor = new cadFornecedor();
       fornecedor.recebeTela(this);
       fornecedor.show();
       this.enable(false);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        frmSobre sobre = new frmSobre();
        sobre.show();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
       System.exit(0);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jButtonFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFornecedorActionPerformed
       cadFornecedor fornecedor = new cadFornecedor();
       fornecedor.recebeTela(this);
       this.enable(false);
       fornecedor.show();

    }//GEN-LAST:event_jButtonFornecedorActionPerformed

    private void jButtonMercadoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMercadoriasActionPerformed
       cadEntradas entrada = new cadEntradas();
       entrada.recebeTela(this);
       this.enable(false);
       entrada.show();
    }//GEN-LAST:event_jButtonMercadoriasActionPerformed

    private void jMenuItemEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEntradaActionPerformed
       cadEntradas entrada = new cadEntradas();
       entrada.recebeTela(this);
       this.enable(false);
       entrada.show();
    }//GEN-LAST:event_jMenuItemEntradaActionPerformed

    private void jMenuItemSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSaidaActionPerformed
        cadVendas venda = new cadVendas();
        venda.recebeTela(this);
        this.enable(false);
        venda.show();
    }//GEN-LAST:event_jMenuItemSaidaActionPerformed

    private void jButtonEmpregadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEmpregadoActionPerformed
        cadVendedor vendedor = new cadVendedor();
        vendedor.recebeTela(this);
        this.enable(false);
        vendedor.show();
    }//GEN-LAST:event_jButtonEmpregadoActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        cadVendedor vendedor = new cadVendedor();
        vendedor.recebeTela(this);
        this.enable(false);
        vendedor.show();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jButtonVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVenderActionPerformed
       cadVendas venda = new cadVendas();
       venda.recebeTela(this);
       this.enable(false);
       venda.show();
        
    }//GEN-LAST:event_jButtonVenderActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
       cadMercadorias mercadorias = new cadMercadorias();
       mercadorias.recebeTela(this);
       this.enable(false);
       mercadorias.show();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jButtonCadMercadoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadMercadoriasActionPerformed
       cadMercadorias mercadorias = new cadMercadorias();
       mercadorias.recebeTela(this);
       this.enable(false);
       mercadorias.show();
    }//GEN-LAST:event_jButtonCadMercadoriasActionPerformed

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(formMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formMain().setVisible(true);
            }
            
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCadMercadorias;
    private javax.swing.JButton jButtonClientes;
    private javax.swing.JButton jButtonEmpregado;
    private javax.swing.JButton jButtonFornecedor;
    private javax.swing.JButton jButtonMercadorias;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JButton jButtonVender;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabelData;
    private javax.swing.JLabel jLabelImg;
    private javax.swing.JLabel jLabelhora;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItemEntrada;
    private javax.swing.JMenuItem jMenuItemSaida;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
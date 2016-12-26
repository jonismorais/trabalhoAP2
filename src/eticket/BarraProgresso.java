/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eticket;

import java.awt.Cursor;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

/**
 *
 * @author Jonis
 */

public class BarraProgresso extends JFrame {
	public BarraProgresso() throws InterruptedException, IOException {
            	JProgressBar progresso = new JProgressBar();
		setSize(300,80);
                setTitle("Gerando comprovante...");
		add(progresso);
		setLocationRelativeTo(null);
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		setVisible(true);
		progresso.setToolTipText("Aguarde, carregando...");
		for (int i=1;i<100;i++){
			progresso.setValue(i);
			Thread.sleep(30);
		}
                this.dispose();
                new ReciboVenda().abreRecibo();
                
                }
}

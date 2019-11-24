/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyek;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author Winda AU
 */
public class formIntro extends javax.swing.JFrame {

    /**
     * Creates new form formIntro
     */
    int ctrWaktu;
    Boolean introDone;
    Timer t;
    public formIntro() {
        try {
            initComponents();
            this.setFocusable(true);
            ctrWaktu = 0;
            introDone = false;
            
            File f = new File("videos/intro.mp4");
            Desktop d = Desktop.getDesktop();
            d.open(f);
            
            t = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    if (ctrWaktu >= 3) {
                        lblSkip.setText("PRESS SPACE TO SKIP THE VIDEO");
                    }
                    else{
                        lblSkip.setText("WELCOME TO SPACE SHOOTER");
                    }
                    
                    checkIntroDone(false);
                    System.out.println("waktu : " + ctrWaktu);
                }
            });
            t.start();
            
            
        } catch (IOException ex) {
            Logger.getLogger(formIntro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    private void showMainMenu(){
        this.setVisible(false);
        LoginFrame login = new LoginFrame();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
    }
    
    private void checkIntroDone(Boolean skip){
        if (skip) {
            t.stop();
            showMainMenu();
        }
        else{
            if (ctrWaktu >= 10) {
                t.stop();
                showMainMenu();
            } 
            else{
                ctrWaktu++;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblSkip = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 500));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        lblSkip.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblSkip.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSkip.setText("WELCOME TO SPACE SHOOTER");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(lblSkip, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addComponent(lblSkip, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(203, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            checkIntroDone(true);
        }
    }//GEN-LAST:event_formKeyPressed

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
            java.util.logging.Logger.getLogger(formIntro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formIntro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formIntro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formIntro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formIntro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblSkip;
    // End of variables declaration//GEN-END:variables
}

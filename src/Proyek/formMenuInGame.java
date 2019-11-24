package Proyek;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Winda AU
 */
public class formMenuInGame extends javax.swing.JFrame {

    /**
     * Creates new form formMenuInGame
     */
    game terimaGame;
    
    static formGameSpace f_game;
    public formMenuInGame() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
//            if (JOptionPane.showConfirmDialog(null, 
//                "Are you sure you want to close this window?", "Close Window?", 
//                JOptionPane.YES_NO_OPTION,
//                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
//                
//            }
            setResumePause();
        }
        });
        setSoundGame();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSetSound = new javax.swing.JButton();
        btnSaveGame = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnExitGame = new javax.swing.JButton();
        btnResume = new javax.swing.JButton();
        btnBackToMain1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        btnSetSound.setText("Sound : On");
        btnSetSound.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSetSoundMouseClicked(evt);
            }
        });

        btnSaveGame.setText("Save Current Game");
        btnSaveGame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveGameMouseClicked(evt);
            }
        });
        btnSaveGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveGameActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setText("Game Paused");

        btnExitGame.setText("Exit Game");
        btnExitGame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitGameMouseClicked(evt);
            }
        });

        btnResume.setText("Resume");
        btnResume.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResumeMouseClicked(evt);
            }
        });
        btnResume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResumeActionPerformed(evt);
            }
        });

        btnBackToMain1.setText("Back to Main Menu");
        btnBackToMain1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackToMain1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnExitGame, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnResume, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnSetSound, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSaveGame, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBackToMain1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnResume, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSetSound, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnBackToMain1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSaveGame, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnExitGame, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSetSoundMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSetSoundMouseClicked
        // TODO add your handling code here:
        LoginFrame.soundOn = !LoginFrame.soundOn;
        setSoundGame();
    }//GEN-LAST:event_btnSetSoundMouseClicked

    private void btnResumeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResumeMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        if (!panelGame.tmr.isRunning()) {
            panelGame.tmr.start();
        }
    }//GEN-LAST:event_btnResumeMouseClicked

    private void btnSaveGameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveGameMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnSaveGameMouseClicked

    private void btnExitGameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitGameMouseClicked
        //struktur message box sementara kayak gini, boleh diubah
        int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit game?", "Exit Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (n == JOptionPane.YES_OPTION) {
            int n2 = JOptionPane.showConfirmDialog(null, "Do you want to save current game?", "Exit Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (n2 == JOptionPane.YES_OPTION) { //save game
                
            }
            
            System.exit(0); //keluar dari program
        }
    }//GEN-LAST:event_btnExitGameMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void btnBackToMain1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackToMain1MouseClicked
        // TODO add your handling code here:
        int n = JOptionPane.showConfirmDialog(null, "Do you want to save current game?", "Exit Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (n == JOptionPane.YES_OPTION) { //save game
            
        }
    }//GEN-LAST:event_btnBackToMain1MouseClicked

    private void btnResumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResumeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnResumeActionPerformed

    private void btnSaveGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveGameActionPerformed
        try{
            FileOutputStream fisp = new FileOutputStream("simpan.txt");
            ObjectOutputStream save_player = new ObjectOutputStream(fisp);
            save_player.writeObject(terimaGame); 
            save_player.close(); fisp.close();
        }
        catch(Exception ex){
            System.out.println("gagal karena " + ex);
        }
    }//GEN-LAST:event_btnSaveGameActionPerformed

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
            java.util.logging.Logger.getLogger(formMenuInGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formMenuInGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formMenuInGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formMenuInGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formMenuInGame().setVisible(true);
            }
        });
    }
    
    public void setSoundGame(){
        if (LoginFrame.soundOn) {
            btnSetSound.setText("Sound : off");
            LoginFrame.soundEffect(true);
        }
        else{
            btnSetSound.setText("Sound : on");
            LoginFrame.soundEffect(false);
        }
    }
    
    public void setResumePause(){
        this.setVisible(false);
        if (!panelGame.tmr.isRunning()) {
            panelGame.tmr.start();
        }
    }
    
    public void showMainMenu(){
        this.setVisible(false);
        f_game.setVisible(false);
        LoginFrame login = new LoginFrame();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackToMain1;
    private javax.swing.JButton btnExitGame;
    private javax.swing.JButton btnResume;
    private javax.swing.JButton btnSaveGame;
    private javax.swing.JButton btnSetSound;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    void kirim(game gameSpace) {
        terimaGame = gameSpace;
    }
}

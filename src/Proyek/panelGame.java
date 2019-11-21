package Proyek;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class panelGame extends javax.swing.JPanel {

    
    game gameSpace;
    
    public String namaPlayer;
    static Timer tmr;
    static Boolean exitGame;
    float ctrWaktu;
    int ctrTembak;
    
    public panelGame() {
        initComponents();
        this.setFocusable(true);
        exitGame = false;
        
        
        newGame();
        for (pesawat p : gameSpace.listMusuh) {
            ((pesawatMusuh)p).tmrTembak = (int)(Math.random()*35) + 20;
        }
        tmr = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                for (pesawat p : gameSpace.listMusuh) {
                    if(((pesawatMusuh)p).tmrTembak == 0){
                        ((pesawatMusuh)p).tmrTembak = (int)(Math.random()*35) + 20;
                        p.shoot();
                    }
                    else{
                        ((pesawatMusuh)p).tmrTembak--;
                    }
                    
                }
                for (pesawat p : gameSpace.listMusuh) {
                    p.move("");
                }
                for (peluru p : ((pesawatPlayer)gameSpace.player).listPeluru){
                    p.move();
                }
                for (pesawat p : gameSpace.listMusuh) {
                    for (peluru pl : ((pesawatMusuh)p).listPeluru){
                        pl.move();
                    }
                }
                
                ctrWaktu+= 0.05;
                if (ctrWaktu >= 10) { //setiap 10 detik
                    gameSpace.nextStage();
                    System.out.println("pura - pura stage bertambah menjadi : " + gameSpace.getStage());
                    ctrWaktu = 0;
                }
                gameSpace.tabrak();
                gameSpace.nembak();
                gameSpace.ketembak();
                repaint();
            }
        });
        
        tmr.start();
    }
    
    private void newGame(){
        gameSpace = new game(newPlayerName.namaPlayer, 1, LoginFrame.mode);
        gameSpace.setPlayer(new pesawatSingleShooter(5, 1));
        gameSpace.randomMusuh();
    }
    
    public void checkExitGame(){
        if (exitGame) {
            this.setVisible(false);
        }
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); 
        gameSpace.drawGame(grphcs);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPause = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(500, 500));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        btnPause.setText("Pause");
        btnPause.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPauseMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(425, Short.MAX_VALUE)
                .addComponent(btnPause)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPause)
                .addContainerGap(464, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_A) {
            gameSpace.getPlayer().move("A");
        }
        else if (evt.getKeyCode() == KeyEvent.VK_D) {
            gameSpace.getPlayer().move("D");
        }
        else if(evt.getKeyCode() == KeyEvent.VK_SPACE){
            ((pesawatPlayer)gameSpace.getPlayer()).shoot();
        }
        else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setPauseGame();
        }
        repaint();
    }//GEN-LAST:event_formKeyPressed

    private void btnPauseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPauseMouseClicked
        // TODO add your handling code here:
        setPauseGame();    
        
    }//GEN-LAST:event_btnPauseMouseClicked

    private void setPauseGame(){
        if (tmr.isRunning()) {
            tmr.stop();
            formMenuInGame ingameMenu = new formMenuInGame();
            ingameMenu.setLocationRelativeTo(null);
            ingameMenu.setVisible(true);
        }
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPause;
    // End of variables declaration//GEN-END:variables
}

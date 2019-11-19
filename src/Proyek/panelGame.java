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
    BufferedImage jpg;
    public String namaPlayer;
    Timer t;
    float ctrWaktu;
    int ctrTembak;
    
    public panelGame() {
        initComponents();
        this.setFocusable(true);
        try {
            jpg = ImageIO.read(new File("images/bg2.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        gameSpace = new game("winda", 1);
        gameSpace.setPlayer(new pesawatPlayer(5, 1));
        randomMusuh();
        for (pesawat p : gameSpace.listMusuh) {
            ((pesawatMusuh)p).tmrTembak = (int)(Math.random()*35) + 20;
        }
        t = new Timer(50, new ActionListener() {
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
                if (ctrWaktu >= 10) {
//                    randomMusuh();
//                    System.out.println("keluar musuh di " + ctrWaktu);
                    nextStage();
                    System.out.println("pura - pura stage bertambah menjadi : " + gameSpace.getStage());
                    ctrWaktu = 0;
                }
                tabrak();
                nembak();
                ketembak();
                repaint();
            }
        });
        
        t.start();
    }
    
    public void nextStage(){
        gameSpace.setStage(gameSpace.getStage() + 1);
        randomMusuh();
    }
    
    public void randomMusuh(){
        int rand = (int)(Math.random() * 15) + 3;
        for(int i =0; i < rand; i++){
            gameSpace.addMusuh(new pesawatMusuh(3));
        }
    }
    
    public void tabrak(){
        pesawat tabrak = null;
        for(pesawat p : gameSpace.listMusuh){
            if(gameSpace.getPlayer().bounds().intersects(p.bounds())){
            tabrak = p;
            }
        }
        if(tabrak != null){
            gameSpace.listMusuh.remove(tabrak);
        }
    }
    
    public void nembak(){
        pesawat tembak = null;
        for(peluru pewpew : ((pesawatPlayer)gameSpace.player).listPeluru){
            for(pesawat p : gameSpace.listMusuh){
                if(pewpew.bounds().intersects(p.bounds())){
                    tembak = p;
                }
            }
            if(tembak != null){
                gameSpace.listMusuh.remove(tembak);
            }
        }
    }
    
    public void ketembak(){
            boolean tembak = false;
            for (pesawat p : gameSpace.listMusuh) {
                for(peluru pewpew : ((pesawatMusuh)p).listPeluru){
                    if(gameSpace.player != null){
                        if(pewpew.bounds().intersects(gameSpace.player.bounds())){
                            tembak = true;
                        }
                        if(tembak != false){
                            JOptionPane.showMessageDialog(null, "Game Over");
                            gameSpace.player = null;
                            t.stop();
                            break;
                        }
                    }
                    else{
                        break;
                    }
                }
                if(gameSpace.player == null){
                    break;
                }
            }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); 
        Graphics2D g2 = (Graphics2D)grphcs;
        java.awt.Font f = new java.awt.Font("ARIAL", java.awt.Font.BOLD, 15);
        g2.setFont(f);
        g2.drawString(newPlayerName.namaPlayer, 0, 5);
        
        g2.drawImage(jpg,0,0,500,500,null);
        g2.drawRect(1, 0, 500, 500);
        
        if(gameSpace.player != null){
            gameSpace.getPlayer().draw(grphcs);    
        }
        for (pesawat p : gameSpace.getListMusuh()) {
            p.draw(grphcs);
        }
        if(gameSpace.player != null){
            for (peluru p : ((pesawatPlayer)gameSpace.getPlayer()).getListPeluru()){
                p.draw(grphcs);
            }
        }
        for (pesawat p : gameSpace.listMusuh) {
            for (peluru pl : ((pesawatMusuh)p).listPeluru){
                pl.draw(grphcs);
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

        setPreferredSize(new java.awt.Dimension(500, 500));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
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
        repaint();
    }//GEN-LAST:event_formKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

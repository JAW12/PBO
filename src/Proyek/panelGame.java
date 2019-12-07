package Proyek;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class panelGame extends javax.swing.JPanel {

    static Timer tmr;
    static Boolean exitGame;
    static Boolean sfxSoundOn;
    static String namaHiScore;
    static int scoreHiScore;
    game gameSpace;
    public String namaPlayer;
    float ctrWaktu;
    int ctrTembak;
    public formGameSpace formGame;
    int ctrLoopSfxLose;
    
    public panelGame() {
        initComponents();
        this.setFocusable(true);
        exitGame = false;
        sfxSoundOn = LoginFrame.soundOn;
        if (LoginFrame.LOADGAME==false)
        {
            newGame();
            runawal();
        }
        this.addKeyListener(Keyboard.listener);
    }
    
    private void gameOver(){
        if(tmr.isRunning() == false){
            Keyboard.reset();
            formGame.setVisible(false);
            LoginFrame.soundOn = false;
            LoginFrame.soundEffect(LoginFrame.soundOn);
            LoginFrame formlogin = new LoginFrame();
            formlogin.setLocationRelativeTo(null);
            formlogin.setVisible(true);
        }
    }
    
    private void runawal()
    {
        gameSpace.loadGambar();
        for (pesawat p : gameSpace.listMusuh) {
                ((pesawatMusuh)p).tmrTembak = (int)(Math.random()*50) + 10;
        }
        tmr = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                for (pesawat p : gameSpace.listMusuh) {
                    if(((pesawatMusuh)p).tmrTembak == 0){
                        ((pesawatMusuh)p).tmrTembak = (int)(Math.random()*50) + 10;
                        p.shoot();
                        pesawatMusuh musuh = (pesawatMusuh)p;
                        sfxTembak(musuh);
                    }
                    else{
                        ((pesawatMusuh)p).tmrTembak--;
                    }
                }
                if(gameSpace.getPlayer().ctrTembak >= 0){
                    gameSpace.getPlayer().ctrTembak--;
                }
                for (pesawat p : gameSpace.listMusuh) {
                    p.moveMusuh();
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
                if (ctrWaktu >= 1) { //setiap 1 detik
                    gameSpace.nextStage();
                }
                gameSpace.tabrak();
                gameSpace.nembak();
                gameSpace.checkPesawatMati();
                gameSpace.ketembak();
                gameSpace.ledak();
                gameSpace.checkPesawatMelewatiLayar();
                gameSpace.checkPeluruMelewatiLayar();
                if(((pesawatPlayer)gameSpace.player).ctrPowerUp > 0){
                    ((pesawatPlayer)gameSpace.player).ctrPowerUp--;
                }
                else{
                    ((pesawatPlayer)gameSpace.player).powerUP = "";
                }
                if(gameSpace.player.hp<=0){
                    tmr.stop();
                    gameSpace.getSoundEffect("sfx/sfx_game over.wav", true);
                    JOptionPane.showMessageDialog(null, "!!!!  Game Over  !!!!");
                    if (scoreHiScore<gameSpace.getScore())
                    {
                        panelGame.scoreHiScore = gameSpace.getScore();
                        panelGame.namaHiScore  = gameSpace.getNama();
                        tulisscore();
                    }
                    gameOver();
                }
                gameSpace.y1 += 5;
                gameSpace.y2 += 5;
                if(gameSpace.y1 >= 550){
                    gameSpace.y1 = -550;
                }
                if(gameSpace.y2 >= 550){
                    gameSpace.y2 = -550;
                }

                repaint();
                checkKeyboardEvt();
            }
        });

        tmr.start();
    }
    public void tulisscore()
    {
        FileOutputStream fos = null;
        try {
            File fout = new File("hs.txt");
            fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(panelGame.namaHiScore+"-"+panelGame.scoreHiScore);
            bw.close();
            fos.close();
        } catch (Exception ex) {
            Logger.getLogger(panelGame.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    private void newGame(){
        gameSpace = new game(newPlayerName.namaPlayer, 1, LoginFrame.mode);
        gameSpace.setPlayer(new pesawatSingleShooter(100, 250, 450, 0, ""));
        gameSpace.randomMusuh();
       
    }
    public void LoadGame(game gg){
        gameSpace = gg;
        runawal();
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

        btnPause.setText("I I");
        btnPause.setFocusable(false);
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
                .addContainerGap(448, Short.MAX_VALUE)
                .addComponent(btnPause)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPause, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(451, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            gameSpace.getSoundEffect("sfx/sfx_zap.wav");
            setPauseGame();
        }
    }//GEN-LAST:event_formKeyPressed

    private void btnPauseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPauseMouseClicked
        // TODO add your handling code here:
        gameSpace.getSoundEffect("sfx/sfx_zap.wav");
        setPauseGame();    
    }//GEN-LAST:event_btnPauseMouseClicked

    private void setPauseGame(){
        if (tmr.isRunning()) {
            tmr.stop();
            formMenuInGame ingameMenu = new formMenuInGame();
            ingameMenu.kirim(gameSpace);
            ingameMenu.setLocationRelativeTo(null);
            ingameMenu.setVisible(true);
        }
    }
    
    private void sfxTembak(pesawat p){
        if (sfxSoundOn) {
            if (p instanceof pesawatPlayer) {
                gameSpace.getSoundEffect("sfx/sfx_laser1.wav");
            }
            else if(p instanceof pesawatMusuh) {
                gameSpace.getSoundEffect("sfx/sfx_laser2.wav");
            }
        }
    }
    
    private void checkKeyboardEvt(){
        if (Keyboard.isPressed(KeyEvent.VK_A)) {
            gameSpace.getPlayer().movePlayer("A");
        }
        if (Keyboard.isPressed(KeyEvent.VK_D)) {
            gameSpace.getPlayer().movePlayer("D");
        }
        if (Keyboard.isPressed(KeyEvent.VK_SPACE)) {
            if(gameSpace.getPlayer().ctrTembak < 0){
                ((pesawatPlayer)gameSpace.getPlayer()).shoot();
                sfxTembak(gameSpace.getPlayer());
                gameSpace.getPlayer().ctrTembak = 3;
            }
        }
//        if (Keyboard.isPressed(KeyEvent.VK_ESCAPE)) {
//            gameSpace.getSoundEffect("sfx/sfx_zap.wav");
//            setPauseGame();
//        }    
        repaint();
    }
    
    static void setSfxSoundOn(Boolean sfxOn){
        sfxSoundOn = sfxOn;
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPause;
    // End of variables declaration//GEN-END:variables
}

package Proyek;

import static Proyek.panelGame.tmr;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class game {
    protected String nama;
    protected int score;
    protected int stage, difficultyLevel;
    protected ArrayList<pesawat> listMusuh;
    protected pesawat player;
    public newPlayerName f_promptNama;
    BufferedImage jpg;
    
    public game(String nama,int stage, int difficulty) {
        this.nama = nama;
        this.stage = stage;
        this.score = 0;
        this.listMusuh = new ArrayList<>();
        this.difficultyLevel = difficulty;
        
        try {
            jpg = ImageIO.read(new File("images/bg2.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public ArrayList<pesawat> getListMusuh() {
        return listMusuh;
    }
    
    public void addMusuh(pesawatMusuh m){
        this.listMusuh.add(m);
    }

    public pesawat getPlayer() {
        return player;
    }

    public void setPlayer(pesawat player) {
        this.player = player;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
    
    public void nextStage(){
        if (this.getListMusuh().size() <= 0) {
            this.setStage(this.getStage() + 1);
            randomMusuh();
            System.out.println("pergantian stage. Stage saat ini : " + this.getStage());
        }
    }
    
    public void randomMusuh(){
        int rand = (int)(Math.random() * 15) + 3;
        for(int i =0; i < rand; i++){
            this.addMusuh(new pesawatMusuh(100 + 5 * this.getStage(), this.getDifficultyLevel()));
        }
    }
    
    public void tabrak(){
        pesawat tabrak = null;
        for(pesawat p : this.listMusuh){
            if(this.getPlayer().bounds().intersects(p.bounds())){
                tabrak = p;
            }
        }
        if(tabrak != null){
            this.listMusuh.remove(tabrak);
        }
    }
    
    public void nembak(){
        pesawat tembak = null;
        for(peluru pewpew : ((pesawatPlayer)this.player).listPeluru){
            for(pesawat p : this.listMusuh){
                if(pewpew.bounds().intersects(p.bounds())){
                    tembak = p;
                }
            }
            if(tembak != null){
                this.listMusuh.remove(tembak);
            }
        }
    }
    
    public void ketembak(){
            boolean tembak = false;
            for (pesawat p : this.listMusuh) {
                for(peluru pewpew : ((pesawatMusuh)p).listPeluru){
                    if(this.player != null){
                        if(pewpew.bounds().intersects(this.player.bounds())){
                            tembak = true;
                        }
                        if(tembak != false){
                            JOptionPane.showMessageDialog(null, "Game Over");
                            this.player = null;
                            tmr.stop();
                            break;
                        }
                    }
                    else{
                        break;
                    }
                }
                if(this.player == null){
                    break;
                }
            }
    }
    
    public void drawGame(Graphics grphcs){
        Graphics2D g2 = (Graphics2D)grphcs;
        java.awt.Font f = new java.awt.Font("ARIAL", java.awt.Font.BOLD, 15);
        g2.setFont(f);
        g2.drawString(newPlayerName.namaPlayer, 0, 5);
        
        g2.drawImage(jpg,0,0,500,500,null);
        g2.drawRect(1, 0, 500, 500);
        
        if(this.player != null){
            this.getPlayer().draw(grphcs);    
        }
        for (pesawat p : this.getListMusuh()) {
            p.draw(grphcs);
        }
        if(this.player != null){
            for (peluru p : ((pesawatPlayer)this.getPlayer()).getListPeluru()){
                p.draw(grphcs);
            }
        }
        for (pesawat p : this.listMusuh) {
            for (peluru pl : ((pesawatMusuh)p).listPeluru){
                pl.draw(grphcs);
            }
        }
    }
}

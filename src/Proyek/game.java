package Proyek;

import static Proyek.panelGame.tmr;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class game implements Serializable{
    protected String nama;
    protected int score;
    protected int stage, difficultyLevel;
    protected ArrayList<pesawat> listMusuh;
    protected pesawat player;
    protected transient BufferedImage jpg;
    protected String gameMode;
    
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
        
        switch (this.difficultyLevel) {
            case 1:
                this.gameMode = "Easy";
                break;
            case 2:
                this.gameMode = "Medium";
                break;
            case 3:
                this.gameMode = "Hard";
                break;
            default:
                break;
        }
    }

    public game getMe(){
        return this;
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
            this.score += 100;
        }
    }
    
    public void randomMusuh(){
        /*
        batasan random sesuai mode -> difficultyLevel * 2 + 1
            easy : 3    
            medium : 5
            hard : 7
        setiap kelipatan 10 stage maka munculkan += 3 musuh
        */
        Random rnd = new Random();
        int batasRand = difficultyLevel * 2 + 1;
        int rand = rnd.nextInt(batasRand - 2 + 1) + 2;
        
        if (rand % 10 == 0) {
            rand += 5;
        }
        
        for(int i =0; i < rand; i++){
            this.addMusuh();
        }
    }
    
    public Boolean isPosXNumpuk(pesawat currentPesawat){
        Boolean numpuk = false;
        for (pesawat p : listMusuh) {
            if (currentPesawat.getPosX() == p.getPosX() || currentPesawat.getPosX() + currentPesawat.width == p.posX) {
                numpuk = true;
            }
        }
        return numpuk;
    }
    
    public void addMusuh(){
        Random rnd = new Random();
        int jenisPesawat = rnd.nextInt(4 - 1 + 1) + 1;
        if (jenisPesawat > 3) {
            jenisPesawat = 1;
        }
        pesawatMusuh pmusuh = new pesawatMusuh(100 + 5 * this.getStage(), this.getDifficultyLevel(), jenisPesawat);
        do {
            pmusuh.setRandomPosX();
        } while (isPosXNumpuk(pmusuh));
        this.listMusuh.add(pmusuh);
    }
    
    public void tabrak(){
        pesawat tabrak = null;
        for(pesawat p : this.listMusuh){
            if(this.getPlayer().bounds().intersects(p.bounds())){
                tabrak = p;
                if(((pesawatPlayer)player).shieldActive<=0){
                    this.player.hp-=50;
                }
            }
        }
        if(tabrak != null){
            this.listMusuh.remove(tabrak);
        }
    }
    ArrayList<peluru> idxHapus;
    public void nembak(){
        idxHapus = new ArrayList<peluru>();
        for(peluru pewpew : ((pesawatPlayer)this.player).listPeluru){
            for(pesawat p : this.listMusuh){
                if(pewpew.bounds().intersects(p.bounds())){
                    p.hp = p.hp-pewpew.damage;
                    idxHapus.add(pewpew);
                }
            }           
        }
        hapus();
    }
    
    
    public void hapus(){
        for (int i = 0; i < idxHapus.size(); i++) {
            ((pesawatPlayer)this.player).listPeluru.remove(idxHapus.get(i));
        }
        for (pesawat p : this.listMusuh){
            for(int i =0; i < ((pesawatMusuh)p).idxHapus.size(); i++){
                ((pesawatMusuh)p).listPeluru.remove(((pesawatMusuh)p).idxHapus.get(i));
            }
        }
    }
    public void musuhmati(){
        for (pesawat p : this.listMusuh) {
            if(p.hp <= 0){
                System.out.println(((pesawatMusuh)p).jenisPowerUp);
                if(((pesawatMusuh)p).jenisPowerUp != 2){
                    ((pesawatPlayer)player).powerUp((((pesawatMusuh)p).jenisPowerUp));
                }
                else{
                    player = ((pesawatPlayer)player).evolve();
                }
            }
        }
    }
    
    public void ketembak(){
        boolean tembak = false;
        for (pesawat p : this.listMusuh) {
            ((pesawatMusuh)p).idxHapus = new ArrayList<peluru>();
            for(peluru pewpew : ((pesawatMusuh)p).listPeluru){
                if(this.player != null){
                    if(pewpew.bounds().intersects(this.player.bounds())){
                        tembak = true;
                        if(((pesawatPlayer)player).shieldActive<=0){
                            if(this.difficultyLevel == 1)
                            this.player.hp-=20;
                            else if(this.difficultyLevel == 2)
                            this.player.hp-=40;
                            else if(this.difficultyLevel == 3)
                            this.player.hp-=60;
                        }
                        ((pesawatMusuh)p).idxHapus.add(pewpew);
                    }
                    if(tembak != false){
                        //JOptionPane.showMessageDialog(null, "Game Over");
                        //this.player = null;
                        //tmr.stop();
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
        hapus();
    }
    
    public void drawGame(Graphics grphcs){
        Graphics2D g2 = (Graphics2D)grphcs;
        g2.drawImage(jpg,0,0,550,550,null);
        Font f = new Font("ARIAL",Font.BOLD, 17);
        g2.setFont(f);
        g2.setColor(Color.WHITE);
        g2.drawString("Player : " + newPlayerName.namaPlayer.toUpperCase(), 5, 30);
        g2.drawString("Score  : " + this.score, 5, 60);
        g2.drawString("Hp : " + this.player.getHp(), 5, 90);
        g2.drawString("Mode : " + this.gameMode, 325, 30);
        g2.drawString("Stage : " + this.stage, 325, 60);
        
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
    
    public Boolean isMelewatiLayar(pesawat p){
        Boolean lewat = false;
        if (p.getPosX() + p.getWidth() >= 540) {
            lewat = true;
        }
        
        if (p.getPosY() + p.getHeight() >= 540) {
            lewat = true;
        }
        
        return lewat;
    }
    
     public Boolean isPeluruMelewatiLayar(peluru p){
        Boolean lewat = false;
        if (p.getPosX()  <= 0) {
            lewat = true;
        }
        
        if (p.getPosY() <=  0) {
            lewat = true;
        }
        
        return lewat;
    }
    
    public Boolean isMati(pesawat p){
        Boolean mati = false;
        if(p.hp < 0){
            mati = true;
        }
        
        return mati;
    }
    
    public void checkPesawatMelewatiLayar(){
        for (int i = 0; i < this.listMusuh.size(); i++) {
            if (isMelewatiLayar(this.listMusuh.get(i))) {
                this.listMusuh.remove(i);
            }
        }
    }
    
    public void checkPeluruMelewatiLayar(){
        for (int i = 0; i < ((pesawatPlayer)player).listPeluru.size(); i++) {
            if (isPeluruMelewatiLayar(((pesawatPlayer)player).listPeluru.get(i))) {
                ((pesawatPlayer)player).listPeluru.remove(i);
            }
        }
    }
    
    public void checkPesawatMati(){
        for (int i = 0; i < this.listMusuh.size(); i++) {
            if (isMati(this.listMusuh.get(i))) {
                this.listMusuh.remove(i);
                this.score += 20;
            }
        }
    }
}

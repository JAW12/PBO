
package Proyek;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class game implements Serializable{
    protected String nama;
    protected int score;
    protected int stage, difficultyLevel;
    protected ArrayList<pesawat> listMusuh;
    protected pesawat player;
    protected transient BufferedImage jpg;
    protected int y1, y2;
    protected String gameMode;
    protected int tmrStage;
    protected int musuh;
    protected int ctrSfxNextStage;
    protected boolean blastActive;
    
    public game(String nama,int stage, int difficulty) {
        this.blastActive = false;
        this.tmrStage = -1;
        this.musuh = 0;
        this.nama = nama;
        this.stage = stage;
        this.score = 0;
        this.listMusuh = new ArrayList<>();
        this.difficultyLevel = difficulty;
        this.y1 = -550;
        this.y2 = 0;
        try {
            jpg = ImageIO.read(new File("images/bg.gif"));
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

    public void loadGambar()
    {
        try {
            jpg = ImageIO.read(new File("images/bg.gif"));
        } catch (IOException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < listMusuh.size(); i++) {
            listMusuh.get(i).loadGambar();
            ((pesawatMusuh)listMusuh.get(i)).loadGambarPeluru();
        }
        ((pesawatPlayer)player).loadGambar();
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

    public pesawatPlayer getPlayer() {
        return (pesawatPlayer)player;
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
            if(tmrStage == -1){
                this.setStage(this.getStage() + 1);
                randomMusuh();
                System.out.println("pergantian stage. Stage saat ini : " + this.getStage());
                this.score += 100;
                getSoundEffect("sfx/sfx next stage.wav");
            }
            if(tmrStage < 0){
                tmrStage = 0;
            }
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
        int hasilRandMusuh = rnd.nextInt(batasRand - 2 + 1) + 2;
        
        //tambah jumlah musuh sesuai stage
        hasilRandMusuh += stage / 5;
        if (hasilRandMusuh >= 15) {
            hasilRandMusuh = 15;
        }
        
        System.out.println("Stage : " + stage + " - Musuh : " + hasilRandMusuh);
        for(int i =0; i < hasilRandMusuh; i++){
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
                if(((pesawatPlayer)player).shieldActive<=0 && p.ctrLedak == -1){
                    tabrak = p;
                    this.player.hp-=50;
                    this.player.ctrLedak = 0;
                    this.player.temtab = 1;
                }
                else if(p.ctrLedak == -1){
                    tabrak = p;
                    this.score+=20;
                }
                if(this.player.hp <= 0)
                    this.player.hp=0;
            }
        }
        if(tabrak != null){
            getSoundEffect("sfx/sfx_twoTone.wav");
            if(tabrak.ctrLedak < 0){
                tabrak.ctrLedak = 0;
            }
        }
    }
    
    public void ledak(){
        pesawat ledak = null;
        for(pesawat p : this.listMusuh){
            if(p.ctrLedak >= 3){
                ledak = p;
            }
        }
        if(ledak != null){
            this.listMusuh.remove(ledak);
        }
    }
    ArrayList<peluru> idxHapus;
    public void nembak(){
        idxHapus = new ArrayList<peluru>();
        for(peluru pewpew : ((pesawatPlayer)this.player).listPeluru){
            for(pesawat p : this.listMusuh){
                if(pewpew.bounds().intersects(p.bounds())){
                    p.hp = p.hp-pewpew.damage;
                    p.tmrDim = 2;
                    idxHapus.add(pewpew);
                }
            }           
        }
        hapus();
    }
    
    public void blast(){
        if(getPlayer().yBlast >= 0){
            Rectangle b = new Rectangle(-15, getPlayer().yBlast, 545, 10);
            for(pesawat p : this.listMusuh){
                if(b.intersects(p.bounds())){
                    p.hp = -1;
                }
            }
        }
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
    
    public void ketembak(){
        boolean tembak = false;
        for (pesawat p : this.listMusuh) {
            ((pesawatMusuh)p).idxHapus = new ArrayList<peluru>();
            for(peluru pewpew : ((pesawatMusuh)p).listPeluru){
                if(this.player != null){
                    if(pewpew.bounds().intersects(this.player.bounds())){
                        tembak = true;
                        if(((pesawatPlayer)player).shieldActive<=0){
                            this.player.temtab = 0;
                            this.player.ctrLedak = 0;
                            if(this.difficultyLevel == 1)
                            this.player.hp-=20;
                            else if(this.difficultyLevel == 2)
                            this.player.hp-=40;
                            else if(this.difficultyLevel == 3)
                            this.player.hp-=60;
                            if(this.player.hp <= 0)
                            this.player.hp=0;
                        }
                        ((pesawatMusuh)p).idxHapus.add(pewpew);
                    }
                    if(tembak != false){
                        //JOptionPane.showMessageDialog(null, "Game Over");
                        //this.player = null;
                        //tmr.stop();
                        getSoundEffect("sfx/sfx_twoTone.wav");
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
        g2.drawImage(jpg,0,y1,550,550,null);
        g2.drawImage(jpg,0,y2,550,550,null);
        if(this.player != null){
            this.getPlayer().draw(grphcs);    
        }
        for (pesawat p : this.getListMusuh()) {
            p.draw(grphcs);
            g2.setColor(Color.red);
            g2.fillRect(p.posX+3-this.getStage(), p.posY-6, (int)((double)p.hp/100 * 35), 5);
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
        Font f = new Font("ARIAL",Font.BOLD, 17);
        g2.setFont(f);
        g2.setColor(Color.WHITE);
        g2.drawString("Player : " + newPlayerName.namaPlayer.toUpperCase(), 5, 30);
        g2.drawString("Score  : " + this.score, 5, 60);
        g2.setColor(Color.RED);
        g2.fillRect(45, 80, ((pesawatPlayer)this.player).hp, 10);
        g2.setColor(Color.WHITE);
        g2.drawString("HP : ", 5, 90);
        g2.drawString("MP : ", 5, 120);
        g2.drawRect(45, 110, 100, 10);
        g2.setColor(Color.CYAN);
        g2.fillRect(45, 110, getPlayer().ctrBlast, 10);
        g2.setColor(Color.WHITE);
        g2.drawString("Mode : " + this.gameMode, 325, 30);
        g2.drawString("Stage : " + this.stage, 325, 60);
        if(!((pesawatPlayer)this.player).powerUP.equals("")){
            if(!((pesawatPlayer)this.player).powerUP.contains("Evolved")){
                g2.drawString(((pesawatPlayer)this.player).powerUP, 175, 435);
            }
            else{
                g2.drawString(((pesawatPlayer)this.player).powerUP, 115, 435);
            }
        }
        if(tmrStage >= 0){
            if(tmrStage >= 10){
                g2.setFont(new Font("Arial", 1, 32));
                g2.drawString("Stage " + stage, 200 - (String.valueOf(stage).length()-1)*10, 250);
                g2.setFont(new Font("Arial", 1, 16));
                g2.drawString(this.listMusuh.size() + " Enemies", 215, 275);
            }
            tmrStage++;
            if(tmrStage >= 75){
                tmrStage = -1;
            }
        }
        if(((pesawatPlayer)this.player).shieldActive >= 0){
            g2.setColor(Color.CYAN);
            g2.fillRect(15, 450,(int)(((pesawatPlayer)this.player).shieldActive*1.5), 10);
        }
    }
   
    public Boolean isMelewatiLayar(pesawat p){
        Boolean lewat = false;
        if (p.getPosX() + p.getWidth() >= 550) {
            lewat = true;
        }
        
        if (p.getPosY() + p.getHeight() >= 550) {
            lewat = true;
        }
        
        return lewat;
    }
    
     public Boolean isPeluruMelewatiLayar(peluru p){
        Boolean lewat = false;
        if (p.getPosX()  <= -10) {
            lewat = true;
        }
        
        if (p.getPosY() <=  -10) {
            lewat = true;
        }
        
        return lewat;
    }
    
    public Boolean isMati(pesawat p){
        Boolean mati = false;
        if(p.hp < 0){
            if(p.ctrLedak < 0){
                p.ctrLedak = 0;
            }
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
            if (isMati(this.listMusuh.get(i)) && this.listMusuh.get(i).ctrLedak >= 3) {
                getSoundEffect("sfx/sfx_explosion.wav");
                System.out.println(((pesawatMusuh)this.listMusuh.get(i)).jenisPowerUp);
                if(((pesawatMusuh)this.listMusuh.get(i)).jenisPowerUp != 2){
                    ((pesawatPlayer)player).powerUp((((pesawatMusuh)this.listMusuh.get(i)).jenisPowerUp));
                    sfxShieldUp(this.listMusuh.get(i));
                }
                else{
                    player = ((pesawatPlayer)player).evolve();
                }
                this.listMusuh.remove(i);
                this.score += 20;
                if(getPlayer().yBlast <= 0 || getPlayer().yBlast >= 550){
                    getPlayer().ctrBlast += 10;
                    if(getPlayer().ctrBlast >= 100){
                        getPlayer().ctrBlast = 100;
                    }  
                }
                
            }
        }
    }
    
    public void sfxShieldUp(pesawat p){
        if (p instanceof pesawatMusuh) {
            pesawatMusuh pmusuh = (pesawatMusuh)p;
            if (pmusuh.jenisPowerUp == 1) {
                getSoundEffect("sfx/sfx_shieldUp.wav");
            }
        }
    }
    
    public void getSoundEffect(String filepath){
        if (panelGame.sfxSoundOn) {
            AudioInputStream ais = null;
            try {
                ais = AudioSystem.getAudioInputStream(new File(filepath));
                Clip sfxClip = AudioSystem.getClip();
                sfxClip.open(ais);
                sfxClip.start();            
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(game.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(game.class.getName()).log(Level.SEVERE, null, ex);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(game.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    ais.close();
                } catch (IOException ex) {
                    Logger.getLogger(game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
   public void getSoundEffect(String filepath, Boolean loopContinously){
        AudioInputStream ais = null;
        try {
            ais = AudioSystem.getAudioInputStream(new File(filepath));
            Clip sfxClip = AudioSystem.getClip();
            if (loopContinously) {
                sfxClip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            sfxClip.open(ais);
            sfxClip.start();            
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(game.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ais.close();
            } catch (IOException ex) {
                Logger.getLogger(game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

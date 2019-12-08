package Proyek;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public abstract class pesawatPlayer extends pesawat{
    /*
    1 = single shooter
    2 = double shooter
    3 = triple shooter
    */
    protected int ctrTembak;
    protected int shieldActive;
    protected ArrayList<peluru> listPeluru;
    protected int damageNabrak;
    transient BufferedImage gbrShield;
    transient BufferedImage gbrBlast;
    protected int ctrBlast;
    protected int yBlast;
    protected int ctrPowerUp;
    protected String powerUP;
    
    protected Boolean shieldOn;

    public pesawatPlayer(int hp, int x, int y) {
        super(hp, x, y);
        this.yBlast = 570;
        this.ctrBlast = 0;
        this.ctrTembak = -1;
        this.damagePesawat = 20;
        this.damageNabrak = 50;
        this.mX = 5;
        //awal game dimulai pasti dia ga punya shield
        this.shieldActive = -1;
        this.shieldOn = false;
        this.listPeluru = new ArrayList<>();
        powerUP = "";
        try {
            this.gbrShield = ImageIO.read(new File("images/shield.png"));
            gbrLedak[0] = ImageIO.read(new File("images/expm1.png"));
            gbrLedak[1] = ImageIO.read(new File("images/expm2.png"));
            gbrLedak[2] = ImageIO.read(new File("images/expm3.png"));
            gbrLedak[3] = ImageIO.read(new File("images/expm4.png"));
            gbrBlast = ImageIO.read(new File("images/blast.png"));
        } catch (IOException ex) {
            Logger.getLogger(pesawatMusuh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public abstract pesawatPlayer evolve();
    
    public void loadGambar()
    {
        try {
            this.gbrShield = ImageIO.read(new File("images/shield.png"));
            loadGambarPeluru();
        } catch (IOException ex) {
            Logger.getLogger(pesawatMusuh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void loadGambarPeluru()
    {
        for (int i = 0; i < listPeluru.size(); i++) {
            listPeluru.get(i).loadGambar();
        }
    }
    @Override
    public abstract void shoot();

    public int getShieldActive() {
        return shieldActive;
    }

    public Boolean getShieldOn() {
        return shieldOn;
    }

    public void setShieldOn(Boolean shieldOn) {
        this.shieldOn = shieldOn;
    }
    
    public void setShieldActive(int shieldActive) {
        this.shieldActive = shieldActive;
    }

    public ArrayList<peluru> getListPeluru() {
        return listPeluru;
    }

    public void setListPeluru(ArrayList<peluru> listPeluru) {
        this.listPeluru = listPeluru;
    }

    public int getDamageNabrak() {
        return damageNabrak;
    }

    public void setDamageNabrak(int damageNabrak) {
        this.damageNabrak = damageNabrak;
    }
    
    public void setDamageNabrak(int damageNabrak, int extraLife) {
        this.damageNabrak = damageNabrak + extraLife;
    }
    
    public void powerUp(int jenisPowerUp){
        if (jenisPowerUp >= 0 && jenisPowerUp <= 3 && jenisPowerUp != 2) {
            getSoundEffect("sfx/sfx_power ups.wav");
        }
        if(jenisPowerUp == 0){
            hp += 20;
            powerUP = "You got HP + 20.";
            ctrPowerUp = 25;
        }
        else if(jenisPowerUp == 1){
            shieldOn = true;
            shieldActive = 300;
            powerUP = "You got 15s Shield.";
            ctrPowerUp = 25;
        }
        else if(jenisPowerUp == 3){
            damagePesawat += 20;
            powerUP = "You got Damage + 20.";
            ctrPowerUp = 25;
        }
    }
    
    public void loadgambarledakan()
    {
        try {
            this.gbrLedak = new BufferedImage[4];
            gbrLedak[0] = ImageIO.read(new File("images/expm1.png"));
            gbrLedak[1] = ImageIO.read(new File("images/expm2.png"));
            gbrLedak[2] = ImageIO.read(new File("images/expm3.png"));
            gbrLedak[3] = ImageIO.read(new File("images/expm4.png"));
        } catch (IOException ex) {
            Logger.getLogger(pesawatPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        if(shieldActive > 0){
            g2.drawImage(gbrShield, posX-12, posY-12, width+25, height+25, null);
            shieldActive--;
        }
        else{
            if (shieldOn) {
                getSoundEffect("sfx/sfx_shieldDown.wav");
                shieldOn = false;
            }
        }
        
        if (gbrLedak == null){
                loadgambarledakan();
            }
        g2.drawImage(gbrPesawat, posX, posY, width, height, null);
        if(ctrLedak >= 0 && ctrLedak <= 7 && temtab == 0){
            int ukuran = (int)(Math.random()*(width-7))+10;
            int x = (int)(Math.random()*10)+posX;
            int y = (int)(Math.random()*10)+posY;
            
            g2.drawImage(gbrLedak[ctrLedak / 2], x, y, ukuran, ukuran, null);
            ctrLedak++;
        }
        else if(ctrLedak >= 0 && ctrLedak <= 7 && temtab == 1){
            g2.drawImage(gbrLedak[ctrLedak / 2], posX-3, posY-3, width+7, height+7, null);
            ctrLedak++;
        }
        if(yBlast <= 550){
            g2.drawImage(gbrBlast, -15, yBlast, 545, 25, null);
            yBlast-=10;
        }
    }
    
    public void getSoundEffect(String filepath){
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

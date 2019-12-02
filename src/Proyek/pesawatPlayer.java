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

public abstract class pesawatPlayer extends pesawat{
    /*
    1 = single shooter
    2 = double shooter
    3 = triple shooter
    */
    protected int shieldActive;
    protected ArrayList<peluru> listPeluru;
    protected int damageNabrak;
    transient BufferedImage gbrShield;
    protected int ctrPowerUp;
    protected String powerUP;

    public pesawatPlayer(int hp, int x, int y) {
        super(hp, x, y);
        this.damagePesawat = 20;
        this.damageNabrak = 50;
        this.mX = 5;
        //awal game dimulai pasti dia ga punya shield
        this.shieldActive = -1;
        this.listPeluru = new ArrayList<>();
        powerUP = "";
        try {
            this.gbrShield = ImageIO.read(new File("images/shield.png"));
            gbrLedak[0] = ImageIO.read(new File("images/expm1.png"));
            gbrLedak[1] = ImageIO.read(new File("images/expm2.png"));
            gbrLedak[2] = ImageIO.read(new File("images/expm3.png"));
            gbrLedak[3] = ImageIO.read(new File("images/expm4.png"));
        } catch (IOException ex) {
            Logger.getLogger(pesawatMusuh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public abstract void shoot();

    public int getShieldActive() {
        return shieldActive;
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
        if(jenisPowerUp == 0){
            hp += 20;
            powerUP = "You got HP + 20.";
            ctrPowerUp = 25;
        }
        else if(jenisPowerUp == 1){
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
    
    @Override
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        if(shieldActive > 0){
            g2.drawImage(gbrShield, posX-12, posY-12, width+25, height+25, null);
            shieldActive--;
        }
        g2.drawImage(gbrPesawat, posX, posY, width, height, null);
        if(ctrLedak >= 0 && ctrLedak <= 7){
            g2.drawImage(gbrLedak[ctrLedak / 2], posX+3, posY+3, width-7, height-7, null);
            ctrLedak++;
        }
    }
    
    public abstract pesawatPlayer evolve();
    
}

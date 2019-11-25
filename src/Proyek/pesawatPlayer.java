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

    public pesawatPlayer(int hp, int x, int y) {
        super(hp, x, y);
        this.damagePesawat = 20;
        this.damageNabrak = 50;
        this.mX = 5;
        //awal game dimulai pasti dia ga punya shield
        this.shieldActive = -1;
        this.listPeluru = new ArrayList<>();
        try {
            this.gbrShield = ImageIO.read(new File("images/shield.png"));
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
        }
        else if(jenisPowerUp == 1){
            shieldActive = 300;
        }
        else if(jenisPowerUp == 3){
            damagePesawat += 20;
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
        
    }
    
    public abstract pesawatPlayer evolve();
    
}

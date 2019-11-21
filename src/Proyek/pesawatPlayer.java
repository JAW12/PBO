package Proyek;

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
    protected int shooterCount;
    protected boolean shieldActive;
    protected ArrayList<peluru> listPeluru;
    
    
    public pesawatPlayer(int hp, int fireRate) {
        super(hp, 200, 425);
        this.damagePesawat = 20;
        this.mX = 5;
        //awal game dimulai pasti dia cuma bisa nembak 1x dan ga punya shield
        this.shooterCount = 1; 
        this.shieldActive = false;
        this.listPeluru = new ArrayList<>();
        try {
            this.gbrPesawat = ImageIO.read(new File("images/playerShip1_blue.png"));
        } catch (IOException ex) {
            Logger.getLogger(pesawatMusuh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getShooterCount() {
        return shooterCount;
    }

    public void setShooterCount(int shooterCount) {
        this.shooterCount = shooterCount;
    }
    
    @Override
    public void shoot(){
        for (int i = 0; i < shooterCount; i++) {
            listPeluru.add(new peluru(damagePesawat, posX + (width / 2) - 3, posY-2, 7, 7, 7, 10));
        }
    }

    public boolean isShieldActive() {
        return shieldActive;
    }

    public void setShieldActive(boolean shieldActive) {
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
        
    }
    
    public abstract pesawatPlayer evolve();
    
}

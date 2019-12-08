package Proyek;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class pesawatDoubleShooter extends pesawatPlayer {

    public pesawatDoubleShooter(int hp, int x, int y, int ctrPowerUp, String powerUP, ArrayList<peluru> peluru, int shield, int ctrBlast, int yBlast) {
        super(hp, x, y);
        this.ctrBlast = ctrBlast;
        this.yBlast = yBlast;
        this.listPeluru = peluru;
        this.shieldActive = shield;
        this.ctrPowerUp = ctrPowerUp;
        this.powerUP = powerUP;
        try {
            this.gbrPesawat = ImageIO.read(new File("images/p2.png"));
            this.gbrBlast = ImageIO.read(new File("images/blast.png"));
        } catch (IOException ex) {
            Logger.getLogger(pesawatMusuh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void loadGambar(){
         try {
            this.gbrPesawat = ImageIO.read(new File("images/p2.png"));
            this.gbrShield = ImageIO.read(new File("images/shield.png"));
            this.gbrBlast = ImageIO.read(new File("images/blast.png"));
            super.loadGambar();                      
        } catch (IOException ex) {
            Logger.getLogger(pesawatMusuh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public pesawatPlayer evolve() {
        powerUP = "You got Evolved to Triple Shooter";
        ctrPowerUp = 25;
        return new pesawatTripleShooter(hp, posX, posY, ctrPowerUp, powerUP, listPeluru, shieldActive, ctrBlast, yBlast);
    }
    
    @Override
    public void shoot() {
        listPeluru.add(new peluru(damagePesawat, posX, posY+10, 7, 7, 7, 15, 1));
        listPeluru.add(new peluru(damagePesawat, posX + width - 7, posY+10, 7, 7, 7, 15, 1));
    }    

}

package Proyek;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class pesawatTripleShooter extends pesawatPlayer{
    
    public pesawatTripleShooter(int hp, int x, int y, int ctrPowerUp, String powerUP, ArrayList<peluru> peluru, int shield) {
        super(hp, x, y);
        this.listPeluru = peluru;
        this.shieldActive = shield;
        this.ctrPowerUp = ctrPowerUp;
        this.powerUP = powerUP;
        try {
            this.gbrPesawat = ImageIO.read(new File("images/p3.png"));
        } catch (IOException ex) {
            Logger.getLogger(pesawatMusuh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public pesawatPlayer evolve() {
        return new pesawatTripleShooter(hp, posX, posY, ctrPowerUp, powerUP, listPeluru, shieldActive);
    }
    
    @Override
    public void shoot() {
        listPeluru.add(new peluru(damagePesawat, posX, posY+10, 7, 8, 7, 15, 1));
        listPeluru.add(new peluru(damagePesawat, posX + (width / 2) - 3, posY-2, 7, 8, 7, 15, 1));
        listPeluru.add(new peluru(damagePesawat, posX + width - 7, posY+10, 7, 8, 7, 15, 1));
    }    
    
}

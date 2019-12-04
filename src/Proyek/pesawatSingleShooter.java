package Proyek;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class pesawatSingleShooter extends pesawatPlayer{
    
    public pesawatSingleShooter(int hp, int x, int y, int ctrPowerUp, String powerUP) {
        super(hp, x, y);
        this.ctrPowerUp = ctrPowerUp;
        this.powerUP = powerUP;
        try {
            this.gbrPesawat = ImageIO.read(new File("images/p1.png"));
        } catch (IOException ex) {
            Logger.getLogger(pesawatMusuh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void loadGambar()
    {
        try {
            this.gbrPesawat = ImageIO.read(new File("images/p1.png"));
            super.loadGambar();
        } catch (IOException ex) {
            Logger.getLogger(pesawatMusuh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public pesawatPlayer evolve() {
        powerUP = "You got Evolved to Double Shooter";
        ctrPowerUp = 25;
        return new pesawatDoubleShooter(hp, posX, posY, ctrPowerUp, powerUP, listPeluru, shieldActive);
    }

    @Override
    public void shoot() {
        listPeluru.add(new peluru(damagePesawat, posX + (width / 2) - 3, posY-2, 7, 6, 7, 15, 1));
    }    
}

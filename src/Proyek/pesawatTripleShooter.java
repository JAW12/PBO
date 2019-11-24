/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyek;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Winda AU
 */
public class pesawatTripleShooter extends pesawatPlayer{
    
    public pesawatTripleShooter(int hp, int x, int y) {
        super(hp, x, y);
        try {
            this.gbrPesawat = ImageIO.read(new File("images/p3.png"));
        } catch (IOException ex) {
            Logger.getLogger(pesawatMusuh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public pesawatPlayer evolve() {
        return new pesawatTripleShooter(hp, posX, posY);
    }
    
      @Override
    public void shoot() {
        listPeluru.add(new peluru(damagePesawat, posX, posY+10, 7, 7, 7, 10));
        listPeluru.add(new peluru(damagePesawat, posX + (width / 2) - 3, posY-2, 7, 7, 7, 10));
        listPeluru.add(new peluru(damagePesawat, posX + width - 5, posY+10, 7, 7, 7, 10));
    }    
    
}

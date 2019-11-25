package Proyek;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class pesawatSingleShooter extends pesawatPlayer{
    
    public pesawatSingleShooter(int hp, int x, int y) {
        super(hp, x, y);
        try {
            this.gbrPesawat = ImageIO.read(new File("images/p1.png"));
        } catch (IOException ex) {
            Logger.getLogger(pesawatMusuh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public pesawatPlayer evolve() {
        return new pesawatDoubleShooter(hp, posX, posY);
    }

    @Override
    public void shoot() {
        listPeluru.add(new peluru(damagePesawat, posX + (width / 2) - 3, posY-2, 7, 6, 7, 15, 1));
    }    
}

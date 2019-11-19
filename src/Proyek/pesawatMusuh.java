package Proyek;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class pesawatMusuh extends pesawat{
    
    protected ArrayList<peluru> listPeluru;
    int tmrTembak;
    public pesawatMusuh(int hp) {
        super(hp, 0, 5);
        try {
            this.gbrPesawat = ImageIO.read(new File("images/enemy.png"));
            this.listPeluru = new ArrayList<>();
        } catch (IOException ex) {
            Logger.getLogger(pesawatMusuh.class.getName()).log(Level.SEVERE, null, ex);
        }
        Random rnd = new Random();
        this.posX = rnd.nextInt(400 - 40 + 1) + 40;
        this.posY = rnd.nextInt(500 + 1) - 500;
    }
    
    public void shoot(){
        listPeluru.add(new peluru(1, posX + (width / 2) - 3, posY+height, 7, -9, 1, 7, 10));    
    }
}

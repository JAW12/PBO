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
    protected int tmrTembak;
    protected int jenisPesawat, jenisPowerUp;
    /*
    jenisPowerUp (tipe data : integer)
    	0 = extra life (HP += 20)
    	1 = shield
    	2 = evolve
    	3 = damage (damagePesawat += 20)
    	4 – 15 = no power up

    jenisPesawat (tipe data : integer)
    	1 = gerak lurus biasa
    	2 = gerak zig zag (mulai dari kiri, apabila kena ujung layar mantul ke arah berlawanan)
    	3 = gerak zig zag (mulai dari kanan, apabila kena ujung layar mantul ke arah berlawanan)
    */
    
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
    
    @Override
    public void shoot(){
        listPeluru.add(new peluru(1, posX + (width / 2) - 3, posY+height, 7, -9, 7, 10));    
    }
}

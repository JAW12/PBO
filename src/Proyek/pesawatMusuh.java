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
    protected ArrayList<peluru> idxHapus;
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
    
    keterangan difficulty :
    1 = easy
    2 = medium
    3 = hard    
    */
    public pesawatMusuh(int hp, int difficulty, int jenisPesawat) {
        super(hp, 0, 5);
        if (difficulty >= 1 && difficulty <= 3) {
            this.damagePesawat *= difficulty;
        }
        this.mX = 2;
        setRandomPosX();
        setRandomPosY();
        this.jenisPesawat = jenisPesawat;
        this.jenisPowerUp = (int)(Math.random()* 15);
        try {
            switch (this.jenisPesawat) {
                case 1:
                    this.gbrPesawat = ImageIO.read(new File("images/enemy.png"));
                    break;
                case 2:
                    this.posX = 5;
                    this.gbrPesawat = ImageIO.read(new File("images/enemyBlue1.png"));
                    break;
                case 3:
                    this.posX = 480 - width;
                    this.gbrPesawat = ImageIO.read(new File("images/enemyRed1.png"));
                    break;
                default:
                    break;
            }
            
            
            this.listPeluru = new ArrayList<>();
            this.idxHapus = new ArrayList<>();
        } catch (IOException ex) {
            Logger.getLogger(pesawatMusuh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void shoot(){
        listPeluru.add(new peluru(damagePesawat, posX + (width / 2) - 3, posY+height, 7, -7, 7, 15, 2));    
    }
    
    @Override
    public void moveMusuh() {
        super.moveMusuh();
        if (this.jenisPesawat == 2) { //diagonal dari kiri ke kanan
            if (this.posX + this.width + this.mX <= 500) {
                this.posX += this.mX;
            }
            else{
                this.jenisPesawat = 3;
            }
        }
        else if (this.jenisPesawat == 3) { //diagonal dari kanan ke kiri
            if (this.posX - this.mX >= 0) {
                this.posX -= mX;
            }
            else{
                this.jenisPesawat = 2;
            }
        }
    }
    
    public void setRandomPosX(){
        Random rnd = new Random();
        this.posX = rnd.nextInt(400 - 40 + 1) + 40;
    }
    
    public void setRandomPosY(){
        Random rnd = new Random();
        this.posY = rnd.nextInt(500 + 1) - 500;
    }
    
}

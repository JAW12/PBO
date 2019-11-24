package Proyek;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class peluru implements Serializable{
    protected int damage, posX, posY, mX, mY, width, height;
    transient BufferedImage gbrTembak;

    public peluru(int damage, int posX, int posY, int mX, int mY, int width, int height) {
        try {
            this.gbrTembak = ImageIO.read(new File("images/laser.png"));
        } catch (IOException ex) {
            Logger.getLogger(pesawatMusuh.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.damage = damage;
        this.posX = posX;
        this.posY = posY;
        this.mX = mX;
        this.mY = mY;
        this.width = width;
        this.height = height;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getmX() {
        return mX;
    }

    public void setmX(int mX) {
        this.mX = mX;
    }

    public int getmY() {
        return mY;
    }

    public void setmY(int mY) {
        this.mY = mY;
    }
    
    public Rectangle bounds(){
        return new Rectangle(posX, posY, width, height);
    }
    
    public void move(){
        this.posY -= mY;
    }
    
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(gbrTembak, posX, posY, width,height, null);
    }
    
}

package Proyek;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class pesawat {
    protected int hp, posX, posY, mX, mY, width, height;
    BufferedImage gbrPesawat;
    protected int damagePesawat;

    public pesawat(int hp, int posX, int posY) {
        this.hp = hp;
        this.posX = posX;
        this.posY = posY;
        this.mX = 5;
        this.mY = 5;
        this.width = 40;
        this.height = 40;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
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
    
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(gbrPesawat, posX, posY, width, height, null);
    }
    
    public void move(String keyMove){
        if (keyMove.toUpperCase().equals("A")) {
            if (this.posX - mX > 0) {
                this.posX -= mX;
            }
        }
        else if (keyMove.toUpperCase().equals("D")) {
            if (this.posX + width + mX < 500) {
                this.posX += mX;
            }
        }
        else if (keyMove.toUpperCase().equals("")) {
            //untuk gerak musuh yang semakin lama semakin ke arah player
            if (this.posY + height + mY < 500) {
                
            }
            this.posY += mY;
        }
    }
    
    public Rectangle bounds(){
        return new Rectangle(posX, posY, width, height);
    }
    
    public abstract void shoot();
}

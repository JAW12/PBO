/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyek;

/**
 *
 * @author Winda AU
 */
public class pesawatDoubleShooter extends pesawatPlayer {

    public pesawatDoubleShooter(int hp, int fireRate) {
        super(hp, fireRate);
        this.shooterCount = 3;
    }

    @Override
    public pesawatPlayer evolve() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

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
public class pesawatTripleShooter extends pesawatPlayer{
    
    public pesawatTripleShooter(int hp, int fireRate) {
        super(hp, fireRate);
        this.shooterCount = 1;
    }
    
}
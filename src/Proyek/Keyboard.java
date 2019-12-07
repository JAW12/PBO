package Proyek;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard {

    private boolean[] pressed = new boolean[128];

    public boolean isPressed(int key) {
        return pressed[key];
    }
    
    public void reset(){
        for(boolean b : pressed){
            b = false;
        }
    }
 
   public KeyListener listener = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            if (code < pressed.length) {
                pressed[code] = true;
            }
        }   

        @Override
        public void keyReleased(KeyEvent e) {
            int code = e.getKeyCode();
            if (code < pressed.length) {
                pressed[code] = false;
            }
        }
        
        
    };
}
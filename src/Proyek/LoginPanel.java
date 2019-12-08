package Proyek;

import static Proyek.panelGame.namaHiScore;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class LoginPanel extends javax.swing.JPanel {
    BufferedImage jpg;
    //Buffered snd;
    /**
     * Creates new form LoginPanel
     */
    public LoginPanel() {
        initComponents();
        bacascore();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //paintBGbyGif();
        paintBGManual(g);
    }
    
    public void paintBGbyGif(){
        JLabel gifBackground = new JLabel(new ImageIcon("images/bgSpace.gif"));
        gifBackground.setBounds(10, 10, 600,600);
        gifBackground.setVisible(true);
        gifBackground.setFocusable(false);
        gifBackground.setEnabled(false);
        add(gifBackground);
    }
    public void bacascore()
    {
        String name="";
         try  {  
            FileReader r=new FileReader("hs.txt");    
            BufferedReader br=new BufferedReader(r);           
            name=br.readLine();
            br.close();    
            r.close();
            String[] baca = name.split("-");
            panelGame.namaHiScore = baca[0];
            panelGame.scoreHiScore= Integer.parseInt(baca[1]);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
    public void paintBGManual(Graphics g){
        try {
            jpg = ImageIO.read(new File("images/bg2.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(jpg,0,0,500,500,null);
        try {
            jpg = ImageIO.read(new File("images/p1.png"));
        } catch (IOException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        g2 = (Graphics2D)g;
        g2.drawImage(jpg,180,410,40,40,null);
        try {
            jpg = ImageIO.read(new File("images/p1.png"));
        } catch (IOException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        g2 = (Graphics2D)g;
        g2.drawImage(jpg,180,410,40,40,null);
        try {
            jpg = ImageIO.read(new File("images/BigMeteor.png"));
        } catch (IOException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        g2 = (Graphics2D)g;
        g2.drawImage(jpg,80,210,60,60,null);
        try {
            jpg = ImageIO.read(new File("images/MediumMeteor.png"));
        } catch (IOException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        g2 = (Graphics2D)g;
        g2.drawImage(jpg,300,210,40,40,null);
        try {
            jpg = ImageIO.read(new File("images/SmallMeteor.png"));
        } catch (IOException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        g2 = (Graphics2D)g;
        g2.drawImage(jpg,280,280,20,20,null);
        try {
            jpg = ImageIO.read(new File("images/MediumMeteor.png"));
        } catch (IOException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        g2 = (Graphics2D)g;
        g2.drawImage(jpg,130,275,40,40,null);
        try {
            jpg = ImageIO.read(new File("images/enemy.png"));
        } catch (IOException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        g2 = (Graphics2D)g;
        g2.drawImage(jpg,0,30,40,40,null);
        try {
            jpg = ImageIO.read(new File("images/enemy.png"));
        } catch (IOException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        g2 = (Graphics2D)g;
        g2.drawImage(jpg,45,10,40,40,null);
        try {
            jpg = ImageIO.read(new File("images/enemy.png"));
        } catch (IOException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        g2 = (Graphics2D)g;
        g2.drawImage(jpg,380,30,40,40,null);
        try {
            jpg = ImageIO.read(new File("images/enemy.png"));
        } catch (IOException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        g2 = (Graphics2D)g;
        g2.drawImage(jpg,335,10,40,40,null);
        try {
            jpg = ImageIO.read(new File("images/logo.png"));
        } catch (IOException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        g2 = (Graphics2D)g;
        g2.drawImage(jpg,10,-20,400,300,null);
        g2.setFont(new Font("Arial", 1, 18));
        g2.setColor(Color.white);
        g2.drawString("Highscore : ", 160, 225);
        g2.drawString(panelGame.namaHiScore + " - " +panelGame.scoreHiScore,170-panelGame.namaHiScore.length()-String.valueOf(panelGame.scoreHiScore).length(),250);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 411, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 427, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

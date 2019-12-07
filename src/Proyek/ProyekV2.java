package Proyek;

import java.awt.Desktop;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javax.swing.Action;
import javax.swing.Timer;

public class ProyekV2 {

    static Timer t;
    static int ctrWaktu;
    static Boolean introDone;
    public static void main(String[] args) throws IOException {
//        formIntro fintro = new formIntro();
//        fintro.setLocationRelativeTo(null);
//        fintro.setState(Frame.ICONIFIED);
//        fintro.setVisible(true);

        Application.launch(JavaFXVideoIntro.class);
    }
    
}

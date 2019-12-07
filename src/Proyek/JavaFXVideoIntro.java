package Proyek;

import java.awt.event.ActionListener;
import java.io.File;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.Timer;

public class JavaFXVideoIntro extends Application {
    private int CANVAS_HEIGHT = 600; // height of the player
    private int CANVAS_WIDTH = 600;  // width of the player
    private MediaPlayer player; // the video player which will be used to play video
    private Timer t; //check if the video has been skipped
    
    @Override
    public void start(Stage primaryStage) {
        uiSetup(primaryStage);
        primaryStage.show();
        t = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent ae) {
                checkWindowClose(primaryStage);
            }
        });
        t.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void uiSetup(final Stage stage) {
        StackPane root = new StackPane();
        Scene theScene = new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT);
        theScene.setCursor(Cursor.NONE);
        
        /*
        //create button pause/play
        final HBox buttonContainer = new HBox(0);
        Button btnPlay = new Button("Play");
        Button btnPause = new Button("Pause");
        buttonContainer.setAlignment(Pos.TOP_RIGHT);
        Insets buttonContainerPadding = new Insets(20, 20, 50, 20);
        buttonContainer.setPadding(buttonContainerPadding);
        buttonContainer.getChildren().addAll(btnPlay, btnPause);
        
        buttonContainer.setVisible(false);
        
        //create procedure for buttons
        btnPause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player.pause();
            }
        });
        
        btnPlay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player.play();
            }
        });
        */
        
        MediaView mediaView; // create a media view
        mediaView = new MediaView(player);
        
        loadMediaPlayer(mediaView, stage);
        player.setAutoPlay(true);
        
        //root.getChildren().addAll(mediaView, buttonContainer); //add the media view and all buttons to the stackpane
        
        root.getChildren().addAll(mediaView); //add the media view to the stackpane
        setUpStage(stage, theScene);
        setSceneKeypress(theScene, stage);
    }
    
    private void setUpStage(Stage stage, Scene theScene){
        stage.setTitle("Welcome to Space Shooter!");
        stage.setFullScreenExitHint("(SPACE) = Skip Video \n(ESC) = Exit Full Screen     (F) = Enter Full Screen");
        stage.setFullScreen(true);
        stage.setAlwaysOnTop(false);
        stage.centerOnScreen();
        stage.setResizable(false);
        //stage.setWidth(500);
        //stage.setHeight(500);
        stage.setScene(theScene);
    }
    
    private void checkWindowClose(Stage stage){
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                skipVideo(event);
            }
        });
        
        player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                skipVideo();
            }
        });
    }
    
    private void skipVideo(){
        if (player != null) {
            player.pause();
        }
        showMainMenu();
    }
    
    private void skipVideo(WindowEvent event){
        if (player != null) {
            player.pause();
        }
//        Alert alert = new Alert(AlertType.CONFIRMATION, "Do you want to skip the video?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
//        alert.showAndWait();
//        if (alert.getResult() == ButtonType.YES) {
//            showMainMenu();
//        }
//        else{
//            event.consume(); //membuat stage dan window gajadi di tutup
//            player.play();
//        }
        showMainMenu();
    }
    
    private void loadMediaPlayer(MediaView mediaView, Stage stage){
        try{
            File file;
            file = new File("videos/intro.mp4");
            String url = file.toURI().toURL().toString();

            if(player != null){
                player.stop();
            }
            
            Media mediaIntro;
            mediaIntro = new Media(url);
            
            player = new MediaPlayer(mediaIntro);
            
            //resize ukuran video disesuaikan dengan ukuran media view
            DoubleProperty mvw = mediaView.fitWidthProperty();
            DoubleProperty mvh = mediaView.fitHeightProperty();
            mvw.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
            mvh.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
            
            mediaView.setPreserveRatio(true);
            mediaView.setMediaPlayer(player);   
            
        } catch(Exception e) {
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Loading Error");
            alert.setHeaderText("Error happen when loading");
            alert.setContentText("Cannot load the video");
            alert.showAndWait().ifPresent(null
            );
        }
    }
    int ctr = 0;
    private void showMainMenu(){
        //menutup video
        Platform.exit(); 
        
        //munculin main menu
        LoginFrame login = new LoginFrame();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
    }
    
    private void setSceneKeypress(Scene scene, Stage stage){
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE && ctr < 1) {
                    skipVideo();
                    ctr++;
                }
                else if (event.getCode() == KeyCode.F) {
                    stage.setFullScreen(true);
                }
            }
        });
    }
    
}

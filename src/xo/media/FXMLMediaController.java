/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.media;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Marina
 */
public class FXMLMediaController implements Initializable {

    @FXML
    private Text congratulationPlayerText;
    @FXML
    private Button backButton;
    @FXML
    private Button playAgainButton;
    @FXML
    private MediaView mediaView;
    
    private MediaPlayer mediaPlayer;
    private File file;
    private  Media media;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        media = new Media(xo.Xo.class.getResource("video/cong.mp4").toExternalForm());
        mediaPlayer= new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }    

    @FXML
    private void backButtonClicked(ActionEvent event) {
    }

    @FXML
    private void playAgainButtonClicked(ActionEvent event) {
    }
    
}
    
   

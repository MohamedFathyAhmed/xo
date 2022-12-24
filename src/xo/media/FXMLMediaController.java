/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.media;

import data.CurrentGameData;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import xo.board.FXMLBoardController;
import xo.board.FXMLBoardOfflineMultiPLayerController;
import xo.utlis.NavigationDestination;
import xo.utlis.TicTacToeNavigator;
import static xo.utlis.TicTacToeNavigator.BOARD_OFFLINE_MULTIPLAYERS;

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
    private Media media;
    CurrentGameData currentGameData;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        media = new Media(xo.Xo.class.getResource("video/cong.mp4").toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
        congratulationPlayerText.setText("Congratulation " + CurrentGameData.getInstance().getWinnerPlayer());
    }

    @FXML
    private void backButtonClicked(ActionEvent event) throws IOException {
        TicTacToeNavigator.navigateTo(event, TicTacToeNavigator.MODES);
    }

    @FXML
    private void playAgainButtonClicked(ActionEvent event) throws IOException {
        if (TicTacToeNavigator.pop() == TicTacToeNavigator.BOARD_OFFLINE_MULTIPLAYERS) {
            TicTacToeNavigator.navigateTo(event,
                    new FXMLBoardOfflineMultiPLayerController((Stage) ((Node) event.getSource()).getScene().getWindow()),
                    TicTacToeNavigator.BOARD_OFFLINE_MULTIPLAYERS);
            
            
        } else {
            TicTacToeNavigator.navigateTo(event,
                    new FXMLBoardOfflineMultiPLayerController((Stage) ((Node) event.getSource()).getScene().getWindow()),
                    TicTacToeNavigator.BOARD_PLAYER_VS_EASY_AI);
        }
    }

}

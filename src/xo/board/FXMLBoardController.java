/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.board;

import data.CurrentGameData;
import data.GameLevel;
import data.GameMode;
import static data.GameMode.SINGLE;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 *
 * @author mohamed
 */
public class FXMLBoardController implements Initializable {

    @FXML
    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8;

    @FXML
    private Text mainTimerText, player1TimerText, player2TimerText , 
            currentScorePlayer1,currentScorePlayer2,oldScorePlayer1,oldScorePlayer2,player1NameText,player2NameText;//Marina
    @FXML
    private ImageView oLightOfImageView ,xLightOfImageView,xLightOnImageView,oLightOnImageView;

    //
    private final Button[] boardButtons = {button0, button1, button2, button3, button4, button5, button6, button7, button8};
    
    private boolean firstPlayerTurn=true;
    
    //timers
    private BoardTimer mainTimer;
    private BoardTimer player1Timer;
    private BoardTimer player2Timer;
    
    CurrentGameData currentGameData; //Marina
    

    @FXML
    private void recordButtonClicked(ActionEvent event) {
        

    }

    @FXML
    private void exitButtonClicked(MouseEvent event) {

    }

    @FXML
    private void boardButtonClicked(ActionEvent event) {
        firstPlayerTurn=!firstPlayerTurn;
        
        
        
        //timer
        resetPLayersTimerUi();
        stopCurrentPlayerTimer();
        startNextPlayerTimer();
        resetCurrentPlayerTimer();
    }

    @FXML
    private void leaveButtonClicked(ActionEvent event) {

        //timer
        resetPLayersTimerUi();
        stopCurrentPlayerTimer();
        startNextPlayerTimer();
        resetCurrentPlayerTimer();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Marina Reciving  && Passing data
        currentGameData=CurrentGameData.getInstance();
        //Passing data
        currentGameData.setGameMode(GameMode.SINGLE);
        currentGameData.setGameLevel(GameLevel.EASY);
        
        player1NameText.setText(currentGameData.getPlayer1());
        player2NameText.setText(currentGameData.getPlayer2());
        
        oldScorePlayer1.setTranslateX(currentGameData.getPlayer1OverAllScore());
        oldScorePlayer2.setTranslateX(currentGameData.getPlayer1OverAllScore());
        
        currentScorePlayer1.setTranslateX(currentGameData.getPlayer1CurrentScore());
        currentScorePlayer2.setTranslateX(currentGameData.getPlayer2CurrentScore());
        
        
        
        
        
   
        
        
        
        
        
        
        
        
        ////////////////////////Marina


        //initlize timers
        mainTimer = new BoardTimerImpl((text) -> mainTimerText.setText(text));
        player1Timer = new BoardTimerImpl((text) -> player1TimerText.setText(text));
        player2Timer = new BoardTimerImpl((text) -> player2TimerText.setText(text));
        mainTimer.start();
        startNextPlayerTimer();
    }

    private void stopCurrentPlayerTimer() {
        if (firstPlayerTurn) {
            player2Timer.pause();
        } else {
            player1Timer.pause();
        }
    }

    private void resetCurrentPlayerTimer() {
        if (firstPlayerTurn) {
            player1Timer.resetTimer();
        } else {
            player2Timer.resetTimer();
        }
    }

    private void startNextPlayerTimer() {
        if (firstPlayerTurn) {
            player1Timer.start();
        } else {
            player2Timer.start();
        }
    }

    private void resetPLayersTimerUi() {
        player1TimerText.setText("0:00");
        player2TimerText.setText("0:00");
    }
}

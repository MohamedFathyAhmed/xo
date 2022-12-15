/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.board;

import data.CurrentGameData;
import data.GameLevel;
import data.GameMode;
import data.GameShapes;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import xo.board.game.GameState;
import xo.board.game.GameHandler;
import xo.utlis.CircularArray;

/**
 *
 * @author mohamed
 */
public abstract class FXMLBoardController implements Initializable {

    @FXML
    GridPane boardGridPane;

    @FXML
    protected Button button0, button1, button2, button3, button4, button5, button6, button7, button8;

    @FXML
    protected Text mainTimerText,
            currentScorePlayer1, currentScorePlayer2, oldScorePlayer1, oldScorePlayer2, player1NameText, player2NameText;//Marina
    @FXML
    protected ImageView oLightOfImageView, xLightOfImageView, xLightOnImageView, oLightOnImageView;

    @FXML
    protected Text recordingText;

    @FXML
    protected ImageView recordingImageView;

    //
    protected Button[] boardButtons = null;

    protected boolean firstPlayerTurn = true;

    //timer
    protected Timer mainTimer;
    protected int timer;

    protected CircularArray<String> boardHoverStyleClasses;
    protected CircularArray<String> boardStyleClasses;
    protected CircularArray<GameShapes> gameShapes;

    //recording
    private boolean isRecording;
    private FadeTransition recordingTextFadeAffect;
    private FadeTransition recordingImageViewFadeAffect;

    protected CurrentGameData currentGameData; //Marina

    protected Stage stage;

    public FXMLBoardController(Stage stage) {
        stage.setOnCloseRequest((windowEvent) -> mainTimer.cancel());
        mainTimer = new Timer();
        this.stage = stage;
    }

    void handleGameState(GameState gameState) {
        switch (gameState) {
            case PLAYER_ONE_WON:
                //update won player #marina
                disableBoardButtons(true);
                mainTimer.cancel();
                break;

            case PLAYER_TWO_WON:
                //update won player #marina
                disableBoardButtons(true);
                mainTimer.cancel();
                break;

            case DRAW:
                //update game state #marina
                mainTimer.cancel();
                break;
            default:
                return;
        }
        //show video #huda
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Marina Reciving  && Passing data
        currentGameData = CurrentGameData.getInstance();
        //Passing data
        currentGameData.setGameMode(GameMode.SINGLE);
        currentGameData.setGameLevel(GameLevel.EASY);

        player1NameText.setText(currentGameData.getPlayer1());
        player2NameText.setText(currentGameData.getPlayer2());

        oldScorePlayer1.setTranslateX(currentGameData.getPlayer1OverAllScore());
        oldScorePlayer2.setTranslateX(currentGameData.getPlayer1OverAllScore());

        currentScorePlayer1.setTranslateX(currentGameData.getPlayer1CurrentScore());
        currentScorePlayer2.setTranslateX(currentGameData.getPlayer2CurrentScore());

        boardButtons = new Button[]{button0, button1, button2, button3, button4, button5, button6, button7, button8};

        setupRecordingTextFadeAffect();
        setupRecordingImageViewFadeAffect();
        setupMainTimer();
        setupBoardStyleClasses();
        gameShapes = new CircularArray<>(currentGameData.getPlayer1Shape(), currentGameData.getPlayer2Shape());

    }

    private void setupBoardStyleClasses() {
        boardStyleClasses = new CircularArray<>("x_board-btn", "o_board-btn");
        boardHoverStyleClasses = new CircularArray<>("empty_x_board-btn", "empty_o_board-btn");
    }

    @FXML
    protected void recordButtonClicked(ActionEvent event) {
        isRecording = !isRecording;
        Button button = (Button) event.getSource();
        toggleRecordingVisibility();
        toggleRecordingFadeAffect();

        if (isRecording) {
            button.setText("Cancel");
        } else {
            button.setText("Record");
        }

    }

    @FXML
    protected void exitButtonClicked(MouseEvent event) {
        //show alret
        //exit
    }

    @FXML
    protected void boardButtonClicked(ActionEvent event) {
        //timer
    }

    @FXML
    protected abstract void boardButtonEntered(MouseEvent event);

    @FXML
    protected abstract void boardButtonExited(MouseEvent event);

    protected abstract void applyStyleClass(Button button);

    @FXML
    protected void leaveButtonClicked(ActionEvent event) {

        //timer
        mainTimer.cancel();
    }

    protected void disableBoardButtons(boolean disabled) {
        for (Button button : boardButtons) {
            button.setDisable(disabled);
        }
    }

    private void setupRecordingTextFadeAffect() {
        recordingTextFadeAffect = new FadeTransition(Duration.millis(3000), recordingText);

        recordingTextFadeAffect.setFromValue(0.0);
        recordingTextFadeAffect.setToValue(1.0);

        recordingTextFadeAffect.setOnFinished((e) -> {
            double temp = recordingTextFadeAffect.getToValue();
            recordingTextFadeAffect.setToValue(recordingTextFadeAffect.getFromValue());
            recordingTextFadeAffect.setFromValue(temp);
            recordingTextFadeAffect.play();
        });
    }

    private void setupRecordingImageViewFadeAffect() {
        recordingImageViewFadeAffect = new FadeTransition(Duration.millis(3000), recordingImageView);

        recordingImageViewFadeAffect.setFromValue(0.0);
        recordingImageViewFadeAffect.setToValue(1.0);

        recordingImageViewFadeAffect.setOnFinished((event) -> {
            double temp = recordingImageViewFadeAffect.getToValue();
            recordingImageViewFadeAffect.setToValue(recordingImageViewFadeAffect.getFromValue());
            recordingImageViewFadeAffect.setFromValue(temp);
            recordingImageViewFadeAffect.play();
        });
    }

    private void toggleRecordingVisibility() {
        recordingText.setVisible(isRecording);
        recordingImageView.setVisible(isRecording);
    }

    private void toggleRecordingFadeAffect() {
        if (isRecording) {
            recordingImageViewFadeAffect.play();
            recordingTextFadeAffect.play();
        } else {
            recordingImageViewFadeAffect.stop();
            recordingTextFadeAffect.stop();
        }
    }

    protected void nextTurn() {
        boardHoverStyleClasses.next();
        boardStyleClasses.next();
        gameShapes.next();
    }

    private void setupMainTimer() {
        mainTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                timer++;
                mainTimerText.setText(timer / 60 + ":" + String.format("%02d", timer % 60));
            }
        }, 1000L, 1000L);
    }

}

package xo.board;

import java.util.TimerTask;
import javafx.scene.Node;
import javafx.util.Duration;
import xo.utlis.TicTacToeNavigator;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import data.GameLevel;
import data.GameMode;
import java.util.ResourceBundle;
import java.net.URL;
import xo.board.game.GameState;
import javafx.stage.Stage;
import data.CurrentGameData;
import javafx.animation.FadeTransition;
import data.GameShapes;
import data.database.DataAccessLayer;
import data.database.models.Game;
import data.database.models.Play;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import xo.utlis.CircularArray;
import java.util.Timer;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.fxml.Initializable;

public abstract class FXMLBoardController implements Initializable {

    @FXML
    GridPane boardGridPane;
    @FXML
    protected Button button0;
    @FXML
    protected Button button1;
    @FXML
    protected Button button2;
    @FXML
    protected Button button3;
    @FXML
    protected Button button4;
    @FXML
    protected Button button5;
    @FXML
    protected Button button6;
    @FXML
    protected Button button7;
    @FXML
    protected Button button8;
    @FXML
    protected Text mainTimerText;
    @FXML
    protected Text player1NameText;
    @FXML
    protected Text player2NameText;
    @FXML
    protected ImageView oLightOfImageView;
    @FXML
    protected ImageView xLightOfImageView;
    @FXML
    protected ImageView xLightOnImageView;
    @FXML
    protected ImageView oLightOnImageView;
    @FXML
    protected Text recordingText;
    @FXML
    protected Text currentPlayer1ScoreText;
    @FXML
    protected Text currentPlayer2ScoreText;
    @FXML
    protected ImageView recordingImageView;
    @FXML
    protected Button recordingButton;

    protected Button[] boardButtons;
    protected boolean firstPlayerTurn;
    protected Timer mainTimer;
    protected int timer;
    protected CircularArray<String> boardHoverStyleClasses;
    protected CircularArray<String> boardStyleClasses;
    protected CircularArray<GameShapes> gameShapes;
    protected boolean isRecording;
    private FadeTransition recordingTextFadeAffect;
    private FadeTransition recordingImageViewFadeAffect;
    protected CurrentGameData currentGameData;
    protected Stage stage;
    protected List<Play> plays = new ArrayList<>();
    protected CircularArray<String> players;

    public FXMLBoardController(final Stage stage) {
        this.boardButtons = null;
        this.firstPlayerTurn = true;
        stage.setOnCloseRequest(windowEvent -> this.mainTimer.cancel());
        this.mainTimer = new Timer();
        this.stage = stage;
    }

    void handleGameState(GameState gameState) {
        String wonPlayer = null;
        switch (gameState) {
            case PLAYER_ONE_WON:
                this.stopBoardActions();
                this.showWinnerDialog(this.player1NameText.getText());
                this.updatePlayerOneScore();
                insertGameToDatabase(currentGameData.getPlayer1());
                break;

            case PLAYER_TWO_WON:
                this.stopBoardActions();
                this.showWinnerDialog(this.player2NameText.getText());
                this.updatePlayerTwoScore();
                insertGameToDatabase(currentGameData.getPlayer2());
                break;

            case DRAW:
                this.stopBoardActions();
                break;

            default:
        }

    }

    public void initialize(final URL url, final ResourceBundle rb) {
        (this.currentGameData = CurrentGameData.getInstance()).setGameMode(GameMode.SINGLE);
        this.currentGameData.setGameLevel(GameLevel.EASY);
        this.player1NameText.setText(this.currentGameData.getPlayer1());
        this.player2NameText.setText(this.currentGameData.getPlayer2());
        this.boardButtons = new Button[]{this.button0, this.button1, this.button2, this.button3, this.button4, this.button5, this.button6, this.button7, this.button8};
        this.setupRecordingTextFadeAffect();
        this.setupRecordingImageViewFadeAffect();
        this.setupMainTimer();
        this.setupBoardStyleClasses();
        this.gameShapes = new CircularArray(this.currentGameData.getPlayer1Shape(), this.currentGameData.getPlayer2Shape());
        players = new CircularArray(currentGameData.getPlayer1(), currentGameData.getPlayer2());
    }

    private void setupBoardStyleClasses() {
        this.boardStyleClasses = new CircularArray(this.currentGameData.getPLayer1BoardStyleCss(), this.currentGameData.getPLayer2BoardStyleCss());
        this.boardHoverStyleClasses = new CircularArray(this.currentGameData.getPlayer1BoardHoverStyleCss(), this.currentGameData.getPlayer2BoardHoverStyleCss());
    }

    @FXML
    protected void recordButtonClicked(final ActionEvent event) {
        this.isRecording = !this.isRecording;
        final Button button = (Button) event.getSource();
        this.toggleRecordingVisibility();
        this.toggleRecordingFadeAffect();
        if (this.isRecording) {
            button.setText("Cancel");
        } else {
            button.setText("Record");
        }
    }

    @FXML
    protected void exitButtonClicked(final MouseEvent event) {
    }

    @FXML
    protected void boardButtonClicked(ActionEvent event) {
        plays.add(new Play((((Button) event.getSource()).getId().charAt(6) - '0') + "", players.get()));
        players.next();
    }

    @FXML
    protected abstract void boardButtonEntered(final MouseEvent p0);

    @FXML
    protected abstract void boardButtonExited(final MouseEvent p0);

    protected abstract void applyStyleClass(final Button p0);

    @FXML
    protected void leaveButtonClicked(ActionEvent event) {
        TicTacToeNavigator.previousLater(event);
        this.mainTimer.cancel();
    }

    protected void disableBoardButtons(final boolean disabled) {
        for (final Button button : this.boardButtons) {
            button.setDisable(disabled);
        }
    }

    private void setupRecordingTextFadeAffect() {
        (this.recordingTextFadeAffect = new FadeTransition(Duration.millis(3000.0), (Node) this.recordingText)).setFromValue(0.0);
        this.recordingTextFadeAffect.setToValue(1.0);
        this.recordingTextFadeAffect.setOnFinished(e -> {
            final double temp = this.recordingTextFadeAffect.getToValue();
            this.recordingTextFadeAffect.setToValue(this.recordingTextFadeAffect.getFromValue());
            this.recordingTextFadeAffect.setFromValue(temp);
            this.recordingTextFadeAffect.play();
        });
    }

    private void setupRecordingImageViewFadeAffect() {
        (this.recordingImageViewFadeAffect = new FadeTransition(Duration.millis(3000.0), (Node) this.recordingImageView)).setFromValue(0.0);
        this.recordingImageViewFadeAffect.setToValue(1.0);
        this.recordingImageViewFadeAffect.setOnFinished(event -> {
            final double temp = this.recordingImageViewFadeAffect.getToValue();
            this.recordingImageViewFadeAffect.setToValue(this.recordingImageViewFadeAffect.getFromValue());
            this.recordingImageViewFadeAffect.setFromValue(temp);
            this.recordingImageViewFadeAffect.play();
        });
    }

    protected void toggleRecordingVisibility() {
        this.recordingText.setVisible(this.isRecording);
        this.recordingImageView.setVisible(this.isRecording);
    }

    private void toggleRecordingFadeAffect() {
        if (this.isRecording) {
            this.recordingImageViewFadeAffect.play();
            this.recordingTextFadeAffect.play();
        } else {
            this.recordingImageViewFadeAffect.stop();
            this.recordingTextFadeAffect.stop();
        }
    }

    protected void nextTurn() {
        this.boardHoverStyleClasses.next();
        this.boardStyleClasses.next();
        this.gameShapes.next();
    }

    private void setupMainTimer() {
        this.mainTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                timer++;
                mainTimerText.setText(timer / 60 + ":" + String.format("%02d", timer % 60));
            }
        }, 1000L, 1000L);
    }

    private void stopBoardActions() {
        this.disableBoardButtons(true);
        this.mainTimer.cancel();
        this.recordingButton.setDisable(true);
        this.recordingTextFadeAffect.stop();
        this.recordingImageViewFadeAffect.stop();
    }

    private void showWinnerDialog(final String text) {
    }

    private void updatePlayerOneScore() {
        this.currentGameData.setPlayer1CurrentScore(this.currentGameData.getPlayer1CurrentScore() + 1);
        this.currentPlayer1ScoreText.setText(this.currentGameData.getPlayer1CurrentScore() + "");
    }

    private void updatePlayerTwoScore() {
        this.currentGameData.setPlayer2CurrentScore(this.currentGameData.getPlayer2CurrentScore() + 1);
        this.currentPlayer2ScoreText.setText(this.currentGameData.getPlayer2CurrentScore() + "");
    }

    protected void insertGameToDatabase(String wonPlayer) {
        try {
            DataAccessLayer.connect();
            int id = DataAccessLayer.insertGame(new Game("",
                    currentGameData.getPlayer1(),
                    isRecording + "",
                    currentGameData.getPlayer2(),
                    getCurrentDate(),
                    wonPlayer));

            if (isRecording) {
                DataAccessLayer.insertPlays(plays, id);
            }
            DataAccessLayer.disconnect();
        } catch (SQLException ex) {
            //catche me
            ex.printStackTrace();
        }
    }

    private String getCurrentDate() {
        LocalDate localDate = LocalDate.now();
        return localDate.getYear()
                + "-"
                + localDate.getMonthValue()
                + "-"
                + localDate.getDayOfMonth();
    }

}

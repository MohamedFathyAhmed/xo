package xo.board;

import java.util.TimerTask;
import javafx.scene.Node;
import javafx.util.Duration;
import xo.utlis.TicTacToeNavigator;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import java.util.ResourceBundle;
import java.net.URL;
import xo.board.game.GameState;
import javafx.stage.Stage;
import data.CurrentGameData;
import javafx.animation.FadeTransition;
import data.GameShape;
import data.database.DataAccessLayer;
import data.database.models.Game;
import data.database.models.Play;
import java.io.IOException;
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
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;

public abstract class FXMLBoardController implements Initializable {

    @FXML
    protected GridPane boardGridPane;

    @FXML
    protected BorderPane playerOneScoreBorderPane, playerTwoScoreBorderPane;

    @FXML
    protected Button button0, button1, button2, button3, button4, button5, button6, button7, button8,
            recordingButton;

    @FXML
    protected ImageView player1OImageView, player1XImageView, player2OImageView, player2XImageView,
            recordingImageView;

    @FXML
    protected Text mainTimerText, player1NameText, player2NameText, recordingText, currentPlayer1ScoreText, currentPlayer2ScoreText;

    protected Stage stage;

    protected Button[] boardButtons;

    protected CircularArray<String> recordingButtonTextValue = new CircularArray<>("Record", "Cancel");
    protected CircularArray<String> boardHoverStyleClasses;
    protected CircularArray<String> boardStyleClasses;
    protected CircularArray<String> players;
    protected CircularArray<GameShape> gameShapes;

    protected List<Play> plays = new ArrayList<>();

    private FadeTransition recordingTextFadeAffect;
    private FadeTransition recordingImageViewFadeAffect;

    protected boolean isRecording;
    protected int timer;

    protected CurrentGameData currentGameData;

    protected Timer mainTimer;

    public FXMLBoardController(Stage stage) {
        this.stage = stage;
        currentGameData = CurrentGameData.getInstance();
        mainTimer = new Timer();
        stage.setOnCloseRequest(windowEvent -> mainTimer.cancel());
        setupBoard();
    }

    void handleGameState(GameState gameState) {
        switch (gameState) {
            case PLAYER_ONE_WON:
                currentGameData.setWinnerPlayer(currentGameData.getPlayer1());
                boardGridPane.setDisable(true);
                updatePlayerOneScore();
                insertGameToDatabase();
//                showWinnerDialog();
                recordingImageViewFadeAffect.stop();
                recordingTextFadeAffect.stop();
                mainTimer.cancel();
                recordingButton.setDisable(true);
                break;

            case PLAYER_TWO_WON:
                currentGameData.setWinnerPlayer(currentGameData.getPlayer2());
                boardGridPane.setDisable(true);
                updatePlayerTwoScore();
                insertGameToDatabase();
//                showWinnerDialog();
                recordingImageViewFadeAffect.stop();
                recordingTextFadeAffect.stop();
                mainTimer.cancel();
                recordingButton.setDisable(true);

                break;

            case DRAW:
                currentGameData.setWinnerPlayer(null);
                boardGridPane.setDisable(true);
                insertGameToDatabase();
                recordingImageViewFadeAffect.stop();
                recordingTextFadeAffect.stop();
                mainTimer.cancel();
                recordingButton.setDisable(true);
                break;

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupRecordingTextFadeAffect();
        setupRecordingImageViewFadeAffect();
        setupBoardUi();
        setupMainTimer();
        boardButtons = new Button[]{this.button0, button1, button2, button3, button4, button5, button6, button7, button8};
    }

    @FXML
    protected void recordButtonClicked(ActionEvent event) {
        isRecording = !isRecording;
        recordingButtonTextValue.next();
        recordingButton.setText(recordingButtonTextValue.get());
        toggleRecordingVisibility();
        toggleRecordingFadeAffect();
    }

    @FXML
    protected void exitButtonClicked(final MouseEvent event) {
    }

    @FXML
    protected void boardButtonClicked(ActionEvent event) {
        plays.add(new Play((((Button) event.getSource()).getId().charAt(6) - '0') + "", players.get()));
    }

    @FXML
    protected abstract void boardButtonEntered(MouseEvent event);

    @FXML
    protected abstract void boardButtonExited(MouseEvent event);

    protected abstract void applyStyleClass(Button event);

    @FXML
    protected void leaveButtonClicked(ActionEvent event) throws IOException {
        TicTacToeNavigator.previous(event);
        mainTimer.cancel();
    }

    protected void disableBoard(boolean disabled) {
        boardGridPane.setDisable(disabled);
    }

    private void setupRecordingTextFadeAffect() {
        (recordingTextFadeAffect = new FadeTransition(Duration.millis(3000.0), (Node) recordingText)).setFromValue(0.0);
        recordingTextFadeAffect.setToValue(1.0);
        recordingTextFadeAffect.setOnFinished(event -> {
            double temp = recordingTextFadeAffect.getToValue();
            recordingTextFadeAffect.setToValue(this.recordingTextFadeAffect.getFromValue());
            recordingTextFadeAffect.setFromValue(temp);
            recordingTextFadeAffect.play();
        });
    }

    private void setupRecordingImageViewFadeAffect() {
        (recordingImageViewFadeAffect = new FadeTransition(Duration.millis(3000.0), (Node) recordingImageView)).setFromValue(0.0);
        recordingImageViewFadeAffect.setToValue(1.0);
        recordingImageViewFadeAffect.setOnFinished(event -> {
            double temp = recordingImageViewFadeAffect.getToValue();
            recordingImageViewFadeAffect.setToValue(this.recordingImageViewFadeAffect.getFromValue());
            recordingImageViewFadeAffect.setFromValue(temp);
            recordingImageViewFadeAffect.play();
        });
    }

    protected void toggleRecordingVisibility() {
        recordingText.setVisible(this.isRecording);
        recordingImageView.setVisible(this.isRecording);
    }

    private void toggleRecordingFadeAffect() {
        if (this.isRecording) {
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
        players.next();
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

    private void stopBoardActions() {
        disableBoard(true);
        mainTimer.cancel();
        recordingButton.setDisable(true);
        recordingTextFadeAffect.stop();
        recordingImageViewFadeAffect.stop();
    }

    private void showWinnerDialog() {
        TicTacToeNavigator.navigateLaterTo(stage, TicTacToeNavigator.MEDIA);
    }

    private void updatePlayerOneScore() {
        currentGameData.setPlayer1CurrentScore(currentGameData.getPlayer1CurrentScore() + 1);
        currentPlayer1ScoreText.setText(currentGameData.getPlayer1CurrentScore() + "");
    }

    private void updatePlayerTwoScore() {
        currentGameData.setPlayer2CurrentScore(currentGameData.getPlayer2CurrentScore() + 1);
        currentPlayer2ScoreText.setText(currentGameData.getPlayer2CurrentScore() + "");
    }

    protected void insertGameToDatabase() {
        try {
            int id = DataAccessLayer.insertGame(new Game("",
                    currentGameData.getPlayer1(),
                    currentGameData.getPlayer2(),
                    getCurrentDate(),
                    isRecording + "",
                    currentGameData.getWinnerPlayer(),
                    currentGameData.getPlayer1Shape().name(),
                    currentGameData.getPlayer2Shape().name()));

            if (isRecording) {
                DataAccessLayer.insertPlays(plays, id);
            }
        } catch (SQLException ex) {
            //catch me
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

    private void setupBoard() {
        gameShapes = new CircularArray(this.currentGameData.getPlayer1Shape(), currentGameData.getPlayer2Shape());
        players = new CircularArray(currentGameData.getPlayer1(), currentGameData.getPlayer2());
        boardStyleClasses = new CircularArray(this.currentGameData.getPLayer1BoardStyleCss(), currentGameData.getPLayer2BoardStyleCss());
        boardHoverStyleClasses = new CircularArray(this.currentGameData.getPlayer1BoardHoverStyleCss(), currentGameData.getPlayer2BoardHoverStyleCss());
    }

    protected void setupBoardUi() {
        player1NameText.setText(currentGameData.getPlayer1());
        player2NameText.setText(currentGameData.getPlayer2());

        if (currentGameData.getPlayer1Shape() == GameShape.O) {
            Paint tempPaint = player1NameText.getFill();
            player1NameText.setFill(player2NameText.getFill());
            player2NameText.setFill(tempPaint);

            Image tempImage = player2OImageView.getImage();
            player2OImageView.setImage(player1OImageView.getImage());
            player1OImageView.setImage(tempImage);

            tempImage = player2XImageView.getImage();
            player2XImageView.setImage(player1XImageView.getImage());
            player1XImageView.setImage(tempImage);
        }
    }
}

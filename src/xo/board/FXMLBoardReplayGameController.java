/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.board;

import data.GameShape;
import data.database.DataAccessLayer;
import data.database.models.Play;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import xo.board.game.GameHandler;
import xo.history.FXMLOfflineHistoryController;
import xo.history.FXMLOnlineHistoryController;
import xo.utlis.CircularArray;
import xo.utlis.TicTacToeNavigator;

/**
 *
 * @author mohamed
 */
public class FXMLBoardReplayGameController extends FXMLBoardController {

    public FXMLBoardReplayGameController(Stage stage, int gameId) throws SQLException {
        super(stage);
        plays = DataAccessLayer.getGamePlays(gameId);
    }

    public FXMLBoardReplayGameController(Stage stage) {
        super(stage);
        mainTimer.cancel();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        playerOneScoreBorderPane.setVisible(false);
        playerTwoScoreBorderPane.setVisible(false);
        mainTimerText.setVisible(false);
        recordingButton.setVisible(false);
        boardGridPane.setDisable(true);
        play();
    }

    @Override
    protected void boardButtonEntered(MouseEvent event) {
    }

    @Override
    protected void boardButtonExited(MouseEvent event) {
    }

    @Override
    protected void leaveButtonClicked(ActionEvent event) throws IOException {
        if (TicTacToeNavigator.pop() == TicTacToeNavigator.ONLINE_HISTORY) {
            TicTacToeNavigator.navigateTo(event, new FXMLOnlineHistoryController(), TicTacToeNavigator.ONLINE_HISTORY);
        } else {
            TicTacToeNavigator.navigateTo(event, new FXMLOfflineHistoryController(), TicTacToeNavigator.OFFLINE_HISTORY);
        }
    }

    @Override
    protected void applyStyleClass(Button button) {
        button.getStyleClass().add(boardStyleClasses.get());
    }

    private void play() {
        new Thread(() -> {
            for (Play play : plays) {
                Platform.runLater(() -> {
                    int position = Integer.parseInt(play.getPosition());
                    applyStyleClass(boardButtons[position]);
                    gameShapes.next();
                    nextTurn();
                });
                try {
                    Thread.sleep(1500L);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FXMLBoardReplayGameController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }).start();
    }

}

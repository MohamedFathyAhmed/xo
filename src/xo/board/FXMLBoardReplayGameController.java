/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.board;

import data.GameShapes;
import data.database.DataAccessLayer;
import data.database.models.Play;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import xo.board.game.GameHandler;
import xo.utlis.CircularArray;

/**
 *
 * @author mohamed
 */
public class FXMLBoardReplayGameController extends FXMLBoardController {

    private final GameHandler gameHandler;
    private final List<Play> plays;
    private final CircularArray<GameShapes> gameShapes = new CircularArray<>(GameShapes.X, GameShapes.O);

    public FXMLBoardReplayGameController(Stage stage, int gameId) throws SQLException {
        super(stage);
        plays = DataAccessLayer.getGamePlays(gameId);
        gameHandler = new GameHandler(this::handleGameState);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        boardGridPane.setDisable(true);
        play();
    }

    @Override
    protected void boardButtonEntered(MouseEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void boardButtonExited(MouseEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                    gameHandler.play(position, gameShapes.get());
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

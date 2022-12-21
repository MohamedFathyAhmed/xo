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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import xo.board.game.GameHandler;

/**
 *
 * @author mohamed
 */
public class FXMLBoardReplayGameController extends FXMLBoardController {

    private final GameHandler gameHandler;
    private final Play[] plays;

    public FXMLBoardReplayGameController(Stage stage, int gameId) throws SQLException {
        super(stage);
        plays = DataAccessLayer.getGamePlays(gameId);
        gameHandler = new GameHandler(this::handleGameState);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void play() {
        GameShapes shape = GameShapes.O;
        boardGridPane.setDisable(true);
        for (Play play : plays) {
            if (shape == GameShapes.X) {
                shape = GameShapes.O;
            } else {
                shape = GameShapes.X;
            }
            GameShapes shape1 = shape;
            new Thread(() -> {
                try {
                    int position = Integer.parseInt(play.getPosition());
                    applyStyleClass(boardButtons[position]);
                    gameHandler.play(position, shape1);
                    nextTurn();
                    Thread.sleep(1500L);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FXMLBoardReplayGameController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }).start();
        }

    }

}

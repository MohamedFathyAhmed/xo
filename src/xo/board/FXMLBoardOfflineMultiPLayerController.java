/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.board;

import data.database.DataAccessLayer;
import data.database.models.Game;
import data.database.models.Play;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import user_alerts.AlertButtonResult;
import static user_alerts.Alerts.displayAlert;
import xo.board.game.GameHandler;
import xo.board.game.GameState;
import xo.utlis.CircularArray;

/**
 *
 * @author mohamed
 */
public class FXMLBoardOfflineMultiPLayerController extends FXMLBoardController  {

    private GameHandler gameHandler;
    public FXMLBoardOfflineMultiPLayerController(Stage stage) {
        super(stage);
        gameHandler = new GameHandler(this::handleGameState);

    }

    @Override
    void handleGameState(GameState gameState) {
        super.handleGameState(gameState);
    }


    @Override
    protected void boardButtonExited(MouseEvent event) {
        ((Button) event.getSource()).getStyleClass().remove(boardHoverStyleClasses.get());
    }

    @Override
    protected void boardButtonEntered(MouseEvent event) {
        ((Button) event.getSource()).getStyleClass().add(boardHoverStyleClasses.get());
    }

    @Override
    protected void boardButtonClicked(ActionEvent event) {
        super.boardButtonClicked(event);
        int position = ((Button) event.getSource()).getId().charAt(6) - '0';
        plays.add(new Play(position + "", players.get()));
        Button button = (Button) event.getSource();
        button.setDisable(true);
        applyStyleClass(button);
        nextTurn();
        gameHandler.play(position, gameShapes.get());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
       players = new  CircularArray(currentGameData.getPlayer2(), currentGameData.getPlayer1());
    }

    @Override
    protected void applyStyleClass(Button button) {
        button.getStyleClass().add(boardStyleClasses.get());
    }

}

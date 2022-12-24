/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.board;

import data.database.models.Play;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import xo.board.game.GameHandler;
import xo.board.game.GameState;

/**
 *
 * @author mohamed
 */
public class FXMLBoardOfflineMultiPLayerController extends FXMLBoardController {

    private final GameHandler gameHandler;

    public FXMLBoardOfflineMultiPLayerController(Stage stage) {
        super(stage);
        gameHandler = new GameHandler(this::handleGameState,
                currentGameData.getPlayer1Shape(),
                currentGameData.getPlayer2Shape());

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
        int position = ((Button) event.getSource()).getId().charAt(6) - '0';
        plays.add(new Play(position + "", players.get()));
        Button button = (Button) event.getSource();
        button.setDisable(true);
        applyStyleClass(button);
        gameHandler.play(position, gameShapes.get());
        nextTurn();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
    }

    @Override
    protected void applyStyleClass(Button button) {
        button.getStyleClass().add(boardStyleClasses.get());
    }

}

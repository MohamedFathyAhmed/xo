/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.board;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
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
public class BoardMultiPlayerModeController extends FXMLBoardController {

    private GameHandler gameHandler;

    public BoardMultiPlayerModeController(Stage stage) {
        super(stage);
        gameHandler = new GameHandler(this::handleGameState);

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
        Button button = (Button) event.getSource();
        button.setDisable(true);
        applyStyleClass(button);
        nextTurn();
        gameHandler.play(((Button) event.getSource()).getId().charAt(6) - '0', gameShapes.get());
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

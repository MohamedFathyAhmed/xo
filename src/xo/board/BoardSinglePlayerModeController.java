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
 * @author Marina
 */
public class BoardSinglePlayerModeController extends FXMLBoardController {

    private final GameHandler gameHandler;
    private int locationPcPlay;
    private GameState gameState = GameState.ONGOING;

    public BoardSinglePlayerModeController(Stage stage) {
        super(stage);
        gameHandler = new GameHandler((gameState) -> {
            this.gameState = gameState;
            handleGameState(gameState);
        },
                currentGameData.getPlayer1Shape(),
                currentGameData.getPlayer2Shape());

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
        gameHandler.play(((Button) event.getSource()).getId().charAt(6) - '0', gameShapes.get());
        nextTurn();
        getPlayFromPc();
    }

    void getPlayFromPc() {
        disableBoard(true);
        if (gameState == GameState.ONGOING) {
            String BoardChar = gameHandler.getBoardAsString();
            EasyAi pcAi = new EasyAi(BoardChar);
            locationPcPlay = pcAi.res;
            plays.add(new Play(locationPcPlay + "", currentGameData.getPlayer2()));
            applyStyleClass(boardButtons[locationPcPlay]);
            gameHandler.play(locationPcPlay, gameShapes.get());
            nextTurn();
            boardButtons[pcAi.res].setDisable(true);
            disableBoard(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        if (!currentGameData.isIsPlayerTurnFirst()) {
            boardGridPane.setDisable(true);
            nextTurn();
            getPlayFromPc();
            boardGridPane.setDisable(false);
        }
    }

    @Override
    protected void applyStyleClass(Button button) {
        button.getStyleClass().add(boardStyleClasses.get());
    }
}

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
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import xo.board.game.GameHandler;
import xo.board.game.GameState;
import xo.utlis.CircularArray;

/**
 *
 * @author mohamed
 */
public class FXMLBoardOfflineMultiPLayerController extends FXMLBoardController {

    private List<Play> plays = new ArrayList<>();
    private GameHandler gameHandler;
    private CircularArray<String> players = new CircularArray<>(currentGameData.getPlayer1(), currentGameData.getPlayer2());

    public FXMLBoardOfflineMultiPLayerController(Stage stage) {
        super(stage);
        gameHandler = new GameHandler(this::handleGameState);

    }

    @Override
    void handleGameState(GameState gameState) {
        super.handleGameState(gameState);
        insertGameToDatabase(gameState);
    }

    private String getCurrentDate() {
        LocalDate localDate = LocalDate.now();
        return localDate.getYear()
                + "-"
                + localDate.getMonthValue()
                + "-"
                + localDate.getDayOfMonth();
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
        players.next();
        gameHandler.play(position, gameShapes.get());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
    }

    @Override
    protected void applyStyleClass(Button button) {
        button.getStyleClass().add(boardStyleClasses.get());
    }

    private void insertGameToDatabase(GameState gameState) {
        try {
            String wonPlayer = null;
            if (gameState != GameState.ONGOING) {
                switch (gameState) {
                    case PLAYER_ONE_WON:
                        wonPlayer = currentGameData.getPlayer1();
                        break;
                    case PLAYER_TWO_WON:
                        wonPlayer = currentGameData.getPlayer2();
                        break;
                }
            }
            int id = DataAccessLayer.insertGame(new Game("",
                    currentGameData.getPlayer1(),
                    isRecording + "",
                    currentGameData.getPlayer2(),
                    getCurrentDate(),
                    wonPlayer));

            if (isRecording) {
                DataAccessLayer.insertPlays(plays, id);
            }
        } catch (SQLException ex) {
            //catche me
        }
    }

}

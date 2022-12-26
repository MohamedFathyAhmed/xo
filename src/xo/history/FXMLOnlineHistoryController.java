package xo.history;

import data.database.models.Game;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import xo.board.FXMLBoardReplayGameController;
import xo.online.handlers.Request;
import xo.online.handlers.RequestHandler;
import xo.online.handlers.RequestType;
import xo.online.handlers.responses.HistoryResponse;
import xo.online.handlers.responses.RececordedGameResponse;
import xo.online.handlers.responses.Response;
import xo.utlis.TicTacToeNavigator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mohamed
 */
public class FXMLOnlineHistoryController extends FXMLHistoryController {

    private RequestHandler requestHandler;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);

        requestHandler = RequestHandler.getInstance((Response response) -> {
            if (response instanceof HistoryResponse) {
                updateTableViewItems(((HistoryResponse) response).getGames());
            } else if (response instanceof RececordedGameResponse) {
                TicTacToeNavigator.navigateLaterTo(
                        stage,
                        new FXMLBoardReplayGameController(stage, ((RececordedGameResponse) response).getPlays()),
                        TicTacToeNavigator.BOARD_REPLAY_GAME
                );
            }
        });

        try {
            requestHandler.create(RequestType.HISTORY);
        } catch (IOException ex) {

        }

    }

    @Override

    protected void viewButtonClicked(ActionEvent event) throws SQLException, IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Game game = historyTableView.getSelectionModel().getSelectedItem();
        requestHandler.create(RequestType.RECORDED_GAME, new Request(game.getId() + ""));
        currentGameData.setPlayer1(game.getPlayer1());
        currentGameData.setPlayer2(game.getPlayer2());
    }

}

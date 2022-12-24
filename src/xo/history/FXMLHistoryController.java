/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.history;

import data.CurrentGameData;
import data.GameShape;
import data.database.models.Game;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import xo.board.FXMLBoardReplayGameController;
import xo.utlis.TicTacToeNavigator;

/**
 *
 * @author mohamed
 */
public class FXMLHistoryController implements Initializable {

    @FXML
    protected TableColumn<Game, String> gameNumberTableColumn;////shoud y type in game class
    @FXML
    protected TableColumn<Game, String> playerOneNameTableColumn;
    @FXML
    protected TableColumn<Game, String> playerOneShapeTableColumn;
    @FXML
    protected TableColumn<Game, String> playerTwoShapeTableColumn;
    @FXML
    protected TableColumn<Game, String> playerTwoNameTableColumn;
    @FXML
    protected TableColumn<Game, Date> dateTableColumn;
    @FXML
    protected TableColumn<Game, String> winnerPlayerTableColumn;
    @FXML
    protected Button backButton;
    @FXML
    protected TableView<Game> historyTableView;
    @FXML
    protected Button viewRecordButton;

    protected CurrentGameData currentGameData;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    public FXMLHistoryController() {
        currentGameData = CurrentGameData.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTableColumns();

        historyTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                viewRecordButton.setVisible(newSelection.getIsRecorded());
            }
        });
    }

    @FXML
    protected void backButtonClicked(ActionEvent event) throws IOException {
        TicTacToeNavigator.previous(event);
    }

    protected void displayGameHistory() throws SQLException {
        if (historyTableView.getSelectionModel().getSelectedItem().equals(true)) {
            viewRecordButton.setVisible(true);
        }
    }

    @FXML
    protected void viewButtonClicked(ActionEvent event) throws SQLException, IOException {
        Game game = historyTableView.getSelectionModel().getSelectedItem();
        updateCurrentGameData(game);
        TicTacToeNavigator.navigateTo(
                event,
                new FXMLBoardReplayGameController((Stage) ((Node) event.getSource()).getScene().getWindow(),
                        game.getId()),
                TicTacToeNavigator.BOARD_REPLAY_GAME
        );
    }

    protected void updateTableViewItems(Game[] games) {
        historyTableView.getItems().clear();
        for (Game game : games) {
            historyTableView.getItems().add(game);
        }
    }

    private void setupTableColumns() {
        gameNumberTableColumn.setCellValueFactory(new PropertyValueFactory("id"));
        playerOneNameTableColumn.setCellValueFactory(new PropertyValueFactory("player1"));
        playerTwoNameTableColumn.setCellValueFactory(new PropertyValueFactory("player2"));
        playerOneShapeTableColumn.setCellValueFactory(new PropertyValueFactory("player1Shape"));
        playerTwoShapeTableColumn.setCellValueFactory(new PropertyValueFactory("player2Shape"));
        winnerPlayerTableColumn.setCellValueFactory(new PropertyValueFactory("wonPLayer"));
        dateTableColumn.setCellValueFactory(new PropertyValueFactory("date"));
    }

    private void updateCurrentGameData(Game game) {
        currentGameData.setPlayer1(game.getPlayer1());
        currentGameData.setPlayer2(game.getPlayer2());
        currentGameData.setPlayer1Shape(GameShape.valueOf(game.getPlayer1Shape()));
        currentGameData.setPlayer2Shape(GameShape.valueOf(game.getPlayer2Shape()));
    }
}

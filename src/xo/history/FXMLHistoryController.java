/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.history;

import data.database.DataAccessLayer;
import data.database.models.Game;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    protected AnchorPane historyTable;
    @FXML
    protected TableColumn<Game, String> gameNumberCulme;////shoud y type in game class
    @FXML
    protected TableColumn<Game, String> playerOneNameColumn;
    @FXML
    protected TableColumn<Game, String> playerTwoNameColumn;
    @FXML
    protected TableColumn<Game, Date> dateColumn;
    @FXML
    protected TableColumn<Game, String> winerColumn;
    @FXML
    protected Button backButton;
    @FXML
    protected TableView<Game> historyTableView;
    @FXML
    protected Button viewRecordButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameNumberCulme.setCellValueFactory(new PropertyValueFactory<Game, String>("id"));
        playerOneNameColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("player1"));
        playerTwoNameColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("player2"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Game, Date>("date"));
        winerColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("wonPLayer"));
        viewRecordButton.setVisible(false);


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
        TicTacToeNavigator.navigateTo(
                event,
                new FXMLBoardReplayGameController((Stage) ((Node) event.getSource()).getScene().getWindow(),
                        historyTableView.getSelectionModel().getSelectedItem().getId()),
                TicTacToeNavigator.BOARD_REPLAY_GAME
        );
    }

    protected void updateTableViewItems(Game[] games) {
        historyTableView.getItems().clear();
        for (Game game : games) {
            historyTableView.getItems().add(game);
        }
    }
}

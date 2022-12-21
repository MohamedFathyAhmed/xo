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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
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
 * FXML Controller class
 *
 * @author Marina
 */
public class FXMLHistoryController implements Initializable {

    @FXML
    private AnchorPane historyTable;
    @FXML
    private TableColumn<Game, Integer> gameNumberCulme;////shoud y type in game class
    @FXML
    private TableColumn<Game, String> playerOneNameColumn;
    @FXML
    private TableColumn<Game, String> playerTwoNameColumn;
    @FXML
    private TableColumn<Game, Date> dateColumn;
    @FXML
    private TableColumn<Game, String> winerColumn;
    @FXML
    private Button backButton;
    @FXML
    private TableView<Game> historyTableView;
    @FXML
    private Button viewRecordButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        playerOneNameColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("player1"));
        playerTwoNameColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("player2"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Game, Date>("date"));
        winerColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("wonPLayer"));
        viewRecordButton.setVisible(false);

        updateTableViewItems();

        historyTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                viewRecordButton.setVisible(true);
            }
        });

    }

    @FXML
    private void backButtonClicked(ActionEvent event) {

        //viewRecordButton.setVisible(true);
    }

    private void displayGameHistory() throws SQLException {
        if (historyTableView.getSelectionModel().getSelectedItem().equals(true)) {
            viewRecordButton.setVisible(true);
        }
    }

    @FXML
    private void viewButtonClicked(ActionEvent event) throws SQLException, IOException {
        TicTacToeNavigator.navigateTo(
                event,
                new FXMLBoardReplayGameController((Stage) ((Node) event.getSource()).getScene().getWindow(),
                        historyTableView.getSelectionModel().getSelectedItem().getId()),
                TicTacToeNavigator.BOARD_REPLAY_GAME
        );
    }

    private void updateTableViewItems() {
        try {
            historyTableView.getItems().clear();
            for (Game game : DataAccessLayer.getGames()) {
                historyTableView.getItems().add(game);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}

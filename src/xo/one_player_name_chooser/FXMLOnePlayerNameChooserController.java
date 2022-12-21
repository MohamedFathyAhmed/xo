/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.one_player_name_chooser;

import data.CurrentGameData;
import data.GameShapes;
import data.database.DataAccessLayer;
import data.database.models.Game;
import data.database.models.Player;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import xo.board.BoardSinglePlayerModeController;
import xo.online.online_players.OnlinePlayer;
import xo.utlis.CircularArray;
import xo.utlis.TicTacToeNavigator;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class FXMLOnePlayerNameChooserController implements Initializable {

    @FXML
    private TableColumn<OnlinePlayer, String> namesTableColumn;
    @FXML
    private TableView<OnlinePlayer> namesTableView;

    @FXML
    private Button computerOButton;
    @FXML
    private Button playerXButton;
    @FXML
    private Button computerXButton;
    @FXML
    private Button playerOButton;
    @FXML
    private Label playerLabel;
    @FXML
    private Label modeLabel;

    @FXML
    private Label usernameErrorLabel;

    @FXML
    private TextField usernameTextField;

    private CircularArray<String> newButtonNames = new CircularArray<>("New Name", "Save");

    private final CurrentGameData currentGameData;

    public FXMLOnePlayerNameChooserController() {
        super();
        this.currentGameData = CurrentGameData.getInstance();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        namesTableColumn.setCellValueFactory(new PropertyValueFactory<OnlinePlayer, String>("username"));
        namesTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                currentGameData.setPlayer1(namesTableView.getSelectionModel().getSelectedItem().getUsername());
            }
        });

        fetchPlayersFromDatabase();

    }

    @FXML
    private void backButtonClicked(ActionEvent event) throws IOException {
        TicTacToeNavigator.previous(event);
    }

    @FXML
    private void startButtonClicked(ActionEvent event) throws IOException {
        currentGameData.setPlayer2(currentGameData.getGameLevel().name());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        TicTacToeNavigator.navigateTo(stage, new BoardSinglePlayerModeController(stage), TicTacToeNavigator.BOARD_PLAYER_VS_EASY_AI);
    }

    @FXML
    private void newNameButtonClicked(ActionEvent event) {
        usernameTextField.setVisible(!usernameTextField.visibleProperty().get());
        newButtonNames.next();
        ((Button) event.getSource()).setText(newButtonNames.get());
        if (!usernameTextField.visibleProperty().get()) {
            insertUserToDatabase();
        }
    }

    @FXML
    private void playerXButtonClicked(ActionEvent event) {
        playerXButton.getStyleClass().add("x_selected_board-btn");
        playerXButton.getStyleClass().remove("x_unselected_board-btn");
        computerXButton.getStyleClass().add("x_unselected_board-btn");
        computerXButton.getStyleClass().remove("x_selected_board-btn");
        playerOButton.getStyleClass().add("o_unselected_board-btn");
        playerOButton.getStyleClass().remove("o_selected_board-btn");
        computerOButton.getStyleClass().add("o_selected_board-btn");
        computerOButton.getStyleClass().remove("o_unselected_board-btn");
        playerXButton.setDisable(true);
        playerOButton.setDisable(false);
        currentGameData.setPlayer1Shape(GameShapes.X);
        currentGameData.setPlayer2Shape(GameShapes.O);
    }

    @FXML
    private void playerOButtonClicked(ActionEvent event) {
        playerXButton.getStyleClass().add("x_unselected_board-btn");
        playerXButton.getStyleClass().remove("x_selected_board-btn");
        computerXButton.getStyleClass().add("x_selected_board-btn");
        computerXButton.getStyleClass().remove("x_unselected_board-btn");
        playerOButton.getStyleClass().add("o_selected_board-btn");
        playerOButton.getStyleClass().remove("o_unselected_board-btn");
        computerOButton.getStyleClass().add("o_unselected_board-btn");
        computerOButton.getStyleClass().remove("o_selected_board-btn");
        playerOButton.setDisable(true);
        playerXButton.setDisable(false);
        currentGameData.setPlayer1Shape(GameShapes.O);
        currentGameData.setPlayer2Shape(GameShapes.X);
    }

    private void insertUserToDatabase() {
        try {
            DataAccessLayer.insertPlayer(usernameTextField.getText());
            usernameErrorLabel.setVisible(false);
            fetchPlayersFromDatabase();

        } catch (SQLException ex) {
            usernameErrorLabel.setVisible(true);
        }
    }

    private void fetchPlayersFromDatabase() {
        namesTableView.getItems().clear();
        try {
            // TODO
            for (String player : DataAccessLayer.getPlayers()) {
                namesTableView.getItems().add(new OnlinePlayer(player));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}

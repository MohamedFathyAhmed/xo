/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.one_player_name_chooser;

import data.CurrentGameData;
import data.GameShape;
import data.database.DataAccessLayer;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import xo.board.FXMLBoardSinglePlayerModeController;
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
    private Button computerOButton, playerXButton, computerXButton, playerOButton;
    @FXML
    private ToggleButton playerPlayFirstToggleButton, modePlayFirstToggleButton;
    @FXML
    private Label usernameErrorLabel, playerLabel;
    @FXML
    private TextField usernameTextField;

    private final CircularArray<String> newButtonNames = new CircularArray<>("New Name", "Save");
    private final CurrentGameData currentGameData;

    public FXMLOnePlayerNameChooserController() {
        this.currentGameData = CurrentGameData.getInstance();
        currentGameData.reset();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupNamesTableView();
        fetchPlayersFromDatabase();
        namesTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            playerLabel.setText(newValue.getUsername());
            currentGameData.setPlayer1(newValue.getUsername());
        });
        currentGameData.setPlayer1Shape(GameShape.X);
        currentGameData.setPlayer2Shape(GameShape.O);

      

    }

    @FXML
    private void backButtonClicked(ActionEvent event) throws IOException {
        TicTacToeNavigator.previous(event);
    }

    @FXML
    private void startButtonClicked(ActionEvent event) throws IOException {
        if (currentGameData.getPlayer1() != null) {
            currentGameData.setPlayer2(currentGameData.getGameLevel().name());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            TicTacToeNavigator.navigateTo(stage,
                    new FXMLBoardSinglePlayerModeController(stage),
                    TicTacToeNavigator.BOARD_PLAYER_VS_EASY_AI);
        } else {
            //please select name
        }

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
    private void playFirstToggleButtonClicked(ActionEvent event) {
        currentGameData.setIsPlayerTurnFirst(!currentGameData.isIsPlayerTurnFirst());
        playerPlayFirstToggleButton.setSelected(currentGameData.isIsPlayerTurnFirst());
        modePlayFirstToggleButton.setSelected(!currentGameData.isIsPlayerTurnFirst());
    }

    @FXML
    private void playerXButtonClicked(ActionEvent event) {
        playerXButtonSelected(true);
        computerXButtonSelected(false);
        currentGameData.setPlayer1Shape(GameShape.X);
        currentGameData.setPlayer2Shape(GameShape.O);
    }

    @FXML
    private void playerOButtonClicked(ActionEvent event) {
        playerXButtonSelected(false);
        computerXButtonSelected(true);
        currentGameData.setPlayer1Shape(GameShape.O);
        currentGameData.setPlayer2Shape(GameShape.X);
    }

    private void insertUserToDatabase() {
        try {
            if (usernameTextField.getText().isEmpty()) {
            Platform.runLater(() -> usernameErrorLabel.setText("username is required"));
            usernameErrorLabel.setVisible(true);
        } else{
            DataAccessLayer.insertPlayer(usernameTextField.getText());
            usernameErrorLabel.setVisible(false);
            fetchPlayersFromDatabase();
            }
        } catch (SQLException ex) {
            usernameErrorLabel.setVisible(true);
        }
    }

    private void fetchPlayersFromDatabase() {
        namesTableView.getItems().clear();
        try {
            for (String player : DataAccessLayer.getPlayers()) {
                namesTableView.getItems().add(new OnlinePlayer(player));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void setupNamesTableView() {
        namesTableColumn.setCellValueFactory(new PropertyValueFactory("username"));
        namesTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                currentGameData.setPlayer1(namesTableView.getSelectionModel().getSelectedItem().getUsername());
            }
        });
    }

    private void playerXButtonSelected(boolean selected) {
        if (selected) {
            playerXButton.getStyleClass().add("x_selected_board-btn");
            playerXButton.getStyleClass().remove("x_unselected_board-btn");
            playerOButton.getStyleClass().add("o_unselected_board-btn");
            playerOButton.getStyleClass().remove("o_selected_board-btn");
        } else {
            playerOButton.getStyleClass().add("o_selected_board-btn");
            playerOButton.getStyleClass().remove("o_unselected_board-btn");
            playerXButton.getStyleClass().add("x_unselected_board-btn");
            playerXButton.getStyleClass().remove("x_selected_board-btn");
        }
        playerOButton.setDisable(!selected);
        playerXButton.setDisable(selected);
    }

    private void computerXButtonSelected(boolean selected) {
        if (selected) {
            computerXButton.getStyleClass().add("x_selected_board-btn");
            computerXButton.getStyleClass().remove("x_unselected_board-btn");
            computerOButton.getStyleClass().add("o_unselected_board-btn");
            computerOButton.getStyleClass().remove("o_selected_board-btn");
        } else {
            computerXButton.getStyleClass().add("x_unselected_board-btn");
            computerXButton.getStyleClass().remove("x_selected_board-btn");
            computerOButton.getStyleClass().add("o_selected_board-btn");
            computerOButton.getStyleClass().remove("o_unselected_board-btn");
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.two_players_name_chooser;

import data.CurrentGameData;
import data.GameShapes;
import data.database.DataAccessLayer;
import data.database.models.Player;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import xo.board.FXMLBoardOfflineMultiPLayerController;
import xo.online.online_players.OnlinePlayer;
import xo.utlis.CircularArray;
import xo.utlis.TicTacToeNavigator;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class FXMLTwoPlayersNameChooserController implements Initializable {
 private CircularArray<String> newButtonNames = new CircularArray<>("New Name", "Save");
    @FXML
    private AnchorPane root;
    @FXML
    private Button playerTwoOButton;
    @FXML
    private Button playerOneXButton;
    @FXML
    private Button playerTwoXButton;
    @FXML
    private Button playerOneOButton;

   
    @FXML
    private TextField usernameTextField;
    @FXML
    private Label usernameErrorLabel;
       @FXML
    private TableColumn<OnlinePlayer, String> namesTableColumn;
    @FXML
    private TableView<OnlinePlayer> namesTableView;

    private final CurrentGameData currentGameData;
    @FXML
    private TableView<OnlinePlayer> namesTableViewTwo;
    @FXML
    private TableColumn<OnlinePlayer, String> namesTableColumnTwo;

    public FXMLTwoPlayersNameChooserController() {
        currentGameData = CurrentGameData.getInstance();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        namesTableColumnTwo.setCellValueFactory(new PropertyValueFactory<OnlinePlayer, String>("username"));
        namesTableViewTwo.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                currentGameData.setPlayer2(newSelection.getUsername());
                System.out.println(newSelection.getUsername());  
            }
         });

        
        namesTableColumn.setCellValueFactory(new PropertyValueFactory<OnlinePlayer, String>("username"));
        namesTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                currentGameData.setPlayer1(newSelection.getUsername());
                System.out.println(newSelection.getUsername());  
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
      
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        TicTacToeNavigator.navigateTo(stage, new FXMLBoardOfflineMultiPLayerController(stage), TicTacToeNavigator.BOARD_OFFLINE_MULTIPLAYERS);

    }


    @FXML
    private void playerTwoOButtonClicked(ActionEvent event) {
        playerOneXButton.fire();
    }

    @FXML
    private void playerOneXButtonClicked(ActionEvent event) {
        playerOneXButton.getStyleClass().add("x_selected_board-btn");
        playerOneXButton.getStyleClass().remove("x_unselected_board-btn");
        playerTwoXButton.getStyleClass().add("x_unselected_board-btn");
        playerTwoXButton.getStyleClass().remove("x_selected_board-btn");
        playerOneOButton.getStyleClass().add("o_unselected_board-btn");
        playerOneOButton.getStyleClass().remove("o_selected_board-btn");
        playerTwoOButton.getStyleClass().add("o_selected_board-btn");
        playerTwoOButton.getStyleClass().remove("o_unselected_board-btn");
        playerOneXButton.setDisable(true);
        playerOneOButton.setDisable(false);
        playerTwoXButton.setDisable(false);
        playerTwoOButton.setDisable(true);
        currentGameData.setPlayer1Shape(GameShapes.X);
        currentGameData.setPlayer2Shape(GameShapes.O);
    }

    @FXML
    private void playerTwoXButtonClicked(ActionEvent event) {
        playerOneOButton.fire();
    }

    @FXML
    private void playerOneOButtonClicked(ActionEvent event) {
        playerOneXButton.getStyleClass().add("x_unselected_board-btn");
        playerOneXButton.getStyleClass().remove("x_selected_board-btn");
        playerTwoXButton.getStyleClass().add("x_selected_board-btn");
        playerTwoXButton.getStyleClass().remove("x_unselected_board-btn");
        playerOneOButton.getStyleClass().add("o_selected_board-btn");
        playerOneOButton.getStyleClass().remove("o_unselected_board-btn");
        playerTwoOButton.getStyleClass().add("o_unselected_board-btn");
        playerTwoOButton.getStyleClass().remove("o_selected_board-btn");
        playerOneOButton.setDisable(true);
        playerOneXButton.setDisable(false);
        playerTwoXButton.setDisable(true);
        playerTwoOButton.setDisable(false);
        currentGameData.setPlayer1Shape(GameShapes.O);
        currentGameData.setPlayer2Shape(GameShapes.X);
    }

    @FXML
    private void newPlayerNameButtonClicked(ActionEvent event) {
      usernameTextField.setVisible(!usernameTextField.visibleProperty().get());
        newButtonNames.next();
        ((Button) event.getSource()).setText(newButtonNames.get());
        if (!usernameTextField.visibleProperty().get()) {
            insertUserToDatabase();
        }
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
              namesTableViewTwo.getItems().clear();

        namesTableView.getItems().clear();
        try {
            // TODO
            for (String player : DataAccessLayer.getPlayers()) {
                namesTableView.getItems().add(new OnlinePlayer(player));
                namesTableViewTwo.getItems().add(new OnlinePlayer(player));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

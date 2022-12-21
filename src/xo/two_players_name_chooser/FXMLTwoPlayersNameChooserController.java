/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.two_players_name_chooser;

import data.CurrentGameData;
import data.GameShapes;
import data.database.models.Player;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import xo.board.FXMLBoardOfflineMultiPLayerController;
import xo.utlis.TicTacToeNavigator;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class FXMLTwoPlayersNameChooserController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TableView<Player> playerOneTableView;
    @FXML
    private TableView<Player> playerTwoTableView;
    @FXML
    private Button playerTwoOButton;
    @FXML
    private Button playerOneXButton;
    @FXML
    private Button playerTwoXButton;
    @FXML
    private Button playerOneOButton;

    private final CurrentGameData currentGameData;

    public FXMLTwoPlayersNameChooserController() {
        currentGameData = CurrentGameData.getInstance();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }

    @FXML
    private void backButtonClicked(ActionEvent event) throws IOException {
        TicTacToeNavigator.previous(event);
    }

    @FXML
    private void startButtonClicked(ActionEvent event) throws IOException {
        currentGameData.setPlayer1(currentGameData.getGameLevel().name());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        TicTacToeNavigator.navigateTo(stage, new FXMLBoardOfflineMultiPLayerController(stage), TicTacToeNavigator.BOARD_PLAYER_VS_EASY_AI);

    }

    @FXML
    private void newPlayerOneNameButtonClicked(ActionEvent event) {

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
    private void newPlayerTwoNameButtonClicked(ActionEvent event) {

    }

}

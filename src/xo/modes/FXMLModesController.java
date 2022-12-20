/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.modes;

import data.CurrentGameData;
import data.GameMode;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import xo.online.handlers.RequestHandler;
import xo.online.signin.FXMLSigninControler;
import xo.utlis.TicTacToeNavigator;

/**
 *
 * @author mohamed
 */
public class FXMLModesController implements Initializable {
    @FXML
    Button logoutButton;

    private final CurrentGameData currentGameData;
    private final RequestHandler requestHandler;
    private Stage stage;
//

    public FXMLModesController() {
        currentGameData = CurrentGameData.getInstance();
        requestHandler = RequestHandler.getInstance(
                (response) -> {
                    if (response.isSuccess()) {
                        TicTacToeNavigator.navigateLaterTo(
                                stage,
                                TicTacToeNavigator.SIGNIN
                        );
                    }
                }, 5005, "127.0.0.1");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logoutButton.setVisible(currentGameData.isLoggedIn());
    }

    
    
    @FXML
    private void singlePlayerButtonClicked(ActionEvent event) throws IOException {
        currentGameData.setGameMode(GameMode.SINGLE);
        TicTacToeNavigator.navigateTo(event, TicTacToeNavigator.ONE_PLAYER_NAME_CHOOSER);

    }
    
    @FXML
    private void logoutButtonClicked(ActionEvent event) throws IOException {
        
    }

    @FXML
    private void offineMultiPlayerButtonClicked(ActionEvent event) throws IOException {
        currentGameData.setGameMode(GameMode.MULTIPLAYER);
        TicTacToeNavigator.navigateTo(event, TicTacToeNavigator.TWO_PLAYER_NAME_CHOOSER);
    }

    @FXML
    private void onlineMultiPlayersButtonClicked(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentGameData.setGameMode(GameMode.ONLINE);
        connectToServer(stage);
    }

    private void connectToServer(Stage stage) {
        if (requestHandler.isConnected()) {
            TicTacToeNavigator.navigateLaterTo(
                    stage,
                    TicTacToeNavigator.SIGNIN
            );

        } else {
            try {
                requestHandler.connect();
            } catch (IOException ex) {
                //catch me
                //server is down
            }
        }
    }

}

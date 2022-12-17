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
import xo.online.handlers.RequestHandler;
import xo.utlis.TicTacToeNavigator;

/**
 *
 * @author mohamed
 */
public class FXMLModesController implements Initializable {

    CurrentGameData currentGameData;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentGameData = CurrentGameData.getInstance();
    }

    @FXML
    private void singlePlayerButtonClicked(ActionEvent event) throws IOException {
        currentGameData.setGameMode(GameMode.SINGLE);
        TicTacToeNavigator.navigateTo(event, TicTacToeNavigator.ONE_PLAYER_NAME_CHOOSER);

    }

    @FXML
    private void offineMultiPlayerButtonClicked(ActionEvent event) throws IOException {
        currentGameData.setGameMode(GameMode.MULTIPLAYER);
        TicTacToeNavigator.navigateTo(event, TicTacToeNavigator.TWO_PLAYER_NAME_CHOOSER);
    }

    @FXML
    private void onlineMultiPlayersButtonClicked(ActionEvent event) {
        currentGameData.setGameMode(GameMode.ONLINE);
        RequestHandler.getInstance(
                (response) -> {

                },
                (message) -> {

                }, 5005, "");

        //        
    }

}

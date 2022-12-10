/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.modes;

import data.CurrentGameData;
import data.GameMode;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import xo.utlis.Navigator;

/**
 *
 * @author mohamed
 */
public class FXMLModesController implements Initializable {
     @FXML
    private Button singleButton;//Marina
    @FXML
    private Button localMultiPlayersButton;//Marina
    @FXML
    private Button onlineMultiPlayersButton;//Marina
    
    CurrentGameData currentGameData;//Marina
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML
    private void singleButtonClicked(ActionEvent event) {
        currentGameData.setGameMode(GameMode.SINGLE);//Marina
        
    }

    @FXML
    private void localMultiPlayersButtonClicked(ActionEvent event) {
         currentGameData.setGameMode(GameMode.MULTIPLAYER);//Marina
    }

    @FXML
    private void onlineMultiPlayersButtonClicked(ActionEvent event){ 
         currentGameData.setGameMode(GameMode.ONLINE);//Marina
    }
    
}

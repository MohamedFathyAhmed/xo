/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.online.signin;

import data.CurrentGameData;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Marina
 */
public class FXMLSigninControler implements Initializable {

    @FXML
    private ImageView levelsImagetwo;
    
    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label signInLabel;
    @FXML
    private Button loginButton;
    @FXML
    private Button signupButton;
    @FXML
    private Button backButton;
    
    CurrentGameData currentGameData;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     @FXML
    private void loginButtonClicked(ActionEvent event) {
       // currentGameData.setPlayer1(userNameTextField.getText());//Marina
       
    }
     @FXML
    private void signupButtonClicked(ActionEvent event) {

    }
    
      @FXML
    private void backButtonClicked(ActionEvent event) {

    }
    
}

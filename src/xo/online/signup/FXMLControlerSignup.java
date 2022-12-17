/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.online.signup;

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
import xo.online.handlers.RequestHandler;
import xo.online.handlers.Response;
import xo.online.handlers.ResponseReceiver;

/**
 * FXML Controller class
 *
 * @author Marina
 */
public class FXMLControlerSignup implements Initializable {

    @FXML
    private ImageView levelsImagetwo;
    @FXML
    private Label signupLabel;
    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField confirmPasswordTextField;
    @FXML
    private Label confirmPassword;
    @FXML
    private Button backButton;
    @FXML
    private Button signupButton;

    CurrentGameData currentGameData;//Marina

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

     
    }

    @FXML
    private void signupButtonClicked(ActionEvent event) {
        // currentGameData.setPlayer1(userNameTextField.getText());//Marina

    }

    @FXML
    private void backButtonClicked(ActionEvent event) {

    }
}

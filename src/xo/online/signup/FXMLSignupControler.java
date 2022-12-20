/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.online.signup;

import data.CurrentGameData;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import xo.online.handlers.ParamterizeRequest;
import xo.online.handlers.RequestHandler;
import xo.online.handlers.responses.Response;
import xo.online.handlers.ResponseReceiver;
import xo.online.online_players.FXMLOnlinePlayersController;
import xo.utlis.TicTacToeNavigator;
import xo.online.handlers.RequestType;

/**
 * FXML Controller class
 *
 * @author Marina
 */
public class FXMLSignupControler implements Initializable {

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
    private Stage stage;
    private final RequestHandler requestHandler;

    public FXMLSignupControler() {
        requestHandler = RequestHandler.getInstance((Response response) -> {
            if (response.isSuccess()) {
                System.out.println(response.isSuccess());
                currentGameData.setOnlineName(userNameTextField.getText());
                TicTacToeNavigator.navigateLaterTo(stage, TicTacToeNavigator.ONLINE_PLAYERS);
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void signupButtonClicked(ActionEvent event) {
        if (stage == null) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        }
        if (passwordTextField.getText().equals(confirmPasswordTextField.getText())) {
            try {
                requestHandler.create(RequestType.SIGNUP,
                        new ParamterizeRequest(passwordTextField.getText(), userNameTextField.getText()));
                // currentGameData.setPlayer1(userNameTextField.getText());//Marina
            } catch (IOException ex) {
                //catch me
            }
        }

    }

    @FXML
    private void backButtonClicked(ActionEvent event) {
        try {
            TicTacToeNavigator.previous(event);
        } catch (IOException ex) {
            //catch me
        }
    }
}

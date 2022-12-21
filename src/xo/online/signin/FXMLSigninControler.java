/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.online.signin;

import data.CurrentGameData;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import xo.online.handlers.ParamterizeRequest;
import xo.online.handlers.RequestHandler;
import xo.online.handlers.responses.Response;
import xo.utlis.TicTacToeNavigator;
import xo.online.handlers.RequestType;
import xo.online.handlers.responses.AuthResponse;

/**
 * FXML Controller class
 *
 * @author Marina
 */
public class FXMLSigninControler implements Initializable {

    @FXML
    private ImageView levelsImagetwo;

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
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

    private CurrentGameData currentGameData;
    private RequestHandler requestHandler;

    private Stage stage;
    @FXML
    private Label nameErrorLabel;
    @FXML
    private Label passwordErrorLabel;

    public FXMLSigninControler() {
        currentGameData = CurrentGameData.getInstance();
        requestHandler = RequestHandler.getInstance((Response response) -> {
            if (((AuthResponse) response).getIsSuccess()) {
                currentGameData.setOnlineName(usernameTextField.getText());
                TicTacToeNavigator.navigateLaterTo(stage, TicTacToeNavigator.ONLINE_PLAYERS);
            }
        });
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void loginButtonClicked(ActionEvent event) {
              boolean checkValidName;
        boolean checkValidPassword;

        String userTxt = usernameTextField.getText();
        String pasTxt = passwordPasswordField.getText();
         if (userTxt.isEmpty()) {
            checkValidName = false;
            Platform.runLater(() -> nameErrorLabel.setText("Username is required"));
        } else {
            checkValidName = true;
            nameErrorLabel.setText("");
        }

        if (pasTxt.isEmpty()) {
            checkValidPassword = false;
            Platform.runLater(() -> passwordErrorLabel.setText("Password is required"));
        } else {
            checkValidPassword = true;
            passwordErrorLabel.setText("");
        }
        boolean checkValid = checkValidName && checkValidPassword;

        if (checkValid) {
        
        if (stage == null) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        }
        try {
            requestHandler.create(RequestType.SIGNIN,
                    new ParamterizeRequest(
                            passwordPasswordField.getText(),
                            usernameTextField.getText()));
        } catch (IOException ex) {
            //catch me
            ex.printStackTrace();
        }
    }
    }

    @FXML
    private void signupButtonClicked(ActionEvent event) {
        try {
            TicTacToeNavigator.navigateTo(event, TicTacToeNavigator.SIGNUP);
        } catch (IOException ex) {
            //catch me
        }
    }

    @FXML
    private void backButtonClicked(ActionEvent event) throws IOException {
        TicTacToeNavigator.previous(event);
    }
}

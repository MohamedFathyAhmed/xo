
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.signin;

import data.CurrentGameData;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import xo.online.handlers.ParamterizeRequest;
import xo.online.handlers.RequestHandler;
import xo.online.handlers.Response;
import xo.utlis.TicTacToeNavigator;


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

    public FXMLSigninControler() {
        currentGameData = CurrentGameData.getInstance();
        requestHandler = RequestHandler.getInstance((Response response) -> {
            if (response.isSuccess()) {
                currentGameData.setOnlineName(userNameTextField.getText());
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
        if (stage == null) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        }
        try {
            requestHandler.create(RequestType.SIGNIN,
                    new ParamterizeRequest(
                            passwordPasswordField.getText(),
                            userNameTextField.getText()));
        } catch (IOException ex) {
            //catch me
            ex.printStackTrace();
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


 @FXML
    private void ipButtonClicked(ActionEvent event) {
          // Create the custom dialog.
    Dialog<Pair<String, String>> dialog = new Dialog<>();dialog.setTitle("Connect to server");dialog.setHeaderText("Please insert remoteIP and portNumber");

    ButtonType connectButtonType = new ButtonType("Connect",
            ButtonBar.ButtonData.OK_DONE);dialog.getDialogPane().getButtonTypes().addAll(connectButtonType,ButtonType.CANCEL);

    GridPane grid = new GridPane();grid.setHgap(10);grid.setVgap(10);grid.setPadding(new Insets(20,150,10,10));

    TextField ipAddress = new TextField();ipAddress.setPromptText("Please enter IP:");
    TextField portNumber = new TextField();portNumber.setPromptText("Please enter portNumber:");

    grid.add(new Label("Ip Address:"),0,0);grid.add(ipAddress,1,0);grid.add(new Label("Port Number:"),0,1);grid.add(portNumber,1,1);

    Node connectButton = dialog.getDialogPane().lookupButton(connectButtonType);connectButton.setDisable(true);

    ipAddress.textProperty().addListener((observable,oldValue,newValue)->
    {
        connectButton.setDisable(newValue.trim().isEmpty());
    });

    dialog.getDialogPane().setContent(grid);

    Platform.runLater(()->ipAddress.requestFocus());

    dialog.setResultConverter(dialogButton->
    {
        if (dialogButton == connectButtonType) {
            return new Pair<>(ipAddress.getText(), portNumber.getText());
        }
        return null;
    });

    Optional<Pair<String, String>> result = dialog.showAndWait();

    result.ifPresent(connectToServer->
    {
        String remoteIP = connectToServer.getKey();
        String portNum = connectToServer.getValue();
        // set ip and port
});
   
    }
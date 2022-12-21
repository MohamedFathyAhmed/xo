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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import xo.online.handlers.RequestHandler;
import xo.online.handlers.responses.Response;
import xo.online.signin.FXMLSigninControler;
import xo.utlis.TicTacToeNavigator;

/**
 *
 * @author mohamed
 */
public class FXMLModesController implements Initializable {

    @FXML
    Button logoutButton, offlineHistoryButton, onlineHistoryButton;

    private final CurrentGameData currentGameData;
    private final RequestHandler requestHandler;
    private Stage stage;
//

    private int port = 5005;
    private String ipAddress = "127.0.0.1";

    public FXMLModesController() {
        currentGameData = CurrentGameData.getInstance();
        requestHandler = RequestHandler.getInstance(
                (Response response) -> {
//                    if (response.isSuccess()) {
                    TicTacToeNavigator.navigateLaterTo(
                            stage,
                            TicTacToeNavigator.SIGNIN
                    );
//                    }
                });
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
    private void offlineHistoryButtonClicked(ActionEvent event) throws IOException {
        TicTacToeNavigator.navigateTo(event, TicTacToeNavigator.HISTORY);    
    }
    

    
    @FXML
    private void onlineHistoryButtonClicked(ActionEvent event) throws IOException {
        currentGameData.setGameMode(GameMode.MULTIPLAYER);
        TicTacToeNavigator.navigateTo(event, TicTacToeNavigator.TWO_PLAYER_NAME_CHOOSER);
    }

    
    
    @FXML
    private void onlineMultiPlayersButtonClicked(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentGameData.setGameMode(GameMode.ONLINE);
        if (currentGameData.isLoggedIn()) {
            TicTacToeNavigator.navigateTo(event, TicTacToeNavigator.ONLINE_PLAYERS);
        } else {
            showIpDialog(event);
        }
    }

    private void connectToServer(Stage stage) {
        if (requestHandler.isConnected()) {
            TicTacToeNavigator.navigateLaterTo(
                    stage,
                    TicTacToeNavigator.SIGNIN
            );

        } else {
            try {
                requestHandler.connect(port, ipAddress);
            } catch (IOException ex) {
                //catch me
                //server is down
            }
        }
    }

    private void showIpDialog(ActionEvent event) {
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Connect to server");
        dialog.setHeaderText("Please insert remoteIP and portNumber");

        ButtonType connectButtonType = new ButtonType("Connect",
                ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(connectButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        final TextField ipAddressTextField = new TextField();
        ipAddressTextField.setPromptText("Please enter IP:");
        TextField portNumber = new TextField();
        portNumber.setPromptText("Please enter portNumber:");
        ipAddressTextField.setText(ipAddress);
        portNumber.setText(port + "");

        grid.add(new Label("Ip Address:"), 0, 0);
        grid.add(ipAddressTextField, 1, 0);
        grid.add(new Label("Port Number:"), 0, 1);
        grid.add(portNumber, 1, 1);

        Node connectButton = dialog.getDialogPane().lookupButton(connectButtonType);
        connectButton.setDisable(true);

        ipAddressTextField.textProperty().addListener((observable, oldValue, newValue)
                -> {
            connectButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> ipAddressTextField.requestFocus());

        dialog.setResultConverter(dialogButton
                -> {
            if (dialogButton == connectButtonType) {
                return new Pair<>(ipAddressTextField.getText(), portNumber.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(connectToServer
                -> {
            ipAddress = connectToServer.getKey();
            port = Integer.parseInt(connectToServer.getValue());
            // set ip and port
            connectToServer(stage);
        });
    }
}

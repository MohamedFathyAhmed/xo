/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.online.online_players;

import data.CurrentGameData;
import data.GameShape;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import xo.board.FXMLOnlineBoardContoller;
import xo.history.FXMLOnlineHistoryController;
import xo.online.handlers.responses.GameRequestResponse;
import xo.online.handlers.responses.HistoryResponse;
import xo.online.handlers.responses.InfoResponse;
import xo.online.handlers.responses.OnlinePlayersResponse;
import xo.online.handlers.ParamterizeRequest;
import xo.online.handlers.Request;
import xo.online.handlers.RequestHandler;
import xo.online.handlers.responses.Response;
import xo.online.handlers.RequestType;
import xo.online.handlers.responses.RequestGameAnswerResponse;
import xo.utlis.TicTacToeNavigator;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class FXMLOnlinePlayersController implements Initializable {

    @FXML
    private Button refreshButton, stageButton;
    @FXML
    private TableView<OnlinePlayer> onlinePlayersTableView;
    @FXML
    private TableColumn<OnlinePlayer, String> onlinePlayersTableColumn;

    private Stage stage;
    private final CurrentGameData currentGameData;
    /**
     * Initializes the controller class.
     */
    private final RequestHandler requestHandler;
    private Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    private String receiver;

    public FXMLOnlinePlayersController() {
        currentGameData = CurrentGameData.getInstance();

        requestHandler = RequestHandler.getInstance((Response response) -> {
            if (response instanceof OnlinePlayersResponse) {
                updateOnlinePlayers(((OnlinePlayersResponse) response).getPlayers());
            } else if (response instanceof HistoryResponse) {

            } else if (response instanceof GameRequestResponse) {
                showRequestGameDialog(((GameRequestResponse) response).getSender());
            } else if (response instanceof RequestGameAnswerResponse) {
                if (((RequestGameAnswerResponse) response).getIsSuccess()) {
                    TicTacToeNavigator.navigateLaterTo(stage, new FXMLOnlineBoardContoller(stage), TicTacToeNavigator.ONLINE_BOARD);
                }
            } else if (response instanceof InfoResponse) {

            }

        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshButton.fire();
        onlinePlayersTableColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
    }

    @FXML
    private void getStage(ActionEvent event) {
        if (stage == null) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        }
    }

    @FXML
    private void backButtonClicked(ActionEvent event) {
        try {
            TicTacToeNavigator.navigateTo(event, TicTacToeNavigator.MODES);
        } catch (IOException ex) {

            ex.printStackTrace();
        }

    }

    @FXML
    private void refreshButtonClicked(ActionEvent event) {
        try {
            requestHandler.create(RequestType.ONLINE_PLAYERS, new Request(""));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void sendButtonClicked(ActionEvent event) {
        if (stage == null) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        }
        String player = onlinePlayersTableView.getSelectionModel().getSelectedItem().getUsername();
        if (player != null && !player.isEmpty()) {
            try {
                receiver = onlinePlayersTableView
                        .getSelectionModel()
                        .getSelectedItem()
                        .getUsername();

                requestHandler.create(RequestType.REQUEST_GAME,
                        new Request(receiver));
            } catch (IOException ex) {
                //catch me
            }
        }
        currentGameData.setPlayer1(receiver);
        currentGameData.setPlayer2(currentGameData.getOnlineName());
    }

    private void updateOnlinePlayers(String[] players) {
        try {
            onlinePlayersTableView.getItems().clear();
            for (String username : players) {
                onlinePlayersTableView.getItems().add(new OnlinePlayer(username));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showRequestGameDialog(String sender) {
        //show me
        Platform.runLater(() -> {
            alert.setTitle("Game request");
            alert.setHeaderText(sender + " request to play with you");
            alert.setContentText(null);
            alert.getButtonTypes().clear();
            alert.getButtonTypes().add(new ButtonType("Accept"));
            alert.getButtonTypes().add(ButtonType.CANCEL);
            Optional<ButtonType> clickedButton = alert.showAndWait();
            ParamterizeRequest gameRequestAnswer;
            stageButton.fire();

            if (clickedButton.get() == ButtonType.CANCEL) {
                gameRequestAnswer = new ParamterizeRequest("false", sender);
            } else {
                gameRequestAnswer = new ParamterizeRequest("true", sender);
                currentGameData.setPlayer1(currentGameData.getOnlineName());
                currentGameData.setPlayer2(sender);
                currentGameData.setPlayer1Shape(GameShape.X);
                currentGameData.setPlayer2Shape(GameShape.O);
            }
            try {
                requestHandler.create(RequestType.REQUEST_GAME_ANSWER, gameRequestAnswer);
            } catch (IOException ex) {
                //catch me
                ex.printStackTrace();
            }
        });
    }

    @FXML
    private void onlineHistoryButtonClicked(ActionEvent event) throws IOException {
        TicTacToeNavigator.navigateTo(event, new FXMLOnlineHistoryController(), TicTacToeNavigator.ONLINE_HISTORY);
    }

}

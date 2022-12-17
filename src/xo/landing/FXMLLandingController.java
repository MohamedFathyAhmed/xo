/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.landing;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import xo.utlis.TicTacToeExecutorService;
import xo.utlis.TicTacToeNavigator;

/**
 * FXML Controller class
 *
 * @author Apple
 */
public class FXMLLandingController implements Initializable {

    private final Stage stage;

    public FXMLLandingController(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TicTacToeExecutorService.getInstance().schedule(() -> {
            Platform.runLater(() -> {
                try {
                    TicTacToeNavigator.navigateTo(stage, TicTacToeNavigator.MODES);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        }, 2L, TimeUnit.SECONDS);
    }
}

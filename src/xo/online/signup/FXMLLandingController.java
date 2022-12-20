/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.online.signup;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import xo.modes.FXMLModesController;
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
        new Thread(() -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException ex) {
                //catch me
            }
            TicTacToeNavigator.navigateLaterTo(stage, TicTacToeNavigator.MODES);
        }).start();
    }
}

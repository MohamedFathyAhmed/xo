/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.landing;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
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
                Thread.sleep(1000L);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            TicTacToeNavigator.navigateLaterTo(stage, TicTacToeNavigator.MODES);
        }).start();
    }
}

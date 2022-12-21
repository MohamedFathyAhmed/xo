/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo;

import data.database.DataAccessLayer;
import data.database.models.Game;
import data.database.models.Play;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javafx.application.Application;
import javafx.stage.Stage;
import xo.landing.FXMLLandingController;
import xo.online.handlers.RequestHandler;
import xo.utlis.TicTacToeNavigator;

/**
 *
 * @author mohamed
 */
public class Xo extends Application {

    private RequestHandler requestHandler;

    @Override
    public void start(Stage stage) throws Exception {
        DataAccessLayer.connect();
        requestHandler = RequestHandler.getInstance((String message) -> {
            try {
                TicTacToeNavigator.navigateLaterTo(stage, TicTacToeNavigator.MODES);
            } catch (Exception e) {

            }
        });

        stage.setResizable(false);
        TicTacToeNavigator.navigateTo(stage, new FXMLLandingController(stage), TicTacToeNavigator.LANDING);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        requestHandler.disconnect();
        DataAccessLayer.disconnect();
        super.stop();
    }

}

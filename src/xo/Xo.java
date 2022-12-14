/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import xo.utlis.Navigator;
import xo.utlis.TicTacToeExecutorService;

/**
 *
 * @author mohamed
 */
public class Xo extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
<<<<<<< HEAD
        
=======
>>>>>>> 01bafe828be58694bcf218b2990d36e3793bb4e0
        Navigator.navigateTo(stage, FXMLLoader.load(getClass().getResource("board/FXMLBoard.fxml")), "modes");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        TicTacToeExecutorService.getInstance().stop();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import xo.utlis.Navigator;

/**
 *
 * @author mohamed
 */
public class Xo extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        Navigator.navigateTo(stage, FXMLLoader.load(getClass().getResource("modes/FXMLModes.fxml")), "modes");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

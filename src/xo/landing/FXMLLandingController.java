/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.landing;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import xo.utlis.Navigator;

/**
 * FXML Controller class
 *
 * @author Apple
 */
public class FXMLLandingController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new SplashScreen().start();
    }

    class SplashScreen extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(1500);
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Stage s = (Stage) (((Button) event.getSource()).getScene().getWindow());
                            Navigator.navigateTo(s, FXMLLoader.load(getClass().getResource("modes/FXMLModes.fxml")), "modes");
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLLandingController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                    
                });
            } catch (InterruptedException ex) {
                Logger.getLogger(FXMLLandingController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo;

import javafx.application.Application;
import javafx.stage.Stage;
import xo.landing.FXMLLandingController;
import xo.utlis.TicTacToeExecutorService;
import xo.utlis.TicTacToeNavigator;

/**
 *
 * @author mohamed
 */
public class Xo extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        TicTacToeNavigator.navigateTo(stage, new FXMLLandingController(stage), TicTacToeNavigator.LANDING);
//        TicTacToeNavigator.navigateTo(stage,TicTacToeNavigator.MEDIA);
    
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
        super.stop();
    }
    

}

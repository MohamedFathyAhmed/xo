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
import xo.board.BoardMultiPlayerModeController;
import xo.board.BoardSinglePlayerModeController;
import xo.board.FXMLBoardController;
import xo.utlis.Navigator;

/**
 *
 * @author mohamed
 */
public class Xo extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);

        FXMLLoader loader = new FXMLLoader(xo.Xo.class.getResource("board/FXMLBoard.fxml"));
        FXMLBoardController controller = new BoardSinglePlayerModeController(stage);
        loader.setController(controller);
        stage.setScene(new Scene(loader.load()));
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

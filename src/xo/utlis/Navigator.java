/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xo.utlis;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import xo.board.BoardMultiPlayerOflineModeController;
//import xo.board.BoardMultiPlayerOnlineModeController;
//import xo.board.BoardSinglePlayerModeController;

/**
 *
 * @author mo_fathy
 */
public class Navigator {
    
    
    public static void navigateTo(Stage stage, Parent r, String title) {
        Parent root = r;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }
    
       
    public static void navigateTo(Node node, Parent root, String title) {
        Stage stage=(Stage)node.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }


//    //Marina
//    public static void navigateToMultiPlayerOflineModeScene(){
//        FXMLLoader loader = new FXMLLoader(xo.Xo.class.getResource("board/FXMLBoard.fxml"));
//        BoardMultiPlayerOflineModeController controller=new BoardMultiPlayerOflineModeController();
//        loader.setController(controller);
//}
// 
//     //Marina
//    public static void navigateToMultiPlayerOnlineModeScene(){
//        FXMLLoader loader = new FXMLLoader(xo.Xo.class.getResource("board/FXMLBoard.fxml"));
//        BoardMultiPlayerOnlineModeController controller=new BoardMultiPlayerOnlineModeController();
//        loader.setController(controller);
//    }
     //Marina
//    public static void navigateToSinglePlayerOnlineModeScene(){
//        FXMLLoader loader = new FXMLLoader(xo.Xo.class.getResource("board/FXMLBoard.fxml"));
//        BoardSinglePlayerModeController controller=new BoardSinglePlayerModeController();
//        loader.setController(controller);
//    }
//        


}

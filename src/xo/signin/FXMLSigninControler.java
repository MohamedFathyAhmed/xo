/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.signin;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import data.database.models.Player;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * FXML Controller class
 *
 * @author Marina
 */
public class FXMLSigninControler implements Initializable {
Thread thread;
    @FXML
    private ImageView levelsImagetwo;
    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label signInLabel;
    @FXML
    private Button backButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button signupButton;
    @FXML
    private Label passwordErrorLabel;
    @FXML
    private Label nameErrorLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     @FXML
    private void backButtonClicked(ActionEvent event) {

    }
     @FXML
    private void loginButtonClicked(ActionEvent event) throws IOException {
      Socket    socket = new Socket("127.0.0.1" , 5005); 
      DataInputStream  dis = new DataInputStream(socket.getInputStream ());
      PrintStream  ps = new PrintStream(socket.getOutputStream ());
        
        //////////////////////////////////////////////
        
        boolean checkValidName;
        boolean checkValidPassword;

        String userTxt = userNameTextField.getText();
        String pasTxt = passwordTextField.getText();

        if (userTxt.isEmpty()) {
            checkValidName = false;
            Platform.runLater(() -> nameErrorLabel.setText("Username is required"));
        } else {
            checkValidName = true;
            nameErrorLabel.setText("");
        }

        if (pasTxt.isEmpty()) {
            checkValidPassword = false;
            Platform.runLater(() -> passwordErrorLabel.setText("Password is required"));
        } else {
            checkValidPassword = true;
            passwordErrorLabel.setText("");
        }
        boolean checkValid = checkValidName && checkValidPassword;

        if (checkValid) {

            ps.println("signIn;;"+userTxt+";;"+pasTxt);
            thread = new Thread(() -> {
                try {
                    String replyMsg;
                    replyMsg = dis.readLine();
                    System.out.println(replyMsg);
                    String[] allReplyMsg = replyMsg.split(";;");
                    if (allReplyMsg[0].equals("true")) {
                        try {
                            Player.setName(allReplyMsg[1]);
                            Player.setScore(Integer.parseInt(allReplyMsg[2]));
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        // navigation
                        //Platform.runLater(this::goToClientPage);
                    } else {
                        Platform.runLater(() -> {
                            if (allReplyMsg[1].equals("isOnlineInOtherPlace")) {
                                //  alert"Your account is open in other desktop";
                            } else {
                                //   alert"Try Again wrong password or username";
                            }
                        });
                        
                        System.out.println(Player.getName());
                    }
                    thread.stop();
                } catch (IOException ex) {
                    Logger.getLogger(FXMLSigninControler.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            thread.start();
    }
    }
      @FXML
    private void signupButtonClicked(ActionEvent event) {

    }
    
}

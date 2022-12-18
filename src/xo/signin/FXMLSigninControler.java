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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Marina
 */
public class FXMLSigninControler implements Initializable {

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
    
    Socket socket;
    DataInputStream dis ;
    PrintStream ps;
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
        socket = new Socket("127.0.0.1" , 5005); 
        String message =userNameTextField.getText();
        
        dis = new DataInputStream(socket.getInputStream ());
        ps = new PrintStream(socket.getOutputStream ());
        ps.println(message);
        String replyMsg = dis.readLine();
        System.out.println(replyMsg);
       userNameTextField.clear();
    
    }
    
      @FXML
    private void signupButtonClicked(ActionEvent event) {

    }
    
}

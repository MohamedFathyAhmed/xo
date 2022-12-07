/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.levels;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Marina
 */
public class FXMLLevelsController implements Initializable {

    @FXML
    private ImageView levelsImagetwo;
    @FXML
    private Label ChooseLevelLabel;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnHistory;
    @FXML
    private Button btnHard;
    @FXML
    private Button btnMedium;
    @FXML
    private Button btnEsay;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void backButtonAction(ActionEvent event) {
    }

    @FXML
    private void historyButtonAction(ActionEvent event) {
    }

    @FXML
    private void hardButtonAction(ActionEvent event) {
    }

    @FXML
    private void mediumButtonAction(ActionEvent event) {
    }

    @FXML
    private void esayButtonAction(ActionEvent event) {
    }
    
}

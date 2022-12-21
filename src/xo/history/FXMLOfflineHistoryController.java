/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.history;

import data.database.DataAccessLayer;
import data.database.models.Game;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import xo.board.FXMLBoardReplayGameController;
import xo.utlis.TicTacToeNavigator;

/**
 * FXML Controller class
 *
 * @author Marina
 */
public class FXMLOfflineHistoryController extends FXMLHistoryController {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);

        try {
            updateTableViewItems(DataAccessLayer.getGames());
        } catch (SQLException ex) {

        }
    }

}

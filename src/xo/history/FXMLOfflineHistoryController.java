/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.history;

import data.database.DataAccessLayer;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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

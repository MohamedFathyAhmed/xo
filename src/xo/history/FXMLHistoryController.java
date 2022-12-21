/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.history;

import data.database.DataAccessLayer;
import data.database.models.Game;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Marina
 */
public class FXMLHistoryController implements Initializable {

    @FXML
    private AnchorPane historyTable;
    @FXML
    private TableColumn<Game, Integer> gameNumberCulme;////shoud y type in game class
    @FXML
    private TableColumn<Game, String> playerOneNameColumn;
    @FXML
    private TableColumn<Game, String> playerTwoNameColumn;
    @FXML
    private TableColumn<Game, Date> dateColumn;
    @FXML
    private TableColumn<Game, String> winerColumn;
    @FXML
    private Button backButton;
    @FXML
    private TableView<Game> historyTableView;
    @FXML
    private Button viewRecordButton;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
       
            
            //gameNumberCulme.setCellValueFactory(new PropertyValueFactory<Game,Integer>(i));
            
            playerOneNameColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("player1"));
            playerTwoNameColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("player2"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<Game, Date>("date"));
            winerColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("wonPLayer"));
            viewRecordButton.setVisible(false);
          
       
         
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////  
        
          ObservableList<Game> games= historyTableView.getItems();
            historyTableView.setItems(games);
          
             
        //historyTableView.getSelectionModel().setCellSelectionEnabled(true);
        for (int i = 0; i < 100; i++) {
            games.add(new Game("1","aaaaa","bbbbbb","da","mmm","nn"));
        }
          
         historyTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null) {
         viewRecordButton.setVisible(true);
        //historyTableView.getSelectionModel().clearSelection();
    }
});
      
            }    

    @FXML
    private void backButtonClicked(ActionEvent event) {
        
        //viewRecordButton.setVisible(true);
    }
    private void displayGameHistory() throws SQLException{
        int historyLength = DataAccessLayer.getGames().length;/////
        Game [] gamesArrayHistory =DataAccessLayer.getGames();
      
       
          
          if(historyTableView.getSelectionModel().getSelectedItem().equals(true)){
              viewRecordButton.setVisible(true);
          }
          
          
          
        
           
            /*
            gameNumberCulme.setCellValueFactory(new PropertyValueFactory<Game,Integer>(i));
            playerOneNameColumn.setCellValueFactory(new PropertyValueFactory<Game, String>(gamesArrayHistory[i].getPlayer1()));
            playerTwoNameColumn.setCellValueFactory(new PropertyValueFactory<Game, String>(gamesArrayHistory[i].getPlayer2()));
            dateColumn.setCellValueFactory(new PropertyValueFactory<Game, Date>(gamesArrayHistory[i].getDate().toString()));
            winerColumn.setCellValueFactory(new PropertyValueFactory<Game, String>(gamesArrayHistory[i].getWonPLayer()));
            */
            //HistoryTable.setItems(game);
            ////////////////////////////////////////////
//            for (int i =0 ; i<historyLength ; i++){
//                historyTableView.setItems((ObservableList<Game>) gamesArrayHistory[i]);
//            }
    }

    @FXML
    private void viewButtonClicked(ActionEvent event) {
        viewRecordButton.setVisible(false);
    }
}

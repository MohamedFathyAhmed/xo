/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.database;

import data.database.models.Game;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author mohamed
 */
public class DataAccessLayer {

    private static Connection connection;
    private static ResultSet record;
    
     //////////////////////////////marina
    private static ResultSet games;
    private static Statement quary;
    private static PreparedStatement pst;
    /////////////////////////////
    
    public void connect() throws SQLException {
        connection = TicTacToeDatabase.getInstance().getConnection();
    }
    
   
    
    public void disconnect() throws SQLException{
       TicTacToeDatabase.getInstance().disconnect();
    }

    
    ////////////////////////////////////////////////////////////////marina
    /////public , game || game table
    /////geter
    public static ResultSet updateGameHistory() throws SQLException {

        quary = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        games = quary.executeQuery("select * from GAME");
        return games;

    }

    private static Game getGame() throws SQLException {
        Game game = new Game(games.getString("player_1"),
                games.getString("player_2"),
                games.getString("date"),
                games.getString("won_player"),
                games.getString("player1Shape"),
                games.getString("player1Shape"));
        return game;
    }

    private static int getGameHistoryCount() throws SQLException {
        quary = connection.createStatement();
        ResultSet gameCount = quary.executeQuery("select count(*) count from GAME");
        return gameCount.getInt("count");

    }

    public static Game[] getGames() throws SQLException {
        Game[] gamesArray = new Game[getGameHistoryCount()];
        int i = 0;
        while (games.next()) {
            gamesArray[i++] = getGame();
        }
        return gamesArray;
    }

////////////////////////////////////////////////////////////////
    /////seter
    
   // private static void setGame(String player_1,String player_2,String date,String wonPlayer){}
     private static void setGame(Game game) throws SQLException  {
        games.moveToInsertRow();
        games.updateString("PLAYER_1", game.getPlayer1());
        games.updateString("PLAYER_2", game.getPlayer2());
        games.updateString("DATE", game.getDate());
        games.updateString("WON_PLAYER", game.getWonPLayer());
        games.insertRow();
        connection.commit();
       }
     
     
     //////////////////////////////////////////////////////////////////////////////
    
}

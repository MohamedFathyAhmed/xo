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


    public static Boolean CreatePlayer(String name) throws SQLException {
        boolean isExist = false;
        Boolean res = false;
        isExist = checkName(name);
        if (isExist == false) {
            try {

                PreparedStatement con = connection.prepareStatement("INSERT INTO PLAYER (NAME ) VALUES (?)", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                con.setString(1, name);
                con.executeUpdate();
                ResultSet rs = con.executeQuery();
                res = true;

            } catch (SQLException e) {
                res = false;
            }
        } else {
            res = false;
        }

        return res;
    }

    public static boolean checkName(String name) {
        boolean flag = false;
        try {
            PreparedStatement con = connection.prepareStatement("SELECT * FROM players WHERE NAME=?");
            con.setString(1, name);
            ResultSet rs = con.executeQuery();
            boolean isExist = rs.next();

            if (isExist) {
                flag = true;
            } else {
                flag = false;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            return flag;
        }

    }

    public static boolean getPlayer(String name) {
        boolean res = false;
        try {
            PreparedStatement con = connection.prepareStatement("SELECT * FROM players WHERE NAME=?");
            con.setString(1, name);
            ResultSet rs = con.executeQuery();
            boolean isExist = rs.next();

            if (isExist) {
                res = true;
            } else {
                res = false;
            }
        } catch (SQLException ex) {
            res = false;
            System.out.println(ex);
        } finally {
            return res;
        }

    }

    public static void newGame(Game game) throws SQLException {
        
        PreparedStatement pst = connection.prepareStatement("INSERT INTO GAME (PLAYER_1, PLAYER_2, DATE, WON_PLAYER) VALUES (?,?,?,?)");
 pst.setString(1,game.getPlayer1());
  pst.setString(2,game.getPlayer2());
   pst.setString(3,game.getDate());
    pst.setString(4,game.getWonPLayer());
pst.execute();
        connection.commit();
    }
    
    
   public static void newPlay (Play play) throws SQLException {
PreparedStatement pst = connection.prepareStatement ("INSERT INTO PLAY (POSITION, PLAYER, TIME, RECORDED, IDGAME) VALUES (?,?,?,?,?)");
pst.setString(1,play.getPosition());
  pst.setString(2,play.getPlayer());
   pst.setString(3,play.);
    pst.setString(4,play.);
pst. execute();
connection.commit ();
    
    
   }
   

} 

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

    public void disconnect() throws SQLException {
        TicTacToeDatabase.getInstance().disconnect();
    }

    ////////////////////////////////////////////////////////////////marina
    public static Game[] getGames() throws SQLException {
        return getGamesFromResultSet(connection.createStatement()
                .executeQuery("SELECT * FROM GAME"));

    }

    private static Game getGame(ResultSet gamesResultSet) throws SQLException {
        Game game = new Game(gamesResultSet.getString("player_1"),
                gamesResultSet.getString("player_2"),
                gamesResultSet.getString("date"),
                gamesResultSet.getString("won_player"),
                gamesResultSet.getString("player1Shape"),
                gamesResultSet.getString("player1Shape"));
        return game;
    }

    private static int getGameHistoryCount() throws SQLException {
        ResultSet gameCount = connection.createStatement().executeQuery("select count(*) count from GAME");
        return gameCount.getInt("count");

    }

    public static Game[] getGamesFromResultSet(ResultSet gamesResultSet) throws SQLException {
        Game[] gamesArray = new Game[getGameHistoryCount()];
        int i = 0;
        while (gamesResultSet.next()) {
            gamesArray[i++] = getGame(gamesResultSet);
        }
        return gamesArray;
    }

    private static void setGame(Game game) throws SQLException {//id

        connection.createStatement().execute("INSET INTO GAME (PLAYER_1,PLAYER_2,DATE,WON_PLAYER)VALUES('"
                + game.getPlayer1()
                + "','"
                + game.getPlayer2()
                + "','"
                + game.getDate()
                + "','"
                + game.getWonPLayer()
                + "');");

    }

}

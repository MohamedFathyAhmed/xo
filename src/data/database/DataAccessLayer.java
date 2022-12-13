/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.database;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author mohamed
 */
import data.database.models.Game;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataAccessLayer {

    private static Connection connection;
    private static ResultSet record;
    ////////////////marina
    private static ResultSet games;
    private static Statement quary;
    //private static int counter;

    /////////////////////////
    public void connect() throws SQLException {
        connection = TicTacToeDatabase.getInstance().getConnection();
    }

    public void disconnect() throws SQLException {
        TicTacToeDatabase.getInstance().disconnect();
    }

    ////////////////////////////////////marina
    public static ResultSet updateGameHistory() throws SQLException {

        quary = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        games = quary.executeQuery("select * from GAME");
        return games;

    }

    private static Game getGame() throws SQLException {
        Game game = new Game(games.getString("player_1"),
                games.getString("player_2"),
                games.getDate("date"),
                games.getString("won_player"));
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
}

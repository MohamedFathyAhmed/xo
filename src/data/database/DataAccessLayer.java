/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.database;

import data.database.models.Game;
import data.database.models.Play;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mohamed
 */
public class DataAccessLayer {

    private static Connection connection;

    public static void connect() throws SQLException {
        connection = TicTacToeDatabase.getInstance().getConnection();
    }

    public static void disconnect() throws SQLException {
        TicTacToeDatabase.getInstance().disconnect();
    }

    private static String[] getNamesFromResultSet(ResultSet playersResultSet) throws SQLException {
        String[] players = new String[getPlayersCount()];
        int i = 0;

        while (playersResultSet.next()) {
            players[i++] = playersResultSet.getString(1);

        }
        return players;
    }

    public static boolean insertPlayer(String name) throws SQLException {
        return connection.createStatement().execute("INSERT INTO PLAYER (name) VALUES ('" + name + "'");
    }

    private static int getPlayersCount() throws SQLException {
        ResultSet gameCount = connection.createStatement().executeQuery("SELECT (*) COUNT FROM PLAYER");
        return gameCount.getInt(1);

    }

    ////////////////////////////////////////////////////////////////marina
    public static Game[] getGames() throws SQLException {
        return getGamesFromResultSet(connection.createStatement()
                .executeQuery("SELECT * FROM GAME"));

    }

    public static String[] getPlayers() throws SQLException {
        return getNamesFromResultSet(connection.createStatement().executeQuery("SELECT * FROM PLAYER"));

    }

    private static Game getGame(ResultSet gamesResultSet) throws SQLException {
        Game game = new Game(
                gamesResultSet.getInt("id")+"",
                gamesResultSet.getString("player_1"),
                gamesResultSet.getString("player_2"),
                gamesResultSet.getString("date"),
                gamesResultSet.getString("won_player"));
        return game;
    }

    private static int getGameHistoryCount() throws SQLException {
        ResultSet gameCount = connection.createStatement().executeQuery("select count(*) count from GAME");
        return gameCount.getInt(1);

    }

    public static Game[] getGamesFromResultSet(ResultSet gamesResultSet) throws SQLException {
        Game[] gamesArray = new Game[getGameHistoryCount()];
        int i = 0;
        while (gamesResultSet.next()) {
            gamesArray[i++] = getGame(gamesResultSet);
        }
        return gamesArray;
    }

    private static int insertGame(Game game) throws SQLException {//id
        connection.createStatement().execute("INSET INTO GAME (PLAYER_1,PLAYER_2,DATE,WON_PLAYER)VALUES('"
                + game.getPlayer1()
                + "','"
                + game.getPlayer2()
                + "','"
                + game.getDate()
                + "','"
                + game.getWonPLayer()
                + "');");

        ResultSet idResultSet = connection.createStatement().executeQuery("SELECT ID FROM GAME ORDER BY ID DESC FETCH FIRST 1 ROWS ONLY");
        idResultSet.next();
        return idResultSet.getInt(1);

    }

    public static Play[] getGamePlays(int gameId) throws SQLException {
        return getPlaysFromResultSet(
                connection.createStatement()
                .executeQuery("SELECT * FROM PLAY"
                        + " WHERE GAME_ID='" + gameId + "' ORDER BY ID"));
    }

    private static Play[] getPlaysFromResultSet(ResultSet playsResultSet) throws SQLException {
        List<Play> plays = new ArrayList<>();

        while (playsResultSet.next()) {
            plays.add(new Play(
                    playsResultSet.getInt("POSITION") + "",
                    playsResultSet.getString("PLAYER")));
        }
        return (Play[]) plays.toArray();
    }



}

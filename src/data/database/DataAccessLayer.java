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
import java.sql.ResultSet;
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
        return connection.createStatement().execute("INSERT INTO PLAYER (NAME) VALUES ('" + name + "')");
    }

    private static int getPlayersCount() throws SQLException {
        ResultSet gameCount = connection.createStatement().executeQuery("SELECT COUNT(*) FROM PLAYER WHERE NAME!='EASY'");
        gameCount.next();
        return gameCount.getInt(1);

    }

    ////////////////////////////////////////////////////////////////marina
    public static Game[] getGames() throws SQLException {
        Game[] games = getGamesFromResultSet(connection.createStatement()
                .executeQuery("SELECT * FROM GAME"));
        return games;
    }

    public static String[] getPlayers() throws SQLException {
        return getNamesFromResultSet(connection.createStatement().executeQuery("SELECT * FROM PLAYER"
                + " WHERE NAME !='EASY'"));

    }

    private static Game getGame(ResultSet gamesResultSet) throws SQLException {
        Game game = new Game(
                gamesResultSet.getInt("ID") + "",
                gamesResultSet.getString("PLAYER_1"),
                gamesResultSet.getString("PLAYER_2"),
                gamesResultSet.getString("DATE"),
                gamesResultSet.getBoolean("RECORDED") + "",
                gamesResultSet.getString("WON_PLAYER"),
                gamesResultSet.getString("PLAYER_1_SHAPE"),
                gamesResultSet.getString("PLAYER_2_SHAPE"));
        return game;
    }

    private static int getGameHistoryCount() throws SQLException {
        ResultSet gameCount = connection.createStatement().executeQuery("SELECT COUNT(*) FROM GAME");
        gameCount.next();
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

    public static int insertGame(Game game) throws SQLException {//id
        if (game.getWonPLayer() != null) {
            connection.createStatement().execute("INSERT INTO GAME ("
                    + "PLAYER_1, "
                    + "PLAYER_2, "
                    + "PLAYER_1_SHAPE,"
                    + "PLAYER_2_SHAPE,"
                    + "DATE,"
                    + "WON_PLAYER,"
                    + "RECORDED"
                    + ")"
                    + " VALUES('"
                    + game.getPlayer1()
                    + "','"
                    + game.getPlayer2()
                    + "','"
                    + game.getPlayer1Shape()
                    + "','"
                    + game.getPlayer2Shape()
                    + "','"
                    + game.getDate()
                    + "','"
                    + game.getWonPLayer()
                    + "','"
                    + game.getIsRecorded() + ""
                    + "')");
        }else{
            
            connection.createStatement().execute("INSERT INTO GAME ("
                    + "PLAYER_1, "
                    + "PLAYER_2, "
                    + "PLAYER_1_SHAPE,"
                    + "PLAYER_2_SHAPE,"
                    + "DATE,"
                    + "WON_PLAYER,"
                    + "RECORDED"
                    + ")"
                    + " VALUES('"
                    + game.getPlayer1()
                    + "','"
                    + game.getPlayer2()
                    + "','"
                    + game.getPlayer1Shape()
                    + "','"
                    + game.getPlayer2Shape()
                    + "','"
                    + game.getDate()
                    + "',"
                    + game.getWonPLayer()
                    + ",'"
                    + game.getIsRecorded() + ""
                    + "')");
        }

        ResultSet idResultSet = connection.createStatement().executeQuery("SELECT ID "
                + "FROM GAME "
                + "ORDER BY ID DESC "
                + "FETCH FIRST 1 ROWS ONLY");
        idResultSet.next();
        return idResultSet.getInt(1);

    }

    public static List<Play> getGamePlays(int gameId) throws SQLException {
        List<Play> plays = getPlaysFromResultSet(
                connection.createStatement()
                        .executeQuery("SELECT * FROM PLAY"
                                + " WHERE GAME_ID=" + gameId + " ORDER BY ID"));

        return plays;
    }

    private static List<Play> getPlaysFromResultSet(ResultSet playsResultSet) throws SQLException {
        List<Play> plays = new ArrayList<>();

        while (playsResultSet.next()) {
            plays.add(new Play(
                    playsResultSet.getInt("POSITION") + "",
                    playsResultSet.getString("PLAYER")));
        }
        return plays;
    }

    public static void insertPlays(List<Play> plays, int gameId) throws SQLException {
        for (Play play : plays) {
            connection.createStatement().execute("INSERT INTO PLAY (PLAYER , GAME_ID ,POSITION) "
                    + "VALUES ('" + play.getPlayer() + "',"
                    + gameId
                    + ","
                    + play.getPosition()
                    + ")");
        }

    }

}

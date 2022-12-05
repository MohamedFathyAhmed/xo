package database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.prefs.Preferences;
import org.apache.derby.jdbc.EmbeddedDriver;

public class TicTacToeDatabase {

    private final String gameTable = " create table GAME "
            + " (ID INTEGER not null primary key, "
            + " PLAYER_1 VARCHAR(50) not null, "
            + " PLAYER_2 VARCHAR(50) not null, "
            + " DATE DATE not null, "
            + " WON_PLAYER VARCHAR(50)) ";

    private final String playTable = " create table PLAY"
            + " ( "
            + " ID INTEGER not null primary key, "
            + " POSITION SMALLINT not null, "
            + " PLAYER VARCHAR(50) not null, "
            + " TIME SMALLINT, "
            + " RECORDED BOOLEAN not null) ";

    private final String playerTable = " create table PLAYER"
            + " ( "
            + " NAME VARCHAR(50) not null primary key "
            + " ) ";

    private final String gameShapeTable = " create table GAME_SHAPE"
            + " ( "
            + "	ID INTEGER not null primary key, "
            + " GAME_ID INTEGER not null, "
            + " PLAYER VARCHAR(50) not null, "
            + "SHAPE_ID INTEGER not null "
            + " ) ";

    private final String shapeTable = " create table SHAPE "
            + " ( "
            + " ID INTEGER not null "
            + " primary key, "
            + " NAME "
            + " VARCHAR(10) not null "
            + " ) ";

    private final String gamePlayerOneKey = " ALTER TABLE GAME "
            + " ADD FOREIGN KEY (PLAYER_1) "
            + " REFERENCES PLAYER(NAME) ";

    private final String gamePlayerTwoKey = " ALTER TABLE GAME "
            + " ADD FOREIGN KEY (PLAYER_2) "
            + " REFERENCES PLAYER(NAME) ";

    private final String gameWonPlayerKey = " ALTER TABLE "
            + " GAME ADD "
            + " FOREIGN KEY (WON_PLAYER) "
            + " REFERENCES PLAYER(NAME) ";

    // game_shape
    private final String gameShapePLayerKey = " ALTER TABLE GAME_SHAPE "
            + " ADD FOREIGN KEY (PLAYER) "
            + " REFERENCES PLAYER(NAME) ";

    private final String gameShapeShapeIdKey = " ALTER TABLE	GAME_SHAPE "
            + " ADD FOREIGN KEY (SHAPE_ID) "
            + " REFERENCES SHAPE(ID) ";

    private final String gameShapeGameIdKey = " ALTER TABLE GAME_SHAPE "
            + " ADD FOREIGN KEY (GAME_ID) "
            + " REFERENCES GAME(ID) ";

    // play
    private final String playPlayerKey = "ALTER TABLE PLAY"
            + " ADD FOREIGN KEY (PLAYER) "
            + " REFERENCES PLAYER(NAME) ";

    private Connection connection = null;

    private static TicTacToeDatabase instance = null;

    private TicTacToeDatabase() throws SQLException {
        Driver derbyDatabase = new EmbeddedDriver();
        DriverManager.registerDriver(derbyDatabase);
        connection = DriverManager.getConnection("jdbc:derby:tic_tac_toe;create=true", "root", "root");
        connection.setAutoCommit(false);

        if (!getIsDatabaseCreated()) {
            return;
        }

        connection.createStatement().execute(playerTable);
        connection.createStatement().execute(gameTable);
        connection.createStatement().execute(shapeTable);
        connection.createStatement().execute(playTable);
        connection.createStatement().execute(gameShapeTable);

        //game
        connection.createStatement().execute(gamePlayerOneKey);
        connection.createStatement().execute(gamePlayerTwoKey);
        connection.createStatement().execute(gameWonPlayerKey);
        //game_shape
        connection.createStatement().execute(gameShapeGameIdKey);
        connection.createStatement().execute(gameShapePLayerKey);
        connection.createStatement().execute(gameShapeGameIdKey);
        //play
        connection.createStatement().execute(playPlayerKey);
        connection.commit();
        setIsDatabaseCreated(true);
    }

    private static TicTacToeDatabase getInstance() throws SQLException {
        if (instance == null) {
            instance = new TicTacToeDatabase();
        }
        return instance;
    }

    private boolean getIsDatabaseCreated() {
        Preferences prefs = Preferences.userNodeForPackage(TicTacToeDatabase.class);
        return prefs.getBoolean("isDatabaseCreated", false);
    }

    private void setIsDatabaseCreated(boolean isDatabaseCreated) {
        Preferences prefs = Preferences.userNodeForPackage(TicTacToeDatabase.class);
        prefs.putBoolean("isDatabaseCreated", isDatabaseCreated);
    }

    public Connection getConnection() {
        return connection;
    }

    public void disconnect() throws SQLException {
        connection.close();
    }
}

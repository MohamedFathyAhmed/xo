/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author mohamed
 */
import data.GameMode;

public class CurrentGameData {

    private static CurrentGameData instance;
    private String player1;
    private String player2;
    private final String X_BOARD_STYLE_CSS = "x_board-btn";
    private final String O_BOARD_STYLE_CSS = "o_board-btn";
    private final String X_BOARD_HOVER_STYLE_CSS = "empty_x_board-btn";
    private final String O_BOARD_HOVER_STYLE_CSS = "empty_o_board-btn";
    private String winerPlayer; //marina
    private boolean isLoggedIn = false;
    private String onlineName;

    private GameShapes player1Shape;
    private GameShapes player2Shape;

    private int player1OverAllScore;
    private int player2OverAllScore;

    private int player1CurrentScore;
    private int player2CurrentScore;

    private GameLevel gameLevel;//Marina

    private GameMode gameMode;

    private CurrentGameData() {
        this.player1 = "";
        this.player2 = "";
        this.player1Shape = GameShapes.X;
        this.player2Shape = GameShapes.O;
        this.player1OverAllScore = 0;
        this.player2OverAllScore = 0;
        this.player1CurrentScore = 0;
        this.player2CurrentScore = 0;
        this.gameMode = GameMode.SINGLE;
        this.gameLevel = GameLevel.EASY;//Marina 
        this.winerPlayer = "";
    }

    public static CurrentGameData getInstance() {
        if (instance == null) {
            instance = new CurrentGameData();
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return onlineName != null;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public GameShapes getPlayer1Shape() {
        return player1Shape;
    }

    public void setPlayer1Shape(GameShapes player1Shape) {
        this.player1Shape = player1Shape;
    }

    public GameShapes getPlayer2Shape() {
        return player2Shape;
    }

    public void setPlayer2Shape(GameShapes player2Shape) {
        this.player2Shape = player2Shape;
    }

    public String getPLayer1BoardStyleCss() {
        if (player1Shape == GameShapes.X) {
            return X_BOARD_STYLE_CSS;
        }
        return O_BOARD_STYLE_CSS;
    }

    public String getPLayer2BoardStyleCss() {
        if (player2Shape == GameShapes.X) {
            return X_BOARD_STYLE_CSS;
        }
        return O_BOARD_STYLE_CSS;
    }

    public String getPlayer1BoardHoverStyleCss() {
        if (player1Shape == GameShapes.X) {
            return X_BOARD_HOVER_STYLE_CSS;
        }
        return O_BOARD_HOVER_STYLE_CSS;
    }

    public String getPlayer2BoardHoverStyleCss() {
        if (player2Shape == GameShapes.X) {
            return X_BOARD_HOVER_STYLE_CSS;
        }
        return O_BOARD_HOVER_STYLE_CSS;
    }

    public int getPlayer1OverAllScore() {
        return player1OverAllScore;
    }

    public void setPlayer1OverAllScore(int player1OverAllScore) {
        this.player1OverAllScore = player1OverAllScore;
    }

    public int getPlayer2OverAllScore() {
        return player2OverAllScore;
    }

    public void setPlayer2OverAllScore(int player2OverAllScore) {
        this.player2OverAllScore = player2OverAllScore;
    }

    public int getPlayer1CurrentScore() {
        return player1CurrentScore;
    }

    public void setPlayer1CurrentScore(int player1CurrentScore) {
        this.player1CurrentScore = player1CurrentScore;
    }

    public int getPlayer2CurrentScore() {
        return player2CurrentScore;
    }

    public void setPlayer2CurrentScore(int player2CurrentScore) {
        this.player2CurrentScore = player2CurrentScore;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public GameLevel getGameLevel() {//Marina
        return gameLevel;
    }

    public void setGameLevel(GameLevel gameLevel) {//Marina
        this.gameLevel = gameLevel;
    }

    public String getWinerPlayer() { //Marina
        return winerPlayer;
    }

    public void setWinerPlayer(String winerPlayer) { //Marina
        this.winerPlayer = winerPlayer;
    }

    public String getOnlineName() {
        return onlineName;
    }

    public void setOnlineName(String onlineName) {
        this.onlineName = onlineName;
    }

}

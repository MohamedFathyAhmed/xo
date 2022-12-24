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

public class CurrentGameData {

    private static CurrentGameData instance;
    private String player1;
    private String player2;
    private final String X_BOARD_STYLE_CSS = "x_board-btn";
    private final String O_BOARD_STYLE_CSS = "o_board-btn";
    private final String X_BOARD_HOVER_STYLE_CSS = "empty_x_board-btn";
    private final String O_BOARD_HOVER_STYLE_CSS = "empty_o_board-btn";
    private String winnerPlayer; //marina
    private boolean isLoggedIn = false;
    private boolean isPlayerTurnFirst = true;
    private String onlineName;

    private GameShape player1Shape;
    private GameShape player2Shape;

    private int player1OverAllScore;
    private int player2OverAllScore;

    private int player1CurrentScore;
    private int player2CurrentScore;

    private GameLevel gameLevel;//Marina

    private GameMode gameMode;

    private CurrentGameData() {
        gameLevel = GameLevel.EASY;
        reset();
    }

    public void reset() {
        player1 = null;
        player2 = null;
        isPlayerTurnFirst = true;
        player1Shape = GameShape.X;
        player2Shape = GameShape.O;
        player1OverAllScore = 0;
        player2OverAllScore = 0;
        player1CurrentScore = 0;
        player2CurrentScore = 0;
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

    public GameShape getPlayer1Shape() {
        return player1Shape;
    }

    public void setPlayer1Shape(GameShape player1Shape) {
        this.player1Shape = player1Shape;
    }

    public GameShape getPlayer2Shape() {
        return player2Shape;
    }

    public void setPlayer2Shape(GameShape player2Shape) {
        this.player2Shape = player2Shape;
    }

    public String getPLayer1BoardStyleCss() {
        if (player1Shape == GameShape.X) {
            return X_BOARD_STYLE_CSS;
        }
        return O_BOARD_STYLE_CSS;
    }

    public String getPLayer2BoardStyleCss() {
        if (player2Shape == GameShape.X) {
            return X_BOARD_STYLE_CSS;
        }
        return O_BOARD_STYLE_CSS;
    }

    public String getPlayer1BoardHoverStyleCss() {
        if (player1Shape == GameShape.X) {
            return X_BOARD_HOVER_STYLE_CSS;
        }
        return O_BOARD_HOVER_STYLE_CSS;
    }

    public String getPlayer2BoardHoverStyleCss() {
        if (player2Shape == GameShape.X) {
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

    public boolean isIsPlayerTurnFirst() {
        return isPlayerTurnFirst;
    }

    public void setIsPlayerTurnFirst(boolean isPlayerTurnFirst) {
        this.isPlayerTurnFirst = isPlayerTurnFirst;
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

    public String getWinnerPlayer() { //Marina
        return winnerPlayer;
    }

    public void setWinnerPlayer(String winerPlayer) { //Marina
        this.winnerPlayer = winerPlayer;
    }

    public String getOnlineName() {
        return onlineName;
    }

    public void setOnlineName(String onlineName) {
        this.onlineName = onlineName;
    }

}

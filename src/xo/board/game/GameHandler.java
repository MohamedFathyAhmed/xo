/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xo.board.game;

import data.GameShape;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

/**
 *
 * @author mo_fathy
 */
public class GameHandler {

    private final String WON_PATTERN = "(^(@..@..@..)|(.@..@..@.)|(..@..@..@)|(@@@......)|(...@@@...)|(......@@@)|(..@.@.@..)|(@...@...@)$)";
    private final String player1WinPattern;
    private final String player2WinPattern;
    private final Consumer<GameState> gameStateUpdater;
    protected final char[] boardChars = {'-', '-', '-', '-', '-', '-', '-', '-', '-'};

    public GameHandler(Consumer<GameState> gameStateUpdater, GameShape player1Shape, GameShape player2Shape) {
        this.gameStateUpdater = gameStateUpdater;
        player1WinPattern = WON_PATTERN.replace("@", player1Shape.name());
        player2WinPattern = WON_PATTERN.replace("@", player2Shape.name());
    }

    public GameHandler(Consumer<GameState> gameStateUpdater, String player1Shape, String player2Shape) {
        this.gameStateUpdater = gameStateUpdater;
        player1WinPattern = WON_PATTERN.replace("@", player1Shape);
        player2WinPattern = WON_PATTERN.replace("@", player2Shape);
    }

    public void play(int position, GameShape shape) {
        updateBoard(position, shape.name());
        updateGameState(new String(boardChars));
    }

    protected void updateBoard(int position, String shape) {
        boardChars[position] = shape.charAt(0);
    }

    private void updateGameState(String board) {
        if (board.matches(player1WinPattern)) {
            gameStateUpdater.accept(GameState.PLAYER_ONE_WON);
        } else if (board.matches(player2WinPattern)) {
            gameStateUpdater.accept(GameState.PLAYER_TWO_WON);
        } else if (board.contains("-")) {
            gameStateUpdater.accept(GameState.ONGOING);
        } else {
            gameStateUpdater.accept(GameState.DRAW);
        }
    }

    public String getBoardAsString() {
        return new String(boardChars);
    }
}

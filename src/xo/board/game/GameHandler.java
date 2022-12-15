/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xo.board.game;

import data.GameShapes;
import java.util.function.Consumer;

/**
 *
 * @author mo_fathy
 */
public class GameHandler {

    private final String xPattern = "(^(X..X..X..)|(.X..X..X.)|(..X..X..X)|(XXX......)|(...XXX...)|(......XXX)|(..X.X.X..)|(X...X...X)$)";
    private final String oPattern;
    private final char[] boardChars = {'-', '-', '-', '-', '-', '-', '-', '-', '-'};
    private final Consumer<GameState> gameStateUpdater;

    public GameHandler(Consumer<GameState> gameStateUpdater) {
        oPattern = xPattern.replaceAll("X", "O");
        this.gameStateUpdater = gameStateUpdater;
    }

    public void play(int position, GameShapes shape) {
        updateBoard(position, shape.name());
        updateGameState(new String(boardChars));
    }

    private void updateBoard(int position, String shape) {
        boardChars[position] = shape.charAt(0);
    }

    private void updateGameState(String board) {
        if (board.matches(xPattern)) {
            gameStateUpdater.accept(GameState.PLAYER_ONE_WON);
        } else if (board.matches(oPattern)) {
            gameStateUpdater.accept(GameState.PLAYER_TWO_WON);
        } else if (board.contains("-")) {
            gameStateUpdater.accept(GameState.ONGOING);
        } else {
            gameStateUpdater.accept(GameState.DRAW);
        }
    }

}

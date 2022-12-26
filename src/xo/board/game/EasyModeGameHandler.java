/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.board.game;

import data.GameShape;
import java.util.Random;
import java.util.function.Consumer;

/**
 *
 * @author mohamed
 */
public final class EasyModeGameHandler extends GameHandler {

    private int playsCount;
    private int modeNextPlayPosition;
    private boolean isPcTurnFirst;
    private GameShape modeShape;

    public EasyModeGameHandler(boolean isPcTurnFirst, GameShape modeShape, GameShape playerShape, Consumer<GameState> gameStateUpdater) {
        super(gameStateUpdater, playerShape, modeShape);
        this.modeShape = modeShape;
        this.isPcTurnFirst = isPcTurnFirst;
        resetPlaysCount();
    }

    @Override
    public void play(int position, GameShape shape) {
        super.play(position, shape);
        updateModeNextPlayPosition();
        updateBoard(modeNextPlayPosition, modeShape.name());
    }

    public int getModeNextPlayPosition() {
        return modeNextPlayPosition;
    }

    private void updateModeNextPlayPosition() {
        int randomPosition = getRandomPosition();
        for (int i = 0; i < boardChars.length; i++) {
            if (boardChars[i] == '-') {
                randomPosition--;

                if (randomPosition == -1) {
                    modeNextPlayPosition = i;
                }
            }
        }

    }

    private int getRandomPosition() {
        playsCount += 2;
        return (int) (Math.random() * (9 - playsCount));
    }

    private void resetPlaysCount() {
        if (isPcTurnFirst) {
            playsCount = -2;
        } else {
            playsCount = -1;
        }
    }
}

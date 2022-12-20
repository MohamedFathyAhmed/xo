/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.online.handlers.responses;

import xo.board.game.GameState;

/**
 *
 * @author mohamed
 */
public class GameDoneResponse extends Response{
    private final String gameState;

    public GameDoneResponse(String gameState, String isSuccess, String message) {
        super(isSuccess, message);
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return GameState.valueOf(gameState);
    }
    
}

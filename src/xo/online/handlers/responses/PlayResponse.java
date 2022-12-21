/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.online.handlers.responses;

import data.CurrentGameData;

/**
 *
 * @author mohamed
 */
public class PlayResponse implements Response {
   private final String position;
    private final String isMyTurn;

    public PlayResponse(String position, String isMyTurn) {
        this.position = position;
        this.isMyTurn = isMyTurn;
    }

    public boolean isMyTurn() {
        return Boolean.valueOf(isMyTurn);
    }

    public int getPosition() {
        return Integer.parseInt(position);
    }

}

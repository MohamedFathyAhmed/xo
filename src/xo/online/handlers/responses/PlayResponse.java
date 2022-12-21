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
public class PlayResponse extends Response {

    private final String position;

    public PlayResponse(String isMyTurn, String position, String message) {
        super(isMyTurn, message);
        this.position = position;
    }

    public int getPosition() {
        return Integer.parseInt(position);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.online.handlers.responses;

import xo.online.handlers.responses.Response;

/**
 *
 * @author Marina
 */
public class OnlinePlayersResponse extends Response {

    private final String[] players;

    public OnlinePlayersResponse(String[] players, String isSuccess, String message) {
        super(isSuccess, message);
        this.players = players;
    }

    public String[] getPlayers() {
        return players;
    }
    

}

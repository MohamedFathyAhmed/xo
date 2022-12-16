/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.online.handlers;

import data.database.models.Game;

/**
 *
 * @author Marina
 */
public class HistoryResponse extends Response {

    private final Game[] games;

    HistoryResponse(Game[] games, String isSuccess, String message) {
        super(isSuccess, message);
        this.games = games;
    }

    public Game[] getGames() {
        return games;
    }
}



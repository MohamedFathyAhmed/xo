/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.database.models;

import data.GameShapes;

/**
 *
 * @author mohamed
 */
public class Play {

    private final String position;
    private final String player;
    private final String gameId;
    private final String gameShape;

    public Play(String position, String player, String gameId, String gameShape) {
        this.position = position;
        this.player = player;
        this.gameId = gameId;
        this.gameShape = gameShape;
    }

    public String getGameId() {
        return gameId;
    }

    public String getGameShape() {
        return gameShape;
    }
    
    public String getPosition() {
        return position;
    }

    public String getPlayer() {
        return player;
    }

}

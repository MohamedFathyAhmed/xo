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

    public Play(String position, String player) {
        this.position = position;
        this.player = player;

    }
    
    public String getPosition() {
        return position;
    }

    public String getPlayer() {
        return player;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.database.models;

/**
 *
 * @author mohamed
 */
public class Play {
    private final int position;
    private final String player;
    private final int time;

    public Play(int position, String player, int time) {
        this.position = position;
        this.player = player;
        this.time = time;
    }

    public int getPosition() {
        return position;
    }

    public String getPlayer() {
        return player;
    }

    public int getTime() {
        return time;
    } 
    
}

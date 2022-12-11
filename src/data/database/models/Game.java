/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.database.models;

import java.util.Date;

/**
 *
 * @author mohamed
 */
public class Game {
    private final String player1;
    private final String player2;
    private final Date date;
    private final String wonPLayer;
    private final int player1Shape;
    private final int player2Shape;

    public Game(String player1, String player2, Date date, String wonPLayer, int player1Shape, int player2Shape) {
        this.player1 = player1;
        this.player2 = player2;
        this.date = date;
        this.wonPLayer = wonPLayer;
        this.player1Shape = player1Shape;
        this.player2Shape = player2Shape;
    }

    public int getPlayer1Shape() {
        return player1Shape;
    }

    public int getPlayer2Shape() {
        return player2Shape;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public Date getDate() {
        return date;
    }

    public String getWonPLayer() {
        return wonPLayer;
    }
}

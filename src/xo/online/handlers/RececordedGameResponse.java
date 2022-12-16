/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.online.handlers;

import data.database.models.Play;

/**
 *
 * @author Marina
 */

public class RececordedGameResponse extends Response {

    private final Play[] plays;

    public RececordedGameResponse(Play[] plays, String isSuccess, String message) {
        super(isSuccess, message);
        this.plays = plays;
    }
}

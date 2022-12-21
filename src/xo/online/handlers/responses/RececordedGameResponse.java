/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.online.handlers.responses;

import xo.online.handlers.responses.Response;
import data.database.models.Play;

/**
 *
 * @author Marina
 */

public class RececordedGameResponse implements Response {

    private final Play[] plays;

    public RececordedGameResponse(Play[] plays) {
        this.plays = plays;
    }
}

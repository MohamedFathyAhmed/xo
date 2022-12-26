/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.online.handlers.responses;

import data.database.models.Play;
import java.util.List;

/**
 *
 * @author Marina
 */

public class RececordedGameResponse implements Response {

    private final List<Play> plays;

    public RececordedGameResponse(List<Play> plays) {
        this.plays = plays;
    }

    public List<Play> getPlays() {
        return plays;
    }
    
}

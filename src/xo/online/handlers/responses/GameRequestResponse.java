/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.online.handlers.responses;

import xo.online.handlers.responses.Response;

/**
 *
 * @author mohamed
 */
public class GameRequestResponse extends Response{
    private final String sender;

    public GameRequestResponse(String sender, String isSuccess, String message) {
        super(isSuccess, message);
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }
    
    
}

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
public class InfoResponse implements Response{

    private final String isSuccess ;

    public InfoResponse(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean getIsSuccess() {
        return Boolean.valueOf(isSuccess);
    }    

}

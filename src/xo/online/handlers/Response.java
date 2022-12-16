/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.online.handlers;

/**
 *
 * @author Marina
 */
public class Response {

    private final String isSuccess;
    private final String message;

    public Response(String isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public boolean getIsSuccess() {
        return isSuccess.equals("true");
    }

    public String getMessage() {
        return message;
    }

}

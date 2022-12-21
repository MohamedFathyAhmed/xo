/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.online.handlers.responses;

/**
 *
 * @author mohamed
 */
public class AuthResponse implements Response {

    private final String isSuccess;
    private final String message;

    public AuthResponse(String isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public Boolean getIsSuccess() {
        return Boolean.valueOf(isSuccess);
    }

    public String getMessage() {
        return message;
    }

}

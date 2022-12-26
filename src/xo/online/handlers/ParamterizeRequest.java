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



public class ParamterizeRequest extends Request {

    private final String paramter;

    public ParamterizeRequest(String paramter, String name) {
        super(name);
        this.paramter = paramter;
    }

    public String getParamter() {
        return paramter;
    }

}
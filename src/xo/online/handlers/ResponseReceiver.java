/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.online.handlers;

import xo.online.handlers.responses.Response;

/**
 *
 * @author Marina
 */
@FunctionalInterface
public interface ResponseReceiver {

    String sendData(Response request);
}

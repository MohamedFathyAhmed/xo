/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.board;

/**
 *
 * @author mohamed
 */
public interface BoardTimer {
    void start();

    int getTime();
    
    void stop();
    
    void resetTimer();

    void pause();
    
    void resume();
}

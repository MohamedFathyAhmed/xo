/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.board;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import xo.utlis.TicTacToeExecutorService;

/**
 *
 * @author mohamed
 */
public class BoardTimerImpl implements BoardTimer, Runnable {

    private int timer = 0;
    private boolean paused = false;
    private final Consumer updateText;
    private ScheduledFuture scheduleAtFixedRate;
    private final TicTacToeExecutorService gameExecutorService;

    public BoardTimerImpl(Consumer<String> updateText) {
        this.updateText = updateText;
        gameExecutorService = TicTacToeExecutorService.getInstance();
    }

    @Override
    public void start() {
        if (scheduleAtFixedRate == null || scheduleAtFixedRate.isCancelled() || scheduleAtFixedRate.isDone()) {
            scheduleAtFixedRate = gameExecutorService.scheduleAtFixedRate(this, 1L, 1L, TimeUnit.SECONDS);
        } else {
            resume();
        }
    }

    @Override
    public void stop() {
        scheduleAtFixedRate.cancel(false);
    }

    @Override
    public void pause() {
        paused = true;
    }

    @Override
    public void resume() {
        paused = false;
    }

    @Override
    public void resetTimer() {
        timer = 0;
    }

    @Override
    public void run() {
        while (paused) {
            return;
        }
        timer++;
        updateText.accept(timer / 60 + ":" + String.format("%02d", timer % 60));
    }

    @Override
    public int getTime() {
        return timer;
    }

}

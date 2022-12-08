/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.utlis;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author mohamed
 */
public class TicTacToeExecutorService {

    private final ScheduledExecutorService executorService;
    private static TicTacToeExecutorService instance;

    private TicTacToeExecutorService() {
        executorService = Executors.newScheduledThreadPool(3);
    }

    public static TicTacToeExecutorService getInstance() {
        if (instance == null) {
            instance = new TicTacToeExecutorService();
        }
        return instance;
    }

    public void execute(Runnable runnable) {
        executorService.execute(runnable);
    }

    public void stop() {
        executorService.shutdown();
    }

    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, Long initialDelay, Long period, TimeUnit unit) {
       return executorService.scheduleAtFixedRate(command, initialDelay, period, unit);
    }

    public void stopNow() {
        executorService.shutdownNow();
    }
}

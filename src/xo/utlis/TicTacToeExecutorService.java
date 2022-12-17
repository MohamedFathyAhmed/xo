/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.utlis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author mohamed
 */
public class TicTacToeExecutorService {

    private static TicTacToeExecutorService instance;
    private final ExecutorService executorService;
    private final ScheduledExecutorService scheduledExecutorService;

    private TicTacToeExecutorService() {
        executorService = Executors.newCachedThreadPool();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    }

    public static TicTacToeExecutorService getInstance() {
        if (instance == null) {
            instance = new TicTacToeExecutorService();
        }
        return instance;
    }

    public void schedule(Runnable command, long delay, TimeUnit unit) {
        scheduledExecutorService.schedule(command, delay, unit);
    }

    public void stop() {
        executorService.shutdown();
        scheduledExecutorService.shutdown();
    }
}

package pl.edu.agh.debtexecutor.utils;

import javafx.application.Platform;

import java.util.concurrent.TimeUnit;

public class Timeout {
    private final Runnable callback;
    private final int millis;
    private Thread thread;

    public Timeout(Runnable callback, int millis) {
        this.callback = callback;
        this.millis = millis;
        this.thread = new TimeoutThread();
        this.thread.start();
    }

    public void clear() {
        if (thread != null && thread.isAlive()) thread.interrupt();
        thread = null;
    }

    private class TimeoutThread extends Thread {
        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(millis);
                Platform.runLater(callback);
            } catch (InterruptedException ignored) {}
        }
    }
}

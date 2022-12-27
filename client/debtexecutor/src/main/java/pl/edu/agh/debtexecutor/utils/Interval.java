package pl.edu.agh.debtexecutor.utils;

import java.util.concurrent.TimeUnit;

public class Interval {
    private final Runnable callback;
    private final int millis;
    private Thread thread;

    public Interval(Runnable callback, int millis) {
        this.callback = callback;
        this.millis = millis;
    }

    public void start() {
        if (thread != null) return;
        thread = new IntervalThread();
        thread.start();
    }

    public void clear() {
        if (thread != null && thread.isAlive()) thread.interrupt();
        thread = null;
    }

    public void reset() {
        clear();
        start();
    }

    private class IntervalThread extends Thread {
        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    callback.run();
                    System.out.println("SLEEP " + millis);
                    TimeUnit.MILLISECONDS.sleep(millis);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }
}

package me.tim;

/**
 * CSVTimer
 */
public class CSVTimer {
    private long time;
    private long lastTime;

    public CSVTimer() {
        this.time = System.nanoTime();
        this.lastTime = 0;
    }

    public void resetTime() {
        this.lastTime = System.nanoTime();
    }

    public void updateTime() {
        this.time = System.nanoTime() - this.lastTime;
    }

    public long getTime() {
        return time;
    }
}

package me.tim;

/**
 * CSVTimer
 */
public class CSVTimer {
    private long time;
    private long lastTime;

    public CSVTimer() {
        this.time = System.currentTimeMillis();
        this.lastTime = 0;
    }

    public void resetTime() {
        this.lastTime = System.currentTimeMillis();
    }

    public void updateTime() {
        this.time = System.currentTimeMillis() - this.lastTime;
    }

    public long getTime() {
        return time;
    }
}

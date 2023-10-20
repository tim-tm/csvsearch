package me.tim;

import java.util.ArrayList;

import me.tim.CSVSearch.CSVLine;

/**
 * CSVSearchAlgorithm
 */
public abstract class CSVSearchAlgorithm {
    protected final CSVTimer timer;

    public CSVSearchAlgorithm() {
        this.timer = new CSVTimer();
    }

    public abstract CSVLine search(ArrayList<CSVLine> lines, String query, int index);

    public CSVTimer getTimer() {
        return timer;
    }
}

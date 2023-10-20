package me.tim;

import java.util.ArrayList;
import me.tim.CSVSearch.CSVLine;

/**
 * CSVSortAlgorithm
 */
public abstract class CSVSortAlgorithm {
    protected final CSVTimer timer;

    public CSVSortAlgorithm() {
        this.timer = new CSVTimer();
    }

    public abstract void sort(ArrayList<CSVLine> lines, int index);

    public CSVTimer getTimer() {
        return timer;
    }
}

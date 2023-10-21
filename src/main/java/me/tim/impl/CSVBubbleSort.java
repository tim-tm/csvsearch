package me.tim.impl;

import java.util.ArrayList;

import me.tim.CSVSortAlgorithm;
import me.tim.CSVSearch.CSVLine;

/**
 * CSVBubbleSort
 */
public class CSVBubbleSort extends CSVSortAlgorithm {
    @Override
    public void sort(ArrayList<CSVLine> lines, int index) {
        this.timer.resetTime();
        for (int j = 0; j < lines.size(); ++j) {
            for (int i = j + 1; i < lines.size(); ++i) {
                if (lines.get(i).objects.get(index).compareTo(lines.get(j).objects.get(index)) < 0) {
                    String tmp = lines.get(j).objects.get(index);
                    lines.get(j).objects.set(index, lines.get(i).objects.get(index));
                    lines.get(i).objects.set(index, tmp);
                }
            }
        }
        this.timer.updateTime();
    }
}

package me.tim.impl;

import java.util.ArrayList;

import me.tim.CSVSortAlgorithm;
import me.tim.CSVSearch.CSVLine;

/**
 * CSVSelectionSort
 */
public class CSVSelectionSort extends CSVSortAlgorithm {
    @Override
    public void sort(ArrayList<CSVLine> lines, int index) {
        this.timer.resetTime();
        for (int i = 0; i < lines.size(); ++i) {
            int k = i;
            for (int j = i + 1; j < lines.size(); ++j) {
                if (lines.get(j).objects.get(index).compareTo(lines.get(k).objects.get(index)) < 0) {
                    k = j;
                }
            }

            String tmp = lines.get(k).objects.get(index);
            lines.get(k).objects.set(index, lines.get(i).objects.get(index));
            lines.get(i).objects.set(index, tmp);
        }
        this.timer.updateTime();
    }
}

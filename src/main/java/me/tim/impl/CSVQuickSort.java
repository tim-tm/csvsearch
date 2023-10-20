package me.tim.impl;

import java.util.ArrayList;
import java.util.Comparator;

import me.tim.CSVSortAlgorithm;
import me.tim.CSVSearch.CSVLine;

/**
 * CSVQuickSort
 */
public class CSVQuickSort extends CSVSortAlgorithm {
    @Override
    public void sort(ArrayList<CSVLine> lines, int index) {
        this.timer.resetTime();
        lines.sort(Comparator.comparing(a -> a.objects.get(index)));
        this.timer.updateTime();
    }
}

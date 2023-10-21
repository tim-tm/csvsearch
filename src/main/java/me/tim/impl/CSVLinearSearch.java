package me.tim.impl;

import java.util.ArrayList;

import me.tim.CSVSearchAlgorithm;
import me.tim.CSVSearch.CSVLine;

/**
 * CSVLinearSearch
 */
public class CSVLinearSearch extends CSVSearchAlgorithm {
    @Override
    public CSVLine search(ArrayList<CSVLine> lines, String query, int index) {    
        this.timer.resetTime();
        for (CSVLine line : lines) {
            String s = line.objects.get(index);
            if (query.equals(s)) {
                this.timer.updateTime();
                return line;
            }
        }
        this.timer.updateTime();
        return null;
    }
}

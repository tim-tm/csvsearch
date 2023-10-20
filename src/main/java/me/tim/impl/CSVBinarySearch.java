package me.tim.impl;

import java.util.ArrayList;

import me.tim.CSVSearchAlgorithm;
import me.tim.CSVSearch.CSVLine;

/**
 * CSVBinarySearch
 */
public class CSVBinarySearch extends CSVSearchAlgorithm {
    /*
         TODO: Kann sehr gut optimisiert werden indem nicht bei jedem rekursiven Aufruf gleichbleibende Parameter geliefert werden.
    */
    private CSVLine binarySearch(ArrayList<CSVLine> lines, String query, int l, int r, int index) {
        if (r < l) return null;

        int i = l + (r - l)/2;
        CSVLine obj = lines.get(i);
        if (obj.objects.get(index).equals(query)) return obj;

        if (obj.objects.get(index).compareTo(query) > 0) {
            return binarySearch(lines, query, l, i-1, index);
        } else {
            return binarySearch(lines, query, i+1, r, index);
        }
    }

    @Override
    public CSVLine search(ArrayList<CSVLine> lines, String query, int index) {
        this.timer.resetTime();
        CSVLine result = binarySearch(lines, query, 0, lines.size()-1, index);
        this.timer.updateTime();
        return result;
    }
}

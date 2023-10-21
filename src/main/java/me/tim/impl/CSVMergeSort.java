package me.tim.impl;

import java.util.ArrayList;

import me.tim.CSVSortAlgorithm;
import me.tim.CSVSearch.CSVLine;

/**
 * CSVMergeSort
 * @see https://www.baeldung.com/java-merge-sort
 */
public class CSVMergeSort extends CSVSortAlgorithm {
    private void merge(ArrayList<CSVLine> lines, ArrayList<CSVLine> left, ArrayList<CSVLine> right, int leftIndex, int rightIndex, int index) {
        int i = 0, j = 0, k = 0;
        while (i < leftIndex && j < rightIndex) {
            if (left.get(i).objects.get(index).compareTo(right.get(j).objects.get(index)) <= 0) {
                lines.set(k++, left.get(i++));
            } else {
                lines.set(k++, right.get(j++));
            }
        }

        while (i < leftIndex) {
            lines.set(k++, left.get(i++));
        }
        
        while (j < rightIndex) {
            lines.set(k++, right.get(j++));
        }
    }

    private void mergeSort(ArrayList<CSVLine> lines, int n, int index) {
        if (n < 2) {
            return;
        }

        int mid = n / 2;
        ArrayList<CSVLine> left = new ArrayList<>();
        ArrayList<CSVLine> right = new ArrayList<>();

        for (int i = 0; i < mid; ++i) {
            left.add(i, lines.get(i));
        }

        for (int i = mid; i < n; ++i) {
            right.add(i - mid, lines.get(i));
        }
        mergeSort(left, mid, index);
        mergeSort(right, n - mid, index);
        
        merge(lines, left, right, mid, n - mid, index);
    }

    @Override
    public void sort(ArrayList<CSVLine> lines, int index) {
        this.timer.resetTime();
        mergeSort(lines, lines.size(), index);
        this.timer.updateTime();
    }
}

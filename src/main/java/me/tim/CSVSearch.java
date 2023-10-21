package me.tim;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import me.tim.impl.*;

public class CSVSearch {
    private final ArrayList<CSVLine> lines;
    private boolean sorted;

    private int sortIndex;
    private int maxSortIndex;

    private CSVSortAlgorithm sortAlgorithm;
    private CSVSearchAlgorithm searchAlgorithm;

    public CSVSearch(CSVSortAlgorithm sortAlgorithm, CSVSearchAlgorithm searchAlgorithm) {
        this.lines = new ArrayList<>();
        this.sorted = false;
        this.sortIndex = 0;
        this.maxSortIndex = 0;
        this.sortAlgorithm = sortAlgorithm;
        this.searchAlgorithm = searchAlgorithm;
    }

    public CSVSearch() {
        this(CSVSortAlgorithms.QUICKSORT.getAlgo(), CSVSearchAlgorithms.BINARYSEARCH.getAlgo());
    }

    /**
     * Liest die gegebene Datei in eine Liste von Objekten ein.
     * @param filename Der Name der Datei, die eingelesen werden soll.
     */
    public void parseCSV(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.printf("Datei: %s wurde nicht gefunden!\n", filename);
            return;
        }

        if (file.isDirectory()) {
            System.out.println("Ordner sind nicht erlaubt!");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            int n = 0;

            while (line != null && !line.isEmpty()) {
                String[] split = line.split(",");
                int iLen = split.length-1;
                if (iLen > this.maxSortIndex) {
                    this.maxSortIndex = iLen;
                }

                CSVLine line1 = new CSVLine(n);
                line1.objects.addAll(List.of(split));

                this.lines.add(line1);
                line = reader.readLine();
                ++n;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Methode um die Daten für writeCSV zusammenzustellen.
     * Diese Methode sollte niemals außerhalb dieser Klasse aufgerufen werden können, deshalb private.
     * @return Die CSV-Datei in String für writeCSV
     */
    private String collectWriteString() {
        StringBuilder builder = new StringBuilder();
        for (CSVLine line : this.lines) {
            for (String object : line.objects) {
                builder.append(object);
                builder.append(",");
            }
            // Das letzte Komma durch ein Leerzeichen ersetzen
            builder.setCharAt(builder.length()-1, ' ');
            builder.append("\n");
        }
        return builder.toString();
    }

    /**
     * Schreibt die in der Liste gespeicherten Daten in eine Datei.
     * Die neue Datei wird automatisch erstellt, falls sie nicht existiert.
     * @param filename Der Name der neuen Datei.
     * @throws Exception Die Methode wirft eine Exception, falls beim Schreiben ein Fehler passiert ist oder die Liste noch unsortiert ist.
     */
    public void writeCSV(String filename) throws Exception {
        if (!this.sorted) {
            throw new IllegalStateException("Die Liste muss vor dem Schreiben sortiert werden!");
        }

        File file = new File(filename);
        if (file.createNewFile()) {
            System.out.printf("Datei: %s wurde erstellt!\n", filename);
            return;
        }

        FileWriter writer = new FileWriter(file);
        writer.write(collectWriteString());
        writer.flush();
        writer.close();
    }

    /**
     * Sortiere nach einem index.
     * Der Index wird automatisch in der Klasse gespeichert.
     * @param index Das element in einer Zeile nach dem sortiert werden soll.
     */
    public void sortBy(int index) {
        if (!this.sorted) {
            if (index > this.maxSortIndex || index < 0) {
                System.out.printf("ACHTUNG: Der höchstmögliche Wert für den sortIndex ist %d, der Index von %d wurde nicht gesetzt.\n", this.maxSortIndex, index);
                return;
            }

            this.sortAlgorithm.sort(this.lines, index);
            this.sortIndex = index;
            this.sorted = true;
        }
    }

    /**
     * Sortiere nach dem Index, der in der Klasse gespeichert ist (default: 0)
     */
    public void sort() {
        this.sortBy(this.sortIndex);
    }

    /**
     * Finde Elemente in der Liste.
     * @param query Die Suche (muss 1zu1 sein)
     * @return Das Objekt, falls es gefunden wurde. Andernfalls null.
     */
    public CSVLine find(String query) {
        if (!this.sorted) {
            System.out.println("Liste wurde noch nicht sortiert, Suche unmöglich!");
            return null;
        }
        return this.searchAlgorithm.search(this.lines, query, this.sortIndex);
    }

    /**
     * Finde Elemente in der Liste und sortiere die Liste vorher nach einem bestimmten Index.
     * ACHTUNG: Die Liste wird bei jedem Aufruf sortiert.
     * @param query Die Suche (muss 1zu1 sein)
     * @param index Das element in einer Zeile nach dem sortiert werden soll.
     * @return Das Objekt, falls es gefunden wurde. Andernfalls null.
     */
    public CSVLine findBy(String query, int index) {
        if (index > this.maxSortIndex || index < 0) {
            System.out.printf("ACHTUNG: Der höchstmögliche Wert für den sortIndex ist %d, der Index von %d wurde nicht gesetzt.\n", this.maxSortIndex, index);
            return null;
        }
        this.sortBy(index);
        return this.searchAlgorithm.search(this.lines, query, this.sortIndex);
    }

    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        if (sortIndex > this.maxSortIndex || sortIndex < 0) {
            System.out.printf("ACHTUNG: Der höchstmögliche Wert für den sortIndex ist %d, der Index von %d wurde nicht gesetzt.\n", this.maxSortIndex, sortIndex);
        } else {
            this.sortIndex = sortIndex;
        }
    }

    public CSVSortAlgorithm getSortAlgorithm() {
        return sortAlgorithm;
    }

    public void setSortAlgorithm(CSVSortAlgorithm sortAlgorithm) {
        this.sortAlgorithm = sortAlgorithm;
    }

    public CSVSearchAlgorithm getSearchAlgorithm() {
        return searchAlgorithm;
    }

    public void setSearchAlgorithm(CSVSearchAlgorithm searchAlgorithm) {
        this.searchAlgorithm = searchAlgorithm;
    }

    public static class CSVLine {
        public ArrayList<String> objects;
        public int number;

        public CSVLine(int number) {
            this.objects = new ArrayList<>();
            this.number = number;
        }
    }

    public enum CSVSortAlgorithms {
        QUICKSORT(new CSVQuickSort()),
        BUBBLESORT(new CSVBubbleSort()),
        SELECTIONSORT(new CSVSelectionSort());

        private final CSVSortAlgorithm algo;

        CSVSortAlgorithms(CSVSortAlgorithm algo) {
            this.algo = algo;
        }

        public CSVSortAlgorithm getAlgo() {
            return algo;
        }
    }
    
    public enum CSVSearchAlgorithms {
        BINARYSEARCH(new CSVBinarySearch()),
        LINEARSEARCH(new CSVLinearSearch());

        private final CSVSearchAlgorithm algo;

        CSVSearchAlgorithms(CSVSearchAlgorithm algo) {
            this.algo = algo;
        }

        public CSVSearchAlgorithm getAlgo() {
            return algo;
        }
    }
}

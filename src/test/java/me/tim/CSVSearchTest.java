package me.tim;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CSVSearchTest extends TestCase {
    private final CSVSearch search;

    public CSVSearchTest(String testName) {
        super(testName);
        this.search = new CSVSearch();
    }

    public static Test suite() {
        return new TestSuite(CSVSearchTest.class);
    }

    public void testCSVSearch() throws Exception {
        assertNotNull(this.search);
        this.search.parseCSV("adressdaten.csv");
        System.out.println(this.search.findBy("Müller", 0));
        this.search.writeCSV("adressdaten_out.csv");
    
        System.out.println("Sort: " + this.search.getSortAlgorithm().timer.getTime() + "ms");
        System.out.println("Search: " + this.search.getSearchAlgorithm().timer.getTime() + "ms");
    }
}

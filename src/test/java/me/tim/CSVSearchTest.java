package me.tim;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import me.tim.CSVSearch.CSVSearchAlgorithms;
import me.tim.CSVSearch.CSVSortAlgorithms;

public class CSVSearchTest extends TestCase {
    public CSVSearchTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(CSVSearchTest.class);
    }

    public void testCSVSearch() throws Exception {
        for (CSVSortAlgorithms values : CSVSortAlgorithms.values()) {
            CSVSortAlgorithm salgo = values.getAlgo();
            for (CSVSearchAlgorithms values2 : CSVSearchAlgorithms.values()) {
                CSVSearchAlgorithm sealgo = values2.getAlgo();
                
                CSVSearch search = new CSVSearch(salgo, sealgo);
                assertNotNull(search);
                search.parseCSV("adressdaten.csv");
                System.out.println(search.findBy("Müller", 0));
                search.writeCSV("target/adressdaten_" + salgo.getClass().getSimpleName() + "_" + sealgo.getClass().getSimpleName() + ".csv");

                double stime = salgo.timer.getTime() / 1E+6;
                double setime = sealgo.timer.getTime() / 1E+6;
                System.out.println(salgo.getClass().getName() + ": " + stime + "ms");
                System.out.println(sealgo.getClass().getName() + ": " + setime + "ms");
            }
        }
    }
}

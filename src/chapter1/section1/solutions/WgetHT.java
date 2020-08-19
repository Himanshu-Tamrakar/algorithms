package chapter1.section1.solutions;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

public class WgetHT {
    public static void main(String[] args) {
        // https://introcs.cs.princeton.edu/java/data/codes.csv
        // read in data from URL
        String url = "https://introcs.cs.princeton.edu/java/data/codes.csv";
        In in = new In(url);
        String data = in.readAll();

        // write data to a file
        String filename = url.substring(url.lastIndexOf('/') + 1);
        Out out = new Out(filename);
        out.println(data);
        out.close();
    }
}

package chapter4.section4.algo;

import edu.princeton.cs.algs4.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class CriticalPathMethod {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/chapter4/section4/data/jobsPC.txt");
//        FileInputStream fis = new FileInputStream(file);

        Scanner scanner = new Scanner(file);
//        scanner.useLocale(Locale.US);

        int N = Integer.parseInt(scanner.next());
        scanner.nextLine();

        EdgeWeightedDigraph G = new EdgeWeightedDigraph(2*N + 2);
        int start = 2 * N;
        int end = 2*N + 1;
        for (int i = 0; i < N; i++) {
            int v = i;

            String[] inputs = scanner.nextLine().split(" ");
            double duration = Double.parseDouble(inputs[0]);

            G.addEdge(new DirectedEdge(i, i+N, Double.parseDouble(inputs[0])));
            G.addEdge(new DirectedEdge(start, i, 0));
            G.addEdge(new DirectedEdge(i+N, end, 0));

            for (int j = 1; j < inputs.length; j++) {
                int successor = Integer.parseInt(inputs[j]);
                G.addEdge(new DirectedEdge(i+N, successor, 0));
//                NOT Required
//                G.addEdge(new DirectedEdge( successor, end, 0));

            }
        }

        EdgeWeightedDAGLongestPath lp = new EdgeWeightedDAGLongestPath(G, 0);

        StdOut.println("Start times:");
        for (int i = 0; i < N; i++){
            StdOut.printf("%4d: %5.1f\n", i, lp.distTo(i));
        }

        StdOut.printf("Finish time: %5.1f\n", lp.distTo(end));

    System.out.println("-----------------");

        AcyclicLP acyclicLP = new AcyclicLP(G, 0);
        for (int i = 0; i < N; i++){
            StdOut.printf("%4d: %5.1f\n", i, acyclicLP.distTo(i));
        }

        StdOut.printf("Finish time: %5.1f\n", acyclicLP.distTo(end));


    }
}

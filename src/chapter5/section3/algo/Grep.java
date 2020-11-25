package chapter5.section3.algo;

import edu.princeton.cs.algs4.StdIn;

public class Grep {
    public static void main(String[] args) {
        String regexp = "(.*" + "java" + ".*)";
        NFA nfa = new NFA(regexp);
        System.out.printf("Enter\n");
        while (StdIn.hasNextLine()) {
            String txt = StdIn.readLine();
            if (nfa.recognizes(txt)) {
                System.out.printf("Present");
            } else {
                System.out.printf("Does not present\n");
            }
        }
    }
}

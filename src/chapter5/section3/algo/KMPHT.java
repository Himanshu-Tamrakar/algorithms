package chapter5.section3.algo;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.KMP;
import sun.awt.X11.XSystemTrayPeer;

import java.util.ArrayList;

public class KMPHT {
    private int[][] dfa;
    private final int R = 256;
    private int M;

    public KMPHT(String pattern) {
        M = pattern.length();
        dfa = new int[R][pattern.length()];
        dfa(pattern);
    }


    /**
     * DFA construction
     * @param pattern
     */

    private void dfa(String pattern) {
        dfa[pattern.charAt(0)][0] = 1;

        for (int X = 0, j = 1; j < pattern.length(); j++) {
            for (int r = 0; r < R; r++) {
                dfa[r][j] = dfa[r][X];
            }
            dfa[pattern.charAt(j)][j] = j + 1;

            X = dfa[pattern.charAt(j)][X];
        }
    }


    public int search(String txt ) {
        int N = txt.length();
        int i, j;
        for (i = 0, j = 0; j < M && i < txt.length(); i++) {
            j = dfa[txt.charAt(i)][j];
        }

        if (j == M) return i - M;
        else return -1;
    }

    public Iterable<Integer> findAll(String txt) {
        ArrayList<Integer> list = new ArrayList();

        int i, j;
        int N = txt.length();
        for (i = 0, j = 0; i < N; i++) {
            if (j == M) {
                list.add(i - M);
                j = 0;
            }
            j = dfa[txt.charAt(i)][j];

        }

        if (j == M) {
            list.add(i - M);
        }

        return list;

    }

    /**
     * Using Input Stream
     * @param in
     * @return
     */
    public boolean search(In in ) {
        int j = 0;
        while (in.hasNextChar() && j < M) {
            int ch = in.readChar();
            j = dfa[ch][j];
        }
        if (j == M) return true;
        else return false;
    }


    public static void main(String[] args) {
        KMPHT kmp = new KMPHT("ATTACK AT DOWN");
        String s = "THE BASIC IDEA ATTACK AT DOWN BEHIND THE ATTACK AT DOWN ALGORITHM DISCOVERED BT KNUTH, MORRIS AND PRATTIS THIS: WHENEVER WE DERTECT A MISMATCH, WE ALREADY KNOW SOME OF THE CHARACTERS IN THE TEXT(SINCE THEU MATCHED THE PATTERN CHARACTORS PRIOR TOT HE MISMATCH).WE CAN TAKE ADVANTAGE OG THIS INFORMATION TO AVIOD BACKTRACKING UP THE TEXT POINTER OVER ALL THOSE KNOWN CHARACTERS.";
//        String s = "THE BASIC IDEA ATTACK AT DOWN ATTACK THE ATTACK AT DOWN ALGORITHM.";
//          String s = "AAAAA";
//        System.out.printf("ATTACK AT DOWN present in text at position %d \n", kmp.findAll(s));

        for (int index: kmp.findAll(s)) {
            System.out.printf("%d\n", index);
        }
    }
}

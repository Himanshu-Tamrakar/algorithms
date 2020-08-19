package chapter1.section1.solutions;

import edu.princeton.cs.algs4.StdDraw;

public class RightTriangleHT {
    public static void main(String[] args) {
        StdDraw.square(.5,.5,.5);
        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        StdDraw.line(.5, .5, .9, .5);
        StdDraw.line(.5, .9, .5, .5);
        StdDraw.line(.5, .9, .9, .5);
        StdDraw.circle(.7, .7, .283);
    }
}

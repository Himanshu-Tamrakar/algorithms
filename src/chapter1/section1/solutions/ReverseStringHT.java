package chapter1.section1.solutions;

public class ReverseStringHT {
    public static void main(String[] args) {
        String s = reverse(args[0]);
        System.out.print(s);
    }
    public static String reverse(String s) {
        int N = s.length();
        if (N <= 1) return s;
        String a = s.substring(0, N/2);
        String b = s.substring(N/2, N);
        return reverse(b) + reverse(a);
    }
}

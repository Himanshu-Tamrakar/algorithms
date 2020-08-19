package chapter1.section2.solutions;

import edu.princeton.cs.algs4.Stack;

public class InfixToPostfixHT {
    public static void main(String[] args) {
        String[] infix = args[0].split(" ");
        Stack<String> ops = new Stack<>();
        Stack<String> oprnd = new Stack<>();

        for (int i = 0; i < infix.length; i++) {
            String s = infix[i];
            if (s.equals("(")) {}
            else if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                ops.push(s);
            } else if (s.equals(")")) {
                String oprnd2 = oprnd.pop();
                String oprnd1 = oprnd.pop();
                String ops1 = ops.pop();
                oprnd.push(oprnd1 + oprnd2 + ops1);
            } else {
                oprnd.push(s);
            }
        }
        System.out.printf("%s \n", oprnd.pop());
    }
}

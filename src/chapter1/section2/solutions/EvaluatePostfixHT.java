package chapter1.section2.solutions;

import edu.princeton.cs.algs4.Stack;

public class EvaluatePostfixHT {
    /**
     * EvaluatePostfixHT.main(new String[]{"7 16 16 16 * * * 5 16 16 * * 3 16 * 1 + + +"});
     * EvaluatePostfixHT.main(new String[]{"1 2 3 4 5 * + 6 * * +"});
     * EvaluatePostfixHT.main(new String[]{"3 4 5 + *"});
     * @param args
     */
    public static void main(String[] args) {
        String[] postfix = args[0].split(" ");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < postfix.length; i++) {
            String s = postfix[i];
            if (s.equals("(")){}
            else if (s.equals("+")) {
                stack.push(Integer.parseInt(stack.pop()) + Integer.parseInt(stack.pop()) + "");
            } else if (s.equals("-")) {
                int oprnd2 = Integer.parseInt(stack.pop());
                int oprnd1 = Integer.parseInt(stack.pop());
                stack.push((oprnd1 - oprnd2) + "");
            } else if (s.equals("*")) {
                int oprnd2 = Integer.parseInt(stack.pop());
                int oprnd1 = Integer.parseInt(stack.pop());
                stack.push((oprnd1 * oprnd2) + "");
            } else if (s.equals("/")) {
                int oprnd2 = Integer.parseInt(stack.pop());
                int oprnd1 = Integer.parseInt(stack.pop());
                stack.push((oprnd1 / oprnd2) + "");
            } else {
                stack.push(s);
            }
        }

        System.out.printf("%d", Integer.parseInt(stack.pop()));
    }
}

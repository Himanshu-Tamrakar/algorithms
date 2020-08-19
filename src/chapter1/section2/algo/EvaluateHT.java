package chapter1.section2.algo;

import java.util.Stack;

public class EvaluateHT {
    /**
     * @param args
     * input => ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )
     * output => 101.0
     */
    public static void main(String[] args) {
        String[] expression = args[0].split(" ");
        Stack<String> optr = new Stack<>();
        Stack<Integer> oprnd = new Stack<>();

        for (int i = 0; i < expression.length; i++) {
            String val = expression[i];

            if(val.equals("(")) {
                continue;
            };

            if (val.equals("*") || val.equals("/") || val.equals("+") || val.equals("-")) {
                optr.push(val);
            } else if (val.equals(")")) {
                Integer oprnd1 = oprnd.pop();
                String optr1 = optr.pop();
                if (optr1.equals("+")) {
                    oprnd.push(oprnd.pop() + oprnd1);
                } else if (optr1.equals("-")) {
                    oprnd.push(oprnd.pop() - oprnd1);
                } else if (optr1.equals("*")) {
                    oprnd.push(oprnd.pop() * oprnd1);
                } else if (optr1.equals("/")) {
                    oprnd.push(oprnd.pop() / oprnd1);
                }

            } else {
                oprnd.push(Integer.parseInt(val));
            }

        }
        System.out.println(oprnd.pop());
    }
}

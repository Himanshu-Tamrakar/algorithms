package chapter3.section5.solutions;

import java.util.HashMap;
import java.util.Scanner;

public class Interpreter {
    public static void main(String[] args) {
        HashMap<String, Double> st = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        String expression = null;
        while (((expression = scanner.nextLine()) != null)) {
            String[] expressionBreakup = expression.split("\\s");

            if (expressionBreakup.length == 1) {
                if (st.containsKey(expressionBreakup[0])) {
                    System.out.printf("%s : %s", expressionBreakup[0], st.get(expressionBreakup[0]));
                } else {
                    System.out.printf("No value is there for %s", expressionBreakup[0]);
                }
            } else if (expressionBreakup.length == 3) {
                String key = expressionBreakup[0];
                String value = expressionBreakup[2];

                if (st.containsKey(value)) {
                    st.put(key, st.get(value));
                } else {
                    st.put(key, Double.parseDouble(value));
                }
            }  else if (expressionBreakup.length == 4) {
                String key = expressionBreakup[0];
                String eq   = expressionBreakup[1];
                String func = expressionBreakup[2];
                String xvar = expressionBreakup[3];
                if (!eq.equals(":=")) throw new RuntimeException("Illegal assignment");

                double x;
                if (st.containsKey(xvar)) x = st.get(xvar);
                else                   x = Double.parseDouble(xvar);

                if      (func.equals("sin")) st.put(key, Math.sin(x));
                else if (func.equals("cos")) st.put(key, Math.cos(x));
                else if (func.equals("sqrt")) st.put(key, Math.sqrt(x));
                else if (func.equals("-")) st.put(key, -x);
                else throw new RuntimeException("Illegal function");
                System.out.println(key + " := " + st.get(key));
            } else if (expressionBreakup.length == 5) {
                String key = expressionBreakup[0];
                double operand1 = st.containsKey(expressionBreakup[2]) ? st.get(expressionBreakup[2]) : Integer.parseInt(expressionBreakup[2]);
                String operator = expressionBreakup[3];
                double operand2 = st.containsKey(expressionBreakup[4]) ? st.get(expressionBreakup[4]) : Integer.parseInt(expressionBreakup[4]);

                if      (operator.equals("+")) st.put(key, operand1 + operand2);
                else if (operator.equals("-")) st.put(key, operand1 - operand2);
                else if (operator.equals("*")) st.put(key, operand1 * operand2);
                else if (operator.equals("/")) st.put(key, operand1 / operand2);
                else if (operator.equals("^")) st.put(key, Math.pow(operand1, operand2));
                else                           throw new RuntimeException("Operation not supported");
            }
        }

        System.out.printf("Exitnig....");
    }
}

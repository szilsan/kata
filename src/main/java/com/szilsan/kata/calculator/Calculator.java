package com.szilsan.kata.calculator;

import java.util.StringTokenizer;

public class Calculator {

    private static char divide = '\\';
    private static char miltiply = '*';

    private static char add = '+';
    private static char sub = '-';


    private static final char[] complex = new char[] {divide, miltiply};
    private static final char[] simple = new char[] {add, sub};


    public static Double evaluate(String expression) {
        double eval = 0;
        if (expression.contains("" + add)) {
            StringTokenizer st = new StringTokenizer(expression, "" + add);
            while (st.hasMoreTokens()) {
                eval += evaluate(st.nextToken());
            }
        } else if (expression.contains("" + sub)) {
            StringTokenizer st = new StringTokenizer(expression, "" + sub);
            while (st.hasMoreTokens()) {
                eval -= evaluate(st.nextToken());
            }
        } else {
            eval = Double.valueOf(expression.trim());
        }

        return eval;
    }
}
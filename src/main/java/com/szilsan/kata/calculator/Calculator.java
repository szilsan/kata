package com.szilsan.kata.calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    private static final Pattern patternDevide = Pattern.compile("(\\d+(?:\\.\\d+)?)([/])([-]*\\d+(?:\\.\\d+)?)");
    private static final Pattern patternMultiply = Pattern.compile("(\\d+(?:\\.\\d+)?)([*])([-]*\\d+(?:\\.\\d+)?)");
    private static final Pattern patternAdd = Pattern.compile("(\\d+(?:\\.\\d+)?)([+])([-]*\\d+(?:\\.\\d+)?)");
    private static final Pattern patternSub = Pattern.compile("([-]*\\d+(?:\\.\\d+)?)([-])([-]*\\d+(?:\\.\\d+)?)");


    public static Double evaluate(final String expression) {
        String processedExpression = expression;
        processedExpression = processedExpression.replaceAll(" ", "");

        // devide
        Matcher m = patternDevide.matcher(processedExpression);
        while (m.find()) {
            String expr = m.group(0);
            String value = "" + (Double.valueOf(m.group(1)) / Double.valueOf(m.group(3)));
            processedExpression = processedExpression.replace(expr, value);
            m = patternDevide.matcher(processedExpression);
        }

        // multiply
        m = patternMultiply.matcher(processedExpression);
        while (m.find()) {
            String expr = m.group(0);
            String value = "" + (Double.valueOf(m.group(1)) * Double.valueOf(m.group(3)));
            processedExpression = processedExpression.replace(expr, value);
            m = patternMultiply.matcher(processedExpression);
        }

        // sum
        m = patternAdd.matcher(processedExpression);
        while (m.find()) {
            String expr = m.group(0);
            String value = "" + (Double.valueOf(m.group(1)) + Double.valueOf(m.group(3)));
            processedExpression = processedExpression.replace(expr, value);
            m = patternAdd.matcher(processedExpression);
        }

        // sub
        m = patternSub.matcher(processedExpression);
        while (m.find()) {
            String expr = m.group(0);
            String value = "" + (Double.valueOf(m.group(1)) - Double.valueOf(m.group(3)));
            processedExpression = processedExpression.replace(expr, value);
            m = patternSub.matcher(processedExpression);
        }

        return Double.valueOf(processedExpression);

    }
}
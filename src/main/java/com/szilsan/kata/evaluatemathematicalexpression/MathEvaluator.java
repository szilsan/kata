package com.szilsan.kata.evaluatemathematicalexpression;

import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathEvaluator {

    private static final String NUMBER_EXPRESSION = "([-|+)]*\\d+(?:\\.\\d+)?(?:[E][-]?\\d+)?)";

    private static final Pattern patternParentheses = Pattern.compile(".*([(][^)]*[)]).*");
    private static final Pattern patternDivide = Pattern.compile(NUMBER_EXPRESSION + "([/])" + NUMBER_EXPRESSION);
    private static final Pattern patternMultiply = Pattern.compile(NUMBER_EXPRESSION + "([*])" + NUMBER_EXPRESSION);
    private static final Pattern patternAdd = Pattern.compile(NUMBER_EXPRESSION + "([+])" + NUMBER_EXPRESSION);
    private static final Pattern patternSub = Pattern.compile(NUMBER_EXPRESSION + "([-])" + NUMBER_EXPRESSION);

    public static Double calculate(final String expression) {
        String processedExpression = collapseSigns(expression);

        Matcher m = patternParentheses.matcher(processedExpression);
        while (m.find()) {
            String expr = m.group(1);
            Double ret = calculate(expr.substring(1, expr.length() -1));
            processedExpression = processedExpression.replace(expr, "" + ret.doubleValue());
            processedExpression = collapseSigns(processedExpression);
            m = patternParentheses.matcher(processedExpression);
        }

        processedExpression = calculateOperandOverExpression(patternDivide, processedExpression, (a, b) -> (a/b));
        processedExpression = calculateOperandOverExpression(patternMultiply, processedExpression, (a, b) -> (a*b));
        processedExpression = calculateOperandOverExpression(patternAdd, processedExpression, (a, b) -> (a+b));
        processedExpression = calculateOperandOverExpression(patternSub, processedExpression, (a, b) -> (a-b));

        return Double.valueOf(processedExpression);
    }

    private static String calculateOperandOverExpression(final Pattern pattern, final String processedExpression, final BiFunction<Double, Double, Double> func) {
        String tmpExpression = processedExpression;
        Matcher m = pattern.matcher(tmpExpression);
        while (m.find()) {
            String expr = m.group(0);
            Double value = func.apply(Double.valueOf(m.group(1)), Double.valueOf(m.group(3)));
            tmpExpression = tmpExpression.replace(expr, (value > 0 ? "+" + value : "" + value));
            m = pattern.matcher(tmpExpression);
        }
        return tmpExpression;
    }

    private static String collapseSigns(String expression) {
        String tmp = expression;
        tmp = tmp.replace(" ", "");
        tmp = tmp.replace("-+", "-");
        tmp = tmp.replace("+-", "-");
        tmp = tmp.replace("--", "+");
        tmp = tmp.replace("++", "+");
        return tmp;
    }
}
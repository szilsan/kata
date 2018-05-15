package com.szilsan.kata.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CalculatorTest {
    @Test
    public void simpleLiteral() {
        assertEquals(new Double(127), Calculator.evaluate("127"));
    }

    @Test
    public void subtractionAndAddition() {
        assertEquals(new Double(5), Calculator.evaluate("2 + 3"));
        assertEquals(new Double(20), Calculator.evaluate("2 + 3 + 7 +8"));
        assertEquals(new Double(-5), Calculator.evaluate("2 - 3 - 4"));
    }

    @Test
    public void divisionAndMultiplication() {
        assertEquals(new Double(10), Calculator.evaluate("10*5/5"));
        assertEquals(new Double(-10), Calculator.evaluate("-10*5/5"));
        assertEquals(new Double(-10), Calculator.evaluate("10*5/-5"));
        assertEquals(new Double(1.710540003E9), Calculator.evaluate("2118952920 - 1976377588 - -1567964671"));
        assertEquals(new Double(8), Calculator.evaluate("2 + 3 * 4 / 3 - 6 / 3 * 3 + 8"));
        assertEquals(new Double(0), Calculator.evaluate("2 - 3 * 4 / 3 - 6 / 3 * 3 + 8"));

    }

    @Test
    public void allMixed() {
        assertEquals(new Double(13), Calculator.evaluate("2 / 2 + 3 * 4"));
    }

    @Test
    public void floats() {
        assertEquals(new Double(0), Calculator.evaluate("7.7 - 3.3 - 4.4"));
    }
}
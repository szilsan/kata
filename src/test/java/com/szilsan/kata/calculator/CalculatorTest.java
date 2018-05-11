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
        assertEquals(new Double(-5), Calculator.evaluate("2 - 3 - 4"));
    }

    //@Test
    public void divisionAndMultiplication() {
        assertEquals(new Double(10), Calculator.evaluate("10 * 5 / 5"));
    }

    //@Test
    public void allMixed() {
        assertEquals(new Double(13), Calculator.evaluate("2 / 2 + 3 * 4"));
    }

    //@Test
    public void floats() {
        assertEquals(new Double(0), Calculator.evaluate("7.7 - 3.3 - 4.4"));
    }
}
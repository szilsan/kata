package com.szilsan.kata.squareeverydigit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SquareDigitTest {
    @Test
    public void test() {
        assertEquals(811181, new SquareDigit().squareDigits(9119));
    }

}
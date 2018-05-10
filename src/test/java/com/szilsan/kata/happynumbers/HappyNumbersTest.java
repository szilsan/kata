package com.szilsan.kata.happynumbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HappyNumbersTest {

    @Test
    void isHappyNumberTest() {
        long goodTestNumbers[] = new long[] {1, 7, 10, 13, 19, 23, 28, 31, 32, 44, 49, 68, 70, 79, 82, 86, 91, 94, 97, 100};
        for (long number: goodTestNumbers) {
            doAllTestForANumber(number);
        }
    }

    private void doAllTestForANumber(long number) {
        Assertions.assertTrue(HappyNumbers.isHappyNumberBasedOnChar(number));
        Assertions.assertTrue(HappyNumbers.isHappyNumber(number));
        Assertions.assertTrue(HappyNumbers.isHappyNumberNonRecursive(number));
    }
}
package com.szilsan.kata.happynumbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

class HappyNumbersTest {

    @Test
    void testCalcSumOfNumber() {
        Assertions.assertTrue(HappyNumbers.calcSumOfNumber(1) == 1);
        Assertions.assertTrue(HappyNumbers.calcSumOfNumber(3) == 9);
        Assertions.assertTrue(HappyNumbers.calcSumOfNumber(11) == 2);
        Assertions.assertTrue(HappyNumbers.calcSumOfNumber(100) == 1);
        Assertions.assertTrue(HappyNumbers.calcSumOfNumber(1234) == 30);
        Assertions.assertTrue(HappyNumbers.calcSumOfNumber(123456) == 1*1 + 2*2 + 3*3 + 4*4 + 5*5 + 6*6);
        Assertions.assertTrue(HappyNumbers.calcSumOfNumber(19741) == 1*1 + 9*9 + 7*7 + 4*4 + 1*1);
    }

    @Test
    void testCalcSumOfNumberNonPositive() {
        Assertions.assertTrue(HappyNumbers.calcSumOfNumber(-1) == 1);
        Assertions.assertTrue(HappyNumbers.calcSumOfNumber(0) == 0);
        Assertions.assertTrue(HappyNumbers.calcSumOfNumber(-5) == 25);
    }

    @Test
    void isHappyNumberTest() {
        long goodTestNumbers[] = new long[] {1, 7, 10, 13, 19, 23, 28, 31, 32, 44, 49, 68, 70, 79, 82, 86, 91, 94, 97, 100};
        for (long number: goodTestNumbers) {
            doAllTestForANumber(number);
        }
    }

    @Test
    void testStackOverflow() {
        //3, 20, 143, 1442, 14377, 143071, 1418854, 14255667, 145674808, 149260914
        Assertions.assertTrue(HappyNumbers.isHappyNumberWithMemoryChecking(1579));
    }

    @Test
    void isNotHappyNumberTest() {
        Assertions.assertFalse(HappyNumbers.isHappyNumberWithMemoryChecking(45));
        Assertions.assertFalse(HappyNumbers.isHappyNumberWithMemoryChecking(4324342554235l));
    }

    private void doAllTestForANumber(long number) {
        Assertions.assertTrue(HappyNumbers.isHappyNumberBasedOnChar(number));
        Assertions.assertTrue(HappyNumbers.isHappyNumber(number));
        Assertions.assertTrue(HappyNumbers.isHappyNumberNonRecursive(number));
        Assertions.assertTrue(HappyNumbers.isHappyNumberWithMemoryChecking(number));
    }
}
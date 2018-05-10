package com.szilsan.kata.happynumbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HappyNumbersTest {

    @Test
    void isHappyNumberBasedOnChar() {
        Assertions.assertTrue(HappyNumbers.isHappyNumberBasedOnChar(1));
        Assertions.assertTrue(HappyNumbers.isHappyNumberBasedOnChar(7));
        Assertions.assertTrue(HappyNumbers.isHappyNumberBasedOnChar(10));
        Assertions.assertTrue(HappyNumbers.isHappyNumberBasedOnChar(13));
        Assertions.assertTrue(HappyNumbers.isHappyNumberBasedOnChar(19));
        Assertions.assertTrue(HappyNumbers.isHappyNumberBasedOnChar(23));
        Assertions.assertTrue(HappyNumbers.isHappyNumberBasedOnChar(28));
        Assertions.assertTrue(HappyNumbers.isHappyNumberBasedOnChar(31));
        Assertions.assertTrue(HappyNumbers.isHappyNumberBasedOnChar(32));
        Assertions.assertTrue(HappyNumbers.isHappyNumberBasedOnChar(97));
        Assertions.assertTrue(HappyNumbers.isHappyNumberBasedOnChar(100));
        Assertions.assertTrue(HappyNumbers.isHappyNumberBasedOnChar(86));
    }

    @Test
    void isHappyNumber() {
        Assertions.assertTrue(HappyNumbers.isHappyNumber(1));
        Assertions.assertTrue(HappyNumbers.isHappyNumber(7));
        Assertions.assertTrue(HappyNumbers.isHappyNumber(10));
        Assertions.assertTrue(HappyNumbers.isHappyNumber(13));
        Assertions.assertTrue(HappyNumbers.isHappyNumber(19));
        Assertions.assertTrue(HappyNumbers.isHappyNumber(23));
        Assertions.assertTrue(HappyNumbers.isHappyNumber(28));
        Assertions.assertTrue(HappyNumbers.isHappyNumber(31));
        Assertions.assertTrue(HappyNumbers.isHappyNumber(32));
        Assertions.assertTrue(HappyNumbers.isHappyNumber(97));
        Assertions.assertTrue(HappyNumbers.isHappyNumber(100));
        Assertions.assertTrue(HappyNumbers.isHappyNumber(86));
    }

    @Test
    void isHappyNumberNonRecursive() {
        Assertions.assertTrue(HappyNumbers.isHappyNumberNonRecursive(1));
        Assertions.assertTrue(HappyNumbers.isHappyNumberNonRecursive(7));
        Assertions.assertTrue(HappyNumbers.isHappyNumberNonRecursive(10));
        Assertions.assertTrue(HappyNumbers.isHappyNumberNonRecursive(13));
        Assertions.assertTrue(HappyNumbers.isHappyNumberNonRecursive(19));
        Assertions.assertTrue(HappyNumbers.isHappyNumberNonRecursive(23));
        Assertions.assertTrue(HappyNumbers.isHappyNumberNonRecursive(28));
        Assertions.assertTrue(HappyNumbers.isHappyNumberNonRecursive(31));
        Assertions.assertTrue(HappyNumbers.isHappyNumberNonRecursive(32));
        Assertions.assertTrue(HappyNumbers.isHappyNumberNonRecursive(97));
        Assertions.assertTrue(HappyNumbers.isHappyNumberNonRecursive(100));
        Assertions.assertTrue(HappyNumbers.isHappyNumberNonRecursive(86));
    }
}
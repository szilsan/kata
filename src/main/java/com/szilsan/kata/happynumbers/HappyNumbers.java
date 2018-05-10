package com.szilsan.kata.happynumbers;

import java.util.HashSet;
import java.util.Set;

public class HappyNumbers {

    // based on characters
    public static boolean isHappyNumberBasedOnChar(long number) {
        if (number == 1) {
            return true;
        }

        long sum = 0;
        for (char c: ("" + number).toCharArray()) {
            sum +=  Math.pow(Long.valueOf("" + c), 2);
        }

        return isHappyNumberBasedOnChar(sum);
    }

    public static boolean isHappyNumber(long number) {
        if (number == 1) {
            return true;
        }

        return isHappyNumber(calcSumOfNumber(number));
    }

    public static boolean isHappyNumberNonRecursive(long number) {
        if (number == 1) {
            return true;
        }
        long sum = number;

        do {
            sum = calcSumOfNumber(sum);
        } while (sum != 1);

        return true;
    }

    public static boolean isHappyNumberWithMemoryChecking(long number) {
        final Set<Long> usedNumbers = new HashSet<Long>();

        long sum = number;
        do {
            sum = calcSumOfNumber(sum);

            if (usedNumbers.contains(sum)) {
                return false;
            }
            usedNumbers.add(sum);
        } while (sum != 1);

        return true;
    }

    private static long calcSumOfNumber(long number) {
        long sum = 0;
        long processeedNumber = number;
        while (processeedNumber > 0) {
            sum += Math.pow(processeedNumber % 10, 2);
            processeedNumber = processeedNumber / 10;
        }

        return sum;
    }


    public static void main(String[] args) {
        System.out.println("hello world");
    }
}

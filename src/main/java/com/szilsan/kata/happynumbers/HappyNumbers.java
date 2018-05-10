package com.szilsan.kata.happynumbers;

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

        long sum = 0;
        long processedNumber = number;
        while (processedNumber > 0) {
            long i = processedNumber % 10;
            processedNumber /= 10;
            sum += Math.pow(i, 2);
        }

        return isHappyNumber(sum);
    }

    public static boolean isHappyNumberNonRecursive(long number) {
        if (number == 1) {
            return true;
        }
        long processedNumber = number;
        long sum = 0;

        do {
            sum = 0;
            while (processedNumber > 0) {
                long i = processedNumber % 10;
                processedNumber /= 10;
                sum += Math.pow(i, 2);
            }
            processedNumber = sum;
        } while (sum != 1);

        return true;
    }



    public static void main(String[] args) {
        System.out.println("hello world");
    }
}

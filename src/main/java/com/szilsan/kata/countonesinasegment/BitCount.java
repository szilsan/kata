package com.szilsan.kata.countonesinasegment;

import java.math.BigInteger;

public class BitCount {

    public static BigInteger countOnes(long left, long right) {

        long rightCount = calculateForOneNumber(right);
        long leftCount = calculateForOneNumber(left-1);
        return BigInteger.valueOf(rightCount - leftCount);
    }

    private static long calculateForOneNumber(long num) {
        final String to = Long.toBinaryString(num);

        long count = 0;
        for (int columnNum = 1; columnNum <= to.length(); columnNum++) {
            long periodSize = (long)Math.pow(2, columnNum);
            long fullPeriodCount = ((num + 1) / periodSize);
            long remainFromPeriod = ((num + 1) % periodSize);
            long toCount = fullPeriodCount * periodSize / 2 + (remainFromPeriod == 0 ? 0 : ((remainFromPeriod <= periodSize/2) ? 0 : (remainFromPeriod - periodSize / 2)));
            count += toCount;
        }

        return count;

    }

    private static BigInteger bruteForce(long left, long right) {
        BigInteger count = BigInteger.valueOf(0);
        for (long i = left; i <= right; i++) {
            String str = Long.toBinaryString(i);
            count = count.add(BigInteger.valueOf(str.chars().parallel().filter(ch -> ch =='1').count()));
        }
        return count;
    }
}

package com.szilsan.kata.countonesinasegment;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BitCount {

    public static BigInteger countOnes(long left, long right) {

        final String to = Long.toBinaryString(right);
        String from = Long.toBinaryString(left);
        from = "000000000000000" + from;
        final long diff = right -left + 1;

        long count = 0;

        for (int columnNum = 1; columnNum <= to.length(); columnNum++) {
            long periodLength = (long) Math.pow(2, columnNum);
            long fullPeriodCount = diff / periodLength;
            count += fullPeriodCount * periodLength / 2;

            long remain = diff % periodLength;

            if (remain != 0) {
                if (columnNum > 1) {
                    long moveToNext = periodLength - Long.parseLong(from.substring(from.length() - columnNum + 1), 2);
                    System.out.println(moveToNext);
                }
                long toUp = periodLength - remain;
                System.out.println(toUp);
            }
            
        }

        return BigInteger.valueOf(0);

        //return bruteForce(left, right);

    }

    private static BigInteger bruteForce(long left, long right) {

        BigInteger count = BigInteger.valueOf(0);

        for (long i = left; i <= right; i++) {
            String str = Long.toBinaryString(i);
            System.out.println(String.format("%1$" + 10 + "s", str));
            count = count.add(BigInteger.valueOf(str.chars().parallel().filter(ch -> ch =='1').count()));
        }

        return count;
    }
}
/*
2->8
0010

0011
0100
0101
0110
0111
1000


start 5
legkozelebbi : 8
end: 9
eredmeny: 10
 0101
 0110
 0111
 1000
 1001

tavolsag / 2 (egesz resz) - barmelyik oszlopra
a kerdes, a maradek

u  : 5%2 = 1 -> 1
u-1: 5%4 = 1 -> 2. 0
u-2: 5%8 = 5 -> 2. 1
u-3: 5%16 = 5 -> 5. 0

u   - 3
u-1 - 2
u-2 - 3
u-3 - 2
szumma: 10

 */
package com.szilsan.kata.countonesinasegment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class BitCountTest {
    @Test
    public void sampleTests() {
        Assertions.assertEquals(new BigInteger("10"), BitCount.countOnes(2,8));
        Assertions.assertEquals(new BigInteger("10"), BitCount.countOnes(5,9));
        //Assertions.assertEquals(new BigInteger("7"), BitCount.countOnes(5,7));
        //Assertions.assertEquals(new BigInteger("51"), BitCount.countOnes(12,29));
        //Assertions.assertEquals(new BigInteger("51"), BitCount.countOnes(55,73));
        // Assertions.assertEquals(new BigInteger("49"), BitCount.countOnes(16,32));
        //Assertions.assertEquals(new BigInteger("51"), BitCount.countOnes(1,64));
        // Assertions.assertEquals(new BigInteger("51"), BitCount.countOnes(0,8));
        //Assertions.assertEquals(new BigInteger("143432739"), BitCount.countOnes(12,12345629));
        //Assertions.assertEquals(new BigInteger("143432739"), BitCount.countOnes(12,200000000000000l));
    }
}
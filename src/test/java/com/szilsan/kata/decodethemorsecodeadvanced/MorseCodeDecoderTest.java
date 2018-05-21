package com.szilsan.kata.decodethemorsecodeadvanced;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MorseCodeDecoderTest {
    @Test
    public void testExampleFromDescription() throws Exception {
        Assertions.assertTrue(MorseCodeDecoder.decodeMorse(MorseCodeDecoder.decodeBits("1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111000000110011001111110000001111110011001100000011")).equals("HEY JUDE"));
    }

    @Test
    public void test1() throws Exception {
        //Assertions.assertTrue(MorseCodeDecoder.decodeMorse(MorseCodeDecoder.decodeBits("01110")).equals("E"));
        //Assertions.assertTrue(MorseCodeDecoder.decodeMorse(MorseCodeDecoder.decodeBits("111")).equals("E"));
        Assertions.assertTrue(MorseCodeDecoder.decodeMorse(MorseCodeDecoder.decodeBits("111000111")).equals("I"));
    }
}
package com.szilsan.kata.decodethemorsecode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MorseCodeDecoderTest {
    @Test
    public void testExampleFromDescription() {
        Assertions.assertTrue(MorseCodeDecoder.decode(".... . -.--   .--- ..- -.. .").equals("HEY JUDE"));
    }
}
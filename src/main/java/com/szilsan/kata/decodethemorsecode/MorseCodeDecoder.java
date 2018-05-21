package com.szilsan.kata.decodethemorsecode;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MorseCodeDecoder {
    public static String decode(String morseCode) {

        return Arrays.asList(morseCode.trim().split("   "))
                .stream()
                .map(i -> Arrays.asList(i.split(" "))
                        .stream()
                        .map(j -> MorseCode.get(j))
                        .collect(Collectors.joining("")))
                .collect(Collectors.joining(" "));
    }
}

package com.szilsan.kata.squareeverydigit;

import java.util.stream.Collectors;

public class SquareDigit {

    public int squareDigits(int n) {

        String ret = ("" + n).chars()
                .mapToObj(c -> (char)c)
                .collect(Collectors.toList())
                .stream()
                .map(i -> "" + (int)Math.pow(Integer.valueOf("" + i), 2))
                .collect(Collectors.joining(""));

        return Integer.parseInt(ret);
    }

}
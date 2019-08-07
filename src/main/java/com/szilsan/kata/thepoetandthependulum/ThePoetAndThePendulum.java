package com.szilsan.kata.thepoetandthependulum;


import java.util.Arrays;

public class ThePoetAndThePendulum {

    public static int[] pendulum(final int[] input) {
        Arrays.sort(input);
        int[] result = new int[input.length];
        int pos = input.length % 2 == 1 ? (input.length - 1) /2 : input.length / 2 - 1;
        int step = 0;

        for (int i: input) {
            result[pos] = i;
            step++;
            pos = pos + (step % 2 == 1 ? step : - step);
        }
        return result;
    }
}

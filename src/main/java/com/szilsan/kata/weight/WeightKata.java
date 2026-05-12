// simple task to test

package com.szilsan.kata.weight;

import java.util.ArrayList;
import java.util.List;

public class WeightKata {
    public static void main(String[] args) {
        System.out.println("Max weight: " + weightCalculator(List.of(3,9,11,4,8), 0, 12));
    }

    public static int weightCalculator(List<Integer> weights, int init, int limit) {
        int localMax = init;
        for (int weight : weights) {
            // System.out.println(weight);
            if (init == limit || init + weight == limit) {
                return limit;
            }

            if (init + weight < limit) {
                List<Integer> subList = new ArrayList<Integer>(weights);
                subList.remove(Integer.valueOf(weight));

                int ret = weightCalculator(subList, init + weight, limit);
                if (ret > localMax) {
                    if (ret == limit) {
                        return limit;
                    }
                    localMax = ret;
                }
            }
        }

        return localMax;
    }
}


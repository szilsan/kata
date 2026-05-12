package com.szilsan.kata.onsemicoloncertasianproduct;

import java.util.HashSet;
import java.util.Set;

public class SetStuff {
    public static java.util.Set<int[]> cartesianProduct(int[][] sets) {
        if (sets == null || sets.length == 0) {
            Set ret = new HashSet();
            ret.add(new int[0]);
            return ret;
        }
        if (sets.length == 1) {
            return Set.of(sets[0]);
        }
        for (int[] set: sets) {
            if (set == null || set.length == 0) {
                Set ret = new HashSet();
                ret.add(new int[0]);
                return ret;
            }
        }
        return calculateProduct(sets);
    }

    public static Set<int[]> calculateProduct(int[][] sets) {
        Set<int[]> result = new HashSet<>();

        int[] positions = new int[sets.length - 1];

        boolean finished;
        do {
            finished = true;
            // rolling over the top set
            for (int e : sets[sets.length - 1]) {
                int[] line = new int[sets.length];
                for (int i = 0; i < positions.length; i++) {
                    line[i] = sets[i][positions[i]];
                }
                line[line.length - 1] = e;
                result.add(line);
            }

            for (int i = 0; i < positions.length; i++) {
                if (positions[positions.length - 1 - i] < (sets[sets.length - 2 - i].length - 1)) {
                    positions[positions.length - 1 - i]++;
                    finished = false;
                    break;
                } else {
                    positions[positions.length - 1 - i] = 0;
                }

            }

        } while (!finished);

        return result;
    }
}

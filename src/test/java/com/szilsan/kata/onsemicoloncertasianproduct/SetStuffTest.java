package com.szilsan.kata.onsemicoloncertasianproduct;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class SetStuffTest {

    @Test
    void test() {
        int[][] sets = {
                {1, 2},
                {3, 4},
                {5, 6, 7}
        };
        Set<int[]> solution = Set.of(
                new int[] {1,3,5},
                new int[] {1,3,6},
                new int[] {1,3,7},
                new int[] {1,4,5},
                new int[] {1,4,6},
                new int[] {1,4,7},
                new int[] {2,3,5},
                new int[] {2,3,6},
                new int[] {2,3,7},
                new int[] {2,4,5},
                new int[] {2,4,6},
                new int[] {2,4,7}
        );
        Assertions.assertEquals(solution, SetStuff.cartesianProduct(sets));
    }
}

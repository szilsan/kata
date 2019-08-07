package com.szilsan.kata.thepoetandthependulum;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ThePoetAndThePendulumTest {

    // pendulum ([-9, -2, -10, -6]) ==> [-6, -10, -9, -2]
    @Test
    void pendulum_test1() {
        int[] result = ThePoetAndThePendulum.pendulum(new int[] {-9, -2, -10, -6});
        assertTrue(Arrays.deepEquals(new Object[] {result}, new Object[] {new int[] {-6, -10, -9, -2}}));
    }

    // pendulum ([-9, -10, -6]) ==> [-6, -10, -9]
    @Test
    void pendulum_test2() {
        int[] result = ThePoetAndThePendulum.pendulum(new int[] {-9, -10, -6});
        assertTrue(Arrays.deepEquals(new Object[] {result}, new Object[] {new int[] {-6, -10, -9}}));
    }

    // pendulum ([11, -16, -18, 13, -11, -12, 3, 18 ]) ==> [13, 3, -12, -18, -16, -11, 11, 18]
    @Test
    void pendulum_test3() {
        int[] result = ThePoetAndThePendulum.pendulum(new int[] {11, -16, -18, 13, -11, -12, 3, 18 });
        assertTrue(Arrays.deepEquals(new Object[] {result}, new Object[] {new int[] {13, 3, -12, -18, -16, -11, 11, 18}}));
    }
}
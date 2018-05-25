package com.szilsan.kata.totalareacoveredbyrectangles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectanglesUnionTest {

    @Test
    public void testShapeSize() {
        Assertions.assertTrue(RectanglesUnion.shapeSize(new int[]{1, 1, 2, 2}) == 1);
        Assertions.assertTrue(RectanglesUnion.shapeSize(new int[]{1, 2, 2, 2}) == 0);
        Assertions.assertTrue(RectanglesUnion.shapeSize(new int[]{2, 1, 4, 3}) == 4);
        Assertions.assertTrue(RectanglesUnion.shapeSize(new int[]{3, 2, 6, 7}) == 15);
        Assertions.assertTrue(RectanglesUnion.shapeSize(new int[]{6, 7, 3, 2}) == 15);
    }

    @Test
    public void testOverlapSize() {
        Assertions.assertTrue(RectanglesUnion.overlap(new int[]{3, 3, 8, 5}, new int[]{6, 3, 8, 9}) == 4);
        Assertions.assertTrue(RectanglesUnion.overlap(new int[]{6, 3, 8, 9}, new int[]{11, 6, 14, 12}) == 0);
    }

    @Test
    public void testZeroRectangles() {
        int[][] recs = {};
        Assertions.assertTrue(0 == RectanglesUnion.calculateSpace(recs));
    }

    @Test
    public void testOneRectangle() {
        int[][] recs = {{0, 4, 11, 6}};
        Assertions.assertTrue(22 == RectanglesUnion.calculateSpace(recs));
    }

    @Test
    public void testThreeRectangle() {
        int[][] recs = {{0, 4, 11, 6}};
        Assertions.assertTrue(36 == RectanglesUnion.calculateSpace(new int[][]{{3, 3, 8, 5}, {6, 3, 8, 9}, {11, 6, 14, 12}}));

    }

    @Test
    public void otherTest1() {
        int[][] recs = {
                {1, 1, 2, 2},
                {1, 4, 2, 7},
                {1, 4, 2, 6},
                {1, 4, 4, 5},
                {2, 5, 6, 7},
                {4, 3, 7, 6}};


        Assertions.assertTrue(21 == RectanglesUnion.calculateSpace(recs));
    }

    @Test
    public void otherTest2() {
        int[][] recs = {
                {1, 3, 4, 5},
                {2, 1, 4, 7},
                {3, 4, 5, 6},
                {6, 6, 8, 7},
                {5, 3, 8, 4},
                {6, 0, 7, 3}};


        Assertions.assertTrue(24 == RectanglesUnion.calculateSpace(recs));
    }

}
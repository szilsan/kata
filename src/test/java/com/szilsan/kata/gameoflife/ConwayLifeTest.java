package com.szilsan.kata.gameoflife;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ConwayLifeTest {

    @Test
    public void testTrimFieldEmpty() {
        int[][] test = {{}};
        Assert.assertEquals(ConwayLife.trimField(test), test);
    }

    @Test
    public void testTrimFieldEmptyFirstLines() {
        int[][] test = {{0, 0, 0}, {1, 0, 1}, {1, 1, 1}};
        Assert.assertEquals(ConwayLife.trimField(test), new int[][]{{1, 0, 1}, {1, 1, 1}});

        int[][] test2 = {{0, 0, 0}, {0, 0, 0}, {1, 1, 1}};
        Assert.assertEquals(ConwayLife.trimField(test2), new int[][]{{1, 1, 1}});
    }

    @Test
    public void testTrimFieldEmptyLastLines() {
        int[][] test = {{1, 0, 0}, {1, 0, 1}, {0, 0, 0}};
        Assert.assertEquals(ConwayLife.trimField(test), new int[][]{{1, 0, 0}, {1, 0, 1}});

        int[][] test2 = {{1, 0, 0}, {1, 0, 1}, {0, 0, 0}, {0, 0, 0}};
        Assert.assertEquals(ConwayLife.trimField(test2), new int[][]{{1, 0, 0}, {1, 0, 1}});
    }

    @Test
    public void testTrimFieldEmptyLines() {
        int[][] test = {{0, 0, 0}, {1, 0, 1}, {0, 0, 0}};
        Assert.assertEquals(ConwayLife.trimField(test), new int[][]{{1, 0, 1}});
    }

    @Test
    public void testTrimFieldEmptyCols() {
        int[][] test = {{0, 0, 1}, {0, 0, 1}, {0, 1, 1}};
        Assert.assertEquals(ConwayLife.trimField(test), new int[][]{{0, 1}, {0, 1}, {1, 1}});

        int[][] test2 = {{0, 0, 1, 0}, {0, 0, 1, 0}, {0, 1, 1, 0}};
        Assert.assertEquals(ConwayLife.trimField(test2), new int[][]{{0, 1}, {0, 1}, {1, 1}});
    }

    @Test
    public void testRoundFieldWithZero() {
        int[][] test = {{0, 0, 1}, {0, 0, 1}, {0, 1, 1}};
        Assert.assertEquals(ConwayLife.roundFieldWithZero(test), new int[][]{{0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0}});
    }

    @Test
    public void testNextValue() {
        int[][] test = {{0, 0, 1}, {0, 0, 1}, {0, 1, 1}};
        Assert.assertEquals(ConwayLife.nextValue(test, 0, 0), 0);
        Assert.assertEquals(ConwayLife.nextValue(test, 1, 0), 1);
        Assert.assertEquals(ConwayLife.nextValue(test, 1, 1), 4);
    }

    @Test
    public void testNextCycle() {
        int[][] test = {{1, 0, 0},
                {0, 1, 1},
                {1, 1, 0}};
        Assert.assertEquals(ConwayLife.calculateNextCycle(test), new int[][] {{0,1,0},
                {0,0,1},
                {1,1,1}});
    }

}

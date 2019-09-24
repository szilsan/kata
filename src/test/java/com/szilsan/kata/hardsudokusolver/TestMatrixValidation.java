package com.szilsan.kata.hardsudokusolver;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestMatrixValidation {

    @Test
    void testInvalidCreation() {
        assertThrows(IllegalArgumentException.class, () -> new PlayGround(null));
    }

    @Test
    void testInvalidGets() {
        int[][] grid = {
                {8, 2, 0, 0, 0, 1, 0, 0, 0},
                {0, 4, 0, 0, 0, 0, 0, 2, 0},
                {0, 0, 0, 0, 0, 6, 0, 0, 4},
                {0, 0, 0, 4, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 8, 0, 0, 0, 0},
                {2, 0, 9, 0, 0, 0, 0, 0, 7},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 8, 0, 0, 3, 0, 6, 0},
                {0, 6, 0, 0, 0, 0, 0, 0, 8}};

        assertThrows(IllegalArgumentException.class, () -> new PlayGround(grid).getRow(-1));
        assertThrows(IllegalArgumentException.class, () -> new PlayGround(grid).getRow(9));
        assertThrows(IllegalArgumentException.class, () -> new PlayGround(grid).getCol(-1));
        assertThrows(IllegalArgumentException.class, () -> new PlayGround(grid).getCol(9));
        assertThrows(IllegalArgumentException.class, () -> new PlayGround(grid).getBlock(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> new PlayGround(grid).getBlock(3, 0));
        assertThrows(IllegalArgumentException.class, () -> new PlayGround(grid).getBlock(1, 3));
        assertThrows(IllegalArgumentException.class, () -> new PlayGround(grid).getBlock(1, -3));
    }

    @Test
    void testCreationRow() {
        int[][] grid = {
                {8, 2, 0, 0, 0, 1, 0, 0, 0},
                {0, 4, 0, 0, 0, 0, 0, 2, 0},
                {0, 0, 0, 0, 0, 6, 0, 0, 4},
                {0, 0, 0, 4, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 8, 0, 0, 0, 0},
                {2, 0, 9, 0, 0, 0, 0, 0, 7},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 8, 0, 0, 3, 0, 6, 0},
                {0, 6, 0, 0, 0, 0, 0, 0, 8}};

        final PlayGround playGround = new PlayGround(grid);
        for (int i = 0; i < 9; i++) {
            Assert.assertTrue(playGround.getRow(i).length == 9);
        }
        Assert.assertTrue(Arrays.equals(playGround.getRow(0), new int[]{8, 2, 0, 0, 0, 1, 0, 0, 0}));
        Assert.assertTrue(Arrays.equals(playGround.getRow(1), new int[]{0, 4, 0, 0, 0, 0, 0, 2, 0}));
        Assert.assertTrue(Arrays.equals(playGround.getRow(2), new int[]{0, 0, 0, 0, 0, 6, 0, 0, 4}));
        Assert.assertTrue(Arrays.equals(playGround.getRow(3), new int[]{0, 0, 0, 4, 0, 0, 0, 0, 0}));
        Assert.assertTrue(Arrays.equals(playGround.getRow(4), new int[]{0, 0, 0, 0, 8, 0, 0, 0, 0}));
        Assert.assertTrue(Arrays.equals(playGround.getRow(5), new int[]{2, 0, 9, 0, 0, 0, 0, 0, 7}));
        Assert.assertTrue(Arrays.equals(playGround.getRow(6), new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0}));
        Assert.assertTrue(Arrays.equals(playGround.getRow(7), new int[]{0, 0, 8, 0, 0, 3, 0, 6, 0}));
        Assert.assertTrue(Arrays.equals(playGround.getRow(8), new int[]{0, 6, 0, 0, 0, 0, 0, 0, 8}));
    }

    @Test
    void testCreationCol() {
        int[][] grid = {
                {8, 2, 0, 0, 0, 1, 0, 0, 0},
                {0, 4, 0, 0, 0, 0, 0, 2, 0},
                {0, 0, 0, 0, 0, 6, 0, 0, 4},
                {0, 0, 0, 4, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 8, 0, 0, 0, 0},
                {2, 0, 9, 0, 0, 0, 0, 0, 7},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 8, 0, 0, 3, 0, 6, 0},
                {0, 6, 0, 0, 0, 0, 0, 0, 8}};

        final PlayGround playGround = new PlayGround(grid);
        for (int i = 0; i < 9; i++) {
            Assert.assertTrue(playGround.getCol(i).length == 9);
        }
        Assert.assertTrue(Arrays.equals(playGround.getCol(0), new int[]{8, 0, 0, 0, 0, 2, 0, 0, 0}));
    }

    @Test
    void testCreationBlock() {
        int[][] grid = {
                {8, 2, 0, 0, 0, 1, 0, 0, 0},
                {0, 4, 0, 0, 0, 0, 0, 2, 0},
                {0, 0, 0, 0, 0, 6, 0, 0, 4},
                {0, 0, 0, 4, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 8, 0, 0, 0, 0},
                {2, 0, 9, 0, 0, 0, 0, 0, 7},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 8, 0, 0, 3, 0, 6, 0},
                {0, 6, 0, 0, 0, 0, 0, 0, 8}};

        final PlayGround playGround = new PlayGround(grid);
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Assert.assertTrue(playGround.getBlock(row, col).length == 3);
                Assert.assertTrue(playGround.getBlock(row, col)[0].length == 3);
                Assert.assertTrue(playGround.getBlock(row, col)[1].length == 3);
                Assert.assertTrue(playGround.getBlock(row, col)[2].length == 3);
            }
        }

        Assert.assertTrue(Arrays.deepEquals(playGround.getBlock(0, 0), new int[][]{
                {8, 2, 0},
                {0, 4, 0},
                {0, 0, 0}
        }));
        Assert.assertTrue(Arrays.deepEquals(playGround.getBlock(1, 1), new int[][]{
                {4, 0, 0},
                {0, 8, 0},
                {0, 0, 0}
        }));
        Assert.assertTrue(Arrays.deepEquals(playGround.getBlock(2, 1), new int[][]{
                {0, 0, 0},
                {0, 0, 3},
                {0, 0, 0}
        }));
    }

    @Test
    void testNonFinalValidationValid() {
        int[][] grid = {
                {8, 2, 0, 0, 0, 1, 0, 0, 0},
                {0, 4, 0, 0, 0, 0, 0, 2, 0},
                {0, 0, 0, 0, 0, 6, 0, 0, 4},
                {0, 0, 0, 4, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 8, 0, 0, 0, 0},
                {2, 0, 9, 0, 0, 0, 0, 0, 7},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 8, 0, 0, 3, 0, 6, 0},
                {0, 6, 0, 0, 0, 0, 0, 0, 8}};

        final PlayGround playGround = new PlayGround(grid);
        Assert.assertTrue(playGround.validateAsNonFinal());
        Assert.assertFalse(playGround.validateAsFinal());
    }

    @Test
    void testNonFinalValidationInvalid() {
        int[][] grid = {
                {8, 2, 0, 0, 0, 1, 0, 0, 0},
                {0, 4, 0, 0, 0, 0, 0, 2, 0},
                {0, 0, 0, 0, 0, 6, 0, 0, 4},
                {0, 0, 0, 4, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 8, 0, 0, 8, 0}, // invalid line
                {2, 0, 9, 0, 0, 0, 0, 0, 7},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 8, 0, 0, 3, 0, 6, 0},
                {0, 6, 0, 0, 0, 0, 0, 0, 8}};

        final PlayGround playGround = new PlayGround(grid);
        Assert.assertFalse(playGround.validateAsNonFinal());
        Assert.assertFalse(playGround.validateAsFinal());
    }

    @Test
    void testFinalValidationValid() {
        int[][] grid = {{3, 4, 6, 1, 2, 7, 9, 5, 8},
                {7, 8, 5, 6, 9, 4, 1, 3, 2},
                {2, 1, 9, 3, 8, 5, 4, 6, 7},
                {4, 6, 2, 5, 3, 1, 8, 7, 9},
                {9, 3, 1, 2, 7, 8, 6, 4, 5},
                {8, 5, 7, 9, 4, 6, 2, 1, 3},
                {5, 9, 8, 4, 1, 3, 7, 2, 6},
                {6, 2, 4, 7, 5, 9, 3, 8, 1},
                {1, 7, 3, 8, 6, 2, 5, 9, 4}};

        final PlayGround playGround = new PlayGround(grid);
        Assert.assertTrue(playGround.validateAsNonFinal());
        Assert.assertTrue(playGround.validateAsFinal());
    }

    @Test
    void testFinalValidationInvalid() {
        int[][] grid = {{3, 4, 6, 1, 2, 7, 9, 5, 8},
                {7, 8, 5, 6, 9, 4, 1, 3, 2},
                {2, 1, 9, 3, 8, 5, 4, 6, 7},
                {4, 6, 8, 5, 3, 1, 8, 7, 9},
                {9, 3, 1, 2, 7, 8, 6, 4, 5},
                {8, 5, 7, 9, 4, 6, 2, 1, 3},
                {5, 9, 8, 4, 1, 3, 7, 2, 6},
                {6, 2, 4, 7, 5, 9, 3, 8, 1},
                {1, 7, 3, 8, 6, 2, 5, 9, 4}};

        final PlayGround playGround = new PlayGround(grid);
        Assert.assertFalse(playGround.validateAsNonFinal());
        Assert.assertFalse(playGround.validateAsFinal());
    }
}

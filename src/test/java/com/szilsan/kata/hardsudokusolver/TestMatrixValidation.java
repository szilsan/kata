package com.szilsan.kata.hardsudokusolver;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TestMatrixValidation {
    @Test
    void testNonFinalValidationValid() {
        int[][] grid = {
                {8,2,0,0,0,1,0,0,0},
                {0,4,0,0,0,0,0,2,0},
                {0,0,0,0,0,6,0,0,4},
                {0,0,0,4,0,0,0,0,0},
                {0,0,0,0,8,0,0,0,0},
                {2,0,9,0,0,0,0,0,7},
                {0,0,0,0,0,0,0,0,0},
                {0,0,8,0,0,3,0,6,0},
                {0,6,0,0,0,0,0,0,8}};

        final SudokuSolver solver = new SudokuSolver(grid, false);
        solver.splitCellsIntoColsRowsBlocks();
        Assert.assertTrue(solver.validateNonFinalMatrix());
    }

    @Test
    void testNonFinalValidationInvalid() {
        int[][] grid = {
                {8,2,0,0,0,1,0,0,0},
                {0,4,0,0,0,0,0,2,0},
                {0,0,0,0,0,6,0,0,4},
                {0,0,0,4,0,0,0,0,0},
                {0,0,0,0,8,0,0,8,0},
                {2,0,9,0,0,0,0,0,7},
                {0,0,0,0,0,0,0,0,0},
                {0,0,8,0,0,3,0,6,0},
                {0,6,0,0,0,0,0,0,8}};

        final SudokuSolver solver = new SudokuSolver(grid, false);
        solver.splitCellsIntoColsRowsBlocks();
        Assert.assertFalse(solver.validateNonFinalMatrix());
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

        final SudokuSolver solver = new SudokuSolver(grid, false);
        solver.splitCellsIntoColsRowsBlocks();
        Assert.assertTrue(solver.validateFinalMatrix());
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

        final SudokuSolver solver = new SudokuSolver(grid, false);
        solver.splitCellsIntoColsRowsBlocks();
        Assert.assertFalse(solver.validateFinalMatrix());
    }
}

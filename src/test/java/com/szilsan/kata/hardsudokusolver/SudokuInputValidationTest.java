package com.szilsan.kata.hardsudokusolver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SudokuInputValidationTest {

    @Test
    void testValidateInvalidNull() {
        int[][] grid = null;
        assertThrows(IllegalArgumentException.class, () -> SudokuSolver.initialInputValidation(grid));
    }

    @Test
    void testValidateInvalidSize1() {
        int[][] grid = new int[3][2];
        assertThrows(IllegalArgumentException.class, () -> SudokuSolver.initialInputValidation(grid));
    }

    @Test
    void testValidateInvalidSize3() {
        int[][] grid = new int[9][];
        grid[0] = new int[3];
        grid[1] = new int[2];
        assertThrows(IllegalArgumentException.class, () -> SudokuSolver.initialInputValidation(grid));
    }

    @Test
    void testValidateInvalidElement() {
        int[][] grid = {{0, 0, 6, 1, 0, 0, 0, 0, 8},
                {0, 8, 0, 0, 9, 0, 0, 3, 0},
                {2, 0, 0, 0, 0, 5, 4, 0, 0},
                {4, 0, 0, 0, 0, 1, 8, 0, 0},
                {0, 3, 0, 0, 7, 0, 0, 11, 0},
                {0, 0, 7, 9, 0, 0, 0, 0, 3},
                {0, 0, 8, 4, 0, 0, 0, 0, 6},
                {0, 2, 0, 0, 5, 0, 0, 8, 0},
                {1, 0, 0, 0, 0, 2, 5, 0, 0}};

        assertThrows(IllegalArgumentException.class, () -> SudokuSolver.initialInputValidation(grid));
    }

    @Test
    void testValidateInvalidElementCount() {
        int[][] grid = {
                {0, 0, 6, 1, 0, 0, 0, 0, 8},
                {0, 8, 0, 0, 9, 0, 0, 3, 0},
                {2, 0, 0, 0, 0, 0, 4, 0, 0},
                {4, 0, 0, 0, 0, 1, 8, 0, 0},
                {0, 3, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 3},
                {0, 0, 0, 4, 0, 0, 0, 0, 6},
                {0, 0, 0, 0, 0, 0, 0, 8, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}};

        assertThrows(IllegalArgumentException.class, () -> SudokuSolver.initialInputValidation(grid));
    }
}

package com.szilsan.kata.sudokusolver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolverTest {

    private static final int[][] solution = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
    };

    private static final int[][] oneZero = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 0, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
    };

    private static final int[][] twoZeroes = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 0, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 0, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
    };

    private static final int[][] fiveZeroes = {
            {5, 0, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 0},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 0, 5, 0, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 0, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
    };

    private static final int[][] manyZeroes = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

    @Test
    public void testSolveLine() {
        Assertions.assertFalse(Solver.lineSolver(new int[] {1,0,3,0,5,6,7,0,9}).wasModified());
        Assertions.assertTrue(Solver.lineSolver(new int[] {1,2,3,4,5,6,7,0,9}).wasModified());
    }

    @Test
    public void testSolveGrid() {
        FieldModification fieldModification = Solver.gridSolver(new int[][]{{1, 2, 3}, {0, 5, 6}, {7, 8, 9}});
        Assertions.assertTrue(fieldModification.wasModified());
        Assertions.assertEquals(1, fieldModification.row());
        Assertions.assertEquals(0, fieldModification.col());
        Assertions.assertEquals(4, fieldModification.newValue());

        Assertions.assertFalse(Solver.gridSolver(new int[][] {{1,2,0},{0,5,6},{7,8,9}}).wasModified());
    }

    @Test
    public void testValidationMatrix() {
        int[][] validPositions = Solver.numberPozFind(new SudokuModel(manyZeroes), 5);
        Assertions.assertArrayEquals(new int[][] {
                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1},
                {1,1,1,0,0,1,0,1,1},
                {1,0,0,0,1,1,0,0,1},
                {1,0,0,1,0,1,0,0,1},
                {1,0,0,0,1,1,0,0,1},
                {1,1,0,0,0,1,1,1,1},
                {1,1,1,1,1,1,1,1,1},
                {1,0,0,0,1,1,0,1,1}}, validPositions);
    }


    @Test
    public void testSolveSudoku() {
//        Assertions.assertArrayEquals(solution, Solver.solve(new SudokuModel(oneZero)).getModel());
//        Assertions.assertArrayEquals(solution, Solver.solve(new SudokuModel(twoZeroes)).getModel());
//        Assertions.assertArrayEquals(solution, Solver.solve(new SudokuModel(fiveZeroes)).getModel());
        Assertions.assertArrayEquals(solution, Solver.solve(new SudokuModel(manyZeroes)).getModel());
    }
}



package com.szilsan.kata.hardsudokusolver;

import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SudokuSolverTest {

    @Test
    void solveEasy() {

        int[][] puzzle ={
                {0,0,0,0,6,8,9,3,5},
                {0,0,9,0,0,0,0,0,0},
                {0,0,3,4,0,0,1,0,0},
                {0,0,0,9,0,7,2,0,0},
                {0,0,2,8,0,0,0,7,1},
                {0,8,5,6,0,0,0,9,0},
                {0,1,0,0,0,6,3,0,9},
                {5,0,6,3,0,0,0,1,0},
                {2,0,7,0,0,5,0,4,6}},

                solution = {{1, 2, 4, 7, 6, 8, 9, 3, 5,},
                        {8, 7, 9, 5, 3, 1, 6, 2, 4,},
                        {6, 5, 3, 4, 2, 9, 1, 8, 7,},
                        {3, 4, 1, 9, 5, 7, 2, 6, 8,},
                        {9, 6, 2, 8, 4, 3, 5, 7, 1,},
                        {7, 8, 5, 6, 1, 2, 4, 9, 3,},
                        {4, 1, 8, 2, 7, 6, 3, 5, 9,},
                        {5, 9, 6, 3, 8, 4, 7, 1, 2,},
                        {2, 3, 7, 1, 9, 5, 8, 4, 6,}};


        assertArrayEquals(solution, new SudokuSolver(puzzle).solve());
    }

    @Test
    void solveEasy2() {

        int[][] puzzle ={
                {0,0,0,0,0,4,0,0,5},
                {0,0,7,9,6,0,0,8,2},
                {0,0,4,8,2,5,0,0,0},
                {0,7,1,0,9,0,3,5,0},
                {4,8,0,0,0,0,9,0,0},
                {5,9,0,0,7,0,0,1,8},
                {9,0,0,6,0,2,0,4,0},
                {0,0,0,0,4,0,8,0,1},
                {0,0,0,0,0,0,5,9,6}},

                solution = {
                        {8,2,9,7,1,4,6,3,5,},
                        {1,5,7,9,6,3,4,8,2,},
                        {3,6,4,8,2,5,1,7,9,},
                        {6,7,1,2,9,8,3,5,4,},
                        {4,8,2,3,5,1,9,6,7,},
                        {5,9,3,4,7,6,2,1,8,},
                        {9,1,5,6,8,2,7,4,3,},
                        {7,3,6,5,4,9,8,2,1,},
                        {2,4,8,1,3,7,5,9,6,}};


        assertArrayEquals(solution, new SudokuSolver(puzzle).solve());
    }

    @Test
    void solveMedium() {

        int[][] puzzle ={
                {0,0,3,0,4,9,0,0,6},
                {0,6,1,8,0,3,2,0,4},
                {9,0,5,0,6,1,0,0,8},
                {0,9,6,0,3,0,7,0,0},
                {0,7,0,5,0,0,9,0,0},
                {0,5,0,0,0,8,6,2,1},
                {0,8,0,0,0,0,1,5,0},
                {4,0,0,0,9,0,0,0,0},
                {0,0,9,1,0,0,4,0,0}},

                solution = {
                        {8,2,3,7,4,9,5,1,6,},
                        {7,6,1,8,5,3,2,9,4,},
                        {9,4,5,2,6,1,3,7,8,},
                        {1,9,6,4,3,2,7,8,5,},
                        {2,7,8,5,1,6,9,4,3,},
                        {3,5,4,9,7,8,6,2,1,},
                        {6,8,7,3,2,4,1,5,9,},
                        {4,1,2,6,9,5,8,3,7,},
                        {5,3,9,1,8,7,4,6,2,}};


        assertArrayEquals(solution, new SudokuSolver(puzzle).solve());
    }

    @Test
    void solveHard() {

        int[][] puzzle ={
                {5,4,1,0,6,0,0,0,0},
                {3,0,0,0,0,1,8,0,0},
                {7,0,0,0,0,0,0,0,0},
                {0,2,0,0,1,0,0,0,8},
                {0,0,0,0,4,0,9,6,0},
                {4,0,0,0,0,8,0,0,3},
                {0,3,0,6,0,2,1,0,0},
                {0,0,0,1,7,0,0,0,5},
                {0,0,0,0,0,0,0,0,0}},

                solution = {
                        {8,2,3,7,4,9,5,1,6,},
                        {7,6,1,8,5,3,2,9,4,},
                        {9,4,5,2,6,1,3,7,8,},
                        {1,9,6,4,3,2,7,8,5,},
                        {2,7,8,5,1,6,9,4,3,},
                        {3,5,4,9,7,8,6,2,1,},
                        {6,8,7,3,2,4,1,5,9,},
                        {4,1,2,6,9,5,8,3,7,},
                        {5,3,9,1,8,7,4,6,2,}};


        assertArrayEquals(solution, new SudokuSolver(puzzle).solve());
    }

    @Test
    void solve() {

        int[][] puzzle = {{0, 0, 6, 1, 0, 0, 0, 0, 8},
                {0, 8, 0, 0, 9, 0, 0, 3, 0},
                {2, 0, 0, 0, 0, 5, 4, 0, 0},
                {4, 0, 0, 0, 0, 1, 8, 0, 0},
                {0, 3, 0, 0, 7, 0, 0, 4, 0},
                {0, 0, 7, 9, 0, 0, 0, 0, 3},
                {0, 0, 8, 4, 0, 0, 0, 0, 6},
                {0, 2, 0, 0, 5, 0, 0, 8, 0},
                {1, 0, 0, 0, 0, 2, 5, 0, 0}},

                solution = {{3, 4, 6, 1, 2, 7, 9, 5, 8},
                        {7, 8, 5, 6, 9, 4, 1, 3, 2},
                        {2, 1, 9, 3, 8, 5, 4, 6, 7},
                        {4, 6, 2, 5, 3, 1, 8, 7, 9},
                        {9, 3, 1, 2, 7, 8, 6, 4, 5},
                        {8, 5, 7, 9, 4, 6, 2, 1, 3},
                        {5, 9, 8, 4, 1, 3, 7, 2, 6},
                        {6, 2, 4, 7, 5, 9, 3, 8, 1},
                        {1, 7, 3, 8, 6, 2, 5, 9, 4}};

        assertArrayEquals(solution, new SudokuSolver(puzzle).solve());
    }

    @Test
    void testValidateInvalidNull() {
        int[][] grid = null;
        assertThrows(IllegalArgumentException.class, () -> new SudokuSolver(grid));
    }

    @Test
    void testValidateInvalidSize1() {
        int[][] grid = new int[3][2];
        assertThrows(IllegalArgumentException.class, () -> new SudokuSolver(grid));
    }

    @Test
    void testValidateInvalidSize3() {
        int[][] grid = new int[9][];
        grid[0] = new int[3];
        grid[1] = new int[2];
        assertThrows(IllegalArgumentException.class, () -> new SudokuSolver(grid));
    }

    @Test
    void testValidateInvalidElement() {
        int[][] puzzle = {{0, 0, 6, 1, 0, 0, 0, 0, 8},
                {0, 8, 0, 0, 9, 0, 0, 3, 0},
                {2, 0, 0, 0, 0, 5, 4, 0, 0},
                {4, 0, 0, 0, 0, 1, 8, 0, 0},
                {0, 3, 0, 0, 7, 0, 0, 11, 0},
                {0, 0, 7, 9, 0, 0, 0, 0, 3},
                {0, 0, 8, 4, 0, 0, 0, 0, 6},
                {0, 2, 0, 0, 5, 0, 0, 8, 0},
                {1, 0, 0, 0, 0, 2, 5, 0, 0}};

        assertThrows(IllegalArgumentException.class, () -> new SudokuSolver(puzzle));
    }

    @Test
    void testValidateValidSolution() {
        int[][] solution = {
                {3, 4, 6, 1, 2, 7, 9, 5, 8},
                {7, 8, 5, 6, 9, 4, 1, 3, 2},
                {2, 1, 9, 3, 8, 5, 4, 6, 7},
                {4, 6, 2, 5, 3, 1, 8, 7, 9},
                {9, 3, 1, 2, 7, 8, 6, 4, 5},
                {8, 5, 7, 9, 4, 6, 2, 1, 3},
                {5, 9, 8, 4, 1, 3, 7, 2, 6},
                {6, 2, 4, 7, 5, 9, 3, 8, 1},
                {1, 7, 3, 8, 6, 2, 5, 9, 4}};

        assertTrue(SudokuSolver.validateFilledGrid(solution));
    }

    @Test
    void testValidateValidSolution2() {
        int[][] solution = {
               {7, 2, 6, 4, 9, 3, 8, 1, 5},
               {3, 1, 5, 7, 2, 8, 9, 4, 6},
               {4, 8, 9, 6, 5, 1, 2, 3, 7},
               {8, 5, 2, 1, 4, 7, 6, 9, 3},
               {6, 7, 3, 9, 8, 5, 1, 2, 4},
               {9, 4, 1, 3, 6, 2, 7, 5, 8},
               {1, 9, 4, 8, 3, 6, 5, 7, 2},
               {5, 6, 7, 2, 1, 4, 3, 8, 9},
               {2, 3, 8, 5, 7, 9, 4, 6, 1}};

        assertTrue(SudokuSolver.validateFilledGrid(solution));
    }

    @Test
    void testValidateInvalidSolution() {
        int[][] solution = {
                {3, 4, 6, 1, 2, 7, 9, 2, 8},
                {7, 8, 5, 6, 9, 4, 1, 3, 2},
                {2, 1, 9, 3, 8, 5, 4, 6, 7},
                {4, 6, 2, 5, 3, 1, 8, 7, 9},
                {9, 3, 1, 2, 7, 8, 6, 4, 5},
                {8, 5, 7, 9, 4, 6, 2, 1, 3},
                {5, 9, 8, 4, 1, 3, 7, 2, 6},
                {6, 2, 4, 7, 5, 9, 3, 8, 1},
                {1, 7, 3, 8, 6, 2, 5, 9, 4}};

        assertFalse(SudokuSolver.validateFilledGrid(solution));
    }

    @Test
    void testValidateInvalidSolution2() {
        int[][] solution = {
                {3, 4, 6, 1, 2, 7, 9, 5, 8},
                {7, 8, 5, 6, 9, 4, 1, 3, 2},
                {2, 1, 9, 3, 8, 5, 4, 6, 7},
                {4, 6, 2, 5, 3, 1, 8, 7, 9},
                {9, 3, 1, 2, 7, 8, 6, 4, 5},
                {8, 5, 7, 9, 4, 6, 2, 1, 3},
                {5, 9, 8, 4, 1, 1, 7, 2, 6},
                {6, 2, 4, 7, 5, 9, 3, 8, 1},
                {1, 7, 3, 8, 6, 2, 5, 9, 4}};

        assertFalse(SudokuSolver.validateFilledGrid(solution));
    }

    @Test
    void testGetBlock() {
        int[][] solution = {
                {3, 4, 6, 1, 2, 7, 9, 2, 8},
                {7, 8, 5, 6, 9, 4, 1, 3, 2},
                {2, 1, 9, 3, 8, 5, 4, 6, 7},
                {4, 6, 2, 5, 3, 1, 8, 7, 9},
                {9, 3, 1, 2, 7, 8, 6, 4, 5},
                {8, 5, 7, 9, 4, 6, 2, 1, 3},
                {5, 9, 8, 4, 1, 3, 7, 2, 6},
                {6, 2, 4, 7, 5, 9, 3, 8, 1},
                {1, 7, 3, 8, 6, 2, 5, 9, 4}};
        SudokuSolver solver = new SudokuSolver(solution);
        Set<SudokuSolver.Cell> result = solver.getBlock(0,0);
        assertTrue(result.size() == 9);
        assertTrue(result.stream().filter(c -> c.row == 0 && c.col == 0).count() == 1);
        assertTrue(result.stream().filter(c -> c.row == 0 && c.col == 1).count() == 1);
        assertTrue(result.stream().filter(c -> c.row == 0 && c.col == 2).count() == 1);
        assertTrue(result.stream().filter(c -> c.row == 1 && c.col == 0).count() == 1);
        assertTrue(result.stream().filter(c -> c.row == 1 && c.col == 1).count() == 1);
        assertTrue(result.stream().filter(c -> c.row == 1 && c.col == 2).count() == 1);
        assertTrue(result.stream().filter(c -> c.row == 2 && c.col == 0).count() == 1);
        assertTrue(result.stream().filter(c -> c.row == 2 && c.col == 1).count() == 1);
        assertTrue(result.stream().filter(c -> c.row == 2 && c.col == 2).count() == 1);


        result = solver.getBlock(1,2);
        assertTrue(result.size() == 9);
        assertTrue(result.stream().filter(c -> c.row == 6 && c.col == 3).count() == 1);
        assertTrue(result.stream().filter(c -> c.row == 7 && c.col == 3).count() == 1);
        assertTrue(result.stream().filter(c -> c.row == 8 && c.col == 3).count() == 1);
        assertTrue(result.stream().filter(c -> c.row == 6 && c.col == 4).count() == 1);
        assertTrue(result.stream().filter(c -> c.row == 7 && c.col == 4).count() == 1);
        assertTrue(result.stream().filter(c -> c.row == 8 && c.col == 4).count() == 1);
        assertTrue(result.stream().filter(c -> c.row == 6 && c.col == 5).count() == 1);
        assertTrue(result.stream().filter(c -> c.row == 7 && c.col == 5).count() == 1);
        assertTrue(result.stream().filter(c -> c.row == 8 && c.col == 5).count() == 1);

    }

    @Test
    void testConvertToGrid() {
        int[][] solution = {
                {3, 4, 6, 1, 2, 7, 9, 5, 8},
                {7, 8, 5, 6, 9, 4, 1, 3, 2},
                {2, 1, 9, 3, 8, 5, 4, 6, 7},
                {4, 6, 2, 5, 3, 1, 8, 7, 9},
                {9, 3, 1, 2, 7, 8, 6, 4, 5},
                {8, 5, 7, 9, 4, 6, 2, 1, 3},
                {5, 9, 8, 4, 1, 3, 7, 2, 6},
                {6, 2, 4, 7, 5, 9, 3, 8, 1},
                {1, 7, 3, 8, 6, 2, 5, 9, 4}};

        assertArrayEquals(solution, new SudokuSolver(solution).convertToGrid());
    }
}

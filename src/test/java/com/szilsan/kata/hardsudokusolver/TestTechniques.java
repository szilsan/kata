package com.szilsan.kata.hardsudokusolver;

import com.sun.source.tree.AssertTree;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestTechniques {

    @Test
    public void testSingle() {
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
        solver.removeUniqueFromUnitsOf9();
        int[][] result = solver.convertCellsToGrid(false);
        Assert.assertTrue(result != null);
        Assert.assertTrue(result[5][5] == 5);
        Assert.assertTrue(solver.validateNonFinalMatrix());
    }

    @Test
    public void testSingle2() {
        int[][] grid = {
                {8,2,0,0,0,1,0,0,0},
                {0,4,0,0,0,0,0,2,0},
                {0,0,0,0,0,7,0,0,4},
                {0,0,0,4,0,0,0,0,0},
                {0,0,0,0,8,0,0,0,0},
                {2,0,9,0,0,0,0,0,7},
                {0,0,0,0,0,0,0,0,0},
                {0,0,8,0,0,3,0,6,0},
                {0,6,0,0,0,0,0,0,8}};

        final SudokuSolver solver = new SudokuSolver(grid, false);
        solver.splitCellsIntoColsRowsBlocks();
        solver.removeUniqueFromUnitsOf9();
        int[][] result = solver.convertCellsToGrid(false);
        Assert.assertTrue(result != null);
        Assert.assertTrue(result[5][5] == 0);
        Assert.assertTrue(solver.validateNonFinalMatrix());
    }
}

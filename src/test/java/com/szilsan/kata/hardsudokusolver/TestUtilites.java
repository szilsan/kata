package com.szilsan.kata.hardsudokusolver;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestUtilites {

    @Test
    void testGetBlock() {
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
        SudokuSolver solver = new SudokuSolver(solution);
        Set<SudokuSolver.Cell> result = solver.getBlock(SudokuSolver.convertGridToCells(solution), 0, 0);
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


        result = solver.getBlock(SudokuSolver.convertGridToCells(solution),1, 2);
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

        assertArrayEquals(solution, new SudokuSolver(solution).convertCellsToGrid(SudokuSolver.convertGridToCells(solution),true));
        assertArrayEquals(solution, new SudokuSolver(solution).convertCellsToGrid(SudokuSolver.convertGridToCells(solution),false));
    }

    @Test
    void testValidateFinal9Elements() {
        SudokuSolver.Cell cell1 = new SudokuSolver.Cell(1, 1, 1);
        SudokuSolver.Cell cell2 = new SudokuSolver.Cell(2, 1, 2);
        SudokuSolver.Cell cell3 = new SudokuSolver.Cell(3, 1, 3);
        SudokuSolver.Cell cell4 = new SudokuSolver.Cell(4, 1, 4);
        SudokuSolver.Cell cell5 = new SudokuSolver.Cell(5, 1, 5);
        SudokuSolver.Cell cell6 = new SudokuSolver.Cell(6, 1, 6);
        SudokuSolver.Cell cell7 = new SudokuSolver.Cell(7, 1, 7);
        SudokuSolver.Cell cell8 = new SudokuSolver.Cell(8, 1, 8);
        SudokuSolver.Cell cell9 = new SudokuSolver.Cell(9, 1, 9);
        Set<SudokuSolver.Cell> cells = new HashSet<>(Arrays.asList(cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9));

        assertTrue(SudokuSolver.validateFinal9Elements(cells));

        cell1 = new SudokuSolver.Cell(1, 1, 2);
        cells = new HashSet<>(Arrays.asList(cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9));
        assertFalse(SudokuSolver.validateFinal9Elements(cells));

        cell1 = null;
        cells = new HashSet<>(Arrays.asList(cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9));
        assertFalse(SudokuSolver.validateFinal9Elements(cells));

        cells = new HashSet<>(Arrays.asList(cell1, cell2, cell3, cell4, cell5, cell6, cell7));
        assertFalse(SudokuSolver.validateFinal9Elements(cells));

        cell1 = new SudokuSolver.Cell(1, 1, 2);
        cell2 = new SudokuSolver.Cell(1, 2, 2);
        cells = new HashSet<>(Arrays.asList(cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9));
        assertFalse(SudokuSolver.validateFinal9Elements(cells));
    }

    @Test
    void testValidateNonFinal9Elements() {
        SudokuSolver.Cell cell1 = new SudokuSolver.Cell(1, 1, 1);
        SudokuSolver.Cell cell2 = new SudokuSolver.Cell(2, 2);
        cell2.getPossibleValues().addAll(Arrays.asList(2,4,7));
        SudokuSolver.Cell cell3 = new SudokuSolver.Cell(3, 1, 3);
        SudokuSolver.Cell cell4 = new SudokuSolver.Cell(4, 1);
        cell4.getPossibleValues().addAll(Arrays.asList(2,4,7));
        SudokuSolver.Cell cell5 = new SudokuSolver.Cell(5, 1, 5);
        SudokuSolver.Cell cell6 = new SudokuSolver.Cell(6, 1, 6);
        SudokuSolver.Cell cell7 = new SudokuSolver.Cell(7, 1);
        cell7.getPossibleValues().addAll(Arrays.asList(2,4,7));
        SudokuSolver.Cell cell8 = new SudokuSolver.Cell(8, 1, 8);
        SudokuSolver.Cell cell9 = new SudokuSolver.Cell(9, 1, 9);
        Set<SudokuSolver.Cell> cells = new HashSet<>(Arrays.asList(cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9));

        assertTrue(SudokuSolver.validateNonFinal9Elements(cells));

        cell3.getPossibleValues().clear();
        cell4.getPossibleValues().clear();
        cell7.getPossibleValues().clear();

        cell2.getPossibleValues().addAll(Arrays.asList(4,7));
        cell4.getPossibleValues().addAll(Arrays.asList(4,7));
        cell7.getPossibleValues().addAll(Arrays.asList(4,7));
        assertFalse(SudokuSolver.validateFinal9Elements(cells));

        cell7.getPossibleValues().clear();
        cell7.getPossibleValues().addAll(Arrays.asList(3));
        assertFalse(SudokuSolver.validateFinal9Elements(cells));

        cell7.getPossibleValues().clear();
        assertFalse(SudokuSolver.validateFinal9Elements(cells));
    }
}

package com.szilsan.kata.hardsudokusolver;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestCellPlayGround {
    @Test
    public void testGetRow() {
        final Set<Cell> cells = new HashSet<>();
        Cell c1 = new Cell(1,2,3);
        Cell c2 = new Cell(1,3,1);
        Cell c3 = new Cell(2,2,1);
        Cell c4 = new Cell(1,1,1);
        Cell c5 = new Cell(2,1,1);
        Cell c6 = new Cell(2,3,1);

        cells.add(c1);
        cells.add(c2);
        cells.add(c3);
        cells.add(c4);
        cells.add(c5);
        cells.add(c6);

        List<Cell> row1 = CellPlayGround.getRow(cells, 1);
        List<Cell> row2 = CellPlayGround.getRow(cells, 2);

        Assert.assertTrue(row1.size() == 3);
        Assert.assertTrue(row2.size() == 3);

        Assert.assertTrue(row1.get(0).equals(c4));
        Assert.assertTrue(row1.get(1).equals(c1));
        Assert.assertTrue(row1.get(2).equals(c2));

        Assert.assertTrue(row2.get(0).equals(c5));
        Assert.assertTrue(row2.get(1).equals(c3));
        Assert.assertTrue(row2.get(2).equals(c6));
    }

    @Test
    public void testGetCol() {
        final Set<Cell> cells = new HashSet<>();
        Cell c1 = new Cell(1,2,3);
        Cell c2 = new Cell(1,3,1);
        Cell c3 = new Cell(2,2,1);
        Cell c4 = new Cell(1,1,1);
        Cell c5 = new Cell(2,1,1);
        Cell c6 = new Cell(2,3,1);

        cells.add(c1);
        cells.add(c2);
        cells.add(c3);
        cells.add(c4);
        cells.add(c5);
        cells.add(c6);

        List<Cell> col1 = CellPlayGround.getCol(cells, 1);
        List<Cell> col2 = CellPlayGround.getCol(cells, 2);
        List<Cell> col3 = CellPlayGround.getCol(cells, 3);

        Assert.assertTrue(col1.size() == 2);
        Assert.assertTrue(col2.size() == 2);
        Assert.assertTrue(col2.size() == 2);

        Assert.assertTrue(col1.get(0).equals(c4));
        Assert.assertTrue(col1.get(1).equals(c5));

        Assert.assertTrue(col2.get(0).equals(c1));
        Assert.assertTrue(col2.get(1).equals(c3));

        Assert.assertTrue(col3.get(0).equals(c2));
        Assert.assertTrue(col3.get(1).equals(c6));
    }

    @Test
    public void testGenerateNewCellPlayGround() {
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

        MatrixPlayGround matrix = new MatrixPlayGround(grid);
        CellPlayGround originalCellPlayGround = new  CellPlayGround(matrix);
        List<Cell> row0 = originalCellPlayGround.getRow(0);

        final Set<Cell> cells = new HashSet<>();
        Cell c1 = new Cell(0,0,3);
        Cell c2 = new Cell(1,3,1);
        Cell c3 = new Cell(2,2,1);
        Cell c4 = new Cell(1,1,1);
        Cell c5 = new Cell(2,1,1);
        Cell c6 = new Cell(2,3,1);

        cells.add(c1);
        cells.add(c2);
        cells.add(c3);
        cells.add(c4);
        cells.add(c5);
        cells.add(c6);

        Assert.assertFalse(originalCellPlayGround.getRow(0).get(0).getPossibleValues().containsAll(c1.getPossibleValues()));

        CellPlayGround newCellPlayGround = originalCellPlayGround.generateNewCellPlayGround(cells);
        Assert.assertTrue(newCellPlayGround.getRow(0).get(0).getPossibleValues().containsAll(c1.getPossibleValues()));
        Assert.assertTrue(newCellPlayGround.getRow(1).get(3).getPossibleValues().containsAll(c2.getPossibleValues()));
        Assert.assertTrue(newCellPlayGround.getRow(2).get(2).getPossibleValues().containsAll(c3.getPossibleValues()));
        Assert.assertTrue(newCellPlayGround.getRow(1).get(1).getPossibleValues().containsAll(c4.getPossibleValues()));
        Assert.assertTrue(newCellPlayGround.getRow(2).get(1).getPossibleValues().containsAll(c5.getPossibleValues()));
        Assert.assertTrue(newCellPlayGround.getRow(2).get(3).getPossibleValues().containsAll(c6.getPossibleValues()));

    }


}

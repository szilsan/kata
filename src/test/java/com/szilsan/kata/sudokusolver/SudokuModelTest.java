package com.szilsan.kata.sudokusolver;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SudokuModelTest {

    private static final int[][] testModel = {
            {5,3,4,6,7,8,9,1,2},
            {6,7,2,1,9,5,3,4,8},
            {1,9,8,3,4,2,5,6,7},
            {8,5,9,7,6,1,4,2,3},
            {4,2,6,8,5,3,7,9,1},
            {7,1,3,9,2,4,8,5,6},
            {9,6,1,5,3,7,2,8,4},
            {2,8,7,4,1,9,6,3,5},
            {3,4,5,2,8,6,1,7,9}};

    private static final int[][] testInvalidModel = {
            {5,3,4,6,7,8,9,1,2},
            {6,7,2,1,9,5,3,4,8},
            {1,9,8,5,4,2,5,6,7},
            {8,5,9,7,6,1,4,2,3},
            {4,2,6,8,5,3,7,9,1},
            {7,1,3,9,2,4,8,5,6},
            {9,6,1,5,3,7,2,8,4},
            {2,8,7,4,1,9,6,3,5},
            {3,4,5,2,8,6,1,7,9}};

    private static final int[][] testNotFilledModel = {
            {5,3,4,6,7,8,9,1,2},
            {6,7,2,1,9,5,3,4,8},
            {1,9,8,0,4,2,5,6,7},
            {8,5,9,7,6,1,4,2,3},
            {4,2,6,8,5,3,7,9,1},
            {7,1,3,9,2,4,8,5,6},
            {9,6,1,5,3,7,2,8,4},
            {2,8,7,4,1,9,6,3,5},
            {3,4,5,2,8,6,1,7,9}};

    private static final SudokuModel model = new SudokuModel(testModel);
    private static final SudokuModel invalidModel = new SudokuModel(testInvalidModel);
    private static final SudokuModel notFilledModel = new SudokuModel(testNotFilledModel);

    @Test
    public void testRead() {
        Assertions.assertArrayEquals(new int[] {5,3,4,6,7,8,9,1,2}, model.getRow(0));
        Assertions.assertArrayEquals(new int[] {4,2,6,8,5,3,7,9,1}, model.getRow(4));

        Assertions.assertArrayEquals(new int[] {5,6,1,8,4,7,9,2,3}, model.getCol(0));
        Assertions.assertArrayEquals(new int[] {2,8,7,3,1,6,4,5,9}, model.getCol(8));

        Assertions.assertArrayEquals(new int[][] {{5,3,4},{6,7,2},{1,9,8}},model.getGrid(0,0));
        Assertions.assertArrayEquals(new int[][] {{7,6,1},{8,5,3},{9,2,4}},model.getGrid(1,1));
        Assertions.assertArrayEquals(new int[][] {{5,3,7},{4,1,9},{2,8,6}},model.getGrid(2,1));
    }

    @Test
    public void testValidLines() {
        Assertions.assertEquals(ValidationStatus.VALID, ValidateModel.validateLine(model.getRow(0)));
        Assertions.assertEquals(ValidationStatus.VALID, ValidateModel.validateLine(model.getRow(1)));
        Assertions.assertEquals(ValidationStatus.VALID, ValidateModel.validateLine(model.getRow(2)));
        Assertions.assertEquals(ValidationStatus.VALID, ValidateModel.validateLine(model.getRow(3)));
        Assertions.assertEquals(ValidationStatus.VALID, ValidateModel.validateLine(model.getCol(3)));
        Assertions.assertEquals(ValidationStatus.VALID, ValidateModel.validateLine(model.getCol(4)));
        Assertions.assertEquals(ValidationStatus.VALID, ValidateModel.validateLine(model.getCol(5)));
    }

    @Test
    public void testInvalidLines() {
        Assertions.assertEquals(ValidationStatus.INVALID, ValidateModel.validateLine(new int[] {1,1,3,4,5,6,7,8,9}));
        Assertions.assertEquals(ValidationStatus.INVALID, ValidateModel.validateLine(new int[] {1,1,3,4,5,6,7,8,9}));
        Assertions.assertEquals(ValidationStatus.INVALID, ValidateModel.validateLine(new int[] {1,2,3,4,5,5,6,8,9}));

        Assertions.assertEquals(ValidationStatus.INVALID, ValidateModel.validateLine(new int[] {1,2,3,4,5,5,6,0,9}));
        Assertions.assertEquals(ValidationStatus.INVALID, ValidateModel.validateLine(new int[] {1,8,3,0,0,5,6,8,9}));

        Assertions.assertEquals(ValidationStatus.NOT_FILLED, ValidateModel.validateLine(new int[] {0,2,3,4,5,7,6,8,9}));
        Assertions.assertEquals(ValidationStatus.NOT_FILLED, ValidateModel.validateLine(new int[] {1,2,0,4,5,7,0,8,9}));
        Assertions.assertEquals(ValidationStatus.NOT_FILLED, ValidateModel.validateLine(new int[] {1,2,3,4,0,0,0,8,9}));
        Assertions.assertEquals(ValidationStatus.NOT_FILLED, ValidateModel.validateLine(new int[] {0,0,0,0,0,0,0,0,0}));
        Assertions.assertEquals(ValidationStatus.NOT_FILLED, ValidateModel.validateLine(new int[] {0,0,0,0,0,0,0,9,0}));
    }

    @Test
    public void testValidGrid() {
        Assertions.assertEquals(ValidationStatus.VALID, ValidateModel.validateGrid(model.getGrid(0, 0)));
        Assertions.assertEquals(ValidationStatus.VALID, ValidateModel.validateGrid(model.getGrid(0, 1)));
        Assertions.assertEquals(ValidationStatus.VALID, ValidateModel.validateGrid(model.getGrid(0, 2)));
        Assertions.assertEquals(ValidationStatus.VALID, ValidateModel.validateGrid(model.getGrid(1, 0)));
        Assertions.assertEquals(ValidationStatus.VALID, ValidateModel.validateGrid(model.getGrid(1, 1)));
        Assertions.assertEquals(ValidationStatus.VALID, ValidateModel.validateGrid(model.getGrid(1, 2)));
        Assertions.assertEquals(ValidationStatus.VALID, ValidateModel.validateGrid(model.getGrid(2, 0)));
        Assertions.assertEquals(ValidationStatus.VALID, ValidateModel.validateGrid(model.getGrid(2, 1)));
        Assertions.assertEquals(ValidationStatus.VALID, ValidateModel.validateGrid(model.getGrid(2, 2)));
    }

    @Test
    public void testInvalidGrid() {
        Assertions.assertEquals(ValidationStatus.INVALID, ValidateModel.validateGrid(new int[][] {{1,2,3},{4,5,6},{7,8,8}}));
        Assertions.assertEquals(ValidationStatus.INVALID, ValidateModel.validateGrid(new int[][] {{1,2,4},{4,4,6},{7,8,8}}));
        Assertions.assertEquals(ValidationStatus.INVALID, ValidateModel.validateGrid(new int[][] {{1,2,3},{1,5,1},{1,8,8}}));
        Assertions.assertEquals(ValidationStatus.INVALID, ValidateModel.validateGrid(new int[][] {{1,2,3},{0,5,0},{8,8,8}}));

        Assertions.assertEquals(ValidationStatus.INVALID, ValidateModel.validateGrid(new int[][] {{1,0,3},{1,5,1},{1,8,9}}));
        Assertions.assertEquals(ValidationStatus.INVALID, ValidateModel.validateGrid(new int[][] {{1,0,3},{1,0,1},{0,8,9}}));

        Assertions.assertEquals(ValidationStatus.NOT_FILLED, ValidateModel.validateGrid(new int[][] {{1,2,0},{0,5,6},{7,9,8}}));
        Assertions.assertEquals(ValidationStatus.NOT_FILLED, ValidateModel.validateGrid(new int[][] {{1,2,0},{0,0,5},{0,8,9}}));
    }

    @Test
    public void testValidate() {
        Assertions.assertEquals(ValidationStatus.VALID, ValidateModel.validate(model));
        Assertions.assertEquals(ValidationStatus.INVALID, ValidateModel.validate(invalidModel));
        Assertions.assertEquals(ValidationStatus.NOT_FILLED, ValidateModel.validate(notFilledModel));
    }
}

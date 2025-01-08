package com.szilsan.kata.sudokusolver;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SudokuSolver {

    public static int[][] sudoku(int[][] puzzle) {
        return Solver.solve(new SudokuModel(puzzle)).getModel();
    }
}

class Solver {

    public static SudokuModel solve(SudokuModel model) {
        ValidationStatus validate = ValidateModel.validate(model);
        if (validate.equals(ValidationStatus.VALID)) {
            return model;
        }
        if (validate.equals(ValidationStatus.INVALID)) {
            throw new RuntimeException("Invalid");
        }

        SudokuModel workModel = new SudokuModel(model.getModel());
        boolean wasModified = false;
        do {
            wasModified = false;

            //  rows - if only one number is missing from the row
            for (int i = 0; i < 9; i++) {
                FieldModification status = Solver.lineSolver(workModel.getRow(i));
                if (status.wasModified()) {
                    wasModified = true;
                    workModel = workModel.createAndModify(i, status.row(), status.newValue());
                }
            }

            // columns - if only one number is missing from the column
            for (int i = 0; i < 9; i++) {
                FieldModification status = Solver.lineSolver(workModel.getCol(i));
                if (status.wasModified()) {
                    wasModified = true;
                    workModel = workModel.createAndModify(status.row(), i, status.newValue());
                }
            }

            // grids - if only one number is missing from the grid
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    FieldModification status = Solver.gridSolver(workModel.getGrid(i,j));
                    if (status.wasModified()) {
                        wasModified = true;
                        workModel = workModel.createAndModify(i * 3 + status.row(), j * 3 + status.col(), status.newValue());
                    }
                }
            }

            // number possibilities checking; checking numbers one by one
            for (int numberToCheck = 1; numberToCheck < 10; numberToCheck++) {
                int[][] validPoz = numberPozFind(workModel, numberToCheck);

                // if there is only one valid position in a row -> fill
                for (int i = 0; i < 9; i++) {
                    int[] col = validPoz[i];
                    Set<Integer> positions = getPosOfNumberInArray( col, 0);
                    if (positions.size() == 1) {
                        wasModified = true;
                        workModel = workModel.createAndModify(i, positions.iterator().next(), numberToCheck);
                    }
                }

                // if there is only one in a grid [3x3] -> fill
                validPoz = numberPozFind(workModel, numberToCheck);
                for (int row=0; row < 3; row++) {
                    for (int col=0; col < 3; col++) {
                        int[][] gridToCheck = SudokuModel.getGrid(validPoz, row, col);
                        Set<Integer> positions = getPosOfNumberInArray(Stream.of(gridToCheck).flatMapToInt(Arrays::stream).toArray(), 0);
                        if (positions.size() == 1) {
                            wasModified = true;
                            int poz = positions.iterator().next();
                            workModel = workModel.createAndModify(row * 3 + poz / 3, col * 3 + poz % 3, numberToCheck);
                        }

                    }
                }

            }

        }
        while (wasModified);

        return workModel;
    }

    static int[][] numberPozFind(SudokuModel model, int numberToCheck) {
        int[][] validationMatrix = new int[9][9];

        // find number and invalidate matrix's proper fields
        // cols and rows
        for (int col = 0; col < 9; col++) {
            int poz = getFirstPosOfNumberInArray(model.getCol(col), numberToCheck);
            if (poz != -1) {
                // fill up rows, cols
                for (int i = 0; i < 9; i++) {
                    validationMatrix[i][col] = 1;
                    validationMatrix[poz][i] = 1;
                }
            }
        }
        // grids
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int poz = getFirstPosOfNumberInArray(Stream.of(model.getGrid(row, col)).flatMapToInt(Arrays::stream).toArray(), numberToCheck);
                if (poz != -1) {
                    validationMatrix[row*3][col*3] = 1;
                    validationMatrix[row*3][col*3+1] = 1;
                    validationMatrix[row*3][col*3+2] = 1;
                    validationMatrix[row*3+1][col*3] = 1;
                    validationMatrix[row*3+1][col*3+1] = 1;
                    validationMatrix[row*3+1][col*3+2] = 1;
                    validationMatrix[row*3+2][col*3] = 1;
                    validationMatrix[row*3+2][col*3+1] = 1;
                    validationMatrix[row*3+2][col*3+2] = 1;
                }
            }
        }


        // where it is filled already
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (model.getModel()[row][col] != 0) {
                    validationMatrix[row][col] = 1;
                }
            }
        }

        return validationMatrix;
    }

    public static FieldModification lineSolver(int[] line) {
        Set<Integer> fullNumbers = Stream.of(1,2,3,4,5,6,7,8,9).collect(Collectors.toSet());
        Arrays.stream(line).forEach(fullNumbers::remove);

        if (fullNumbers.size() != 1) {
            return new FieldModification(false, -1, -1, -1);
        }

        for (int i = 0; i < 9; i++) {
            if (line[i] == 0) {
                return new FieldModification(true, i, 0, fullNumbers.iterator().next());
            }
        }

        return new FieldModification(false, -1, -1, -1);
    }

    public static FieldModification gridSolver(int[][] grid) {
        Set<Integer> fullNumbers = Stream.of(1,2,3,4,5,6,7,8,9).collect(Collectors.toSet());
        Stream.of(grid).flatMapToInt(Arrays::stream).forEach(fullNumbers::remove);

        if (fullNumbers.size() != 1) {
            return new FieldModification(false, -1, -1, -1);

        }

        int missingNumber = fullNumbers.iterator().next();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 0) {
                    return new FieldModification(true, i, j, missingNumber);
                }
            }
        }

        return new FieldModification(false, -1, -1, -1);
    }

    private static Set<Integer> getPosOfNumberInArray(int[] array, int valueToFind) {
        Set<Integer> positions = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == valueToFind) {
                positions.add(i);
            }
        }
        return positions;
    }

    private static int getFirstPosOfNumberInArray(int[] array, int valueToFind) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == valueToFind) {
                return i;
            }
        }
        return -1;
    }
}

class SudokuModel {
    private final int[][] model;
    public SudokuModel(int[][] puzzle) {
        this.model = puzzle;
    }

    public SudokuModel createAndModify(int row, int col, int newValue) {
        int[][] newModel = Arrays.copyOf(model, model.length);
        newModel[row][col] = newValue;
        return new SudokuModel(newModel);
    }

    public int[] getRow(int row) {
        return model[row];
    }

    public int[] getCol(int column) {
        int[] col = new int[9];
        for (int i = 0; i < 9; i++) {
            col[i] = model[i][column];
        }

        return col;
    }

    public static int[][] getGrid(int[][] model, int row, int col) {
        if (row < 0 || row > 3 || col < 0 || col > 3) {
            throw new RuntimeException("Invalid row or col number. Row: " + row + "Col: " + col);
        }
        int[][] grid = new int[3][3];

        int r =0, c = 0;
        for (int i = 3 * row; i < 3 * row + 3; i++) {
            for (int j = 3 * col ; j < 3 * col + 3; j++) {
                grid[r][c] = model[i][j];
                c++;
            }
            c =0;
            r++;

        }

        return grid;
    }

    public int[][] getGrid(int row, int col) {
       return getGrid(model, row, col);
    }

    public int[][] getModel() {
        return model;
    }
}

enum ValidationStatus {
    VALID,
    INVALID,
    NOT_FILLED
}

record FieldModification(boolean wasModified, int row, int col, int newValue) {}

class ValidateModel {
    public static ValidationStatus validate (SudokuModel model) {

        ValidationStatus status = ValidationStatus.VALID;

        for (int i = 0; i < 9; i++) {
            ValidationStatus tmpStatus = validateLine(model.getRow(i));
            if (tmpStatus == ValidationStatus.INVALID) {
                return ValidationStatus.INVALID;
            }
            if (tmpStatus == ValidationStatus.NOT_FILLED) {
                status = ValidationStatus.NOT_FILLED;
            }

            tmpStatus = validateLine(model.getCol(i));
            if (tmpStatus == ValidationStatus.INVALID) {
                return ValidationStatus.INVALID;
            }
            if (tmpStatus == ValidationStatus.NOT_FILLED) {
                status = ValidationStatus.NOT_FILLED;
            }
        }

        for (int i =0; i<3; i++) {
            for (int j =0; j<3; j++) {
                ValidationStatus tmpStatus = validateGrid(model.getGrid(i,j));
                if (tmpStatus == ValidationStatus.INVALID) {
                    return ValidationStatus.INVALID;
                }
                if (tmpStatus == ValidationStatus.NOT_FILLED) {
                    status = ValidationStatus.NOT_FILLED;
                }
            }
        }

        return status;
    }

    public static ValidationStatus validateLine(int[] line) {
        int[] counter = new int[10];
        for (int i : line) {
            counter[i]++;
        }

        for (int i = 1; i < 10; i++) {
            if (counter[i] > 1) {
                return ValidationStatus.INVALID;
            }
        }

        if (counter[0] > 0) {
            return ValidationStatus.NOT_FILLED;
        }

        return ValidationStatus.VALID;
    }

    public static ValidationStatus validateGrid(int[][] grid) {
        return validateLine(new int[] {grid[0][0], grid[0][1], grid[0][2], grid[1][0], grid[1][1], grid[1][2], grid[2][0], grid[2][1], grid[2][2]});
    }
}
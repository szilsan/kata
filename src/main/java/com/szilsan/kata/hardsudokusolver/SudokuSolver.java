package com.szilsan.kata.hardsudokusolver;

import java.util.*;
import java.util.stream.Collectors;

public class SudokuSolver {

    private static int stepCounter = 0;

    class Cell {
        public final int row;
        public final int col;
        private final String id;
        private final Set<Integer> possibleValues = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        public Cell(final int col, final int row) {
            this.row = row;
            this.col = col;
            this.id = col + "" + row;
        }

        public Cell(final int col, final int row, final int initValue) {
            this.row = row;
            this.col = col;
            this.id = col + "" + row;
            this.possibleValues.removeIf(e -> e.intValue() != initValue);
        }

        public Set<Integer> getPossibleValues() {
            return possibleValues;
        }

        public String getId() {
            return col + "" + row;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return id.equals(cell.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            final StringBuilder ret = new StringBuilder();
            ret.append("[");

            for (int pv : possibleValues) {
                ret.append(pv + ",");
            }
            for (int i = possibleValues.size(); i < 9; i++) {
                ret.append(" ,");
            }

            ret.append("]");
            return ret.toString();
        }
    }

    private final int[][] grid;
    private List<List<Cell>> cells = new ArrayList<>(9);

    public SudokuSolver(int[][] grid) {
        stepCounter++;
        System.out.println(stepCounter);
        this.grid = grid;
        initialInputValidation(grid);
        initPossibleValues();
        //System.out.println("Init matrix:");
        this.printCurrentStatus();
        System.out.println("Simplified matrix:");
        this.calculateEffects();
        this.printCurrentStatus();
        System.out.println("Initialization is done");
        //System.out.println("================================================================================================");
    }

    public int[][] solve() {

        try {
            validateFilledGrid(convertToGrid());
            return convertToGrid();
        } catch (Exception ex) {
            // we have to calculate
        }

        int solutionCount = 0;

        for (int col = 0; col < cells.size(); col++) {
            for (int row = 0; row < cells.get(col).size(); row++) {
                final Cell cell = cells.get(col).get(row);
                if (cell.getPossibleValues().size() > 1) {
                    for (int possibility : cell.getPossibleValues()) {
                        int[][] newGrid = convertToNewSudokuGrid();
                        newGrid[cell.col][cell.row] = possibility;
                        int[][] solution = new SudokuSolver(newGrid).solve();
                        if (solution != null) {
                            solutionCount++;
                        }
                    }
                } else if (cell.getPossibleValues().size() == 0) {
                    return null;
                }
            }
        }

        return solutionCount == 0 ? null : convertToGrid();
    }

    void calculateEffects() {
        boolean modified = false;
        do {
            modified = false;
            for (List<Cell> lstCol : cells) {
                for (Cell cell : lstCol) {
                    if (cell.possibleValues.size() == 1) {
                        final int value = cell.getPossibleValues().iterator().next();
                        // remove from its row and col
                        for (List<Cell> lstColInner : cells) {
                            for (Cell cellInner : lstColInner) {
                                if (!cellInner.equals(cell) && (cellInner.row == cell.row || cellInner.col == cell.col)) {
                                    if (cellInner.getPossibleValues().remove(value)) {
                                        modified = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            for (int row = 0; row < 9; row++) {
                Set<Cell> setRow = getRow(row);
                for (Cell cell : setRow) {
                    if (cell.getPossibleValues().size() > 1) {
                        Set<Integer> sub = new HashSet<>();
                        for (Cell cellInner : setRow) {
                            if (!cellInner.equals(cell)) {
                                sub.removeAll(cellInner.possibleValues);
                            }
                        }
                        if (sub.size() == 1) {
                            modified = true;
                            cell.getPossibleValues().clear();
                            cell.getPossibleValues().add(sub.iterator().next());
                        }
                    }
                }
            }

            for (int col = 0; col < 9; col++) {
                Set<Cell> setCol = getCol(col);
                for (Cell cell : setCol) {
                    if (cell.getPossibleValues().size() > 1) {
                        Set<Integer> sub = new HashSet<>();
                        for (Cell cellInner : setCol) {
                            if (!cellInner.equals(cell)) {
                                sub.removeAll(cellInner.possibleValues);
                            }
                        }
                        if (sub.size() == 1) {
                            modified = true;
                            cell.getPossibleValues().clear();
                            cell.getPossibleValues().add(sub.iterator().next());
                        }
                    }
                }
            }

            // check the block
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    final Set<Cell> block = getBlock(x, y);


                    for (Cell cell : block) {
                        // remove unique one from each
                        if (cell.possibleValues.size() == 1) {
                            final int value = cell.getPossibleValues().iterator().next();
                            for (Cell cellInner : block) {
                                if (!cellInner.equals(cell)) {
                                    if (cellInner.getPossibleValues().remove(value)) {
                                        modified = true;
                                    }
                                }
                            }
                        } else {
                            // check if one contains the possibility for a number
                            final Set<Integer> sub = new HashSet<>(cell.getPossibleValues());
                            for (Cell cellInner : block) {
                                if (!cellInner.equals(cell)) {
                                    sub.removeAll(cellInner.possibleValues);
                                }
                            }
                            if (sub.size() == 1) {
                                modified = true;
                                cell.getPossibleValues().clear();
                                cell.getPossibleValues().add(sub.iterator().next());
                            }

                            // ha a blockben barmely 2 cellaban a lehetseges elemek kozott ugyanaz a 2, vagy barmely 3-ban ugyanaz a 3 stb elem van, akkor azokat a tobbibol ki lehet vonni
                            final Set<Integer> same = new HashSet<>(cell.getPossibleValues());
                            for (Cell cellInner : block) {
                                if (!cellInner.equals(cell)) {
                                    if (cellInner.getPossibleValues().size() == 2 && cellInner.getPossibleValues().size() == cell.getPossibleValues().size() && cellInner.getPossibleValues().containsAll(cell.getPossibleValues())) {
                                        block.stream().filter(c -> !c.equals(cell) && !c.equals(cellInner)).forEach(c -> c.getPossibleValues().removeAll(cell.getPossibleValues()));
                                    }
                                }
                            }
                        }
                    }
                }
            }


            if (modified) {
                // this.printCurrentStatus();
                // System.out.println("------------------------------------------------------------------------------------------------------------------");
            }
        } while (modified);
    }

    /**
     * Fill up the possible values list with 1-9 and set the given values and set their's possible values to the given one
     */
    private void initPossibleValues() {
        for (int col = 0; col < 9; col++) {
            cells.add(new ArrayList<Cell>());
            for (int row = 0; row < 9; row++) {
                final int cellValue = grid[col][row];
                if (cellValue != 0) {
                    cells.get(col).add(row, new Cell(col, row, cellValue));
                } else {
                    cells.get(col).add(row, new Cell(col, row));
                }
            }
        }
    }

    /**
     * Initial grid validation - size, element values
     *
     * @param grid
     */
    static void initialInputValidation(int[][] grid) {
        if (grid == null) {
            throw new IllegalArgumentException();
        }

        if (grid.length != 9) {
            throw new IllegalArgumentException();
        }

        for (int[] row : grid) {
            if (row.length != 9) {
                throw new IllegalArgumentException();
            }
        }

        for (int[] row : grid) {
            for (int element : row) {
                if (element < 0 || element > 9) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    /**
     * Validate a filled grid - rows, columns, blocks are filled fine.
     *
     * @param grid
     * @return
     */
    static boolean validateFilledGrid(int[][] grid) {

        // validate columns
        for (int[] col : grid) {
            if (!validateFilledLine(col)) {
                return false;
            }
        }

        // validate rows
        for (int rowNumber = 0; rowNumber < grid.length; rowNumber++) {
            int[] row = new int[9];
            for (int col = 0; col < grid[rowNumber].length; col++) {
                row[col] = grid[col][rowNumber];
            }

            if (!validateFilledLine(row)) {
                return false;
            }
        }

        // validate blocks [3x3]
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int[] block = new int[9];
                block[0] = grid[i * 3][j * 3];
                block[1] = grid[i * 3 + 1][j * 3];
                block[2] = grid[i * 3 + 2][j * 3];
                block[3] = grid[i * 3][j * 3 + 1];
                block[4] = grid[i * 3 + 1][j * 3 + 1];
                block[5] = grid[i * 3 + 2][j * 3 + 1];
                block[6] = grid[i * 3][j * 3 + 2];
                block[7] = grid[i * 3 + 1][j * 3 + 2];
                block[8] = grid[i * 3 + 2][j * 3 + 2];

                if (!validateFilledLine(block)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Validate of 9 elements (all of them should be different
     *
     * @param elements
     * @return
     */
    static boolean validateFilledLine(final int[] elements) {
        if (elements == null || elements.length != 9) {
            return false;
        }

        final Set<Integer> line = new HashSet<>();
        for (int e : elements) {
            if (e == 0 || !line.add(e)) {
                return false;
            }
        }

        return true;
    }

    // 9 times 3x3 block

    /**
     * Get a block
     *
     * @param col : 0-3
     * @param row : 0-3
     * @return @Cells of the block
     */
    Set<Cell> getBlock(final int col, final int row) {
        final Set<Cell> result = new HashSet<>(9);

        for (List<Cell> lstCol : cells) {
            for (Cell cell : lstCol) {
                if (cell.col >= col * 3 && cell.col < (col + 1) * 3) {
                    if (cell.row >= row * 3 && cell.row < (row + 1) * 3) {
                        result.add(cell);
                    }
                }
            }
        }

        return result;
    }

    Set<Cell> getRow(final int row) {
        return cells.stream().flatMap(lst -> lst.stream().filter(c -> c.row == row)).collect(Collectors.toSet());
    }

    Set<Cell> getCol(final int col) {
        return cells.stream().flatMap(lst -> lst.stream().filter(c -> c.col == col)).collect(Collectors.toSet());
    }

    int[][] convertToGrid() {
        int[][] result = new int[9][9];

        for (int col = 0; col < cells.size(); col++) {
            for (int row = 0; row < cells.get(col).size(); row++) {
                final Cell cell = cells.get(col).get(row);
                if (cell.getPossibleValues().size() != 1) {
                    throw new IllegalArgumentException("Cell is invalid!");
                } else {
                    result[col][row] = cell.getPossibleValues().iterator().next();
                }
            }
        }

        return result;
    }

    int[][] convertToNewSudokuGrid() {
        int[][] result = new int[9][9];

        for (int col = 0; col < cells.size(); col++) {
            for (int row = 0; row < cells.get(col).size(); row++) {
                final Cell cell = cells.get(col).get(row);
                if (cell.getPossibleValues().size() != 1) {
                    result[col][row] = 0;
                } else {
                    result[col][row] = cell.getPossibleValues().iterator().next();
                }
            }
        }

        return result;
    }

    public void printCurrentStatus() {
        for (List<Cell> lstCol : cells) {
            for (Cell cell : lstCol) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }
}

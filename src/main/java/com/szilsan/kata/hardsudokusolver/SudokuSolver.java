package com.szilsan.kata.hardsudokusolver;

import java.util.*;
import java.util.stream.Collectors;

public class SudokuSolver {

    // as there is no logger, a simple one is here
    private static final boolean LOG_INPUT = false;
    private static final boolean LOG_INIT_MATRIX = false;
    private static final boolean LOG_SIMPLIFIED_MATRIX = false;

    static class Cell {
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
            this.possibleValues.clear();
            this.possibleValues.add(initValue);
        }

        public Set<Integer> getPossibleValues() {
            return possibleValues;
        }

        public String getId() {
            return col + "" + row;
        }

        public boolean isFinal() {
            return getPossibleValues().size() == 1;
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
    private final List<List<Cell>> cells;

    public SudokuSolver(int[][] grid) {
        this.grid = grid;

        initialInputValidation(grid);
        cells = convertGridToCells(grid);
        inputContentValidation();

        if (LOG_INIT_MATRIX) {
            System.out.println("Init matrix:");
            this.printCurrentStatus();
        }
        this.calculateEffects();

        if(LOG_SIMPLIFIED_MATRIX) {
            System.out.println("Simplified matrix:");
            this.printCurrentStatus();
            System.out.println("Initialization is done");
            System.out.println("================================================================================================");
        }
    }

    public int[][] solve() {

        try {
            validateFilledCells(this.cells);
            return convertCellsToGrid(this.cells, true);
        } catch (Exception ex) {
            // we have to calculate
        }

        for (int col = 0; col < cells.size(); col++) {
            for (int row = 0; row < cells.get(col).size(); row++) {
                final Cell cell = cells.get(col).get(row);
                if (cell.getPossibleValues().size() > 1) {
                    for (int possibility : cell.getPossibleValues()) {
                        int[][] newGrid = convertCellsToGrid(this.cells, false);
                        newGrid[cell.col][cell.row] = possibility;
                        int[][] solution = new SudokuSolver(newGrid).solve();
                        if (solution != null) {
                            return solution;
                        }
                    }
                } else if (cell.getPossibleValues().size() == 0) {
                    return null;
                }
            }
        }

        return null;
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
                Set<Cell> setRow = getRow(this.cells, row);
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
                Set<Cell> setCol = getCol(this.cells, col);
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
                    final Set<Cell> block = getBlock(this.cells, x, y);


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

    void inputContentValidation() {
        for (int i =0; i < 9; i++ ) {
            if (!validateFilled9Element(getRow(cells, i), true)) {
                throw new IllegalArgumentException("Row is invalid: [" + i +"]");
            }
            if (!validateFilled9Element(getCol(cells, i), true)) {
                throw new IllegalArgumentException("Col is invalid: [" + i +"]");
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!validateFilled9Element(getBlock(cells, i, j), true)) {
                    throw new IllegalArgumentException("Block is invalid: [" + i +"][" + j + "]");
                }
            }
        }
    }

    /**
     * Validate of a 9 elements block and throw exception if it is not valid
     */
    static boolean validateFilledCells(final List<List<Cell>> cells) {
        boolean result = true;
        for (int i =0; i < 9; i++ ) {
            if (!validateFilled9Element(getRow(cells, i), false)) {
                return false;
            }
            if (!validateFilled9Element(getCol(cells, i), false)) {
                return false;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!validateFilled9Element(getBlock(cells, i, j), false)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Validate of 9 elements (all of them should be different)
     *
     * @param elements
     * @param onlyFinals
     * @return
     */
    static boolean validateFilled9Element(final Set<Cell> elements, final boolean onlyFinals) {
        if (elements == null || elements.size() != 9) {
            return false;
        }

        final Set<Cell> finalCells = elements.parallelStream().filter(c -> onlyFinals ? c.isFinal() : true).collect(Collectors.toSet());
        return finalCells.parallelStream().map(c -> c.getPossibleValues().iterator().next()).collect(Collectors.toSet()).size() == finalCells.size();
    }

    /**
     * Get a block
     *
     * @param col : 0-3
     * @param row : 0-3
     * @return @Cells of the block
     */
    static Set<Cell> getBlock(final List<List<Cell>> cells, final int col, final int row) {
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

    static Set<Cell> getRow(final List<List<Cell>> cells, final int row) {
        return cells.stream().flatMap(lst -> lst.stream().filter(c -> c.row == row)).collect(Collectors.toSet());
    }

    static Set<Cell> getCol(final List<List<Cell>> cells, final int col) {
        return cells.stream().flatMap(lst -> lst.stream().filter(c -> c.col == col)).collect(Collectors.toSet());
    }

    static int[][] convertCellsToGrid(final List<List<Cell>> cells, final boolean withValidationException) {
        int[][] result = new int[9][9];

        for (int col = 0; col < cells.size(); col++) {
            for (int row = 0; row < cells.get(col).size(); row++) {
                final Cell cell = cells.get(col).get(row);
                if (!cell.isFinal()) {
                    if (withValidationException) {
                        throw new IllegalArgumentException("Cell is invalid!");
                    } else {
                        result[col][row] = 0;
                    }
                } else {
                    result[col][row] = cell.getPossibleValues().iterator().next();
                }
            }
        }

        return result;
    }

    /**
     * Fill up the possible values list with 1-9 and set the given values and set their's possible values to the given one
     */
    static List<List<Cell>> convertGridToCells(int[][] grid) {
        final List<List<Cell>> cells = new ArrayList<>(9);
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
        return cells;
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

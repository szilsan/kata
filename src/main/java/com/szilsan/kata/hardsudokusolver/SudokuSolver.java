package com.szilsan.kata.hardsudokusolver;

import java.util.*;
import java.util.stream.Collectors;

public class SudokuSolver {

    // as there is no logger, a simple one is here
    private static final boolean LOG_INPUT = false;
    private static final boolean LOG_INIT_MATRIX = false;
    private static final boolean LOG_SIMPLIFIED_MATRIX = false;

    private static int counter = 0;

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

    private Set<Set<Cell>> rows = new HashSet<>();
    private Set<Set<Cell>> cols = new HashSet<>();
    private Set<Set<Cell>> blocks = new HashSet<>();
    private Set<Set<Cell>> unitsOf9 = new HashSet<>(27);

    // calculated rows, cols, blocks - helps for being faster

    public SudokuSolver(int[][] grid) {
        //System.out.println("Counter: " + counter++);
        this.grid = grid;

        initialInputValidation(grid);
        cells = convertGridToCells(grid);
        inputContentValidation();
        splitCellsIntoColsRowsBlocks();

        if (LOG_INIT_MATRIX) {
            System.out.println("Init matrix:");
            this.printCurrentStatus();
        }
        this.calculateEffects();

        if (LOG_SIMPLIFIED_MATRIX) {
            System.out.println("Simplified matrix:");
            this.printCurrentStatus();
            System.out.println("Initialization is done");
            System.out.println("================================================================================================");
        }
    }

    public int[][] solve() {

        if (validateFinalMatrix()) {
            return convertCellsToGrid(this.cells, true);
        }

        if (!validateNonFinalMatrix()) {
            return null;
        }

        int[][] solution = null;

        for (int col = 0; col < cells.size(); col++) {
            for (int row = 0; row < cells.get(col).size(); row++) {
                final Cell cell = cells.get(col).get(row);
                if (cell.getPossibleValues().size() > 1) {
                    for (int possibility : cell.getPossibleValues()) {
                        int[][] newGrid = convertCellsToGrid(this.cells, false);
                        newGrid[cell.col][cell.row] = possibility;
                        solution = new SudokuSolver(newGrid).solve();
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
            if (LOG_SIMPLIFIED_MATRIX) {
                printCurrentStatus();
            }

            int[][] startGrid = convertCellsToGrid(this.cells, false);

            for (Set<Cell> unitOf9 : unitsOf9) {
                unitOf9.stream().forEach(c -> removeCellValuesFromAll(c, unitOf9)); // remove single single values
                findSinglePossibility(unitOf9); // check if there is only one possibility for a number
            }

            // check: if 2 cells have the same possibilities in the 9, these 2 can be removed from all the others
            // check the block

            /*
            for (Set<Cell> block : blocks) {
                block.stream().filter(c -> c.getPossibleValues().size() == 2).
                        forEach(c -> block.stream().filter(cIn ->
                                !c.getId().equals(cIn.getId()) &&
                                c.getPossibleValues().size() != cIn.getPossibleValues().size() &&
                                c.getPossibleValues().containsAll(cIn.getPossibleValues())));

            }


             */
            for (Set<Cell> block : blocks) {
                for (Cell cell : block) {
                    // remove unique one from each
                    if (!cell.isFinal()) {

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

            //printCurrentStatus();
            int[][] endGrid = convertCellsToGrid(this.cells, false);
            modified = !Arrays.deepEquals(startGrid, endGrid);
        } while (validateNonFinalMatrix() && modified);
    }

    private void findSinglePossibility(final Set<Cell> row) {
        final Set<Integer> sub = new HashSet<>();
        row.stream().forEach(
                c -> {
                    sub.clear();
                    sub.addAll(c.getPossibleValues());
                    row.stream().filter(cIn -> !cIn.getId().equals(c.getId())).forEach(cIn2 -> sub.removeAll(cIn2.getPossibleValues()));
                    if (sub.size() == 1) {
                        c.getPossibleValues().clear();
                        c.getPossibleValues().addAll(sub);
                    }
                });
    }

    private void splitCellsIntoColsRowsBlocks() {
        for (int i = 0; i < 9; i++) {
            rows.add(getRow(this.cells, i));
            cols.add(getCol(this.cells, i));
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                blocks.add(getBlock(this.cells, i, j));
            }
        }

        unitsOf9.addAll(rows);
        unitsOf9.addAll(cols);
        unitsOf9.addAll(blocks);
    }

    void inputContentValidation() {
        if (!validateNonFinalMatrix()) {
            throw new IllegalArgumentException("Invalid input");
        }
    }

    void removeCellValuesFromAll(final Cell cell, final Set<Cell> lst) {
        if (cell.isFinal()) {
            lst.stream().filter(c -> !c.getId().equals(cell.id)).forEach(c -> c.getPossibleValues().removeAll(cell.getPossibleValues()));
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
     * Validate of a 9 elements units for non final
     */
    boolean validateNonFinalMatrix() {
        for (Set<Cell> unitOf9 : unitsOf9) {
            if (!validateNonFinal9Elements(unitOf9)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Validate of a 9 elements units for non final
     */
    boolean validateFinalMatrix() {
        for (Set<Cell> unitOf9 : unitsOf9) {
            if (!validateFinal9Elements(unitOf9)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Validate of 9 elements (all of them should be different)
     *
     * @param elements
     * @return
     */
    static boolean validateNonFinal9Elements(final Set<Cell> elements) {
        if (elements == null || elements.size() != 9) {
            return false;
        }

        final Set<Integer> numbers = new HashSet<>(9);
        for (Cell cell : elements) {
            if (cell.getPossibleValues().size() == 0) {
                return false;
            }
            for (Cell cellIn : elements) {
                if (!cellIn.getId().equals(cell.getId()) && cellIn.isFinal() && cell.isFinal() && cellIn.getPossibleValues().iterator().next().intValue() == cell.getPossibleValues().iterator().next().intValue()) {
                    return false;
                }
            }

            numbers.addAll(cell.getPossibleValues());
        }
        return numbers.size() == elements.size();
    }

    static boolean validateFinal9Elements(final Set<Cell> elements) {
        final Set<Integer> numbers = new HashSet<>(9);
        if (elements == null || elements.size() != 9) {
            return false;
        }
        for (final Cell cell : elements) {
            if (cell == null || !cell.isFinal()) {
                return false;
            }
            numbers.addAll(cell.getPossibleValues());
        }
        return numbers.size() == 9;
    }

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
        System.out.println();
    }
}

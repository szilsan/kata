package com.szilsan.kata.hardsudokusolver;

import jdk.jshell.spi.ExecutionControl;

import java.util.*;
import java.util.stream.Collectors;

public class SudokuSolver {

    // as there is no logger, a simple one is here
    private static final boolean LOG_INPUT = false;
    private static final boolean LOG_INIT_MATRIX = false;
    private static final boolean LOG_SIMPLIFIED_MATRIX = false;
    private static final boolean LOG_SIMPLIFIED_DETAILS = false;

    private static int counter = 0;

    private final int[][] grid;
    private final List<List<Cell>> cells;

    private Set<Set<Cell>> rows = new HashSet<>();
    private Set<Set<Cell>> cols = new HashSet<>();
    private Set<Set<Cell>> blocks = new HashSet<>();
    private Set<Set<Cell>> unitsOf9 = new HashSet<>(27);
    private Set<Cell> allCells = new HashSet<>(27);

    // calculated rows, cols, blocks - helps for being faster

    public SudokuSolver(int[][] grid, boolean init) {
        this.grid = grid;
        initialInputValidation(grid);
        this.cells = convertGridToCells(grid);
        if (init) {
            init();
        }
    }

    public SudokuSolver(int[][] grid) {
        this(grid, true);
    }

    public SudokuSolver(final List<List<Cell>> cells) {
        this.cells = cells;
        this.grid = convertCellsToGrid(cells, false);
        init();
    }


    private void init() {
        System.out.println("Counter: " + counter++);
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
            System.out.println("Valid final");
            return convertCellsToGrid(this.cells, true);
        }

        if (!validateNonFinalMatrix()) {
            System.out.println("Invalid non-final");
            return null;
        }

        int[][] solution = null;
        final List<Cell> orderedByPossibilities = allCells.stream().filter(c -> !c.isFinal()).sorted((c1, c2) -> Integer.valueOf(c1.getPossibleValues().size()).compareTo(c2.getPossibleValues().size())).collect(Collectors.toList());

        for (Cell cell : orderedByPossibilities) {
            for (int possibility : cell.getPossibleValues()) {
                System.out.println("Simplify with cell " + cell.fullToString() + "  " + possibility);
                int[][] newGrid = convertCellsToGrid(this.cells, false);
                newGrid[cell.getCol()][cell.getRow()] = possibility;

                List<List<Cell>> newCells = cloneAllCells(this.cells);
                newCells.get(cell.getCol()).get(cell.getRow()).getPossibleValues().clear();
                newCells.get(cell.getCol()).get(cell.getRow()).getPossibleValues().add(possibility);

                solution = new SudokuSolver(newCells).solve();
                if (solution != null) {
                    return solution;
                }
            }
        }
        return null;
    }

    void calculateEffects() {
        boolean modified = false;
        do {
            if (LOG_SIMPLIFIED_DETAILS) {
                printCurrentStatus();
            }

            int[][] startGrid = convertCellsToGrid(this.cells, false);

            removeUniqueFromUnitsOf9(); // remove single single values - unique candidate
            for (Set<Cell> unitOf9 : unitsOf9) {
                findSinglePossibility(unitOf9); // check if there is only one possibility for a number
            }

            // check: if 2 cells have the same possibilities in the 9, these 2 can be removed from all the others
            // naked subset
            for (Set<Cell> block : unitsOf9) {
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

    void removeUniqueFromUnitOf9(final Set<Cell> unitOf9) {
        unitOf9.stream().filter(c -> c.isFinal()).
                forEach(c -> unitOf9.stream().filter(cIn -> !c.getId().equals(cIn.getId())).
                        forEach(cIn -> cIn.getPossibleValues().removeAll(c.getPossibleValues())));
    }

    void removeUniqueFromUnitsOf9() {
        for (Set<Cell> unitOf9 : unitsOf9) {
            removeUniqueFromUnitOf9(unitOf9);
        }
    }

    void findSinglePossibility(final Set<Cell> row) {
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

    void splitCellsIntoColsRowsBlocks() {
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
        unitsOf9.stream().forEach(unitOf9 -> allCells.addAll(unitOf9));
    }

    void inputContentValidation() {
        if (!validateNonFinalMatrix()) {
            throw new IllegalArgumentException("Invalid input");
        }
    }

    void removeCellValuesFromAll(final Cell cell, final Set<Cell> lst) {
        if (cell.isFinal()) {
            lst.stream().filter(c -> !c.getId().equals(cell.getId())).forEach(c -> c.getPossibleValues().removeAll(cell.getPossibleValues()));
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

        int countOfNumbers = 0;
        for (int[] row : grid) {
            for (int element : row) {
                if (element < 0 || element > 9) {
                    throw new IllegalArgumentException();
                } else if (element != 0) {
                    countOfNumbers++;
                }
            }
        }

        if (countOfNumbers < 17) {
            throw new IllegalArgumentException();
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
                if (cell.getCol() >= col * 3 && cell.getCol() < (col + 1) * 3) {
                    if (cell.getRow() >= row * 3 && cell.getRow() < (row + 1) * 3) {
                        result.add(cell);
                    }
                }
            }
        }

        return result;
    }

    static Set<Cell> getRow(final List<List<Cell>> cells, final int row) {
        return cells.stream().flatMap(lst -> lst.stream().filter(c -> c.getRow() == row)).collect(Collectors.toSet());
    }

    static Set<Cell> getCol(final List<List<Cell>> cells, final int col) {
        return cells.stream().flatMap(lst -> lst.stream().filter(c -> c.getCol() == col)).collect(Collectors.toSet());
    }

    int[][] convertCellsToGrid(final boolean withValidationException) {
        return SudokuSolver.convertCellsToGrid(this.cells, withValidationException);
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
                    cells.get(col).add(row, new Cell(col, row, new HashSet<>(Arrays.asList(cellValue))));
                } else {
                    cells.get(col).add(row, new Cell(col, row));
                }
            }
        }
        return cells;
    }

    static List<List<Cell>> cloneAllCells(final List<List<Cell>> cells) {
        List<List<Cell>> clonedCells = new ArrayList<List<Cell>>();
        for (final List<Cell> col : cells) {
            final ArrayList<Cell> newCol = new ArrayList<Cell>(9);
            clonedCells.add(newCol);
            for (Cell c : col) {
                newCol.add(new Cell(c));
            }
        }

        return clonedCells;
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

class SudokeTechnics {

}

class SudokeConverter {
    public static Set<Cell> convertGroupToCells(int[] group) {
        final Set<Cell> cells = new HashSet<>();
        for (int element : group) {
        }

        return cells;
    }

    public static Collection<Cell> convertMatrixPlayGround2Cells(final MatrixPlayGround matrixPlayGround) {
        final Collection<Cell> retColl = new HashSet<>(27);
        for (int rowCount = 0; rowCount < MatrixPlayGround.MAX_ROW_NUMBER; rowCount++) {
            int[] row = matrixPlayGround.getRow(rowCount);
            for (int colCount = 0; colCount < MatrixPlayGround.MAX_COL_NUMBER; colCount++) {
                if (row[colCount] == 0) {
                    retColl.add(new Cell(rowCount, colCount));
                } else {
                    retColl.add(new Cell(rowCount, colCount, row[colCount]));
                }
            }
        }
        return retColl;
    }
}

class SudokuValidator {
    public boolean validateMatrix(final MatrixPlayGround playGround, final boolean finalState) {
        throw new UnsupportedOperationException();
    }

    public boolean validateCells(final CellPlayGround playGround, final boolean finalState) {
        throw new UnsupportedOperationException();
    }
}

/**
 * Cell based model of the playfield.
 * Is it fine to be immutable?
 */
class CellPlayGround {

    private final List<List<Cell>> rows = new ArrayList<>(9);
    private final List<List<Cell>> cols = new ArrayList<>(9);
    private final Set<Set<Cell>> blocks = new HashSet<>(9);
    private final Set<Cell> allCells = new HashSet<>(27);

    public CellPlayGround(final MatrixPlayGround matrixPlayGround) {
        allCells.addAll(SudokeConverter.convertMatrixPlayGround2Cells(matrixPlayGround));
        splitCellsIntoColsRowsBlocks();
    }

    private CellPlayGround(final Set<Cell> allCells ) {
        this.allCells.addAll(new HashSet<>(allCells));
        splitCellsIntoColsRowsBlocks();
    }

    public CellPlayGround generateNewCellPlayGround(final Collection<Cell> newCells) {
        final Set<Cell> newAllCells = new HashSet<>(this.allCells);
        for (Cell c: newCells) {
            newAllCells.remove(c);
            newAllCells.add(c);
        }

        return new CellPlayGround(newAllCells);
    }

    private void splitCellsIntoColsRowsBlocks() {
        for (int i = 0; i < 9; i++) {
            rows.add(getRow(this.allCells, i));
            cols.add(getCol(this.allCells, i));
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                blocks.add(getBlock(this.allCells, i, j));
            }
        }
    }

    public List<Cell> getRow(final int row) {
        return rows.get(row);
    }

    public List<Cell> getCol(final int col) {
        return cols.get(col);
    }

    public static Set<Cell> getBlock(final Collection<Cell> cells, final int col, final int row) {
        final Set<Cell> result = new HashSet<>(9);

        for (Cell cell : cells) {
            if (cell.getCol() >= col * 3 && cell.getCol() < (col + 1) * 3) {
                if (cell.getRow() >= row * 3 && cell.getRow() < (row + 1) * 3) {
                    result.add(cell);
                }
            }
        }

        return Collections.unmodifiableSet(result);
    }

    public static List<Cell> getRow(final Collection<Cell> cells, final int row) {
        return cells.stream().filter(c -> c.getRow() == row).sorted(new Comparator<Cell>() {
            @Override
            public int compare(Cell o1, Cell o2) {
                return o1.getCol() > o2.getCol() ? 1 : -1;
            }
        }).collect(Collectors.toUnmodifiableList());
    }

    public static List<Cell> getCol(final Collection<Cell> cells, final int col) {

        return cells.stream().filter(c -> c.getCol() == col).sorted(new Comparator<Cell>() {
            @Override
            public int compare(Cell o1, Cell o2) {
                return o1.getRow() > o2.getRow() ? 1 : -1;
            }
        }).collect(Collectors.toUnmodifiableList());
    }
}

/**
 * Model for the playfield. It is a value object.
 */
class MatrixPlayGround {
    public static final int MAX_COL_NUMBER = 9;
    public static final int MAX_ROW_NUMBER = 9;

    private final int[][] matrix;
    private final int[][] rows;
    private final int[][] cols;
    private final int[][][][] blocks;
    private final int[][] groups;

    public MatrixPlayGround(final int[][] initMatrix) {
        if (initMatrix == null) {
            throw new IllegalArgumentException("Input can not be null");
        }

        if (initMatrix.length != 9) {
            throw new IllegalArgumentException("Number of rows must be 9.");
        }
        for (int[] rows : initMatrix) {
            if (rows == null || rows.length != 9) {
                throw new IllegalArgumentException("Number of elements in a row must be 9.");
            }
        }

        this.matrix = Arrays.copyOf(initMatrix, initMatrix.length);
        this.rows = new int[9][9];
        this.cols = new int[9][9];
        this.blocks = new int[3][3][3][3];
        this.groups = new int[27][9];

        calculateGroups();
    }

    public int[] getRow(final int position) {
        if (position < 0 || position > MAX_ROW_NUMBER - 1) {
            throw new IllegalArgumentException("Invalid row position: " + position);
        }
        return rows[position];
    }

    public int[] getCol(final int position) {
        if (position < 0 || position > MAX_COL_NUMBER - 1) {
            throw new IllegalArgumentException("Invalid col position: " + position);
        }

        return cols[position];
    }

    public int[][] getBlock(final int row, final int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            throw new IllegalArgumentException("Invalid block position. Row:  " + row + " Col: " + col);
        }

        return blocks[row][col];
    }

    private void calculateGroups() {
        int groupCounter = 0;
        // rows
        for (int position = 0; position < MAX_COL_NUMBER; position++) {
            this.rows[position] = Arrays.copyOf(matrix[position], MAX_ROW_NUMBER);
            groups[groupCounter++] = this.rows[position];
        }

        // cols
        for (int position = 0; position < MAX_COL_NUMBER; position++) {
            int[] col = new int[MAX_COL_NUMBER];
            int pos = 0;
            for (int[] row : matrix) {
                col[pos] = row[position];
                pos++;
            }
            cols[position] = col;
            groups[groupCounter++] = col;
        }

        // blocks
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int[][] block = new int[3][3];
                int currentRow = 0;
                for (int rowNum = row * 3; rowNum < row * 3 + 3; rowNum++) {
                    block[currentRow][0] = matrix[rowNum][col * 3];
                    block[currentRow][1] = matrix[rowNum][col * 3 + 1];
                    block[currentRow][2] = matrix[rowNum][col * 3 + 2];
                    currentRow++;
                }
                blocks[row][col] = block;
                groups[groupCounter++] = flatBlock(block);
            }
        }

    }

    private int[] flatBlock(int[][] block) {
        int[] ret = new int[9];
        int counter = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                ret[counter++] = block[row][col];
            }
        }
        return ret;
    }

    /**
     * Zero field value is not allowed
     *
     * @return
     */
    boolean validateAsFinal() {
        for (int[] group : groups) {
            final Set<Integer> values = new HashSet<>(9);
            for (int element : group) {
                if (!values.add(element)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Zero field value is allowed
     */
    boolean validateAsNonFinal() {
        for (int[] group : groups) {
            final Set<Integer> values = new HashSet<>(9);
            for (int element : group) {
                if (element != 0) {
                    if (!values.add(element)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

/**
 * A cell to store the possible values. It is immutable.
 */
class Cell {
    private final int row;
    private final int col;
    private final String id;
    private final Set<Integer> possibleValues = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

    public Cell(final Cell cell) {
        this.row = cell.row;
        this.col = cell.col;
        this.id = col + "" + row;
        this.getPossibleValues().clear();
        this.getPossibleValues().addAll(cell.getPossibleValues());
    }

    public Cell(final int row, final int col, final Set<Integer> initValues) {
        this.row = row;
        this.col = col;
        this.id = row + "" + col;
        if (initValues != null && initValues.size() != 0) {
            this.possibleValues.clear();
            this.possibleValues.addAll(initValues);
        }
    }

    public Cell(final int row, final int col) {
        this(row, col, new HashSet<>());
    }

    public Cell(final int row, final int col, final int initValue) {
        this(row, col, new HashSet<>(Arrays.asList(initValue)));
    }

    public Set<Integer> getPossibleValues() {
        return new HashSet<>(possibleValues);
    }

    public String getId() {
        return row + "" + col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isFinal() {
        return getPossibleValues().size() == 1;
    }

    public Cell removePossibilities(final Set<Integer> possibilitiesToRemove) {
        final Set<Integer> newPossibleValues = this.getPossibleValues();
        newPossibleValues.removeAll(possibilitiesToRemove);
        return new Cell(this.getRow(), this.getCol(), newPossibleValues);
    }

    public String fullToString() {
        return "[" + this.row + "][" + this.col + "]" + toString();
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

        ret.append("]");
        return ret.toString();
    }
}
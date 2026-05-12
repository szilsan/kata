package com.szilsan.kata.breakthepieces;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class BreakPieces {

    private static int rowNumber;
    private static int [] lineStartPositions;
    private static Set<CrossPoint> crosspoints = new HashSet<>();

    public static String[] process(String shape) {

        initAnalysis(shape);
        return null;
    }

    static char getCharAtPosition(String input, int row, int col) {
        return input.substring(lineStartPositions[row], lineStartPositions[row + 1]).charAt(col);
    }

    static void initAnalysis(String input) {
        rowNumber = (int) input.chars().filter(ch -> ch == '\n').count() + 1;;
        lineStartPositions = new int[rowNumber];

        int rowNumber = 1;
        int col =0;
        lineStartPositions[0] = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+') {
                crosspoints.add(new CrossPoint(rowNumber - 1, col));
            }
            if (input.charAt(i) == '\n') {
                lineStartPositions[rowNumber] = i + 1;
                rowNumber = rowNumber + 1;
                col = -1;
            }
            col = col + 1;
        }

        findNeighbours(input);
    }

    static void findNeighbours(String input) {
        for (CrossPoint cp : crosspoints) {
            // check up
            if (cp.row > 0) {
                if (getCharAtPosition(input, cp.row - 1, cp.col) != ' ') {
                    Set<CrossPoint> cps = filterCrosspoints(cp.row - 1, 0, cp.col, cp.col);
                    CrossPoint closesCp = cps.stream().sorted(Comparator.comparingInt(CrossPoint::getRow).reversed()).findFirst().orElse(null);
                    cp.getNeighbours().add(closesCp);
                }
            }
            // check down
            // check left
            // check right

        }
    }

    static Set<CrossPoint> filterCrosspoints(int maxRow, int minRow, int maxCol, int minCol) {
        Set<CrossPoint> filteredCps = new HashSet<>();
        for (CrossPoint cp : crosspoints) {
            if (cp.row <= maxRow && cp.row >= minRow && cp.col <= maxCol && cp.col >= minCol) {
                filteredCps.add(cp);
            }
        }
        return filteredCps;
    }

    public static int getRowNumber() {
        return rowNumber;
    }

    public static int[] getLineStartPositions() {
        return lineStartPositions;
    }

    public static Set<CrossPoint> getCrosspoints() {
        return crosspoints;
    }
}

class CrossPoint {
    public int row;
    public int col;

    public Set<CrossPoint> neighbours = new HashSet<>();

    public CrossPoint(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "CrossPoint{" + "row=" + row + ", col=" + col + ", neightbours=" + neighbours +
                '}';
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Set<CrossPoint> getNeighbours() {
        return neighbours;
    }
}

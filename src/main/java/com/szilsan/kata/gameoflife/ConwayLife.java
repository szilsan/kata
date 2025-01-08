package com.szilsan.kata.gameoflife;

import java.util.Arrays;

public class ConwayLife {

    public static int[][] getGeneration(int[][] cells, int generations) {

        int[][] result = cells;

        for (int i = 0; i < generations; i++) {
            result = calculateNextCycle(result)
        }

        return result;
    }

    public static int[][] calculateNextCycle(int[][] current) {
        int[][] roundCurrentState = roundFieldWithZero(current);
        int[][] newState = new int[roundCurrentState.length][];

        for (int i = 0; i < roundCurrentState.length; i++) {
            newState[i] = new int[roundCurrentState[i].length];
            for (int j = 0; j < roundCurrentState[i].length; j++) {
                int nextValue = nextValue(roundCurrentState, i, j);
                if (roundCurrentState[i][j] == 0) {
                    if (nextValue == 3) {
                        newState[i][j] = 1;
                    }
                }
                else {
                    if (nextValue == 2 || nextValue == 3) {
                        newState[i][j] = 1;
                    } else {
                        newState[i][j] = 0;
                    }
                }
            }
        }

        return trimField(newState);
    }

    public static int nextValue(int[][] currentMatrix, int row, int col) {
        int value = 0;

        value = getCellValue(currentMatrix, row - 1, col - 1) +
                getCellValue(currentMatrix, row - 1, col) +
                getCellValue(currentMatrix, row - 1, col + 1) +
                getCellValue(currentMatrix, row, col - 1) +
                getCellValue(currentMatrix, row, col + 1) +
                getCellValue(currentMatrix, row + 1, col - 1) +
                getCellValue(currentMatrix, row + 1, col) +
                getCellValue(currentMatrix, row + 1, col + 1);

        return value;
    }

    public static int getCellValue(int[][] currentMatrix, int row, int col) {
        if (row < 0 || col < 0 || row > (currentMatrix.length - 1) || col > (currentMatrix[0].length - 1)) {
            return 0;
        }
        return currentMatrix[row][col];
    }

    public static int[][] trimField(int[][] input) {
        if (input == null || input[0].length == 0) {
            return input;
        }

        boolean trimmed = false;
        int[][] result = Arrays.stream(input).map(int[]::clone).toArray(int[][]::new);
        do {
            trimmed = false;
            // first row
            if (!Arrays.stream(result[0]).anyMatch(i -> i == 1)) {
                result = Arrays.stream(result).skip(1).toArray(int[][]::new);
                trimmed = true;
            }

            // last row
            if (!Arrays.stream(result[result.length - 1]).anyMatch(i -> i == 1)) {
                result = Arrays.stream(result).limit(result.length - 1).toArray(int[][]::new);
                trimmed = true;
            }

            // first + last col
            boolean firstColEmpty = true;
            boolean lastColEmpty = true;
            for (int i = 0; i < result.length; i++) {
                if (result[i][0] == 1) {
                    firstColEmpty = false;
                }
                if (result[i][result[i].length - 1] == 1) {
                    lastColEmpty = false;
                }
            }

            if (firstColEmpty || lastColEmpty) {
                trimmed = true;
                int[][] tmpResult = new int[result.length][];
                for (int i = 0; i < result.length; i++) {
                    int newSize = result[i].length - (firstColEmpty ? 1 : 0) - (lastColEmpty ? 1 : 0);
                    tmpResult[i] = new int[newSize];
                    for (int j = 0; j < newSize; j++) {
                        tmpResult[i][j] = result[i][j + (firstColEmpty ? 1 : 0)];
                    }
                }
                result = tmpResult;
            }
        } while (trimmed);
        return result;
    }

    public static int[][] roundFieldWithZero(int[][] field) {
        int[][] result = new int[field.length + 2][];
        result[0] = new int[field[0].length + 2];
        result[field.length + 1] = new int[field[0].length + 2];
        for (int i = 0; i < field.length; i++) {
            result[1 + i] = new int[field[i].length + 2];
            for (int j = 0; j < field[i].length; j++) {
                result[1 + i][1 + j] = field[i][j];
            }
        }
        return result;
    }
}


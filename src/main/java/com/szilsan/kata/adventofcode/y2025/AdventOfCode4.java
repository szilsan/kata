package com.szilsan.kata.adventofcode.y2025;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class AdventOfCode4 {
    static void main() throws Exception {
        (new AdventOfCode4()).task2();
    }

    int[][] table;

    public void task1() throws Exception {
        List<String> steps = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(new File("D:\\workspaces\\kata\\src\\main\\java\\com\\szilsan\\kata\\adventofcode\\y2025\\AdventOfCode4Input.txt")))) {
            for (String line; (line = br.readLine()) != null; ) {
                steps.add(line);
            }
        }

        table = new int[steps.get(0).length()+2][steps.size()+2];
        for (int row = 0; row <  steps.size(); row ++) {
            for (int col = 0; col <  steps.get(0).length(); col ++) {
                table[row+1][col+1] = (steps.get(row).charAt(col) == '@' ? 1 : 0);

            }
        }

        int sum = 0;
        for (int i = 1; i < table.length-1; i++) {
            for (int j = 1; j < table[0].length-1; j++) {
                if (table[i][j] == 1 && windowWeight(i,j) < 4) {
                    System.out.println(i + "," + j);
                    sum++;
                }
            }
        }

        System.out.println("Code: " + sum);

    }

    public void task2() throws Exception {
        List<String> steps = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(new File("D:\\workspaces\\kata\\src\\main\\java\\com\\szilsan\\kata\\adventofcode\\y2025\\AdventOfCode4Input.txt")))) {
            for (String line; (line = br.readLine()) != null; ) {
                steps.add(line);
            }
        }

        table = new int[steps.get(0).length()+2][steps.size()+2];
        for (int row = 0; row <  steps.size(); row ++) {
            for (int col = 0; col <  steps.get(0).length(); col ++) {
                table[row+1][col+1] = (steps.get(row).charAt(col) == '@' ? 1 : 0);

            }
        }

        int sum = 0;
        boolean found = false;
        do {
            found = false;
            for (int i = 1; i < table.length - 1; i++) {
                for (int j = 1; j < table[0].length - 1; j++) {
                    if (table[i][j] == 1 && windowWeight(i, j) < 4) {
                        table[i][j] = 0;
                        System.out.println(i + "," + j);
                        sum++;
                        found = true;
                    }
                }
            }
        } while (found);

        System.out.println("Code: " + sum);

    }


    public int windowWeight(int x, int y) {
        return (table[x - 1][y - 1] +
                table[x + 1][y - 1] +
                table[x - 1][y + 1] +
                table[x + 1][y + 1] +
                table[x + 1][y] +
                table[x - 1][y] +
                table[x][y - 1] +
                table[x][y + 1]);
    }
}

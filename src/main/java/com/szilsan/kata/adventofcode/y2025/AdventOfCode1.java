package com.szilsan.kata.adventofcode.y2025;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdventOfCode1 {

    private int posZero;

    public static void main() throws IOException {
//        (new AdventOfCode1()).task1();
        (new AdventOfCode1()).task2();
    }

    public void task1() throws IOException {
        posZero = 0;
        List<String> steps = new ArrayList<>();
        int currPos = 50;

        try(BufferedReader br = new BufferedReader(new FileReader(new File("D:\\workspaces\\kata\\src\\main\\java\\com\\szilsan\\kata\\adventofcode\\y2025\\AdventOfCode1Input.txt")))) {
            for(String line; (line = br.readLine()) != null; ) {
                steps.add(line);
            }
        }

        for (String step : steps) {
            currPos = nextPoz(currPos,step);
            if (currPos == 0) {
                posZero++;
            }
        }

        System.out.println("Code: " + posZero);
    }

    public void task2() throws IOException {
        posZero = 0;
        List<String> steps = new ArrayList<>();
        int currPos = 50;

        try(BufferedReader br = new BufferedReader(new FileReader(new File("D:\\workspaces\\kata\\src\\main\\java\\com\\szilsan\\kata\\adventofcode\\y2025\\AdventOfCode1Input.txt")))) {
            for(String line; (line = br.readLine()) != null; ) {
                steps.add(line);
            }
        }

        for (String step : steps) {
            currPos = nextPoz2(currPos,step);
        }

        System.out.println("Code: " + posZero);
    }

    public int nextPoz(int currPos, String move) {
        int newPoz = 0;
        Character direction = move.charAt(0);
        int steps = Integer.parseInt(move.substring(1));

        steps = (steps - (steps / 100) * 100);
        newPoz = direction.charValue() == 'L' ? currPos - steps : currPos + steps;

        if (newPoz > 99) {
            newPoz = newPoz - 100;
        }
        if (newPoz < 0) {
            newPoz = newPoz + 100;
        }

        return newPoz;
    }

    public int nextPoz2(int currPos, String move) {
        int newPoz = 0;
        Character direction = move.charAt(0);
        int steps = Integer.parseInt(move.substring(1));

        posZero += (steps/100);
        steps = (steps - (steps / 100) * 100);
        newPoz = direction.charValue() == 'L' ? currPos - steps : currPos + steps;

        if (newPoz > 99) {
            newPoz = newPoz - 100;
        }
        if (newPoz < 0) {
            newPoz = newPoz + 100;
        }

        if (newPoz == 0) {
            posZero++;
        } else {

            if (currPos != 0 && direction.charValue() == 'L' && newPoz > currPos) {
                posZero++;
            }
            if (currPos != 0 && direction.charValue() == 'R' && newPoz < currPos) {
                posZero++;
            }
        }

        return newPoz;
    }

    public int getPosZero() {
        return posZero;
    }
}


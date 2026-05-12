package com.szilsan.kata.adventofcode.y2025;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class AdventOfCode3 {
    static void main() throws Exception {
//        (new AdventOfCode3()).task1();
        (new AdventOfCode3()).task2();
    }

    public void task1() throws Exception {
        System.out.println(highestJoltage("987654321111111"));
        System.out.println(highestJoltage("811111111111119"));
        System.out.println(highestJoltage("234234234234278"));
        System.out.println(highestJoltage("818181911112111"));

        List<String> steps = new ArrayList<>();
        int currPos = 50;

        try(BufferedReader br = new BufferedReader(new FileReader(new File("D:\\workspaces\\kata\\src\\main\\java\\com\\szilsan\\kata\\adventofcode\\y2025\\AdventOfCode3Input.txt")))) {
            for(String line; (line = br.readLine()) != null; ) {
                steps.add(line);
            }
        }

        long sum = 0;
        for (String line : steps) {
            sum += highestJoltage(line);
        }

        System.out.println("Code: " + sum);
    }

    public void task2() throws Exception {
//        System.out.println(highestJoltage2("987654321111111"));
//        System.out.println(highestJoltage2("811111111111119"));
        System.out.println(highestJoltage2("234234234234278"));
        //434234234278
        //434234234278
        System.out.println(highestJoltage2("818181911112111"));
        // 888911112111
        // 888911112111

        List<String> steps = new ArrayList<>();
        int currPos = 50;

        try(BufferedReader br = new BufferedReader(new FileReader(new File("D:\\workspaces\\kata\\src\\main\\java\\com\\szilsan\\kata\\adventofcode\\y2025\\AdventOfCode3Input.txt")))) {
            for(String line; (line = br.readLine()) != null; ) {
                steps.add(line);
            }
        }

        long sum = 0;
        for (String line : steps) {
            sum += highestJoltage2(line);
        }

        System.out.println("Code: " + sum);
    }

    public static int highestJoltage(String str) {
        int[] highestNumber1 = highestNumber(str.substring(0, str.length()-1));
        int[] highestNumber2 = highestNumber(str.substring(highestNumber1[1]+1, str.length()));
        return highestNumber1[0] * 10 + highestNumber2[0];
    }

    public static long highestJoltage2(String str) {
        long sum = 0;
        int[] highestNumber = new int[2];
        int poz = 0;
        for (int i = 0; i < 12; i++ ) {
//            System.out.println(str.substring(poz, str.length()-12+i) + " " + str.substring(poz));
            highestNumber = highestNumber(str.substring(poz, str.length()-11+i));
            sum+=highestNumber[0] * Math.pow(10, 11-i);
            poz +=highestNumber[1]+1;
        }
        return sum;
    }

    public static int[] highestNumber(String str) {
        int highest = 0;
        int poz = 0;
        for (int i = 0; i < str.length(); i++) {
            char c=str.charAt(i);
            if (Integer.parseInt(c + "") > highest) {
                highest = Integer.parseInt(c + "");
                poz = i;
            }
        }

        return new int[] {highest, poz};
    }
}

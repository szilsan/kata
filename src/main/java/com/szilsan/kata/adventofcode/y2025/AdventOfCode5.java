package com.szilsan.kata.adventofcode.y2025;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

record range(long from, long end) {
}

public class AdventOfCode5 {
    static void main() throws Exception {
        (new AdventOfCode5()).task2();
    }


    public void task1() throws Exception {
        int sum = 0;
        List<range> ranges = new ArrayList<>();
        List<Long> idsToCheck = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(new File("D:\\workspaces\\kata\\src\\main\\java\\com\\szilsan\\kata\\adventofcode\\y2025\\AdventOfCode5Input.txt")))) {
            for (String line; (line = br.readLine()) != null; ) {
                if (line.length() == 0) {
                    break;
                }
                String[] inputRanges = line.split("-");
                ranges.add(new range(Long.parseLong(inputRanges[0]), Long.parseLong(inputRanges[1])));
            }
            for (String line; (line = br.readLine()) != null; ) {
                idsToCheck.add(Long.parseLong(line));
            }
        }

        for (long id : idsToCheck) {
            for (range r : ranges) {
                if (id >= r.from() && id <= r.end()) {
                    sum++;
                    break;
                }
            }
        }


        System.out.println("Code: " + sum);

    }

    public void task2() throws Exception {
        List<range> ranges = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(new File("D:\\workspaces\\kata\\src\\main\\java\\com\\szilsan\\kata\\adventofcode\\y2025\\AdventOfCode5Input.txt")))) {
            for (String line; (line = br.readLine()) != null; ) {
                if (line.length() == 0) {
                    break;
                }
                String[] inputRanges = line.split("-");
                ranges.add(new range(Long.parseLong(inputRanges[0]), Long.parseLong(inputRanges[1])));
            }
        }

        boolean wasMerge;
        int count = 0;
        do {
            wasMerge = false;

            for (int i = 0; i < ranges.size() && !wasMerge; i++) {
                range r1 = ranges.get(i);
                for (int j = 0; j < ranges.size() && !wasMerge; j++) {
                    range r2 = ranges.get(j);
                    if (!r1.equals(r2)) {
                        range merged = merge(r1, r2);
                        if (merged != null) {
                            System.out.println(count++ + " - " + r1 + r2 + merged + " Size: " + (merged.end() - merged.from()));
                            ranges.remove(r1);
                            ranges.remove(r2);
                            ranges.add(merged);
                            wasMerge = true;
                        }
                    }
                }
            }

        } while (wasMerge);

        long sum = 0;
        for (range r: ranges) {
            sum+= (r.end() - r.from() + 1);
            System.out.println(sum);
        }


        System.out.println("Code: " + sum);

        // 349662463077350
    }

    private range merge(range r1, range r2) {
        // contains
        if (inRange(r1.from(), r2) && inRange(r1.end(), r2)) {
            return new range(r2.from(), r2.end());
        }
        if (inRange(r2.from(), r1) && inRange(r2.end(), r1)) {
            return new range(r1.from(), r1.end());
        }

        // overlapping
        if (inRange(r1.from(), r2)) {
            return new range(r2.from(), Math.max(r1.end(), r2.end()));
        }
        if (inRange(r1.end(), r2)) {
            return new range(Math.min(r2.from(), r1.from()), r2.end());
        }

        if (inRange(r2.from(), r1)) {
            return new range(r1.from(), Math.max(r1.end(), r2.end()));
        }
        if (inRange(r2.end(), r1)) {
            return new range(Math.min(r2.from(), r1.from()), r1.end());
        }
        return null;
    }

    public static boolean inRange(long value, range r) {
        return value >= r.from() && value <= r.end();
    }

}

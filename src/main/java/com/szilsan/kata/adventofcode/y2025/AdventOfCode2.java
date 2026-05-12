package com.szilsan.kata.adventofcode.y2025;

import java.util.HashSet;
import java.util.Set;

public class AdventOfCode2 {

    static void main() {
        //(new AdventOfCode2()).task1();
        (new AdventOfCode2()).task2();

    }

    public void task1() {
        //String data = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124";
        String data="4077-5314,527473787-527596071,709-872,2487-3128,6522872-6618473,69137-81535,7276-8396,93812865-93928569,283900-352379,72-83,7373727756-7373754121,41389868-41438993,5757-6921,85-102,2-16,205918-243465,842786811-842935210,578553879-578609405,9881643-10095708,771165-985774,592441-692926,7427694-7538897,977-1245,44435414-44469747,74184149-74342346,433590-529427,19061209-19292668,531980-562808,34094-40289,4148369957-4148478173,67705780-67877150,20-42,8501-10229,1423280262-1423531012,1926-2452,85940-109708,293-351,53-71";

        String[] ranges = data.split(",");
        long code = 0;

        for (String token : ranges) {
            String start = token.split("-")[0];
            String end = token.split("-")[1];

            for (long i = Long.parseLong(start); i <= Long.parseLong(end); i++) {
                if (isStringDoubled("" + i)) {
                    System.out.println(i);
                    code += i;
                }
            }
        }
        System.out.println("Code: " + code);
    }

    public void task2() {
//        String data = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124";
        String data="4077-5314,527473787-527596071,709-872,2487-3128,6522872-6618473,69137-81535,7276-8396,93812865-93928569,283900-352379,72-83,7373727756-7373754121,41389868-41438993,5757-6921,85-102,2-16,205918-243465,842786811-842935210,578553879-578609405,9881643-10095708,771165-985774,592441-692926,7427694-7538897,977-1245,44435414-44469747,74184149-74342346,433590-529427,19061209-19292668,531980-562808,34094-40289,4148369957-4148478173,67705780-67877150,20-42,8501-10229,1423280262-1423531012,1926-2452,85940-109708,293-351,53-71";

        String[] ranges = data.split(",");
        long code = 0;

        for (String token : ranges) {
            String start = token.split("-")[0];
            String end = token.split("-")[1];

            for (long i = Long.parseLong(start); i <= Long.parseLong(end); i++) {
                if (isMultiplied("" + i)) {
                    System.out.println(i);
                    code += i;
                }
            }
        }
        System.out.println("Code: " + code);
    }

    public static boolean isMultiplied(String str) {
        Set<String> pieces = new HashSet<>();
        int length = str.length();

        if (length%2 == 0) {
            pieces.add(str.substring(0, length/2));
            pieces.add(str.substring(length/2,length));
            if (pieces.size() == 1) return true;
            pieces.clear();
        }

        if (length%3 == 0) {
            pieces.add(str.substring(0, length/3));
            pieces.add(str.substring(length/3,2*length/3));
            pieces.add(str.substring(2*length/3,length));
            if (pieces.size() == 1) return true;
            pieces.clear();
        }

        if (length%4 == 0) {
            pieces.add(str.substring(0, length/4));
            pieces.add(str.substring(length/4,2*length/4));
            pieces.add(str.substring(2*length/4,3*length/4));
            pieces.add(str.substring(3*length/4,length));
            if (pieces.size() == 1) return true;
            pieces.clear();
        }

        if (length%5 == 0) {
            pieces.add(str.substring(0, length/5));
            pieces.add(str.substring(length/5,2*length/5));
            pieces.add(str.substring(2*length/5,3*length/5));
            pieces.add(str.substring(3*length/5,4*length/5));
            pieces.add(str.substring(4*length/5,length));
            if (pieces.size() == 1) return true;
            pieces.clear();
        }

        if (length%6 == 0) {
            pieces.add(str.substring(0, length/6));
            pieces.add(str.substring(length/6,2*length/6));
            pieces.add(str.substring(2*length/6,3*length/6));
            pieces.add(str.substring(3*length/6,4*length/6));
            pieces.add(str.substring(4*length/6,5*length/6));
            pieces.add(str.substring(5*length/6,length));
            if (pieces.size() == 1) return true;
            pieces.clear();
        }

        if (length%7 == 0) {
            pieces.add(str.substring(0, length/7));
            pieces.add(str.substring(length/7,2*length/7));
            pieces.add(str.substring(2*length/7,3*length/7));
            pieces.add(str.substring(3*length/7,4*length/7));
            pieces.add(str.substring(4*length/7,5*length/7));
            pieces.add(str.substring(5*length/7,6*length/7));
            pieces.add(str.substring(6*length/7,length));
            if (pieces.size() == 1) return true;
            pieces.clear();
        }


        return false;
    }

    public static boolean isStringDoubled(String str) {
        if (str.length()%2 ==1) {
            return false;
        }
        int length = str.length();

        return (str.substring(0, length/2).equals(str.substring(length/2,length)));
    }
}

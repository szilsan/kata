package com.szilsan.kata.deodorantevaporator;

public class Evaporator {

    public static int evaporator(double content, double evap_per_day, double threshold) {

        int count = 0;
        while (true) {
            double current = content * Math.pow(((100 - evap_per_day) / 100), count);
            if (100 * (current / content) < threshold) {
                return count;
            } else {
                count++;
            }
        }
    }
}
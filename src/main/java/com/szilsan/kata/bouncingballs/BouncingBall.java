package com.szilsan.kata.bouncingballs;

public class BouncingBall {

    public static int bouncingBall(double h, double bounce, double window) {

        if (h <= 0 ||bounce >= 1 || bounce <=0 || window >= h) {
            return -1;
        }

        int count = 1;
        double height = h * bounce;
        while (height > window) {
            count += 2;
            height *= bounce;
        }

        return count;
    }
}
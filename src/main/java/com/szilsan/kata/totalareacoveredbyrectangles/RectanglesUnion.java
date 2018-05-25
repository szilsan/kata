package com.szilsan.kata.totalareacoveredbyrectangles;

public class RectanglesUnion {
    public static int calculateSpace(int[][] rectangles) {

        if (rectangles == null || rectangles.length == 0) {
            return 0;
        }

        if (rectangles.length == 1) {
            return shapeSize(rectangles[0]);
        }

        int sumSize = 0;
        int sumOverlap = 0;

        for (int i = 0; i < rectangles.length; i++) {
            int[] ri = rectangles[i];
            int sizeI = shapeSize(rectangles[i]);
            sumSize += sizeI;
            for (int j = i + 1; j < rectangles.length; j++) {
                int[] rj = rectangles[j];
                int sizeO = overlap(rectangles[i], rectangles[j]);
                sumOverlap += sizeO;
            }
        }

        return sumSize - sumOverlap;
    }

    static int shapeSize(int[] shape) {
        return Math.abs(shape[0] - shape[2]) * Math.abs(shape[1] - shape[3]);
    }

    static int overlap(final int[] shape1, final int[] shape2) {
        int ax1, ax2, ay1, ay2, bx1, bx2, by1, by2;
        ax1 = shape1[0];
        ay1 = shape1[1];
        ax2 = shape1[2];
        ay2 = shape1[3];
        bx1 = shape2[0];
        by1 = shape2[1];
        bx2 = shape2[2];
        by2 = shape2[3];

        return Math.max(0, Math.min(ax2, bx2) - Math.max(ax1, bx1)) * Math.max(0, Math.min(ay2, by2) - Math.max(ay1, by1));
    }
}

/*
   b
   b
aaaoaaa
   c
   c
        int[][] recs = {
    a            {1, 1, 2, 2}, 1
    b            {1, 4, 2, 7}, 3
    c            {1, 4, 2, 6}, 2
    d            {1, 4, 4, 5}, 3
    e            {2, 5, 6, 7}, 8
    f            {4, 3, 7, 6}}; 9
                sum = 26 OK
                intersect: 5 kene legyen ??
 */
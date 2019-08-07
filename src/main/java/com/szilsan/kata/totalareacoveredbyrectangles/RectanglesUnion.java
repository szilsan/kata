package com.szilsan.kata.totalareacoveredbyrectangles;

import java.util.*;
import java.util.stream.Collectors;

public class RectanglesUnion {
    public static int calculateSpace(int[][] rectangles) {
        int value = calculateSpace(rectangles, 1);
        return value;
    }

    public static int calculateSpace(int[][] rectangles, int pre) {
        if (rectangles == null || rectangles.length == 0) {
            return 0;
        }

        if (rectangles.length == 1) {
            return shapeSize(rectangles[0]);
        }

        // calculate sum size of rectangles
        int sumSize = 0;
        for (int i = 0; i < rectangles.length; i++) {
            int[] ri = rectangles[i];
            int sizeI = shapeSize(rectangles[i]);
            sumSize += sizeI;
        }

        // calculate overlapping rectangles
        List<int[]> overlaps = new ArrayList<>();
        for (int i = 0; i < rectangles.length; i++) {
            for (int j = i + 1; j < rectangles.length; j++) {
                int[] overlap = calculateOverlap(rectangles[i], rectangles[j]);
                if (overlap.length != 0) {
                    List<int[]> a = overlaps.stream().filter(p -> Arrays.equals(p, overlap)).collect(Collectors.toList());
                    if (a.size() == 0) {
                        overlaps.add(overlap);
                    }
                }
            }
        }

        if (overlaps.size() > 0) {
            int[][] passedOverlaps = new int[overlaps.size()][];
            overlaps.toArray(passedOverlaps);
            int overlapsSize = calculateSpace(passedOverlaps, -1 * pre);
            return sumSize + pre *  overlapsSize;
        } else {
            return sumSize;
        }


        //return oldVersion(rectangles); // doesn't work properly
        //return bruteForce(rectangles);
    }

        private static int[] calculateOverlap(int[] r1, int[] r2) {
        int leftX   = Math.max(r1[0],r2[0]);
        int rightX  = Math.min(r1[2], r2[2]);
        int topY    = Math.max(r1[1], r2[1]);
        int bottomY = Math.min(r1[3], r2[3]);

        if ( leftX < rightX && topY < bottomY ) {
            return new int[] {leftX, topY, rightX, bottomY};
        } else {
            return new int[] {};
        }
    }

    private static void fillField(int[] rectangle, int[][] field, int offsetX, int offsetY) {
        for (int i = rectangle[0]; i < rectangle[2]; i++) {
            for (int j = rectangle[1]; j < rectangle[3]; j++) {
                field[i-offsetX][j-offsetY] = 1;
            }
        }
    }

    private static int bruteForce(int[][] rectangles) {
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        for (int i = 0; i < rectangles.length; i++) {
            minX = Math.min(rectangles[i][0], minX);
            maxX = Math.max(rectangles[i][2], maxX);
            minY = Math.min(rectangles[i][1], minY);
            maxY = Math.max(rectangles[i][3], maxY);
        }

        int[][] field = new int[maxX-minX][maxY-minY];
        for (int i = 0; i < rectangles.length; i++) {
            fillField(rectangles[i], field, minX, minY);
        }

        int count = 0;
        for (int[] r : field) {
            for (int c : r) {
                if (c == 1) {
                    count++;
                }
            }
        }
        return  count;
    }

    private static int oldVersion(int[][] rectangles) {
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
 0123456789
0
1
2
3
4 xx
5 xx
6 xx
7 xx
8
9


 */
package com.szilsan.kata.battleshipfieldvalidator;

import java.util.HashSet;
import java.util.Set;

public class BattleField {

    final static Set<Shape> shapes = new HashSet<>();

    public static boolean fieldValidator(int[][] field) {

        int size = field[0].length;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j] == 1) {
                    Point p = new Point(i, j);
                    processField(p);
                }
            }
        }

        return false;
    }

    private static void processField(final Point p) {
        int hasAffectedShape = 0;
        for (Shape shape : shapes) {
            hasAffectedShape += (shape.validPointForExtension(p) ? 1 : 0);
        }

        if (hasAffectedShape == 0) {
            Shape s = new Shape();
            s.getPoints().add(p);
            shapes.add(s);
        }

        if (hasAffectedShape > 0) {
            // error!
        }
    }
}

class Shape {
    private Set<Point> points = new HashSet<>();

    public Shape() {
    }

    public Set<Point> getPoints() {
        return points;
    }

    public boolean validPointForExtension(final Point p) {
        // TODO
        return false;
    }
}

class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
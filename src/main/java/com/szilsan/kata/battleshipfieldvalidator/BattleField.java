package com.szilsan.kata.battleshipfieldvalidator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class BattleField {

    static Set<Shape> shapes = new HashSet<>();

    public static boolean fieldValidator(int[][] field) {

        int size = field[0].length;
        shapes = new HashSet<>();

        try {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (field[i][j] == 1) {
                        Point p = new Point(i, j);
                        processField(p);
                    }
                }
            }
        } catch (Exception ex) {
            return false;
        }

        // checking sizes
        int count4 = 0;
        int count3 = 0;
        int count2 = 0;
        int count1 = 0;
        for (Shape s: shapes) {
            if (s.getPoints().size() == 4) {
                count4 ++;
            } else if (s.getPoints().size() == 3) {
                count3 ++;
            } if (s.getPoints().size() == 2) {
                count2 ++;
            } if (s.getPoints().size() == 1) {
                count1 ++;
            }
        }

        return (count4 == 1 && count3 == 2 && count2 ==3 && count1 == 4);
    }

    private static void processField(final Point p) {
        int hasAffectedShape = 0;
        for (Shape shape : shapes) {
            int ext = (shape.validPointForExtension(p));
            if (ext == -1) {
                throw new RuntimeException("Invalid point.");
            }
            hasAffectedShape += ext;
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
    private static final Set<PointTouch>  VALID_NEOGHTBOURS = new HashSet<>(Arrays.asList(PointTouch.T, PointTouch.B, PointTouch.L, PointTouch.R));
    private static final Set<PointTouch>  INVALID_NEOGHTBOURS = new HashSet<>(Arrays.asList(PointTouch.TR, PointTouch.TL, PointTouch.BL, PointTouch.BR));

    private Set<Point> points = new HashSet<>();

    public Shape() {
    }

    public Set<Point> getPoints() {
        return points;
    }

    /**
     * Valid a point as an extension
     *
     * @param p
     * @return 0 :
     */
    public int validPointForExtension(final Point p) {

        int isNeightbour = 0;
        if (points.isEmpty()) {
            isNeightbour = 0;
        } else {

            for (Point ip : points) {
                int cp =isGoodNeightbour(ip, p);

                if (cp == 1) {
                    isNeightbour = 1;
                } else if (cp == 0){
                    //isNeightbour = 0;
                } else {
                    isNeightbour = -1;
                    break;
                }
            }
        }
        if (isNeightbour == 1) {
            this.getPoints().add(p);
        }

        // point - point
        // not touched, invalid, valid
        // valid for shape: true for each point: not touched and/or valid for each
        return isNeightbour;
    }

    @Override
    public String toString() {
        return "Shape{" +
                "points=" + points +
                '}';
    }

    static int isGoodNeightbour(final Point p1, final Point p2) {
        PointTouch orientation = p1.orientation(p2);
        if (orientation != PointTouch.SAME && VALID_NEOGHTBOURS.contains(orientation)) {
            return 1;
        }

        if (orientation != PointTouch.SAME && INVALID_NEOGHTBOURS.contains(orientation)) {
            return -1;
        }
        return 0;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public PointTouch orientation(final Point p) {
        if (this.equals(p)) {
            return PointTouch.SAME;
        }

        if (this.getX() == p.getX() + 1) {
            if (this.getY() == p.getY() + 1) {
                return PointTouch.TL;
            } else if (this.getY() == p.getY() - 1) {
                return PointTouch.TR;
            } else if (this.getY() == p.getY() - 1) {
                return PointTouch.T;
            } else {
                return PointTouch.NOT;
            }
        }

        if (this.getX() == p.getX() - 1) {
            if (this.getY() == p.getY() + 1) {
                return PointTouch.BL;
            } else if (this.getY() == p.getY() - 1) {
                return PointTouch.BR;
            } else if (this.getY() == p.getY()) {
                return PointTouch.B;
            } else {
                return PointTouch.NOT;
            }
        }

        if (this.getY() == p.getY() + 1 && this.getX() == p.getX()) {
            return PointTouch.L;
        } else if (this.getY() == p.getY() - 1 && this.getX() == p.getX()) {
            return PointTouch.R;
        }
        return PointTouch.NOT;
    }
}

enum PointTouch {
    T,
    B,
    L,
    R,
    TL,
    TR,
    BL,
    BR,
    NOT,
    SAME
}
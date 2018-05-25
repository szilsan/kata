package com.szilsan.kata.battleshipfieldvalidator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BattleFieldTest {

    private static int[][] battleFieldGood = {
            {1, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {1, 0, 1, 0, 0, 0, 0, 0, 1, 0},
            {1, 0, 1, 0, 1, 1, 1, 0, 1, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
    /*
    1: 4
    2: 3
    3: 2
    4: 1

     */


    private static int[][] battleFieldBad = {
            {1, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {1, 0, 1, 0, 0, 0, 0, 0, 1, 0},
            {1, 0, 1, 0, 1, 1, 1, 0, 1, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    @Test
    public void testGood() {
        assertEquals(true, BattleField.fieldValidator(battleFieldGood));
    }

    @Test
    public void testBad() {
        assertEquals(false, BattleField.fieldValidator(battleFieldBad));
    }


    //@Test
    public void testShape() {
        Shape sh = new Shape();

        Point p1 = new Point(1,1);
        Point p2 = new Point(2,1);
        Point p3 = new Point(2,2);

        assertTrue(sh.validPointForExtension(p1) == 0);
        assertTrue(sh.validPointForExtension(p2) == 1);
        assertTrue(sh.validPointForExtension(p3) == -1);
}
}
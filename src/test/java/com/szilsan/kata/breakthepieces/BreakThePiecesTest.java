package com.szilsan.kata.breakthepieces;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Set;


public class BreakThePiecesTest {

    final String shape = String.join("\n", new String[] {"+------------+",
            "|            |",
            "|            |",
            "|            |",
            "+------+-----+",
            "|      |     |",
            "|      |     |",
            "+------+-----+"});

    @Test
    public void testInitAnalysis() {
        BreakPieces.initAnalysis(shape);
        Assert.assertEquals(BreakPieces.getRowNumber(),8);

        int[] lineStartPositions = BreakPieces.getLineStartPositions();
        Assert.assertEquals(lineStartPositions.length,8);
        Assert.assertEquals(lineStartPositions[0],0);
        Assert.assertEquals(lineStartPositions[1],15);
        Assert.assertEquals(lineStartPositions[2],30);
        Assert.assertEquals(lineStartPositions[3],45);
        Assert.assertEquals(lineStartPositions[4],60);
        Assert.assertEquals(lineStartPositions[5],75);
        Assert.assertEquals(lineStartPositions[6],90);
        Assert.assertEquals(lineStartPositions[7],105);

        Set<CrossPoint> crossPoints = BreakPieces.getCrosspoints();
        Assert.assertEquals(crossPoints.size(), 8);
        Assert.assertEquals(crossPoints.stream().filter(cp -> cp.col ==0 && cp.row == 0).count(), 1);
        Assert.assertEquals(crossPoints.stream().filter(cp -> cp.col ==0 && cp.row == 4).count(), 1);
        Assert.assertEquals(crossPoints.stream().filter(cp -> cp.col ==0 && cp.row == 7).count(), 1);
        Assert.assertEquals(crossPoints.stream().filter(cp -> cp.col ==13 && cp.row == 0).count(), 1);
        Assert.assertEquals(crossPoints.stream().filter(cp -> cp.col ==13 && cp.row == 4).count(), 1);
        Assert.assertEquals(crossPoints.stream().filter(cp -> cp.col ==13 && cp.row == 7).count(), 1);
        Assert.assertEquals(crossPoints.stream().filter(cp -> cp.col ==7 && cp.row == 4).count(), 1);
        Assert.assertEquals(crossPoints.stream().filter(cp -> cp.col ==7 && cp.row == 7).count(), 1);



    }

    @Test
    public void simpleTest() {

        String expected[] = {String.join("\n", new String[] {"+------------+",
                "|            |",
                "|            |",
                "|            |",
                "+------------+"}),
                String.join("\n", new String[] {"+------+",
                        "|      |",
                        "|      |",
                        "+------+"}),
                String.join("\n", new String[] {"+-----+",
                        "|     |",
                        "|     |",
                        "+-----+"})};
        String actual[] = BreakPieces.process(shape);
        Arrays.sort(expected);
        Arrays.sort(actual);
        assertEquals(expected, actual);
    }
}
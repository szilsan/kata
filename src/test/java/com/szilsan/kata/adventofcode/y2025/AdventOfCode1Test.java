package com.szilsan.kata.adventofcode.y2025;

import org.junit.Assert;
import org.junit.Test;

public class AdventOfCode1Test {

    @Test
    public void AdventOfCode1() {
        AdventOfCode1 ac1 = new AdventOfCode1();

        Assert.assertEquals(ac1.nextPoz2(50, "R5"), 55);
        Assert.assertEquals(ac1.getPosZero(), 0);

        Assert.assertEquals(ac1.nextPoz2(50, "L5"), 45);
        Assert.assertEquals(ac1.getPosZero(), 0);
    }

    @Test
    public void AdventOfCode1_2_leftover() {
        AdventOfCode1 ac1 = new AdventOfCode1();
        Assert.assertEquals(ac1.nextPoz2(50, "L55"), 95);
        Assert.assertEquals(1, ac1.getPosZero());

    }

    @Test
    public void AdventOfCode1_2_lefright() {
        AdventOfCode1 ac1 = new AdventOfCode1();
        Assert.assertEquals(ac1.nextPoz2(50, "R55"), 5);
        Assert.assertEquals(1, ac1.getPosZero());
    }

    @Test
    public void AdventOfCode1_2_multileft() {
        AdventOfCode1 ac1 = new AdventOfCode1();
        Assert.assertEquals(ac1.nextPoz2(50, "L55"), 95);
        Assert.assertEquals(1, ac1.getPosZero());

        Assert.assertEquals(ac1.nextPoz2(50, "L255"), 95);
        Assert.assertEquals(4, ac1.getPosZero());
    }


}

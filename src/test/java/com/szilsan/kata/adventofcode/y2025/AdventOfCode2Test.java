package com.szilsan.kata.adventofcode.y2025;

import org.junit.Assert;
import org.junit.Test;

public class AdventOfCode2Test {

    @Test
    public void AdventOfCode2_doubled() {
        Assert.assertEquals(false, AdventOfCode2.isStringDoubled("1234"));
        Assert.assertEquals(false, AdventOfCode2.isStringDoubled("101"));
        Assert.assertEquals(true, AdventOfCode2.isStringDoubled("1010"));
        Assert.assertEquals(true, AdventOfCode2.isStringDoubled("123123"));
    }

    @Test
    public void AdventOfCode2_multiplied() {
        Assert.assertEquals(false, AdventOfCode2.isMultiplied("1234"));
        Assert.assertEquals(false, AdventOfCode2.isMultiplied("101"));
        Assert.assertEquals(true, AdventOfCode2.isMultiplied("1010"));
        Assert.assertEquals(true, AdventOfCode2.isMultiplied("123123"));
        Assert.assertEquals(true, AdventOfCode2.isMultiplied("123123123"));
        Assert.assertEquals(true, AdventOfCode2.isMultiplied("11"));
        Assert.assertEquals(true, AdventOfCode2.isMultiplied("1212121212"));
        Assert.assertEquals(true, AdventOfCode2.isMultiplied("121212"));
        Assert.assertEquals(true, AdventOfCode2.isMultiplied("38593859"));
        Assert.assertEquals(true, AdventOfCode2.isMultiplied("824824824"));
    }
}

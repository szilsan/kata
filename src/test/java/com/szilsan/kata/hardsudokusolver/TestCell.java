package com.szilsan.kata.hardsudokusolver;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

public class TestCell {
    @Test
    public void testRemovePossibilities() {
        Cell cell = new Cell(1,2);
        Assert.assertTrue(cell.getPossibleValues().size() == 9);

        cell = cell.removePossibilities(new HashSet<>(Arrays.asList(1,2,3)));
        Assert.assertTrue(cell.getPossibleValues().size() == 6);

        cell = cell.removePossibilities(new HashSet<>(Arrays.asList(1,2,3)));
        Assert.assertTrue(cell.getPossibleValues().size() == 6);
    }
}

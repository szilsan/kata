package com.szilsan.kata.deodorantevaporator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EvaporatorTest {
    @Test
    public void testEvaporatorOne() {
        assertEquals(22 , Evaporator.evaporator(10, 10, 10));
    }
}
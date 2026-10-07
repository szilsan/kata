package com.szilsan.kata.alphabetwar;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlphabetWarTest {

    @Test
    public void alphabetWar() {
        Assert.assertEquals(AlphabetWar.alphabetWar(""), ReturnValues.DRAW.desc);
        Assert.assertEquals(AlphabetWar.alphabetWar(null), ReturnValues.DRAW.desc);
        Assert.assertEquals(AlphabetWar.alphabetWar("ag"), ReturnValues.DRAW.desc);

        Assert.assertEquals(AlphabetWar.alphabetWar("z"), ReturnValues.RIGHT.desc);
        Assert.assertEquals(AlphabetWar.alphabetWar("b"), ReturnValues.LEFT.desc);

        Assert.assertEquals(AlphabetWar.alphabetWar("tz"), ReturnValues.LEFT.desc);
        Assert.assertEquals(AlphabetWar.alphabetWar("jb"), ReturnValues.RIGHT.desc);

        Assert.assertEquals(AlphabetWar.alphabetWar("tzj"), ReturnValues.RIGHT.desc);
        Assert.assertEquals(AlphabetWar.alphabetWar("azt"), ReturnValues.LEFT.desc);
        Assert.assertEquals(AlphabetWar.alphabetWar("zt"), ReturnValues.LEFT.desc);

    }

}
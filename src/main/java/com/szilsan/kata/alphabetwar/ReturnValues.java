package com.szilsan.kata.alphabetwar;

public enum ReturnValues {
    RIGHT ("Right side wins!"),
    LEFT("Left side wins!"),
    DRAW ("Let's fight again!");

    public final String desc;

    ReturnValues(String desc) { {
        this.desc = desc;
    }
    }
}

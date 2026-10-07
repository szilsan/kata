package com.szilsan.kata.alphabetwar;

public class AlphabetWar {

    public static String alphabetWar(String fight) {
        if (fight == null || fight.trim().isEmpty()) {
            return ReturnValues.DRAW.desc;
        }

        int calculatedWeightSum= 0;
        for (int i = 0; i < fight.length(); i++) {
            char currentChar = fight.charAt(i);
            char leftchar = i > 0 ? fight.charAt(i - 1) : ' ';
            char rightchar = i < fight.length() - 1 ? fight.charAt(i + 1) : ' ';

            calculatedWeightSum += calculateWeight(currentChar, leftchar, rightchar);
        }

        if (calculatedWeightSum > 0) {
            return ReturnValues.LEFT.desc;
        }
        if (calculatedWeightSum < 0) {
            return ReturnValues.RIGHT.desc;
        }

        return ReturnValues.DRAW.desc;
    }

    public static int calculateWeight(char middle, char left, char right) {

        char calculatedMiddle = middle;

        if ((left == 't' && right != 'j') || (left != 'j' && right == 't')) {
            switch (middle) {
                case 'm': calculatedMiddle = 'w'; break;
                case 'q': calculatedMiddle = 'p'; break;
                case 'd': calculatedMiddle = 'b'; break;
                case 'z': calculatedMiddle = 's'; break;
            }
        }

        if ((left == 'j' && right != 't') || (left != 't' && right == 'j')) {
            switch (middle) {
                case 'w': calculatedMiddle = 'm'; break;
                case 'p': calculatedMiddle = 'q'; break;
                case 'b': calculatedMiddle = 'd'; break;
                case 's': calculatedMiddle = 'z'; break;
            }
        }

        switch (calculatedMiddle) {
            case 'w': return 4;
            case 'p': return 3;
            case 'b': return 2;
            case 's': return 1;
            case 'm': return -4;
            case 'q': return -3;
            case 'd': return -2;
            case 'z': return -1;
        }

        return 0;
    }
}

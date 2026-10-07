package com.szilsan.kata.vigenerecipher;

public class VigenereCipherHelper {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static String encode(String alphabet, String message, String password) {
        StringBuffer ret = new StringBuffer(message.length());

        for (int pos = 0; pos < message.length(); pos++) {
            ret.append(encodedChar(alphabet, message, password, pos));
        }

        return ret.toString();
    }

    public static char encodedChar(String alphabet, String message, String password, int pos) {
        if (alphabet.indexOf(message.charAt(pos)) == -1) {
            return message.charAt(pos);
        }
        char passwordChar = getPasswordNth(password, pos);
        int passwordCharPos = getCharPosInAlphabet(alphabet, passwordChar);
        return getAlphabetNth(alphabet, alphabet.indexOf("" + message.charAt(pos)), passwordCharPos);
    }

    public static int getCharPosInAlphabet(String alphabet, char c) {
        return alphabet.indexOf(c);
    }

    public static char getAlphabetNth(String alphabet, int start, int offset) {
        int position = start + offset;
        int calculatedPosition = (position - (position / alphabet.length()) * alphabet.length());
        return alphabet.charAt(calculatedPosition);
    }

    public static char getPasswordNth(String password, int position) {
        return getAlphabetNth(password, 0, position);
    }


}

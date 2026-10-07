package com.szilsan.kata.vigenerecipher;

import org.junit.Assert;
import org.junit.Test;

public class VigenereCipherHelperTest {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    @Test
    public void testPasswordPosition() {
        Assert.assertEquals('a', VigenereCipherHelper.getPasswordNth("alm", 0));
        Assert.assertEquals('m', VigenereCipherHelper.getPasswordNth("alm", 2));
        Assert.assertEquals('m', VigenereCipherHelper.getPasswordNth("almafa", 2));
        Assert.assertEquals('l', VigenereCipherHelper.getPasswordNth("almafa", 7));
        Assert.assertEquals('l', VigenereCipherHelper.getPasswordNth("almafa", 13));
    }

    @Test
    public void testGetCharPosInAlphabet() {
        Assert.assertEquals(0, VigenereCipherHelper.getCharPosInAlphabet(ALPHABET, 'a'));
        Assert.assertEquals(2, VigenereCipherHelper.getCharPosInAlphabet(ALPHABET, 'c'));
    }

    @Test
    public void testGetAlphabetNth() {
        Assert.assertEquals('a', VigenereCipherHelper.getAlphabetNth(ALPHABET, 0, 0));
        Assert.assertEquals('c', VigenereCipherHelper.getAlphabetNth(ALPHABET, 2, 0));
        Assert.assertEquals('d', VigenereCipherHelper.getAlphabetNth(ALPHABET, 2, 1));
        Assert.assertEquals('d', VigenereCipherHelper.getAlphabetNth(ALPHABET, ALPHABET.length() + 2, 1));
        Assert.assertEquals('d', VigenereCipherHelper.getAlphabetNth(ALPHABET, ALPHABET.length() + 2, ALPHABET.length() + 1));
    }

    @Test
    public void testEmpty() {
        Assert.assertEquals("", VigenereCipherHelper.encode(ALPHABET, "", ""));
    }

    @Test
    public void testEncodedChar() {
        // Assert.assertEquals('r', VigenereCipherHelper.encodedChar(ALPHABET, "codewars", "password", 0));
        Assert.assertEquals('o', VigenereCipherHelper.encodedChar(ALPHABET, "codewars", "password", 1));
        Assert.assertEquals('v', VigenereCipherHelper.encodedChar(ALPHABET, "codewars", "password", 2));
        Assert.assertEquals('w', VigenereCipherHelper.encodedChar(ALPHABET, "codewars", "password", 3));
        Assert.assertEquals('s', VigenereCipherHelper.encodedChar(ALPHABET, "codewars", "password", 4));
        Assert.assertEquals('o', VigenereCipherHelper.encodedChar(ALPHABET, "codewars", "password", 5));
        Assert.assertEquals('i', VigenereCipherHelper.encodedChar(ALPHABET, "codewars", "password", 6));
        Assert.assertEquals('v', VigenereCipherHelper.encodedChar(ALPHABET, "codewars", "password", 7));
    }

    @Test
    public void testEncode() {
        Assert.assertEquals("rovwsoiv", VigenereCipherHelper.encode(ALPHABET, "codewars", "password"));
        Assert.assertEquals("laxxhsj", VigenereCipherHelper.encode(ALPHABET, "waffles", "password"));
        Assert.assertEquals("CODEWARS", VigenereCipherHelper.encode(ALPHABET, "CODEWARS", "password"));
    }

}

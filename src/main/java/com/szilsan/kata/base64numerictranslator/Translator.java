package com.szilsan.kata.base64numerictranslator;

public class Translator {

    private static final String BASE64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";


    public static long translate(String input) {
        if (input == null || input.trim().length() == 0) {
            return 0;
        }

        long sum = 0;
        int pos = 1;
        for (char c : input.toCharArray()) {
            sum += (BASE64.indexOf("" + c) * Math.pow(64, input.length() - pos++));
        }
        return sum;
    }

}

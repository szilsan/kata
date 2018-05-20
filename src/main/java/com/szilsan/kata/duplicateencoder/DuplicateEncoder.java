package com.szilsan.kata.duplicateencoder;

import java.util.HashMap;
import java.util.Map;

public class DuplicateEncoder {

    static String encode(String word){
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : word.toUpperCase().toCharArray()) {
            if (counts.containsKey(c)) {
                counts.put(c, counts.get(c) + 1);
            } else {
                counts.put(c, 1);
            }
        }

        StringBuffer sb = new StringBuffer();
        for (char c : word.toUpperCase().toCharArray()) {
            sb.append( counts.get(c) > 1 ? ')' : '(');
        }

        return sb.toString();
    }
}

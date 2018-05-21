package com.szilsan.kata.decodethemorsecodeadvanced;

import com.szilsan.kata.decodethemorsecode.MorseCode;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MorseCodeDecoder {

    private static final Pattern patternZeroFields = Pattern.compile("[1]([0]+)");
    private static final Pattern patternOneFields = Pattern.compile("[0]*([1]+)");

    public static String decodeBits(String bits) {
        String processingBits = removeNotNecessaryZeros(bits);
        int period = detectPeriod(processingBits, patternZeroFields);
        if (period == -1) {
            detectPeriod(processingBits, patternOneFields);
        }

        String dot = new String(new char[period]).replace("\0", "1");
        String dash = new String(new char[period]).replace("\0", "111");
        String pauseSigns = new String(new char[period]).replace("\0", "0");
        String pauseChars = new String(new char[period]).replace("\0", "000");
        String pauseWords = new String(new char[period]).replace("\0", "0000000");


        List<List<String>> tokens = Arrays.asList(bits.trim().split(pauseWords))
                .stream()
                .map(i -> Arrays.asList(i.split(pauseChars))).collect(Collectors.toList());

        Pattern signs = Pattern.compile("([1]+)[0]*");
        StringBuffer sb = new StringBuffer();
        for (List<String> token : tokens) {
            for (String s : token) {
                Matcher mSigns = signs.matcher(s);
                while (mSigns.find()) {
                    String sign = mSigns.group(1);
                    if (sign.equals(dot)) {
                      sb.append(".");
                    } else if (sign.equals(dash)) {
                        sb.append("-");
                    }
                }
                sb.append(" ");
            }
            sb.append("   ");
        }

        return sb.toString().trim();
    }

    public static String decodeMorse(String morseCode) {
        StringBuffer sb = new StringBuffer();

        List<List<String>> words = Arrays.asList(morseCode.trim().split("   "))
                .stream()
                .map(i -> Arrays.asList(i.split(" "))).collect(Collectors.toList());
        for (List<String> lstWords : words) {
            for (String ch : lstWords) {
                if (ch.trim().length() == 0 ){
                    sb.append(" ");
                } else {
                    sb.append(MorseCode.get(ch));
                }
            }
        }


        return sb.toString();
    }

    private static String removeNotNecessaryZeros(final String bits) {
        String processedBits = bits;
        Pattern leadingZeros = Pattern.compile("(^[0]*)");
        Pattern endingZeros = Pattern.compile("([0]*$)");

        Matcher m = leadingZeros.matcher(processedBits);
        if (m.find()) {
            processedBits = processedBits.substring(m.group(1).length());
        }

        m = endingZeros.matcher(processedBits);
        if (m.find()) {
            processedBits = processedBits.substring(0, processedBits.length() - m.group(1).length());
        }
        return processedBits;
    }

    private static int detectPeriod(final String bits, final Pattern pattern) {
        Matcher m = pattern.matcher(bits);
        List<String> groupsOfFields = new ArrayList<>();

        while (m.find()) {
            groupsOfFields.add(m.group(1));
        }

        Optional<String> max = groupsOfFields.stream().max(Comparator.comparingInt(String::length));
        if (max.isPresent()) {
            if (max.get().length() % 7 == 0) {
                return max.get().length() / 7;
            } else if (max.get().length() % 3 == 0) {
                return max.get().length() / 3;
            } else {
                return max.get().length();
            }
        }

        return -1;
    }
}


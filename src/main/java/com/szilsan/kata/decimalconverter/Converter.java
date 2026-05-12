package com.szilsan.kata.decimalconverter;

// https://en.wikipedia.org/wiki/Non-integer_base_of_numeration#Base_e
public class Converter {

    static void main() {
        System.out.println(Converter.converter(13, 0, Math.PI));
        System.out.println(Converter.converter(13, 5, Math.PI));
        System.out.println(Converter.converter(13, 0, 2));
        System.out.println(Converter.converter(13, 0, 16));
        System.out.println(Converter.converter(15, 0, 16));
        System.out.println(Converter.converter(15123, 0, 16));
        System.out.println(Converter.converter(8, 0, 2));
        System.out.println(Converter.converter(8, 0, 7));
    }

//    function toBase(n, b) {
//        k = floor(log(b, n)) + 1
//        precision = 8
//        result = ""
//
//        for (i = k - 1, i > -precision-1, i--) {
//            if (result.length == k) result += "."
//
//            digit = floor((n / b^i) mod b)
//            n -= digit * b^i
//            result += digit
//        }
//
//        return result
//    }

    public static String converter(double num, int precision, double base) {
        double k = Math.floor(Math.log(num)/Math.log(base)) + 1;
        String result = "";
        if (k < 0 ) {
            k = 0;
        }

        for (double i = k - 1; i > (-precision) - 1; i--) {
            if (result.length() == k) {
                result += ".";
            }

            double digit = Math.floor((num / Math.pow(base, i)) % base);

            if (digit >= 10) {
                result += (char)(55 + digit);
            } else {
                result += ("" + digit).charAt(0);
            }

            num -= digit * Math.pow(base, i);
        }


        return result;
    }


}

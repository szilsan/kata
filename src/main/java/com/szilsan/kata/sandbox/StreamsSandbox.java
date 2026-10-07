package com.szilsan.kata.sandbox;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsSandbox {
    public static void main(String[] args) {
//        Stream<String> stream = Stream.of("A", "B", "C", "D", "E", "F");
//        AtomicInteger count = new AtomicInteger();
//        Stream.generate(() -> 2 * count.getAndAdd(1)).limit(5).forEach(System.out::println);
        for (int i = 100; i< 150; i++){
            Integer i1 = Integer.valueOf(i);
            Integer i2 = Integer.valueOf(i);
            System.out.println(i + ": " + (i1 == i2));
        }





    }
}

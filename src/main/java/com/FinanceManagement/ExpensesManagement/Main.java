package com.FinanceManagement.ExpensesManagement;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Chetainya");
        names.add("Himanshi");
        names.add("Himanshi");

        Map<Integer, List<String>> collect1 = names.stream().collect(Collectors.groupingBy(String -> String.length()));
        System.out.println(collect1);



        AtomicInteger counter = new AtomicInteger(0);
        Map<Integer, String> collect = names.stream().distinct().collect(Collectors.toMap(i -> counter.getAndIncrement(), i -> i));





        System.out.println(collect);


        List<List<Integer>> listofList = Arrays.asList(
                Arrays.asList(1,2),
                Arrays.asList(3,4),
                Arrays.asList(5,6)
        );
        System.out.println(listofList);
        List<Integer> result = listofList.stream().flatMap(list -> list.stream()).collect(Collectors.toList());
        System.out.println(result);

        Map<Boolean, List<Integer>> answer = result.stream().collect(Collectors.partitioningBy(x -> x % 2 == 0));
        System.out.println(answer);

        List<String> words = Arrays.asList(
                "apple", "banana", "apple", "orange", "banana", "apple"
        );

        Map<String, Long> collect2 = words.stream().collect(Collectors.groupingBy(word -> word, Collectors.counting()));
        System.out.println(collect2);
    }
}

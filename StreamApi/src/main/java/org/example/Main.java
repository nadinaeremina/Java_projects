package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // 'Stream' - инструмент для перебора, в нем есть методы,
        // позволяющие перебрать кол-цию с разными целями

        ArrayList<Integer> my_numbers = new ArrayList<>(List.of(-5, -2, 33, 0, 15, 0, -100));
        // получаем стрим нашей коллекции
        Stream<Integer> numbersStream = my_numbers.stream();
        // переберем и выведем
        // 1
        // numbersStream.forEach(number -> System.out.println((number)));
        // 2
        numbersStream.forEach(System.out::println);

        // получим новый лист
        List<Integer> positiveNumbers2 = my_numbers.stream().filter(number -> number > 0).toList();

        int n = 10, min = -100, max = 100;
        final Random random = new Random();
        ArrayList<Integer> numbers = Stream.generate(() -> random.nextInt(min, max+1))
                // лимит генераций
                .limit(n)
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println("numbers: " + numbers);

        // если хотим собрать в коллекцию
        ArrayList<Integer> positiveNumbers = numbers.stream()
                .filter(number -> number > 0)
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println("positive numbers: " + positiveNumbers);

        // десятичное число в шестнадцатиричное в строку
        ArrayList<String> numbersAsHEXStrings = numbers.stream()
                .map(number -> {
                    String hexString = Integer.toHexString(Math.abs(number));
                    if (number < 0) {
                        return "-" + hexString;
                    }
                    return hexString;
                }).collect(Collectors.toCollection(ArrayList::new));
        System.out.println("hex numbers: " + numbersAsHEXStrings);

        // нулевые значения в начало
        ArrayList<Integer> numbersWithStartZeroes = my_numbers.stream()
                .sorted((n1, n2) -> {
                    if (n1.equals(n2)) {
                        return 0;
                    }
                    if (n1 == 0) {
                        return -1;
                    }
                    return 1;
                }).collect(Collectors.toCollection(ArrayList::new));
        System.out.println("numbers with start zeroes: " + numbersWithStartZeroes);

        // найти первое положительное число
        Optional<Integer> firstPositiveNumber = numbers.stream()
                .filter(number -> number > 0)
                .findFirst();
        // если есть зачение
        if (firstPositiveNumber.isPresent()) {
            System.out.println("first positive number: " + firstPositiveNumber.get());
        } else {
            System.out.println("no positive numbers in list");
        }

        // посчитать нулевые значения
        long zeroesCount = my_numbers.stream()
                .filter(number -> number == 0)
                .count();
        System.out.println("zeroes count: " + zeroesCount);

        // сумма модулей чисел
        int absSum = numbers.stream()
                .reduce(0, (sum, number) -> Math.abs(number) + sum);
        System.out.println("abs sum: " + absSum);
    }
}
package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    // 'static' - потому что это вложенный класс в классе 'Main'
    // используем этот класс в статическом методе 'static void main'
    public static void withAnonymousClass() {
        ArrayList<Integer> numbers = new ArrayList<>(List.of(111, 101, -1010, -12345, 0));
        System.out.println("numbers: " + numbers);

        // анонимный класс - можем описать прямо внутри метода
        // сортировка чисел с компаратором
        // implements Comparator<Integer>
        Comparator<Integer> comparator = new Comparator<Integer>() {
            // тело компоратора
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 == null || o2 == null) {
                    throw new UnsupportedOperationException("null values is unsupported");
                }
                int o1DigitsCount = Integer.toString(Math.abs(o1)).length();
                int o2DigitsCount = Integer.toString(Math.abs(o2)).length();
                return Integer.compare(o1DigitsCount, o2DigitsCount);
            }
        };
        numbers.sort(comparator);
        System.out.println("sorted numbers: " + numbers);
    }

    public static void withCallbackMethod() {
        ArrayList<Integer> numbers = new ArrayList<>(List.of(111, 101, -1010, -12345, 0));
        System.out.println("numbers: " + numbers);

        // сортировка чисел с передачей callback-метода (должен быть статическим)
        numbers.sort(Main::compareByDigitsLength);
        System.out.println("sorted numbers: " + numbers);
    }

    // вместо компаратора можем просто передать метод
    public static int compareByDigitsLength(Integer o1, Integer o2) {
        if (o1 == null || o2 == null) {
            throw new UnsupportedOperationException("null values is unsupported");
        }
        int o1DigitsCount = Integer.toString(Math.abs(o1)).length();
        int o2DigitsCount = Integer.toString(Math.abs(o2)).length();
        return Integer.compare(o1DigitsCount, o2DigitsCount);
    }

    // лямбда является упрощенным синтаксисом анонимного класса с одним методом
    public static void withLambdaExpression() {
        ArrayList<Integer> numbers = new ArrayList<>(List.of(111, 101, -1010, -12345, 0));
        System.out.println("numbers: " + numbers);

        // сортировка чисел с компаратором
        // 1
        // Comparator<Integer> comparator = (o1, o2) -> o1.compareTo(o2);
        // numbers.sort(comparator);
        // 2
        // Comparator<Integer> comparator = (o1, o2) -> {...}
        // 3 // сразу передаем в сортировку
        final Counter counter = new Counter();
        numbers.sort((o1, o2) -> {
            counter.inc();
            System.out.println("compareTo#" + counter.get());
            if (o1 == null || o2 == null) {
                throw new UnsupportedOperationException("null values is unsupported");
            }
            int o1DigitsCount = Integer.toString(Math.abs(o1)).length();
            int o2DigitsCount = Integer.toString(Math.abs(o2)).length();
            return Integer.compare(o1DigitsCount, o2DigitsCount);
        });
        System.out.println("compareTo count: " + counter.get());
        System.out.println("sorted numbers: " + numbers);
    }

    public static void main(String[] args) {
        withAnonymousClass();
        withCallbackMethod();
        withLambdaExpression();
    }

    public static class Counter {
        private int counter;

        public Counter() {
            counter = 0;
        }

        public void inc() {
            counter++;
        }

        public int get() {
            return counter;
        }
    }
}
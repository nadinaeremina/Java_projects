package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static Integer scanInteger(Scanner sc, String argName, Integer min, Integer max) {
        try {
            String annotation = "Enter '" + argName + "' (integer";
            if (min != null) {
                annotation += ", >= " + min;
            }
            if (max != null) {
                annotation += ", <= " + max;
            }
            annotation += "): ";
            System.out.print(annotation);

            int value = Integer.parseInt(sc.nextLine());
            if (min != null && value < min) {
                System.out.println("too low value");
                return null;
            }
            if (max != null && value > max) {
                System.out.println("too high value");
                return null;
            }
            return value;
        } catch (NumberFormatException ex) {
            System.out.println("format error: " + ex.getMessage());
            return null;
        }
    }

    public static int[] generateNumbers(int n, int min, int max, Random random) {
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = random.nextInt(min, max + 1);
        }
        return numbers;
    }

    public static void printNumbers(int[] numbers) {
        String path = "numbers.txt";
        // если 'PrintWriter' создадим здесь в 'try' - то блок 'finally' будет не нужен
        // в противном случае нужен для того, чтобы закрыть 'pw.close()'
        try (PrintWriter pw = new PrintWriter(path)) {
            for (int number : numbers) {
                pw.print(number + " ");
            }
        } catch (IOException ex) {
            System.out.println("file exception: " + ex.getMessage());
        }
    }

    // throws IOException
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            // 1. ввод данных
            Integer n = scanInteger(sc, "n", 1, 1000);
            if (n == null) {
                return;
            }
            Integer min = scanInteger(sc, "min", null, null);
            if (min == null) {
                return;
            }
            Integer max = scanInteger(sc, "max", min + 1, null);
            if (max == null) {
                return;
            }

            // 2. генерация чисел
            Random random = new Random();
            int[] numbers = generateNumbers(n, min, max, random);

            // 3. вывод ответа
            printNumbers(numbers);

        } catch (Exception ex) {
            System.out.println("unhandled exception: " + ex.getMessage());
        }
    }
}
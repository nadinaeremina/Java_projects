package org.example.arrays;

import java.util.Arrays;
import java.util.Random;

// класс IntArray – целочисленный массив фиксированного размера
public class IntArray {
    // поле - массив целых чисел
    protected final int[] numbers;

    // конструктор по умолчанию
    public IntArray() {
        numbers = new int[10];
        // создаем объект класса Random
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            // заполняем каждый элемент случайным числом от 0 до 99
            numbers[i] = random.nextInt(100);
        }
    }

    // конструктор с параметрами
    public IntArray(int size) {
        numbers = new int[size];
        // создаем объект класса Random
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            // заполняем каждый элемент случайным числом от 0 до 99
            numbers[i] = random.nextInt(100);
        }
    }

    // методы
    // getter
    public int[] getNumbers() {
        return numbers;
    }

    // получение элемента по индексу
    public int getElementByIndex(int index) {
        if (index >= 0 && index < numbers.length) {
            return numbers[index];
        }
        throw new ArrayIndexOutOfBoundsException("index not found");
    }

    // установка элемента по индексу
    public void setElementByIndex(int element, int index) {
        if (index >= 0 && index < numbers.length) {
            numbers[index] = element;
        } else {
            throw new ArrayIndexOutOfBoundsException("index not found");
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(numbers);
    }
}

package org.example.arrays;

import java.util.Arrays;
import java.util.EmptyStackException;

// класс IntStack – наследник IntArray – целочисленный стек, использующий для хранения элементов массив
public class IntStack extends IntArray{
    // размер стека – может быть пустым
    private int size;

    // конструктор по умолчанию
    public IntStack() {
        size = 0;
    }

    // конструктор с параметрами
    public IntStack(int capacity) {
        super(capacity);
        // numbers.length
        this.size = 0;
    }

    // методы
    // getter (вычисляемый)
    public int getCapacity() {
        return numbers.length;
    }

    public int getSize() {
        return size;
    }

    // проверка на переполняемость
    private boolean isFull() {
        return size == numbers.length;
    }

    // проверка, не пустой ли стек
    private  boolean isEmpty() {
        return size == 0;
    }

    // добавление элемента в верхушку стека
    public void push(int item) {
        if (isFull()) {
            throw new IndexOutOfBoundsException("stack is full");
        }
        // добавили элемент в стек - размер стека увеличился
        numbers[size++] = item;
    }

    // удаление элемента из верхушки стека
    public void pop() {
        if (!isEmpty()) {
            size--;
        } else {
            throw new EmptyStackException();
        }
    }

    // получение элемента с верхушки стека без удаления
    public int top() {
        if (!isEmpty()) {
            return numbers[size - 1];
        } else {
            throw new EmptyStackException();
        }
    }

    // вспомогательный метод для вывода содержимого стека
    private String stackOutput() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(numbers[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    // toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(stackOutput());
        sb.append("]");
        return sb.toString();
    }

    // deepToString
    public String deepToString() {
        StringBuilder sb = new StringBuilder(stackOutput());
        if (sb.length() > 1) {
            sb.append(", ");
        }
        for (int i = size; i < getCapacity(); i++) {
            sb.append("null");
            if (i < getCapacity() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

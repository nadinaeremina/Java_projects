package org.example;

import org.example.arrays.IntArray;
import org.example.arrays.IntStack;

import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        IntArray newIntArray1 = new IntArray();
        System.out.println("Первый массив с размером по умолчанию:");
        System.out.println(newIntArray1.toString());

        IntArray newIntArray2 = new IntArray(12);
        System.out.println("Второй массив с фиксированным размером:");
        System.out.println(newIntArray2.toString());

        int[] getNumbers = newIntArray1.getNumbers();
        System.out.println("Вывод первого массива через гет-метод:");
        System.out.println(Arrays.toString(getNumbers));

        int getElement = newIntArray1.getElementByIndex(2);
        System.out.println("Вывод элемента по индексу 2:");
        System.out.println(getElement);

        newIntArray2.setElementByIndex(9, 3);
        System.out.println("Вывод второго массива после установки нового элемента 9 на индекс 3:");
        System.out.println(newIntArray2.toString());

        IntStack newIntStack1 = new IntStack();
        IntStack newIntStack2 = new IntStack(20);

        int getCapacity1 = newIntStack1.getCapacity();
        System.out.println("Емкость первого стэка:");
        System.out.println(getCapacity1);

        int getCapacity2 = newIntStack2.getCapacity();
        System.out.println("Емкость второго стэка:");
        System.out.println(getCapacity2);

        System.out.println("Вывод первого стэка:");
        System.out.println(newIntStack1.toString());

        newIntStack1.push(7);
        newIntStack1.push(8);
        newIntStack1.push(4);

        System.out.println("Вывод первого стэка после добавления элементов:");
        System.out.println(newIntStack1.toString());

        int getElementFromTop1 = newIntStack1.top();
        System.out.println("Вывод первого элемента с верхушки стэка:");
        System.out.println(getElementFromTop1);

        System.out.println("Вывод первого стэка (его глубины-элементы всей его емкости):");
        System.out.println(newIntStack1.deepToString());


        newIntStack1.pop();
        System.out.println("Вывод первого стэка после удаления элемента с верхушки стэка:");
        System.out.println(newIntStack1.toString());
        System.out.println("Вывод первого стэка (его глубины - элементы всей его емкости):");
        System.out.println(newIntStack1.deepToString());

        newIntStack1.pop();
        newIntStack1.pop();

        // getElementFromTop1 = newIntStack1.top();
        System.out.println("Вывод первого стэка после удаления всех элементов:");
        System.out.println(newIntStack1.toString());
        System.out.println("Вывод первого стэка (его глубины - элементы всей его емкости):");
        System.out.println(newIntStack1.deepToString());

        // newIntStack1.pop();
    }
}
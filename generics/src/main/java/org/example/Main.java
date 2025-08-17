package org.example;

import org.example.pair.GenericPair;
import org.example.pair.ObjectPair;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /////////////// необобщенный подход сводится к использованию object ////////////////////////////////
        ObjectPair stringInteger = new ObjectPair("Hello world!", 10);
        System.out.println(stringInteger);

        // прилетает object - поэтому вынуждены сделать приведение типов
        String firstFromStringInteger = (String)stringInteger.getFirst();
        System.out.println(firstFromStringInteger);

        ObjectPair stringString = new ObjectPair("Hello", "  world!");
        System.out.println(stringString);

        // может быть даже пара: строка-обьект

        ////////////// обобщенный подход сводится к использованию generics /////////////////////////////////////
        // в правой части выражения (при вызове кон-ра) не обязательно указывать типы
        GenericPair<String, Integer> pair = new GenericPair<>("Hello world!", 10);
        System.out.println(pair);

        // уже не можем
        // pair.setFirst(33);

        // у generic не можем вызывать никакие методы, кроме методов для object
        // можно имплементировать интерфейс и вызывать методы этого  нтерфейса

        // Стирание типов - процесс приведения дженериков к работе с Object при компиляции в байт-код
        System.out.println(pair.getByIndex(0));
        System.out.println(pair.getByIndex(1));

        pair.setByIndex(0, "Привет мир!");
        pair.setByIndex(1, 33);
        System.out.println(pair);

        // здесь все сводится к object - поэтому в integer зайдет строка
        pair.setByIndex(1, "BUGAGA");
        System.out.println(pair);

        // здесь object в integer не приведется
        //Integer second = pair.getSecond();
        //System.out.println("second: " + second);
    }
}
package org.example;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // инициализация переменной списка
        // нельзя создать список примитивных типов
        List<Integer> ints = new ArrayList<>();

        // заполнение случайными данными
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            ints.add(random.nextInt(100));
        }

        // вывод через for each
        // можно использовать как обертку (Integer), так и int
        for (Integer item : ints) {
            System.out.println(item + " ");
        }
        System.out.println();

        // домножить все элементы на 100
        for (int i = 0; i < 10; i++) {
            ints.set(i, ints.get(i) * 100);
            // 'i' - индекс
            // 'ints.get(i)' - само это значение по индексу
        }

        // JCF - JAVA COLLECTION FRAMEWORK - модуль JAVA, который реализует коллекции

        /////////////////////////////////////////// Задача 1 //////////////////////////////////////////////

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = Integer.parseInt(sc.nextLine());

        // 1 // создаем два списка ArrayList<Integer>
        ArrayList<Integer> first = new ArrayList<>();
        ArrayList<Integer> second = new ArrayList<>();

        // 2 // заполнить данные списки случайными числами от 0 до 100 в кол-ве n штук
        for (int i = 0; i < n; i++) {
            first.add(random.nextInt(101));
            second.add(random.nextInt(101));
        }

        // 3 // вывести сгенерированные списки
        System.out.println("first: " + first);
        System.out.println("second: " + second);

        // 4 // создать новый список, в котором будут (без повторений, порядок не важен):
        // элементы первого исходного списка, отсутствующие во втором
        // элементы второго исходного списка, отсутствующие в первом

        // из 'ArrayList' нужно получить 'Set'
        Set<Integer> firstSet = new HashSet<>(first);
        Set<Integer> secondSet = new HashSet<>(second);

        ArrayList<Integer> result = new ArrayList<>();

        for (Integer item : firstSet) {
            if (!secondSet.contains(item)) {
                result.add(item);
            }
        }

        for (Integer item : secondSet) {
            if (!firstSet.contains(item)) {
                result.add(item);
            }
        }

        // 5 // вывести полученный список
        System.out.println("result: " + result);

        // 6 // удалить из исходных списков элементы, которые вошли в третий новый список
        first.removeAll(result);
        second.removeAll(result);

        System.out.println("first after remove: " + first);
        System.out.println("second after remove: " + second);

        /////////////////////////////////////// MAP ////////////////////////////////////////////
        // контейнер, который хранит ключ-значение
        HashMap<String, Integer> intByString = new HashMap<>();

        // добавляем значения
        intByString.put("ten", 10);
        intByString.put("fifteen", 10);

        // получаем значения по ключу
        System.out.println(intByString.get("ten"));
        System.out.println(intByString.get("fifteen"));

        ///////////////////////////////////// Задача 2 //////////////////////////////////////////////
        // Пользователь вводит произвольную строку
        // Программа должна выполнить анализ строки и вывести по отдельности каждый символ данной строки
        //  и кол-во его вхождений в строку

        // порядок вывода не важен
        // информация для каждого символа выводиться без повторений

        // ввод: "hello world"

        // вывод:
        // 'h' - 1
        // 'e' - 1
        // 'l' - 3
        // 'o' - 2
        // ' ' - 1
        // 'w' - 1
        // 'r' - 1
        // 'd' - 1

        System.out.print("Enter a string: ");
        String str = sc.nextLine();

        // ключ - это символ, значение - кол-во повторений
        HashMap<Character, Integer> charRepeats = new HashMap<>();

        // нужно перевести строку в 'CharArray'
        for (Character ch : str.toCharArray()) {
            if (charRepeats.containsKey(ch)) {
                charRepeats.put(ch, charRepeats.get(ch) + 1);
            } else {
                charRepeats.put(ch, 1);
            }
        }

        // красивый вывод
        for (Map.Entry<Character, Integer> charRepeat : charRepeats.entrySet()) {
            System.out.printf("'%c' - %d\n", charRepeat.getKey(), charRepeat.getValue());
        }
    }
}
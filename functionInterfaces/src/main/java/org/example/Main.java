package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    // любая лямбда, любой колбек в Java сводится к обьекту интерфейса или абстрактного класса
    // функциональные интерфейсы (встроенные или нами обьявленные) - это интерфейсы
    // представляющие 1 обязательный метод для реализации
    // если там более олного, то один должен быть обязательным для реализации
    // а остальные должны иметь реализацию по умолчанию
    // интерфейсы позволяют использовать с ними лямбды и колбеки

    // deactivateUsers - деактивация пользователей (user.setActive(false))
    // проверяется для всех пользователей условие, и если оно выполняется, то деактировать такого пользователя
    public static void deactivateUsers(List<User> users, Predicate<User> condition) {
        throw new UnsupportedOperationException("implement me");
    }

    // extractUsers - извлечение пользователей из полученного списка по некоторому условию
    // все пользователи, для которых выполняется условие, должны быть удалены из полученного списка (параметр меняется)
    // и добавлены в новый список, который вернуть через return
    public static List<User> extractUsers(List<User> users, Predicate<User> condition) {
        throw new UnsupportedOperationException("implement me");
    }

    public static void main(String[] args) {
        // 1 // Predicate
        ArrayList<Integer> numbers = new ArrayList<>(List.of(111, 101, -1010, -12345, 0));
        System.out.println(("numbers: " + numbers));
        // передали предикат - удаляем все четные числа
        numbers.removeIf(number -> number % 2 == 0);
        System.out.println(("odd numbers: " + numbers));

        // 2 // forEach
        // интерфейс позволяет применить некоторые действия к эл-ам, но ничего не вычисляется
        numbers.forEach(number -> System.out.println(number + " "));

        // 3 // Задача
        List<User> users = generateUserList(10, 100, new Random());
        System.out.println("users:");
        // users.forEach(user -> System.out.println(user));
        // упрощенно:
        users.forEach(System.out::println);

        // ЗАДАНИЕ:
        // 1. реализовать тело метода deactivateUsers
        // 2. с помощью данного метода деактивировать всех пользователей у которых бонусный баланс меньше
        // среднего бонусного баланса среди всех пользователей
        // 3. вывести результат

        // 4. реализовать тело метода extractUsers
        // 5. с помощью данного метода извлечь всех деактивированных пользователей из исходного списка
        // вывести полученные списки (исходный с извлеченными деактивированными пользователями, и список деактивированных)

        // УСЛОВИЕ: в main без циклов, в процедурах использовать циклы с функциональными интерфейсами
    }

    private static List<User> generateUserList(int n, int maxBonusBalance, Random random) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int id = i + 1;
            String nickname = "user#1" + (i + 1);
            int bonusBalance = random.nextInt(maxBonusBalance+1);
            users.add(new User(id, nickname, bonusBalance));
        }
        return users;
    }
}

package org.top.finance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @Test
    public void testAbsolute() {
        // 'origin' - какое-либо исходное значение
        // 'expected' - ожидаемое значение
        // 'actual' - актуальное значение, полученное при выполнении операции

        // 1. подготовка тестирования
        int expected = 631;
        Money money = new Money(0, expected);

        // 2. непосредственно тестирование
        int actual = money.absolute();

        // 3. проверка результатов
        // 'assert' - в случае успеха пропускают тест,
        // а в случае не успеха - 'failed data test'
        // все находится в классе, который содержит статические проверки
        assertEquals(expected, actual);
    }

    @Test
    public void testSum() {
        // 'origin' - какое-либо исходное значение
        // 'expected' - ожидаемое значение
        // 'actual' - актуальное значение, полученное при выполнении операции

        // 1. подготовка тестирования
        Money origin1 = new Money(3, 70);
        Money origin2 = new Money(4, 50);
        int expected = 820;

        // 2. непосредственно тестирование
        Money res = origin1.sum(origin2);
        int actual = (int)res.absolute();

        // 3. проверка результатов
        // 'assert' - в случае успеха пропускают тест,
        // а в случае не успеха - 'failed data test'
        // все находится в классе, который содержит статические проверки
        assertEquals(expected, actual);
    }

    @Test
    public void testSub() {
        // 'origin' - какое-либо исходное значение
        // 'expected' - ожидаемое значение
        // 'actual' - актуальное значение, полученное при выполнении операции

        // 1. подготовка тестирования
        Money origin1 = new Money(3, 70);
        Money origin2 = new Money(4, 50);
        int expected = 80;

        // 2. непосредственно тестирование
        Money res = origin1.sub(origin2);
        int actual = (int)res.absolute();

        // 3. проверка результатов
        // 'assert' - в случае успеха пропускают тест,
        // а в случае не успеха - 'failed data test'
        // все находится в классе, который содержит статические проверки
        assertEquals(expected, actual);
    }

    @Test
    public  void testIncreaseWithRoundingToSmaller() {
        int origin = 60;
        int percent = 13;
        int expected = 67;

        Money money = new Money(0, origin);
        money = money.increaseToSmaller(percent);
        int actual = money.absolute();

        assertEquals(expected, actual);
    }

    @Test
    public  void testIncreaseWithRoundingToBigger() {
        int origin = 60;
        int percent = 13;
        int expected = 68;

        Money money = new Money(0, origin);
        money = money.increaseToBigger(percent);
        int actual = money.absolute();

        assertEquals(expected, actual);
    }
}
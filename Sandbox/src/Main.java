import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    //////////////////////////////////////////// Процедуры /////////////////////////////////////////////////////
    // процедур нет, процедурная парадигма реализована с помощью статических методов

    // что есть:
    // 1 // параметрические перегрузки процедуры/методов ('sum')
    public static int sum(int a, int b) {
        return a+b;
    }
    public static double sum(double a, double b) {
        return a+b;
    }
    // 2 // variadic args
    public static double sum(double a, double b, double ...other) {
        double result = a + b;
        for (double o : other) {
            result += o;
        }
        return result;
    }
    // вызов:
    // System.out.println(sum(10,20,30,40));

    // чего нет:
    // 1 // значения по умолчанию
    // 2 // именованные параметры при вызове функции
    // 3 // передача параметров по ссылке
    // только если передадим обьект класса - передастся ссылка на обьект

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Scanner sc = new Scanner((System.in));
        System.out.print(("Введите номер дня недели: "));
        int weekDayNumber = Integer.parseInt((sc.nextLine()));

        // сокращенный вариант switch
        String weekDay = switch (weekDayNumber) {
            case 1 -> "Понедельник";
            case 2 -> "Вторник";
            case 3 -> "Среда";
            default -> "Некорректный номер";
        };

        // цикл 'for'
        int[] ints = {10, 20, 15};
        for (int n : ints) {
            System.out.println(n);
        }

        int a = 5;
        for (int i = 0; i < a; i++) {
            System.out.println(("*"));
            a -= i;
        }

        // ///////////////////////////////////////// Строки ////////////////////////////////////////////////////
        // строка - это набор символов - ссылочный иммутабелььный тип
        String string1 = "Hello, world!";
        String string2 = "Hello, world!";

        // нельзя сравнить строки  (только по адресам)
        // if (string1 == string2)

        // сравниваем через 'equals' - лучше при работе со 'Scanner'
        String s1 = sc.nextLine();
        String s2 = "hello";

        if (s1.equals((s2))) {
            System.out.println("equals");
        } else {
            System.out.println("not equals");
        }

        // при вводе 'hello' на клавиатуре - строки 's1' и 's2' будут равны
        // 'StringBuilder' // 'StringBuffer'
        StringBuilder sb = new StringBuilder();
        int n = 5;
        for (int i = 0; i < n; i++) {
            sb.append(i);
        }

        // у строк есть куча методов, нет индексов
        // получение символа по методу
        char ch = s1.charAt(0);
        // можно то перебрать всю строку - ее можно получить в виде массива байт, массива чаров

        ///  /////////////////////////////////////// Массив /////////////////////////////////////////////////
        int[] arr = new int[5];

        Random random = new Random();
        // заполнение массива случайными элементами от 0 до 100
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(101);
        }

        // вывод элементов массива
        for (int item : arr) {
            System.out.print(item + " ");
        }

        // массивы передаются в функции по ссылке

        // многомерные массивы
        int[][] matrix = new int[5][];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new int[7];
        }

        for (int[] row : matrix) {
            for (int item : row) {
                System.out.print((item + ""));
            }
            System.out.println();
        }

    }
}
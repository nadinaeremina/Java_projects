import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner((System.in));

        // Задание 1 (стажер)
        // Пользователь вводит шестизначное число.
        // Оно считается счастливым если суммы первых трех цифр и последних трех равны.
        // Вывести информацию о том, является ли введенное число счастливым.

        do {
            System.out.print(("Введите шестизначное число: "));
            String str = sc.nextLine();
            if (str == null || str.isEmpty()) {
                System.out.println("Вы ничего не ввели!");
                continue;
            } else if (!str.matches("\\d+")) {
                System.out.println("Вы ввели не число!");
                continue;
            }
            else if (str.length() != 6) {
                System.out.println("Вы ввели не шестизначное число!");
                continue;
            }
            int index = 0, number1 = 0, number2 = 0;
            do {
                char charAtIndex = str.charAt(index);
                if (index < 3) {
                    number1 += Character.getNumericValue((charAtIndex));
                } else {
                    number2 += Character.getNumericValue((charAtIndex));
                }
                index++;
            } while (index <= 5);
            if (number1 == number2) {
                System.out.println("Число счастливое!");
            } else {
                System.out.println("Число несчастливое!");
            }
            break;
        } while (true);

        // Задание 2 (джун)
        // Пользователь вводит 3 стороны треугольника a, b,c
        // Сказать, существует ли такой треугольник (условие существования по сторонам)

        int a, b, c;
        do {
            System.out.print(("Введите первую сторону треугольника: "));
            String str2 = sc.nextLine();
            if (str2 == null || str2.isEmpty()) {
                System.out.println("Вы ничего не ввели!");
                continue;
            } else if (!str2.matches("\\d+")) {
                System.out.println("Вы ввели не число!");
                continue;
            }
            a = Integer.parseInt(str2);
            break;
        } while(true);
        do {
            System.out.print(("Введите вторую сторону треугольника: "));
            String str3 = sc.nextLine();
            if (str3 == null || str3.isEmpty()) {
                System.out.println("Вы ничего не ввели!");
                continue;
            } else if (!str3.matches("\\d+")) {
                System.out.println("Вы ввели не число!");
                continue;
            }
            b = Integer.parseInt(str3);
            break;
        } while(true);
        do {
            System.out.print(("Введите третью сторону треугольника: "));
            String str4 = sc.nextLine();
            if (str4 == null || str4.isEmpty()) {
                System.out.println("Вы ничего не ввели!");
                continue;
            } else if (!str4.matches("\\d+")) {
                System.out.println("Вы ввели не число!");
                continue;
            }
            c = Integer.parseInt(str4);
            break;
        } while(true);
        if (a + b > c && a + c > b && b + c > a) {
            System.out.print(("Треугольник существует!"));
        } else {
            System.out.println(("Треугольника не существует!"));
        }

        // Задание 3 (джун плюс плюс)
        // Пользователь вводит целое неотрицательное число.
        // Необходимо возвести число 2 в степень, равную этому числу.

        int pow, two = 2, result;
        do {
            System.out.print(("Введите целое положитльное число: "));
            String str5 = sc.nextLine();
            if (str5 == null || str5.isEmpty()) {
                System.out.println("Вы ничего не ввели!");
                continue;
            } else if (!str5.matches("\\d+")) {
                System.out.println("Вы ввели не число!");
                continue;
            }
            pow = Integer.parseInt(str5);
            result = (int)Math.pow(two, pow);
            System.out.println("2 в степени " + pow + " = " + result);
            break;
        } while (true);
    }
}
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static boolean isSimple(int number) {
        if (number <= 1) {
            return false;
        } else if (number == 2) {
            return true;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return true;
            }
        }
        return false;
    }
    public static int sum(int number) {
        int res = 0;
        while (number > 0) {
            res += number % 10;
            number /= 10;
        }
        return res;
    }
    public static int composition(int number) {
        int res = 1;
        while (number > 0) {
            res *= number % 10;
            number /= 10;
        }
        return res;
    }
    static int gcd(int a, int b)
    {
        while (b != 0)
        {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
    static int lcm(int a, int b)
    {
        return a / gcd(a, b) * b;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner((System.in));
        // 1 // Пользователь вводит число, необходимо определить, является ли введенное число простым.

        do {
            System.out.print(("Введите целое число: "));
            String str = sc.nextLine();
            if (str == null || str.isEmpty()) {
                System.out.println("Вы ничего не ввели!");
                continue;
            } else if (!str.matches("\\d+")) {
                System.out.println("Вы ввели не целое число!");
                continue;
            }
            int number = Integer.parseInt(str);
            if (isSimple(number)) {
                System.out.println("Число не является простым!");
            } else {
                System.out.println("Число является простым!");
            }
            break;
        } while (true);

        // 2 // Пользователь вводит целое число.
        // Программа должна посчитать сумму и произведение цифр данного числа.

        do {
            System.out.print(("Введите целое число: "));
            String str2 = sc.nextLine();
            if (str2 == null || str2.isEmpty()) {
                System.out.println("Вы ничего не ввели!");
                continue;
            } else if (!str2.matches("\\d+")) {
                System.out.println("Вы ввели не целое число!");
                continue;
            }
            int number = Integer.parseInt(str2);
            System.out.println("Сумма всех чисел данного числа равна " + sum(number));
            System.out.println("Произведение всех чисел данного числа равна " + composition(number));
            break;
        } while (true);

        // 3 // Пользователь вводит 2 числа.
        // Необходимо найти НОК и НОД двух этих чисел

        String str3, str4;
        do {
            System.out.print(("Введите первое число: "));
            str3 = sc.nextLine();
            if (str3 == null || str3.isEmpty()) {
                System.out.println("Вы ничего не ввели!");
                continue;
            } else if (!str3.matches("\\d+")) {
                System.out.println("Вы ввели не целое число!");
                continue;
            }
            break;
        } while (true);
        do {
            System.out.print(("Введите первое число: "));
            str4 = sc.nextLine();
            if (str4 == null || str4.isEmpty()) {
                System.out.println("Вы ничего не ввели!");
                continue;
            } else if (!str4.matches("\\d+")) {
                System.out.println("Вы ввели не целое число!");
                continue;
            }
            break;
        } while (true);
        int number1 = Integer.parseInt(str3);
        int number2 = Integer.parseInt(str4);
        System.out.println("Наибольшее число, которое является делителем одновременно для двух " +
                "этих чисел: " + gcd(number1, number2));
        System.out.println("Наименьшее число, которое делится на эти два числа без остатка " +
                + lcm(number1, number2));
    }
}
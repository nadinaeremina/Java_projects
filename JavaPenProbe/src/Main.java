import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Привет, мир!!");
        // 'println' - переносит строку, 'print' - не переносит

        int a = 10;
        int b = 15;
        System.out.println("a= " + a);
        // для примитивных типов

        Integer c = 10;
        System.out.println("b= " + c.toString());
        // для примитивных типов

        // Интерполяции нет, но есть форматирование строк
        // System.out.println(String.format());
        System.out.printf("a = %d", c);

        // SWAP (только так)
        int tmp = a;
        a = b;
        b = tmp;

        System.out.printf("a = %d; b = %d;\n", a, b);

        // 'Scanner' - обьект, который имплементирует работу с устройством ввода

        // обьект для ввода данных
        Scanner sc = new Scanner(System.in);
        // передаем поток, откуда сканер будет считывать данные

        System.out.print("Enter d: ");
        int d = sc.nextInt();
        // 'nextInt' - введет значение 'int'
        System.out.println("d = " + d);
        // есть проблема очистки входного потока

        System.out.print("Enter e: ");
        // лучше использовать 'nextLine()' - он считывает до переноса строки,
        // но считывает вместе с этим переносом строки
        int e = Integer.parseInt(sc.nextLine());
        // здесь мы считываем строку и конвертируем ее в 'int'
        System.out.println("e = " + e);

        System.out.print("Enter str: ");
        // лучше использовать 'nextLine()' - он считывает до переноса строки,
        // но считывает вместе с этим переносом строки
        String str = sc.nextLine();
        // здесь мы считываем строку и конвертируем ее в 'int'
        System.out.println("str = " + str);

        sc.close();



    }
}
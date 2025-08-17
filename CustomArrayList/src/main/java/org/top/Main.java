package org.top;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        CustomArrayList <String> list= new CustomArrayList<>();

        System.out.println("Добавление элемента по индексу");
        String str1 = "world";
        String str2 = "Hello";
        String str3 = "!";
        list.setElementByIndex(0, str2);
        list.setElementByIndex(1, str3);
        list.setElementByIndex(1, str1);
        System.out.println(list.toString());

        System.out.println("Получение элемента по индексу 1");
        System.out.println(list.getElementByIndex(1));

        System.out.println("Добавление элемента в конец массива");
        String obj = "!";
        list.addElement(obj);
        System.out.println(list.toString());

        System.out.println("Удаление элемента из конца массива");
        list.deleteEnd();
        System.out.println(list.toString());

        System.out.println("Уменьшение до 2");
        list.shrinkOrGrow(2);
        System.out.println(list.toString());

        System.out.println("Увеличение до 5");
        list.shrinkOrGrow(5);
        System.out.println(list.toString());
    }
}
package org.top;

import org.top.strings.CustomText;

public class Main {

    // наследование - отношение между классами, при котором класс-наследник (произодный(derived))
    // расщиряет функциональность базового класса собственными полями и методами (те членами)

    // если бы мы не написали static - у нас незакомпилилась бы программа
    // не создался бы в 'Main' экземпляр класса тогда, потому что класс 'Base' яв-ся вложенным
    // а вложенным мы его написали, чтобы не делать в отдельном файле
    // 'static' нужен для того, чтобы вложенный класс можно было исп-ть в статич методе
    // если бы метод был у нас нестатический, то можно было исп-ть нестатический класс в нем

    static class Base {
        // для теста с вызовом кон-ра по умолчанию
        // private int baseField;

        // для теста с вызовом кон-ра с пар-ми
        protected final int baseField;

        // кон-р по умолчанию
        public Base() {
            baseField = 0;
        }

        // кон-р с пар-ми
        public Base(int baseField) {
            this.baseField = baseField;
        }

        // для теста с вызовом кон-ра по умолчанию
        //public  void setBaseField(int value) {
            //baseField = value;
        //}

        public  void testBase() {
            System.out.println("baseField: " + baseField);
        }
    }

    // класс-наследник (множественного наследования нет)
    // наследоваться от класса с модификатором final мы не сможем
    static class Derived extends  Base {
        // для теста с вызовом кон-ра по умолчанию
        // private int derivedField;

        // для теста с вызовом кон-ра с пар-ми
        private final int derivedField;

        // кон-р по умолчанию
        public Derived() {
            derivedField = 0;
        }

        // кон-р с пар-ми
        public  Derived(int baseField, int derivedField) {
            // вызов кон-ра базового класса - обязательно в первой строчке
            // инициализировали поля наследуемого класса
            super(baseField);
            // инициализировали поля класса-наследника
            this.derivedField = derivedField;
        }

        // для теста с вызовом кон-ра по умолчанию
        // public void setDerivedField(int value) {
            // derivedField = value;
        //}

        public void testDerived() {
            // так не можем обратиться к полю, потому что оно private
            // если бы оно было protected, мы могли бы обратиться к нему напрямую
            // System.out.println("derivedField: " + baseField);

            // для теста с вызовом кон-ра по умолчанию
            // зато можем обратиться к публичному методу
            // testBase();

            // для теста с вызовом кон-ра с пар-ми
            System.out.println("baseField: " + baseField);
            System.out.println("derivedField: " + derivedField);
        }
    }

    public static void main(String[] args) {

        // для теста с вызовом кон-ра по умолчанию
        // baseField
        // Base base = new Base();
        // base.setBaseField(15);
        // base.testBase();

        // derivedfield
        // Derived d = new Derived();
        // есть доступ к методам наследуемого класса
        // и как следствие к его приватным полям через эти методы

        //d.setBaseField(10);
        //d.setDerivedField(20);
        // d.testBase();
        // d.testDerived();

        // для теста с вызовом кон-ра с пар-ми
        Derived d = new Derived(30, 40);
        d.testDerived();

        // Для чего нужно наследование?
        // механизм, через который можно исп-ть абстракции

        CustomText text = new CustomText("привет мир", " ");

        System.out.println(text.getTextToken(0)); // привет
        System.out.println(text.getTextToken(1)); // мир

        text.insertStr("hello world", 0);

        System.out.println(text.getTextToken(0)); // hello
        System.out.println(text.getTextToken(1)); // worldпривет
    }
}

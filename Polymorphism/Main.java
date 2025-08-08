package org.top;

import org.top.figures.Figure;
import org.top.figures.Rectangle;

import java.awt.print.Printable;

public class Main {

    // полиморфизм - многообразие форм - св-во чего-либо предоставлять единый интерфейс
    // для использования разных реализаций этого интерфейса без деталей реализации
    static class Base {
        public void doSomething() {
            System.out.println("[base] do something ...");
        }
    }

    interface Interface {
        // интерфейс - контракт между тем, кто использует тип интерфейса
        // и тем, кто реализует методы интерфейса
        // могут быть static-методы
        static void something() {

        }

        // реализация по-умолчанию - дает право реализовать не все методы интерфейса
        // чтобы не выкидывать ошибку из-за того, что не все методы реализуются
        default void someDefault() {

        }

        // а вообще интерфейс содержит только сигнатуры методов
        // а наследники должны переопределять эти методы
        void some();

        // все работате как с абстрактным классом - мы можем использовать ссылку типа Interface
        // в котором хранится какая-либо из имплементаций, какой-либо из наследников
        // который реализует методы этого интерфейса
        // тем самым абстрагируясь от деталей реализации этого самого наследника
        // интерфейсы более легковесные, в них нельзя обьявить поля, нельзя хранить состояния
        // совершать какие-либо действия с окружением - это по сути просто контракт
        // один класс может имплементировать несколько интерфейсов
        // Interface - по сути тот же абстрактный класс
        // Interface - набор методов, которые клиент может использовать, не вдаваясь в детали реализации
        // Интерфейсы позволяют более гибко создавать абстракции
    }

    static class Derived extends Base {
        // в Java принято писать Override - переопределение метода
        @Override
        public void doSomething() {
            // увидим выполнение метода базового класса
            // super - относится к классу, от которого мы наследуемся
            super.doSomething();

            // увидим выполнение метода класса-наследника
            System.out.println("[derived] do something ...");
        }
    }

    static void testFigure(Figure figure) {
        System.out.println("figure: " + figure);
        System.out.println("area: " + figure.area());
        System.out.println("perimeter: " + figure.perimeter());
    }

    static void testFigurePrint(Figure figure) {
        // не сможем вызвать этот метод у figure, потому что этот класс
        // не имплементирует никакой интерфейс
        // figure.print();
        // при привидении типов используем безопасный оператор instanceof
        // if (figure instanceof Printable) {
        // ((Printable) figure).print();
        // } else {
        // System.out.println("figure is not printable");
        // }
        // привели Figure к Printable и вызвали метод print()
    }

    public static void main(String[] args) {
        Base base = new Base();
        base.doSomething();

        Derived derived = new Derived();
        derived.doSomething();

        Base object = new Derived(); // позднее связывание
        object.doSomething(); // реализация Derived
        // здесь сработало переопределение метода

        testFigure(new Figure("UNKNOWN FIGURE"));
        testFigure(new Rectangle("ABCD", 3, 9.1));

        // ОКРУЖНОСТЬ, ТРЕУГОЛЬНИК, ТРАПЕЦИЯ, ПАРАЛЛЕЛОГРАММ, РОМБ, КВАДРАТ, ЭЛЛИПС - РЕАЛИЗОВАТЬ ЛЮБЫЕ 3 И ПРОТЕСТИРОВАТЬ ЧЕРЕЗ testFigure
    }
}
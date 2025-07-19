package org.top;

import org.top.figures.Figure;
import org.top.figures.Rectangle;

public class Main {

    static class Base {
        public void doSomething() {
            System.out.println("[base] do something ...");
        }
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

// полиморфизм - это многообразие форм,
// возм-ть исп-ия различных реализаций без деталей реализации
// через единый интерфейс
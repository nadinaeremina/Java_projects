package org.top.figures;

// так имплементируем интерфейс, ели передаем имплементацию дальше по цепочке
// то обьявляем класс абстрактным, иначе обязаны реализовать в этом классе
// public class Rectangle extends Figure implements Printable { }

public class Rectangle extends Figure {
    private double a;
    private double b;

    public Rectangle() {
        a = b = 0;
    }

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public Rectangle(String name, double a, double b) {
        super(name);
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    // ПЕРЕОПРЕДЕЛЕННЫЕ МЕТОДЫ БАЗОВОГО КЛАССА
    @Override
    public double area() {
        return a * b;
    }

    @Override
    public double perimeter() {
        return (a + b) * 2;
    }

    @Override
    public String toString() {
        return getName() + "(" + a + "; " + b + ")";
    }
    // к полю name напрямую обратиться не можем, потому что оно private
    // поэтому через метод getName()
}

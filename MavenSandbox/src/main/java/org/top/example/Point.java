package org.top.example;

// Point - точка на плоскости декартовой системы координат
public class Point {
    // поля
    private final String name;
    private double x;
    private double y;

    // конструкторы
    public Point() {
        this(0);
    }

    public Point(double value) {
        this(value, value);
    }

    public Point(double x, double y) {
        this("", x, y);
    }

    public Point(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    // getters&setters
    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    // методы

    public double norm() {
        return Math.sqrt(x * x + y * y);
    }

    // 1-ый операнд передается через this
    // 2-ой операнд передается через аргумент
    // результат возвращается в виде нового объекта через return
    public Point sum(Point p) {
        return new Point(name, x + p.x, y + p.y);
    }

    // оба операнда передаются через аргументы
    // результат возвращается в виде нового объекта через return
    public static Point sum(Point p1, Point p2) {
        return p1.sum(p2);
    }

    // 1-ый операнд передается через this
    // 2-ой операнд передается через аргумент
    // результат сохраняется в первый операнд
    public void add(Point p) {
        x += p.x;
        y += p.y;
    }

    // вычитание
    public Point sub(Point p) {
        return new Point(name, x - p.x, y - p.y);
    }

    // умножение
    public Point mult(Point p) {
        return new Point(name, x * p.x, y * p.y);
    }

    // умножение
    public static Point mult(Point p1, Point p2) {
        return p1.mult(p2);
    }

    // смена знака
    public Point change() {
        return new Point(name, - x, - y);
    }

    // деление двух точек
    public void devide(Point p) {
        x /= p.x;
        y /= p.y;
    }

    // переопределение toString
    @Override
    public String toString() {
        return String.format("%s(%.3f, %.3f)", name, x, y);
    }
}

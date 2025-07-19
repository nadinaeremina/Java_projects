package org.top.example;

// класс создаем в папке 'src' - new- JavaClass
// у классов могут быть модификаторы доступа: public и default
// можно написать либо public, либо ничего не писать

// модификатор public дает возможность этому классу быть доступным в др. пакетах
// если не писать модификатор доступа - он будет по умолчанию - default
// такой класс импортировать будет нельзя

// Point - точка на плоскости декартовой системы координат
public class Point {

    // у класса есть поля, методы, конструкторы и вложенные типы
    // (вложенные классы, рекорды и тд) и тп
    // модификаторы доступа: public, private, protected и default
    // public - позволяет получать доступ к члену класса как внутри класса, так и снаружи
    // private - запрещает снаружи получать доступ, а внутри он сохраняется
    // protected - разрешает доступ к полю внутри класса и из его наследников
    // в то время как к private полям наследники не имеют доступ
    // если доступ не писать - по умолчанию - default
    // если модификатор не указан - то для классов из этого же пакета члены класса будут доступны
    // а в не этого пакета - недоступны
    // а если private - то даже в одном и том же пакеик, но в другом классе поля не будут доступны

    // только так можно обьявить константу
    // final double PI = 3.14;
    // но это будет не совсем константа, а будет readonly
    // чтобы второй раз нельзя было инициализировать

    // поля
    private final String name;
    private double x;
    private double y;

    //////////////////////////////////// конструкторы ///////////////////////////////////////////////

    // конструктор по умолчанию - желателен
    public Point() {
        this(0);
    }

    public Point(double value) {
        this(value, value);
    }

    public Point(String name, double x, double y) {
        // this - это наш класс, который вызвал метод
        this.name = name;
        this.x = x;
        this.y = y;
    }

    // можно так
    // 1
    // public Point(double x, double y) {
        // name = "";
        // this.x = x;
        // this.y = y;
    // }

    // или так
    // 2
    // перегруженный кон-р (вызов другого кон-ра)
    public Point(double x, double y) {
        // вызов -кон-ра должен быть обязательно в первой строчке
        this("", x, y);
        // после вызова кон-ра может быть еще какая-то работа
    }

    // getters & setters

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
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

    // В Java есть статические поля и статические методы
    // они принадлежат не конкретному обьекту, а классу в целом
    // их можно вызывать без использования обьекта
    // статические поля и методы принадлежат классу вцелом
    // те одно значение поля на всеь класс
    // один метод на все эк-ры класса - к ним не обратиться через this
    // в статических методах можно исп-ть только статические поля
    // в java есть также и статические классы, например: Math
    // static class в Java - это вложенный класс,
    // который можно исп-ть в статич. контексте - static-методы

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
    // принято указывать - Override
    @Override
    public String toString() {
        return String.format("%s(%.3f, %.3f)", name, x, y);
    }
}

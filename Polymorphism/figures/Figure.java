package org.top.figures;

// Figure - геометрическая фигура
// public abstract class Figure - класс, экземпляр котрого нельзя создать
// но можно использовать ссылки типа этого класса
// это такой же класс, у котрого есть методы
public class Figure {
    private final String name;

    public Figure() {
        name = "";
    }

    public Figure(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // методы базового класса
    // если класс абстратный - то эти методы у него будут тоже абстрактными
    // public abstract double area() { return 0; }
    public double area() {
        return 0;
    }

    // public abstract double perimeter() { return 0; }
    public double perimeter() {
        return 0;
    }

    // абстрактные методы у экземпляра 'Figure' мы вызвать не сможем
    // в классе-наследнике мы эти методы должны переопределить
    // а если мы этого не сделаем - можно класс-наследник пометить
    // тоже абстрактным и сказать, что у него будет класс-наследник,
    // который переопределит этот метод

    @Override
    public String toString() {
        return name;
    }
}

// абстракция - понятие некотрого предмета, в котором сохранены существенные,
// значимые в данной ситуации признаки, а незначимые признаки не рассматриваются

package org.top.figures;

// Figure - геометрическая фигура
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
    public double area() {
        return 0;
    }

    public double perimeter() {
        return 0;
    }

    @Override
    public String toString() {
        return name;
    }
}

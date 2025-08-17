package org.top.points;

import java.util.Iterator;
import java.util.List;

// на класс Point навешиваем компоратор и укажем generic
public class Point implements Comparable<Point>, Iterable<Double> {
    private String name;
    private double x;
    private double y;

    public Point(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double norm() {
        return Math.sqrt(x * x + y * y);
    }

    // после того, как навешали компоратор - нужно переопределить метод
    @Override
    public int compareTo(Point other) {
        double thisNorm = norm();
        double otherNorm = other.norm();
        return Double.compare(thisNorm, otherNorm);
    }

    @Override
    public Iterator<Double> iterator() {
        return List.of(x, y).iterator();
    }

    @Override
    public String toString() {
        return String.format("%s(%.2f, %.2f)", name, x, y);
    }
}

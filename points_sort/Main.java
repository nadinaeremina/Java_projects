package org.top;


import org.top.points.ByCoordsComparator;
import org.top.points.Point;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Point[] points = {
            new Point("P1", 1, 1),
            new Point("P2", 0.78, 3),
            new Point("P3", -6.3, 7.31),
            new Point("P4", 1.18, 2),
            new Point("P5", 0.33, -1),
        };
        System.out.println("points: " + Arrays.toString(points));

        Arrays.sort(points);
        System.out.println("points sorted by norm: " + Arrays.toString(points));

        // ЗАДАЧА: выполнить сортировку точек по сумме координат не меняя существующий компоратор
        Comparator<Point> comparator = new ByCoordsComparator();
        Arrays.sort(points, comparator);
        System.out.println("points sorted by cords sum: " + Arrays.toString(points));

        // Iterable<Double> - переберем нашу точку описанным образом
        Point p = new Point("P", 10, 15);
        for (double cord : p) {
            System.out.println(cord);
        }
    }
}

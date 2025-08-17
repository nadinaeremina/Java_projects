package org.top.points;

import java.util.Comparator;

// класс, который используется для того, чтобы в нем описать правила сравнения обьектов
// имплементируем интерфейс Comparator
public class ByCoordsComparator implements Comparator<Point> {

    // переопределяем лишь один метод
    @Override
    public int compare(Point o1, Point o2) {
        double o1Sum = o1.getX() + o1.getY();
        double o2Sum = o2.getX() + o2.getY();
        return Double.compare(o1Sum, o2Sum);
    }
}

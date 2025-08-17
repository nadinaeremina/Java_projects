package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        String[] names = new String[]{"KIA", "MAZDA", "LEXUS", "AUDI", "PORSСHE", "HYUNDAI", "TOYOTA"};
        int n = 10, minPrice = 500, maxPrice = 2000, minPower = 70, maxPower = 220;
        Random random = new Random();

        //Создать список автомобилей, заполнить его автомобилями с произвольными данными
        ArrayList<Car> cars = Stream.generate(() ->
                        new Car(names[random.nextInt(0, names.length)],
                                random.nextInt(minPrice, maxPrice) * 1000L,
                                random.nextInt(minPower, maxPower)))
                .limit(n)
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println("All cars");
        System.out.println(cars);

        //Сделать выборку автомобилей ценой более 1 000 000 руб.
        ArrayList<Car> expensiveCars = cars.stream()
                .filter(car -> car.price > 1000000)
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println("Expensive cars more then 1 000 000");
        System.out.println(expensiveCars);

        //Посчитать кол-во автомобилей с ценой выше средней цены по всем автомобилям.
        int countMoreThenAverage = cars.stream()
                .filter(car -> car.price > cars.stream().mapToLong(Car::getPrice)
                        .summaryStatistics()
                        .getAverage()).toArray().length;
        System.out.println("Count cars more then average price: " + countMoreThenAverage);

        //Получить новый список автомобилей из исходного списка,
        // в котором увеличить цену каждого автомобиля на 20% от исходной цены этого автомобиля.
        ArrayList<Car> afterIncrease = cars.stream().map(car -> new Car(car.model,
                        car.price + (int) (car.price * 0.2), car.power))
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println("Cars after increase");
        System.out.println(afterIncrease);

        //Сгруппировать (group by) автомобили по мощности в группы до 95 л.с., 95 – 130 л.с., более 130 л.с
        Map<String, List<Car>> carsGroupBy = cars.stream().collect(Collectors.groupingBy(car -> {
            int power = car.getPower();
            if (power < 95) return "Менее 95 л.с.";
            else if (power <= 130) return "95 – 130 л.с.";
            else return "Более 130 л.с.";
        }));
        System.out.println(carsGroupBy);

        //Вывести список автомобилей, отсортированный по цене
        System.out.println("Sorted price \n" + cars.stream()
                .sorted(Comparator.comparingLong(Car::getPrice))
                .toList());

        //Вывести список автомобилей, отсортированный по мощности
        System.out.println("Sorted power \n" + cars.stream()
                .sorted(Comparator.comparingLong(Car::getPower))
                .toList());

        //Полученные ранее группы автомобилей по мощности соединить (join
        // в один список с автомобилями стоимостью до 1 000 000 руб
        String filteredAndJoin = carsGroupBy.entrySet().stream()
                .map(entry -> entry.getKey() + ": " +
                        entry.getValue().stream()
                                .filter(car -> car.getPrice() < 1000000)
                                .map(Car::toString)
                                .collect(Collectors.joining(", "))
                )
                .filter(s -> !s.endsWith(": ")) // Убираем пустые группы
                .collect(Collectors.joining("\n"));
        System.out.println("Filter and join cars \n" +filteredAndJoin);
    }
}
package org.top;

import java.util.Arrays;

public class CustomArrayList<T> {
    public T[] list;

    public CustomArrayList() {
        // создание пустого массива заданного типа T.
        // Это часто встречается в коде, использующем дженерики,
        // когда необходимо вернуть массив,
        // но нет возможности определить конкретный тип заранее.
        list = (T[]) new Object[0];
    }

    public void setList(T[] list) {
        this.list = list;
    }

    public T[] getList() {
        return list;
    }

    public int getSize() {
        return list.length;
    }

    // установка элемента по индексу
    public void setElementByIndex(int index, T object) {
        if (index > list.length || index < 0) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + list.length);
        }

        // создаем новый массив
        Object[] newList = new Object[list.length + 1];

        // Копируем элементы до индекса вставки
        System.arraycopy(list, 0, newList, 0, index);

        // Вставляем новый элемент
        newList[index] = object;

        // Копируем оставшиеся элементы
        System.arraycopy(list, index, newList, index + 1, list.length - index);

        // обновляем данные в нашем массиве
        list = (T[]) newList;
    }

    // получение элемента по индексу
    public Object getElementByIndex(int index) {
        if (index > list.length || index < 0) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + list.length);
        }
        return list[index];
    }

    // добавление элемента в конец массива
    public void addElement(T object) {
        // создаем новый массив
        Object[] newList = Arrays.copyOf(list, list.length + 1);

        // добавляем наш элемент
        newList[list.length] = object;

        // обновляем наш массив
        list = (T[]) newList;
    }

    public void deleteElementByIndex(int index) {

    }

    // удаление элемента из конца массива
    public void deleteEnd() {
        deleteElementByIndex(list.length - 1);

        if (list.length == 0) {
            throw new IndexOutOfBoundsException("Лист пустой!");
        }

        // создаем новый массив
        Object[] newList = new Object[list.length - 1];

        // Копируем элементы до индекса
        System.arraycopy(list, 0, newList, 0, list.length - 1);

        // возвращаем новый лист
        list = (T[]) newList;
    }

    // уменьшение емкости массива до текущего размера
    public void shrinkOrGrow(int newLenght) {
        if (newLenght < 0) {
            throw new IllegalArgumentException("Длина не может быть отрицательной");
        }
        list = Arrays.copyOf(list, newLenght);
    }

    @Override
    public String toString() {
        return Arrays.toString(list);
    }
}

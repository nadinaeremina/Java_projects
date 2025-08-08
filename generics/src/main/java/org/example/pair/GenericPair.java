package org.example.pair;

import java.util.List;

// generic позволяют параметризовать обобщенный код
// есть контроль безопасности типов
// фиксирует тип для каждого отдельного обьекта
public class GenericPair<F, S> {
    private F first;
    private S second;

    public GenericPair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public S getSecond() {
        return second;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    public Object getByIndex(int i) {
        return switch (i) {
            case 0 -> first;
            case 1 -> second;
            default -> throw new IndexOutOfBoundsException();
        };
    }

    public void setByIndex(int i, Object value) {
        switch (i) {
            case 0:
                first = (F)value;
                break;
            case 1:
                second = (S)value;
                break;
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}

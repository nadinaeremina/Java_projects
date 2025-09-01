package org.top.finance;

// Money - денежное значение, хранящее данные в целочисленном виде
public class Money {
    private int mainPart;   // основная часть - рубли
    private int subPart;    // не основная часть - копейки

    public Money() {
        this(0, 0);
    }

    public Money(int mainPart) {
        this(mainPart, 0);
    }

    public Money(int mainPart, int subPart) {
        if (mainPart < 0 || subPart < 0) {
            throw new IllegalArgumentException("negative money are not allowed");
        }
        this.mainPart = mainPart;
        this.subPart = subPart;
        normalize();
    }

    // getters&setters
    public int getMainPart() {
        return mainPart;
    }

    public void setMainPart(int mainPart) {
        this.mainPart = mainPart;
    }

    public int getSubPart() {
        return subPart;
    }

    public void setSubPart(int subPart) {
        this.subPart = subPart;
    }

    // методы
    // получение абсолютной величины
    public int absolute() {
        // 2 руб. 20 коп. -> 220 абс. ед.
        return mainPart * 100 + subPart;
    }

    // сложить 2 денежки
    public Money sum(Money other) {
        int mainPartAbsolut = this.absolute();
        int otherPartAbsolut = other.absolute();
        int sum = mainPartAbsolut + otherPartAbsolut;
        int main = sum / 100;
        int part = sum % 100;
        return new Money(main, part);
    }

    // sub - отнять от текущей денежки другую - разность по модулю
    // не важно кто кого вызвал, всегда отнимается от большей денежки меньшая
    public Money sub(Money other) {
        int mainPartAbsolut = this.absolute();
        int otherPartAbsolut = other.absolute();
        int sub;
        if (mainPartAbsolut >= otherPartAbsolut) {
            sub = mainPartAbsolut - otherPartAbsolut;
        } else {
            sub = otherPartAbsolut - mainPartAbsolut;
        }
        int main = sub / 100;
        int part = sub % 100;
        return new Money(main, part);
    }

    // increase - увеличить текущую денежку на заданный процент
    // 2 руб 50 коп. увеличить на 20% то получим 3 руб.
    // округление в меньшую сторону
    public Money increaseToSmaller(int percent) {
        // посчитаем, сколько надо добавить
        double toIncrease = Math.floor(this.absolute() / 100.0 * percent);
        return new Money(mainPart, (int)(subPart + toIncrease));
    }

    // increase - увеличить текущую денежку на заданный процент
    // 2 руб 50 коп. увеличить на 20% то получим 3 руб.
    // округление в большую сторону
    public Money increaseToBigger(int percent) {
        // посчитаем, сколько надо добавить
        double toIncrease = Math.ceil(this.absolute() / 100.0 * percent);
        return new Money(mainPart, (int)(subPart + toIncrease));
    }

    private void normalize() {
        // 2 руб. 520 коп. -> 7 руб. 20 коп.
        this.mainPart += this.subPart / 100;
        this.subPart %= 100;
    }

    @Override
    public String toString() {
        return String.format("%d руб. %d коп.", mainPart, subPart);
    }
}

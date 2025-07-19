package org.example;

public class Fraction {
    // знаменатель
    private int denominator;
    // числитель
    private int numerator;
    // целое число
    private int integer;
    // знак дроби
    private boolean sign = true;

    // кон-р по умолчанию
    public Fraction() {
        this(0, 1);
    }

    // кон-ры с парам-ми
    public Fraction(int numerator, int denominator) {
        this(0, numerator, denominator);
    }

    public Fraction(int integer, int numerator, int denominator) {
        this(integer, numerator, denominator, true);
    }

    public Fraction(int integer, int numerator, int denominator, boolean sign) {
        if (denominator != 0) {
            this.integer = integer;
            this.numerator = numerator;
            this.denominator = denominator;
            if (integer < 0) {
                this.sign = false;
                integer *= -1;
                if (numerator < 0) {
                    numerator *= -1;
                }
                if (denominator < 0) {
                    denominator *= -1;
                }
            } else if (denominator < 0) {
                this.sign = false;
                denominator *= -1;
            } else if (numerator < 0) {
                this.sign = false;
                this.numerator *= -1;
            } else {
                this.sign = sign;
            }
        } else {
            this.integer = 0;
            this.numerator = 0;
            this.denominator = 1;
        }
    }

    // getters & setters
    public int getDenominator() {
        return denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getInteger() {
        return integer;
    }

    public boolean isSign() {
        return sign;
    }

    public void setDenominator(int denominator) {
        if (denominator > 0 ) {
            this.denominator = denominator;
        }
    }

    public void setNumerator(int numerator) {
        if (numerator >= 0 ) {
            this.numerator = numerator;
        }
    }

    public void setInteger(int integer) {
        this.integer = integer;
    }

    public void setSign(boolean sign) {
        this.sign = sign;
    }

    /////////////////////////////////////// методы //////////////////////////////////////////

    // изменение знака
    public void changeSign() {
        this.sign = !this.sign;
    }

    // сложение
    // нестатический
    public Fraction sum(Fraction f) {
        f.ToImproper();
        this.ToImproper();
        int commonDenominator;
        int newNumOurFrac;
        int newNumIntoFrac;
        if (this.denominator != f.denominator) {
            // поиск общего знаменателя
            commonDenominator = this.searchCommonDenominator(f);
            // создание нового числителя для нашей дроби
            newNumOurFrac = this.createNewNumerator(commonDenominator);
            // создание нового числителя для приходящей дроби
            newNumIntoFrac = f.createNewNumerator(commonDenominator);
        } else {
            commonDenominator = denominator;
            newNumOurFrac = this.numerator;
            newNumIntoFrac = f.numerator;
        }
        if (!sign && !f.sign) {
            return new Fraction(0,newNumOurFrac + newNumIntoFrac, commonDenominator, false);
        } else if (!sign) {
            return new Fraction(newNumIntoFrac - newNumOurFrac, commonDenominator);
        } else if (!f.sign){
            return new Fraction(newNumOurFrac - newNumIntoFrac, commonDenominator);
        }
        return new Fraction(newNumOurFrac + newNumIntoFrac, commonDenominator);
    }

    // статический
    public static Fraction sum(Fraction f1, Fraction f2) {
        return f1.sum(f2);
    }

    // вычитание
    public Fraction sub(Fraction f) {
        f.ToImproper();
        this.ToImproper();
        int commonDenominator;
        int newNumOurFrac;
        int newNumIntoFrac;
        if (this.denominator != f.denominator) {
            // поиск общего знаменателя
            commonDenominator = this.searchCommonDenominator(f);
            // создание нового числителя для нашей дроби
            newNumOurFrac = this.createNewNumerator(commonDenominator);
            // создание нового числителя для приходящей дроби
            newNumIntoFrac = f.createNewNumerator(commonDenominator);
        } else {
            commonDenominator = denominator;
            newNumOurFrac = this.numerator;
            newNumIntoFrac = f.numerator;
        }
        if (!sign && !f.sign) {
            return new Fraction(0,newNumIntoFrac - newNumOurFrac, commonDenominator, false);
        } else if (!sign) {
            return new Fraction(0,newNumIntoFrac + newNumOurFrac, commonDenominator, false);
        } else if (!f.sign){
            return new Fraction(0,newNumOurFrac + newNumIntoFrac, commonDenominator, true);
        }
        return new Fraction(newNumOurFrac - newNumIntoFrac, commonDenominator);
    }

    // умножение
    public Fraction multip(Fraction f) {
        f.ToImproper();
        this.ToImproper();
        if (!sign && !f.sign) {
            return new Fraction(0,numerator * f.numerator, denominator * f.denominator, true);
        } else if (!sign || !f.sign) {
            return new Fraction(0,numerator * f.numerator, denominator * f.denominator, false);
        }
        return new Fraction(numerator * f.numerator, denominator * f.denominator);
    }

    // деление
    public Fraction div(Fraction f) {
        f.ToImproper();
        this.ToImproper();
        if ((!sign && !f.sign) || (sign && f.sign)) {
            return new Fraction(0,numerator * f.denominator, denominator * f.numerator, true);
        }
        return new Fraction(0,numerator * f.denominator, denominator * f.numerator, false);
    }

    // выделение целой части с ее возвратом из метода и удалением из дроби
    public void ToProper()
    {
        if (numerator >= denominator) {
            integer = numerator / denominator;
            numerator = numerator % denominator;
        }
    }

    // переводим в неправильную дробь
    public void ToImproper()
    {
        if (integer != 0) {
            numerator = integer * denominator + numerator;
        }
    }

    // сокращение дроби
    public void ToReduction()
    {
        int divider = gcd(denominator, numerator);
        if (divider != 1) {
            numerator = numerator / divider;
            denominator = denominator / divider;
        }
    }

    // поиск общего знаменателя
    public int searchCommonDenominator(Fraction f)
    {
        return lcm(denominator, f.denominator);
    }

    // создание нового числителя
    public int createNewNumerator(int commonDenominator)
    {
        // находим новый числитель
        return commonDenominator / this.denominator * this.numerator;
    }

    // ищем НОД
    public int gcd(int a, int b)
    {
        while (b != 0)
        {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    // ищем НОК
    public int lcm(int a, int b)
    {
        return a / gcd(a, b) * b;
    }

    @Override
    public String toString() {
        this.ToReduction();
        this.ToProper();
        if (integer != 0) {
            if (numerator != 0) {
                if (!sign) {
                    return String.format("-%d(%d/%d)", integer, numerator, denominator);
                }
                return String.format("%d(%d/%d)", integer, numerator, denominator);
            }
            if (!sign) {
                return String.format("-%d", integer);
            }
            return String.format("%d", integer);
        }
        if (!sign) {
            return String.format("-%d/%d", numerator, denominator);
        }
        return String.format("%d/%d", numerator, denominator);
    }
}
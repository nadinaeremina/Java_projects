package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Fraction f1 = new Fraction(13, 4);
        Fraction f2 = new Fraction(2, 3, 4);
        Fraction f3 = new Fraction( 2,7);
        Fraction f4 = new Fraction(0,-3,4);
        Fraction f5 = new Fraction( 2,4);
        Fraction f6 = new Fraction(4,5);
        Fraction f7 = new Fraction( 3,5);
        Fraction f8 = new Fraction(-4,9);
        Fraction f9 = new Fraction( -3,5);
        Fraction f10 = new Fraction(4,9);

        System.out.println("f1: " + f1);
        System.out.println("f2: " + f2);
        System.out.println("f3: " + f3);
        System.out.println("f4: " + f4);
        System.out.println("f5: " + f5);
        System.out.println("f6: " + f6);
        System.out.println("f7: " + f7);
        System.out.println("f8: " + f8);
        System.out.println("f9: " + f9);
        System.out.println("f10: " + f10);

        Fraction sum1 = f1.sum(f2);
        Fraction sum2 = f3.sum(f4);
        Fraction sub = f9.sub(f10);
        Fraction multip = f5.multip(f6);
        Fraction div = f7.div(f8);

        System.out.println("f1+f2: " + sum1);
        System.out.println("f3+f4: " + sum2);
        System.out.println("f9-f10: " + sub);
        System.out.println("f5*f6: " + multip);
        System.out.println("f7/f8: " + div);

        multip.changeSign();
        div.changeSign();

        System.out.println("div change sign: " + multip);
        System.out.println("div change sign: " + div);
    }
}
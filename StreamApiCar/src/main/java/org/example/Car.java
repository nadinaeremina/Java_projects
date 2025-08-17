package org.example;

public class Car {
    public String model;
    public long price;
    public int power;

    public Car(String model, long price, int power) {
        this.model = model;
        this.price = price;
        this.power = power;
    }

    public String getModel() {
        return model;
    }

    public long getPrice() {
        return price;
    }

    public int getPower() {
        return power;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return model + " - " + price + " rub. - " + power + " hp.";
    }
}

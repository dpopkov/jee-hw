package ru.otus.dpopkov.json.model;

public class Car {
    private String mark;
    private String model;
    private double price;
//    private transient Owner owner;
    private Owner owner;

    public Car() {
    }

    public Car(String mark, String model, double price) {
        this.mark = mark;
        this.model = model;
        this.price = price;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}

package ru.job4j.ood.srp.parkinglot;

public class Car implements Vehicle {

    private final String name;
    private static final int SIZE = 1;

    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return SIZE;
    }
}

package ru.job4j.ood.srp.parkinglot;

public class Truck implements Vehicle {

    private final String name;
    private final int size;

    public Truck(String name, int size) {
        if (size < 2) {
            throw new IllegalArgumentException("The size of a truck cannot be smaller than 2.");
        }
        this.size = size;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }
}

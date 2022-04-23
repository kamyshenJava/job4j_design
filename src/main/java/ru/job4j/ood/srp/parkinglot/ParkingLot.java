package ru.job4j.ood.srp.parkinglot;

public interface ParkingLot {

    boolean accept(Vehicle vehicle);

    void release(Vehicle vehicle);
}

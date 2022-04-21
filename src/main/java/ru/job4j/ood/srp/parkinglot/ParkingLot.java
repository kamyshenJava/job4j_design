package ru.job4j.ood.srp.parkinglot;

public interface ParkingLot {

    void accept(Vehicle vehicle);

    void release(Vehicle vehicle);
}

package ru.job4j.ood.srp.parkinglot;

public interface ParkingLot {

    boolean accept(Vehicle vehicle);

    boolean release(Vehicle vehicle);
}

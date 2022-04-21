package ru.job4j.ood.srp.parkinglot;

public interface ParkingManager {

    boolean manageParking(ParkingLot parkingLot, Vehicle vehicle);

    void manageLeaving(ParkingLot parkingLot, Vehicle vehicle);
}

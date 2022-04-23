package ru.job4j.ood.srp.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSimple implements ParkingLot {

    private final int parkingPlacesCar;
    private final int parkingPlacesTruck;
    private List<Vehicle> parkedCars = new ArrayList<>();
    private List<Vehicle> parkedTrucks = new ArrayList<>();
    private int carCount;
    private int truckCount;

    public ParkingLotSimple(int parkingPlacesCar, int parkingPlacesTruck) {
        if (parkingPlacesTruck < 2) {
            throw new IllegalArgumentException("A parking lor for trucks cannot be smaller than 2.");
        }
        this.parkingPlacesCar = parkingPlacesCar;
        this.parkingPlacesTruck = parkingPlacesTruck;
    }

    @Override
    public boolean accept(Vehicle vehicle) {
        boolean accepted = false;
        int size = vehicle.getSize();
        if (size == 1 && carCount < parkingPlacesCar) {
            accepted = true;
            parkedCars.add(vehicle);
            carCount++;
        }
        if (size > 1) {
            if (parkingPlacesTruck - truckCount >= size) {
                accepted = true;
                parkedTrucks.add(vehicle);
                truckCount += size;
            } else if (parkingPlacesCar - carCount >= size) {
                accepted = true;
                parkedCars.add(vehicle);
                carCount += size;
            }
        }
        return accepted;
    }

    @Override
    public void release(Vehicle vehicle) {
        int size = vehicle.getSize();
        if (parkedCars.remove(vehicle)) {
            carCount -= size;
        }
        if (parkedTrucks.remove(vehicle)) {
            truckCount -= size;
        }
    }

    public int getCarCount() {
        return carCount;
    }

    public int getTruckCount() {
        return truckCount;
    }
}

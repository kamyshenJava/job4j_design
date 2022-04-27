package ru.job4j.ood.srp.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSimple implements ParkingLot {

    private final int parkingPlacesCar;
    private final int parkingPlacesTruck;
    private List<Vehicle> parkedCars;
    private List<Vehicle> parkedTrucks;
    private int carCount;
    private int truckCount;


    public ParkingLotSimple(int parkingPlacesCar, int parkingPlacesTruck) {
        this.parkingPlacesCar = parkingPlacesCar;
        this.parkingPlacesTruck = parkingPlacesTruck;
        parkedCars = new ArrayList<>(parkingPlacesCar);
        parkedTrucks = new ArrayList<>(parkingPlacesTruck);
    }

    @Override
    public boolean accept(Vehicle vehicle) {
        boolean accepted = false;
        int size = vehicle.getSize();
        if (size == Car.SIZE && carCount < parkingPlacesCar) {
            accepted = true;
            parkedCars.add(vehicle);
            carCount++;
        } else if (size > Car.SIZE && parkingPlacesTruck > truckCount) {
                accepted = true;
                parkedTrucks.add(vehicle);
                truckCount++;
            } else if (size > Car.SIZE && parkingPlacesCar - carCount >= size) {
                accepted = true;
                parkedCars.add(vehicle);
                carCount += size;
            }
        return accepted;
    }

    @Override
    public boolean release(Vehicle vehicle) {
        boolean released = false;
        int size = vehicle.getSize();
        if (parkedCars.remove(vehicle)) {
            carCount -= size;
            released = true;
        } else if (parkedTrucks.remove(vehicle)) {
            truckCount--;
            released = true;
        }
        return released;
    }

    public int getCarCount() {
        return carCount;
    }

    public int getTruckCount() {
        return truckCount;
    }
}

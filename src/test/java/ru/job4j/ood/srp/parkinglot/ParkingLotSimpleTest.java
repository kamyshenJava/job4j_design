package ru.job4j.ood.srp.parkinglot;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingLotSimpleTest {

    @Test
    public void acceptCar() {
        Vehicle car = new Car("BMW");
        ParkingLotSimple pl = new ParkingLotSimple(1, 2);
        pl.accept(car);
        assertEquals(1, pl.getCarCount());
    }

    @Test
    public void acceptTruck() {
        Vehicle truck = new Truck("BMW", 2);
        ParkingLotSimple pl = new ParkingLotSimple(1, 1);
        pl.accept(truck);
        assertEquals(1, pl.getTruckCount());
    }

    @Test
    public void whenNoPlacesForCarsThenNoChange() {
        Vehicle car = new Car("BMW");
        Vehicle car1 = new Car("Volvo");
        ParkingLotSimple pl = new ParkingLotSimple(1, 2);
        pl.accept(car);
        pl.accept(car1);
        assertEquals(1, pl.getCarCount());
    }

    @Test
    public void whenNoPlacesForTruckThenCarPlaceTaken() {
        Vehicle truck = new Truck("BMW", 2);
        Vehicle truck1 = new Truck("Volvo", 2);
        ParkingLotSimple pl = new ParkingLotSimple(2, 1);
        pl.accept(truck);
        pl.accept(truck1);
        assertEquals(1, pl.getTruckCount());
        assertEquals(2, pl.getCarCount());
    }

    @Test
    public void releaseCar() {
        Vehicle car = new Car("BMW");
        ParkingLotSimple pl = new ParkingLotSimple(1, 2);
        pl.accept(car);
        pl.release(car);
        assertEquals(0, pl.getCarCount());
    }

    @Test
    public void releaseTruck() {
        Vehicle truck = new Truck("BMW", 2);
        ParkingLotSimple pl = new ParkingLotSimple(1, 2);
        pl.accept(truck);
        pl.release(truck);
        assertEquals(0, pl.getTruckCount());
    }

    @Test
    public void releaseTruckFromCarPlace() {
        Vehicle truck = new Truck("BMW", 3);
        Vehicle truck1 = new Truck("Volvo", 2);
        ParkingLotSimple pl = new ParkingLotSimple(2, 1);
        pl.accept(truck);
        pl.accept(truck1);
        pl.release(truck1);
        assertEquals(0, pl.getCarCount());
        assertEquals(1, pl.getTruckCount());
    }
}
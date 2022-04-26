package ru.job4j.ood.dip;

public class Divider {

    public double divide(double x, double y) {
        if (y == 0) {
            System.out.println("Нельзя делить на ноль");
            throw new IllegalArgumentException();
        }
        return x / y;
    }
}

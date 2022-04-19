package ru.job4j.ood.lsp;

public class Plane {

    String name;
    int speed;

    public void fly(int speed) {
        if (speed < 200) {
            throw new IllegalArgumentException("Too slow");
        }
        System.out.println("Let's fly");
    }
}

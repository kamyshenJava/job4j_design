package ru.job4j.ood.lsp;

public class ToyPlane extends Plane {

    @Override
    public void fly(int speed) {
        if (speed > 500) {
            throw new IllegalArgumentException("Too fast");
        }
        super.fly(speed);
    }
}

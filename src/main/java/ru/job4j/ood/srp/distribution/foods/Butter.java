package ru.job4j.ood.srp.distribution.foods;

import java.time.LocalDate;

public class Butter extends Food {
    public Butter(String name, LocalDate expiryDate, LocalDate createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}

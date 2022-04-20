package ru.job4j.ood.srp.distribution.storage;

import ru.job4j.ood.srp.distribution.foods.Food;

import java.time.LocalDate;

public class Trash extends Storage {

    @Override
    public boolean ifFoodFitsStorage(Food food, LocalDate date) {
        boolean accept = false;
        LocalDate expireDate = food.getExpiryDate();
        LocalDate createDate = food.getCreateDate();

        if (daysBetween(createDate, date) > (daysBetween(createDate, expireDate))) {
            accept = true;
        }
        return accept;
    }
}

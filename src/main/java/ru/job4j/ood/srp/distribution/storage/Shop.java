package ru.job4j.ood.srp.distribution.storage;

import ru.job4j.ood.srp.distribution.foods.Food;

import java.time.LocalDate;

public class Shop extends Storage {

    @Override
    public boolean accept(Food food, LocalDate date) {
        boolean accepted = super.accept(food, date);
        if (accepted) {
            LocalDate expireDate = food.getExpiryDate();
            LocalDate createDate = food.getCreateDate();
            if (daysBetween(createDate, date) > (daysBetween(createDate, expireDate)) * 0.75) {
                food.setPrice(food.getPrice() * (1 - food.getDiscount() / 100));
            }
        }
        return accepted;
    }

    @Override
    public boolean ifFoodFitsStorage(Food food, LocalDate date) {
        boolean accept = false;
        LocalDate expireDate = food.getExpiryDate();
        LocalDate createDate = food.getCreateDate();

        if (daysBetween(createDate, date) <= (daysBetween(createDate, expireDate))
                && daysBetween(createDate, date) >= (daysBetween(createDate, expireDate)) * 0.25
        ) {
            accept = true;
        }
        return accept;
    }
}

package ru.job4j.ood.srp.distribution.storage;

import ru.job4j.ood.srp.distribution.foods.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public abstract class Storage {

    private List<Food> availableLots = new ArrayList<>();

    public abstract boolean ifFoodFitsStorage(Food food, LocalDate date);

    public boolean accept(Food food, LocalDate date) {
        boolean accepted = ifFoodFitsStorage(food, date);
        if (accepted) {
            availableLots.add(food);
        }
        return accepted;
    }

    public List<Food> distribute(LocalDate date) {
        List<Food> foodsToDistribute = new ArrayList<>();
        ListIterator<Food> iterator = availableLots.listIterator();
        while (iterator.hasNext()) {
            Food food = iterator.next();
            if (!ifFoodFitsStorage(food, date)) {
                foodsToDistribute.add(food);
                iterator.remove();
            }
        }
        return foodsToDistribute;
    }

    public List<Food> getAvailableLots() {
        return availableLots;
    }

    public long daysBetween(LocalDate ld1, LocalDate ld2) {
        return Math.abs(ChronoUnit.DAYS.between(ld1, ld2)) + 1;
    }
}

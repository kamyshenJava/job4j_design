package ru.job4j.ood.srp.distribution;

import ru.job4j.ood.srp.distribution.foods.Food;
import ru.job4j.ood.srp.distribution.storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControlQuality {

    private List<Storage> storages;

    public ControlQuality(List<Storage> storages) {
        this.storages = storages;
    }

    public void addFood(Food food, LocalDate date) {
        for (Storage storage : storages) {
            if (storage.accept(food, date)) {
                break;
            }
        }
    }

    public void redistribute(LocalDate date) {
        List<Food> lotsToRedistribute = new ArrayList<>();
        for (Storage storage : storages) {
            lotsToRedistribute.addAll(storage.distribute(date));
        }
        for (Storage storage : storages) {
            lotsToRedistribute.removeIf(food -> storage.accept(food, date));
        }
    }
}

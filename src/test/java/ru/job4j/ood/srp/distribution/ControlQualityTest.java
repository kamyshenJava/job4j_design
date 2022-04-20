package ru.job4j.ood.srp.distribution;

import org.junit.Test;
import ru.job4j.ood.srp.distribution.foods.Bread;
import ru.job4j.ood.srp.distribution.foods.Butter;
import ru.job4j.ood.srp.distribution.foods.Food;
import ru.job4j.ood.srp.distribution.foods.Milk;
import ru.job4j.ood.srp.distribution.storage.Shop;
import ru.job4j.ood.srp.distribution.storage.Storage;
import ru.job4j.ood.srp.distribution.storage.Trash;
import ru.job4j.ood.srp.distribution.storage.Warehouse;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class ControlQualityTest {

    @Test
    public void addFoodFreshThenItGoesToWarehouse() {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        ControlQuality cq = new ControlQuality(List.of(
                warehouse,
                shop,
                trash
        ));
        LocalDate createDate = LocalDate.now().minusDays(1);
        LocalDate expiryDate = LocalDate.now().plusDays(89);
        Food milk = new Milk("Простоквашино", expiryDate, createDate, 150, 30);
        cq.addFood(milk, LocalDate.now());
        assertEquals(warehouse.getAvailableLots().size(), 1);
        assertEquals(shop.getAvailableLots().size(), 0);
        assertEquals(trash.getAvailableLots().size(), 0);
    }

    @Test
    public void addFoodThenItGoesToShop() {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        ControlQuality cq = new ControlQuality(List.of(
                warehouse,
                shop,
                trash
        ));
        LocalDate createDate = LocalDate.now().minusDays(30);
        LocalDate expiryDate = LocalDate.now().plusDays(30);
        Food bread = new Bread("Столичный", expiryDate, createDate, 150, 30);
        cq.addFood(bread, LocalDate.now());
        assertEquals(warehouse.getAvailableLots().size(), 0);
        assertEquals(shop.getAvailableLots().size(), 1);
        assertEquals(trash.getAvailableLots().size(), 0);
    }

    @Test
    public void addFoodWithDiscountThenDiscount() {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        ControlQuality cq = new ControlQuality(List.of(
                warehouse,
                shop,
                trash
        ));
        LocalDate createDate = LocalDate.now().minusDays(100);
        LocalDate expiryDate = LocalDate.now().plusDays(10);
        Food butter = new Butter("Valio", expiryDate, createDate, 500, 30);
        cq.addFood(butter, LocalDate.now());
        assertEquals(butter.getPrice(), 350, 0.01);
    }

    @Test
    public void addFoodExpiredThenItGoesToTrash() {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        ControlQuality cq = new ControlQuality(List.of(
                warehouse,
                shop,
                trash
        ));
        LocalDate createDate = LocalDate.now().minusDays(100);
        LocalDate expiryDate = LocalDate.now().minusDays(1);
        Food milk = new Milk("Простоквашино", expiryDate, createDate, 150, 30);
        cq.addFood(milk, LocalDate.now());
        assertEquals(warehouse.getAvailableLots().size(), 0);
        assertEquals(shop.getAvailableLots().size(), 0);
        assertEquals(trash.getAvailableLots().size(), 1);
    }

    @Test
    public void whenRedistribute() {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        ControlQuality cq = new ControlQuality(List.of(
                trash,
                shop,
                warehouse
        ));
        LocalDate createDate = LocalDate.now().minusDays(10);
        LocalDate expiryDate = LocalDate.now().plusDays(100);
        Food milk = new Milk("Простоквашино", expiryDate, createDate, 150, 30);
        cq.addFood(milk, LocalDate.now());
        Food bread = new Bread("Столичный", expiryDate.minusDays(30), createDate, 150, 30);
        cq.addFood(bread, LocalDate.now());
        Food butter = new Butter("Valio", expiryDate, createDate, 500, 30);
        cq.addFood(butter, LocalDate.now());
        assertEquals(warehouse.getAvailableLots().size(), 3);
        assertEquals(shop.getAvailableLots().size(), 0);
        assertEquals(trash.getAvailableLots().size(), 0);
        cq.redistribute(LocalDate.now().plusDays(80));
        assertEquals(warehouse.getAvailableLots().size(), 0);
        assertEquals(shop.getAvailableLots().size(), 2);
        assertEquals(trash.getAvailableLots().size(), 1);
    }
}
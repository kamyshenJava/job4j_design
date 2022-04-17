package ru.job4j.ood.srp;

import ru.job4j.collection.list.List;

import java.sql.Connection;

public interface Store<T> {
    void add(T value);

    T delete(int index);

    List<T> find(int index);

    Connection connect(String...args);
}

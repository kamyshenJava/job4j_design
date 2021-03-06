package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        storage.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        T tmp = storage.replace(id, model);
        return tmp != null;
    }

    @Override
    public boolean delete(String id) {
        T tmp = storage.remove(id);
        return tmp != null;
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}

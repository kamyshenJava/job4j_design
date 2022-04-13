package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public V get(K key) {
        V rsl = cache.getOrDefault(key, new SoftReference<>(null)).get();
        if (rsl == null) {
            System.out.println("This file is not in the cache yet. Loading from the file...");
            rsl = load(key);
            cache.put(key, new SoftReference<>(rsl));
        }
        return rsl;
    }

    protected abstract V load(K key);
}

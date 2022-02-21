package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = table[indexFor(hash(Objects.hashCode(key)))] == null;
        if (rsl) {
            table[indexFor(hash(Objects.hashCode(key)))] = new MapEntry<>(key, value);
            count++;
            modCount++;
            if (count >= capacity * LOAD_FACTOR) {
                expand();
            }
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode == 0 ? 0 : hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] expandedTable = new MapEntry[capacity];
        MapEntry<K, V>[] tmp = table;
        table = expandedTable;

        for (int i = 0; i < tmp.length; i++) {
            this.put(tmp[i].key, tmp[i].value);
        }
    }

    @Override
    public V get(K key) {
        return table[indexFor(hash(Objects.hashCode(key)))] == null
                ? null : table[indexFor(hash(Objects.hashCode(key)))].value;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = table[indexFor(hash(Objects.hashCode(key)))] != null;
        if (rsl) {
            table[indexFor(hash(Objects.hashCode(key)))] = null;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            private int itCount = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count > itCount;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (table[itCount] == null) {
                   itCount++;
                }
                return table[itCount++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

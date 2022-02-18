package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private transient Node<E> first;
    private transient Node<E> last = new Node<>(null, null, null);
    private int size = 0;
    private int modCount = 0;

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value, null, last);
        last.next = newNode;
        last = newNode;
        if (size == 0) {
            first = last;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rsl;
        if (index > size / 2) {
            rsl = last;
            int count = size - 1;
            while (count != index) {
                rsl = rsl.previous;
                count--;
            }
        } else {
            rsl = first;
            int count = 0;
            while (count != index) {
                rsl = rsl.next;
                count++;
            }
        }
        return rsl.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            private Node<E> currNode = first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = currNode.item;
                currNode = currNode.next;
                return rsl;
            }
        };
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> previous;

        Node(E value, Node<E> next, Node<E> previous) {
            this.item = value;
            this.next = next;
            this.previous = previous;
        }
    }
}

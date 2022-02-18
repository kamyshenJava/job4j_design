package ru.job4j.collection.list;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int count;
    boolean pushLast;

    public T poll() {
        if (pushLast) {
            int tempCount = count;
            while (tempCount != 0) {
                out.push(in.pop());
                tempCount--;
            }
        }
        count--;
        pushLast = false;
        return out.pop();
    }

    public void push(T value) {
        if (!pushLast) {
            int tempCount = count;
            while (tempCount != 0) {
                in.push(out.pop());
                tempCount--;
            }
        }
        in.push(value);
        count++;
        pushLast = true;
    }
}

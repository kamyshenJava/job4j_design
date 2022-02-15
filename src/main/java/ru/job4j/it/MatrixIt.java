package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        int sumLeft = data[row].length - column;
        for (int i = row + 1; i < data.length; i++) {
            sumLeft += data[i].length;
        }
        return sumLeft > 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (column == data[row].length) {
            row++;
            column = 0;
        }
        while (data[row].length == 0) {
            row++;
        }
        return data[row][column++];
    }
}

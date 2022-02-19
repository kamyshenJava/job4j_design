package ru.job4j.iterator;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);

        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 3, 2);
    }

    @Test
    public void whenRemoveIfNegative() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, -1, 2, -4));
        ListUtils.removeIf(input, x -> x < 0);

        assertThat(input, is(Arrays.asList(0, 2)));
    }

    @Test
    public void whenReplaceIfNegativeWithZeros() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, -1, 2, -4));
        ListUtils.replaceIf(input, x -> x < 0, 0);

        assertThat(input, is(Arrays.asList(0, 0, 2, 0)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, -1, 2, -4, 6));
        List<Integer> elements = new ArrayList<>(Arrays.asList(-1, 2, 3, 0));
        ListUtils.removeAll(input, elements);

        assertThat(input, is(Arrays.asList(-4, 6)));
    }
}
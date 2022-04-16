package ru.job4j.kiss;

import org.junit.Test;
import java.util.Comparator;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void whenListOf5689ThenMax9() {
        List<Integer> value = List.of(5, 6, 8, 9);
        MaxMin maxMin = new MaxMin();
        assertThat(maxMin.max(value, Comparator.naturalOrder()), is(9));
    }

    @Test
    public void whenListOfStringsThenMaxX() {
        List<String> value = List.of("a", "bg", "uii", "x");
        MaxMin maxMin = new MaxMin();
        assertThat(maxMin.max(value, Comparator.naturalOrder()), is("x"));
    }

    @Test
    public void whenListOf5689ThenMin5() {
        List<Integer> value = List.of(5, 6, 8, 9);
        MaxMin maxMin = new MaxMin();
        assertThat(maxMin.min(value, Comparator.naturalOrder()), is(5));
    }

    @Test
    public void whenListOfStringsThenMinA() {
        List<String> value = List.of("a", "bg", "uii", "x");
        MaxMin maxMin = new MaxMin();
        assertThat(maxMin.min(value, Comparator.naturalOrder()), is("a"));
    }
}
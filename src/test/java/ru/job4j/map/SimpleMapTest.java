package ru.job4j.map;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMapTest {

    @Test
    public void whenPutToEmptyMapThenTrue() {
        SimpleMap<String, Integer> table = new SimpleMap<>();
        assertTrue(table.put("one", 1));
    }

    @Test
    public void whenPutToEmptyMapThenGet() {
        SimpleMap<String, Integer> table = new SimpleMap<>();
        table.put("one", 1);
        assertThat(1, is(table.get("one")));
    }

    @Test
    public void whenPutToEmptyMapWithKeyNullThenGet() {
        SimpleMap<String, Integer> table = new SimpleMap<>();
        table.put(null, 1);
        assertThat(1, is(table.get(null)));
    }

    @Test
    public void whenPutDublicateThenFalse() {
        SimpleMap<String, Integer> table = new SimpleMap<>();
        table.put("one", 1);
        table.put("two", 2);
        assertFalse(table.put("two", 1));
        assertThat(1, is(table.get("one")));
        assertThat(2, is(table.get("two")));
    }

    @Test
    public void whenPutRemoveThenTrue() {
        SimpleMap<String, Integer> table = new SimpleMap<>();
        table.put("one", 1);
        assertTrue(table.remove("one"));
    }

    @Test
    public void whenPutRemoveGetThenNull() {
        SimpleMap<String, Integer> table = new SimpleMap<>();
        table.put("one", 1);
        table.remove("one");
        assertThat(null, is(table.get("one")));
    }

    @Test
    public void whenRemoveNotExistingThenFalse() {
        SimpleMap<String, Integer> table = new SimpleMap<>();
        assertFalse(table.remove("one"));
    }

    @Test
    public void whenGetIteratorFromEmptyMapThenHasNextReturnFalse() {
        SimpleMap<String, Integer> table = new SimpleMap<>();
        assertFalse(table.iterator().hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyMapThenNextThrowException() {
        SimpleMap<String, Integer> table = new SimpleMap<>();
        table.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorPutOneTimeInvokeNextTwoTimesThenThrowException() {
        SimpleMap<String, Integer> table = new SimpleMap<>();
        table.put("one", 1);
        Iterator<String> it = table.iterator();
        it.next();
        it.next();
    }

    @Test
    public void whenCheckIterator() {
        SimpleMap<String, Integer> table = new SimpleMap<>();
        table.put("one", 1);
        Iterator<String> it = table.iterator();
        assertTrue(it.hasNext());
        assertThat("one", is(it.next()));
        assertFalse(it.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenPutDuringIteratingThenException() {
        SimpleMap<String, Integer> table = new SimpleMap<>();
        table.put("one", 1);
        Iterator<String> it = table.iterator();
        table.put("two", 1);
        it.next();
    }

}
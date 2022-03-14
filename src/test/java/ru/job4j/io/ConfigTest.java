package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(nullValue()));
    }

    @Test
    public void whenPairWithCommentAndEmptyString() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr"));
        assertThat(config.value("surname"), is("Arsentev"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithBrokenLines() {
        String path = "./data/pair_with_broken_lines.properties";
        Config config = new Config(path);
        config.load();
    }


}
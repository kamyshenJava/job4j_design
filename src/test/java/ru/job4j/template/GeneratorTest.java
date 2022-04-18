package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@Ignore
public class GeneratorTest {

    @Test
    public void whenGenerate() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        Generator gen = new SimpleGenerator();
        String expected = "I am a Petr Arsentev, Who are you? ";
        assertThat(gen.produce(template, args), is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNotAllKeysThenException() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        Generator gen = new SimpleGenerator();
        gen.produce(template, args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenExcessKeysThenException() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        args.put("key", "value");
        Generator gen = new SimpleGenerator();
        gen.produce(template, args);
    }
}
package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(x -> !x.equals(""))
                    .filter(x -> !x.startsWith("#"))
                    .filter(this::checkThePattern)
                    .map(x -> x.split("=", 2))
                    .forEach(x -> values.put(x[0], x[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkThePattern(String str) throws IllegalArgumentException {
        String[] tmp = str.split("=", 2);
        boolean rsl = !tmp[0].isEmpty() && !tmp[1].isEmpty();
        if (!rsl) {
            throw new IllegalArgumentException();
        }
       return true;
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}

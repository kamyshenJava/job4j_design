package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            rsl = in.lines()
                    .filter(LogFilter::has404)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private static boolean has404(String str) {
        String[] strArr = str.split(" ", -2);
        return strArr[strArr.length - 2].equals("404");
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        log.stream()
                .forEach(System.out::println);
    }
}

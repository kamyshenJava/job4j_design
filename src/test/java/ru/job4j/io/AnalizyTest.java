package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.StringJoiner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailableTestThen2Lines() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        String ln = System.lineSeparator();
        StringJoiner rsl = new StringJoiner(ln);
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines()
                    .forEach(rsl::add);
        }
        assertThat(rsl.toString(), is("10:57:01;10:59:01;" + ln
                + "11:01:02;11:02:02;"));
    }

    @Test
    public void unavailableTestThen1Line() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("400 11:01:02");
            out.println("200 11:02:02");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        String ln = System.lineSeparator();
        StringJoiner rsl = new StringJoiner(ln);
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines()
                    .forEach(rsl::add);
        }
        assertThat(rsl.toString(), is("10:57:01;11:02:02;"));
    }
}
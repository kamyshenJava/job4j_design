package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Analizy {
    public void unavailable(String source, String target) {
        boolean idle = false;
        StringJoiner sj = new StringJoiner(";", "", ";");
        try (BufferedReader in = new BufferedReader(new FileReader(source)); PrintWriter out =
                new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            List<String[]> lines = in.lines()
                    .map(x -> x.split(" "))
                    .collect(Collectors.toList());
        for (String[] line : lines) {
            if (!idle && (line[0].equals("400") || line[0].equals("500"))) {
                sj.add(line[1]);
                idle = true;
            }
            if (idle && (line[0].equals("200") || line[0].equals("300"))) {
                sj.add(line[1]);
                out.println(sj);
                sj = new StringJoiner(";", "", ";");
                idle = false;
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy analizy = new Analizy();
        analizy.unavailable("idleLog.txt", "idleLogFiltered.txt");
    }
}

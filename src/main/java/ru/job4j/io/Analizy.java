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
        for (String l = in.readLine(); l != null; l = in.readLine()) {
            String[] line = l.split(" ");
            if (!idle && ("400".equals(line[0]) || "500".equals(line[0]))) {
                sj.add(line[1]);
                idle = true;
            }
            if (idle && ("200".equals(line[0]) || "300".equals(line[0]))) {
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
        Analizy analizy = new Analizy();
        analizy.unavailable("idleLog.txt", "idleLogFiltered.txt");
    }
}

package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class CSVReader {

    public static void handle(ArgsName argsName) {
        CSVReader csvReader = new CSVReader();
        csvReader.validateArgs(argsName);
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");
        List<ArrayList<String>> parsedCSVfile = csvReader.parseFile(path, delimiter);
        String data = csvReader.formOutputString(parsedCSVfile, filter, delimiter);
        csvReader.outputData(data, out);
    }

    private void validateArgs(ArgsName argsName) {
        if (!Files.isRegularFile(Path.of(argsName.get("path")))
                || !(";".equals(argsName.get("delimiter")) || ",".equals(argsName.get("delimiter")))
                || !("stdout".equals(argsName.get("out")) || Path.of(argsName.get("out")) != null)) {
            throw new IllegalArgumentException();
        }
    }

    private ArrayList<String> parseLine(String str, String delimiter) {
        ArrayList<String> parsedLine = new ArrayList<>();
        Scanner sc = new Scanner(str).useDelimiter(delimiter);
        while (sc.hasNext()) {
            parsedLine.add(sc.next());
        }
        return parsedLine;
    }

    private List<ArrayList<String>> parseFile(String path, String delimiter) {
        List<ArrayList<String>> parsedCSVfile = new ArrayList<>();
        try (Scanner scanner = new Scanner(Path.of(path))) {
            while (scanner.hasNextLine()) {
                parsedCSVfile.add(parseLine(scanner.nextLine(), delimiter));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parsedCSVfile;
    }

    private void outputData(String data, String out) {
        if ("stdout".equals(out)) {
            System.out.println(data);
        } else {
            try (PrintWriter pw = new PrintWriter(new FileWriter(out, StandardCharsets.UTF_8))) {
                pw.println(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String formOutputString(List<ArrayList<String>> parsedCSVfile, String filter, String delimiter) {
        List<String> keys = parsedCSVfile.get(0);
        if (filter != null) {
            keys = keys.stream()
                    .filter(key -> Arrays.asList(filter.split(",")).contains(key))
                    .collect(Collectors.toList());
        }
        List<Integer> indexes = keys.stream()
                .map(key -> parsedCSVfile.get(0).indexOf(key)).toList();

        StringJoiner line = new StringJoiner(delimiter);
        StringJoiner wholeText = new StringJoiner(System.lineSeparator());

        for (List<String> list : parsedCSVfile) {
            for (Integer i : indexes) {
                line.add(list.get(i));
            }
            wholeText.add(line.toString());
            line = new StringJoiner(delimiter);
        }
        return wholeText.toString();
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}

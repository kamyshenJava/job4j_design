package ru.job4j.findfiles;

import ru.job4j.io.ArgsName;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class Find {

    public static void handle(ArgsName argsName) throws IOException {
        Find find = new Find();
        find.validateArgs(argsName);
        String directory = argsName.get("d");
        String searchPattern = argsName.get("n");
        String typeOfPattern = argsName.get("t");
        String output = argsName.get("o");
        PathMatcher pathMatcher = find.getPathMatcher(searchPattern, typeOfPattern);
        FindFileVisitor findFileVisitor = new FindFileVisitor(pathMatcher);
        Files.walkFileTree(Path.of(directory), findFileVisitor);
        List<String> resultingPaths = findFileVisitor.getResultingPaths();
        find.outputResults(output, resultingPaths);
    }

    private void validateArgs(ArgsName argsName) {
        String typeOfPattern = argsName.get("t");
        if (!Files.isDirectory(Path.of(argsName.get("d"))) || !("mask".equals(typeOfPattern)
        || "name".equals(typeOfPattern) || "regex".equals(typeOfPattern)) || Path.of(argsName.get("o")) == null) {
            throw new IllegalArgumentException("The correct usage must be: "
                    + "java -jar find.jar -d=DIRECTORY -n=SEARCH_PATTERN -t=TYPE_OF_PATTERN -o=OUTPUT_FILE where "
                    + "TYPE_OF_PATTERN can be either NAME, MASK or REGEX");
        }
    }

    private PathMatcher getPathMatcher(String searchPattern, String typeOfPattern) throws IllegalArgumentException {
        FileSystem fileSystem = FileSystems.getDefault();
        String syntax = "";
        if ("mask".equals(typeOfPattern) || "name".equals(typeOfPattern)) {
            syntax = "glob:**/";
        }
        if ("regex".equals(typeOfPattern)) {
            syntax = "regex:";
        }
        return fileSystem.getPathMatcher(syntax + searchPattern);
    }

    private void outputResults(String output, List<String> resultingPaths) {
                try {
                    Files.write(Path.of(output), resultingPaths);
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}

package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {

        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("C:\\projects"), duplicatesVisitor);
        for (List<Path> value : duplicatesVisitor.getFilePaths().values()) {
            if (value.size() > 1) {
                value.forEach(System.out::println);
            }
        }
    }
}

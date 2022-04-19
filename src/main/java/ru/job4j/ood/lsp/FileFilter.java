package ru.job4j.ood.lsp;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FileFilter {

    public List<Path> filter(List<Path> paths, Predicate<Path> condition) {
        return paths.stream()
                .filter(condition)
                .limit(10)
                .collect(Collectors.toList());
    }
}

package ru.job4j.ood.lsp;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TextFileFilter extends FileFilter {

    @Override
    public List<Path> filter(List<Path> paths, Predicate<Path> condition) {
        return paths.stream()
                .filter(x -> x.endsWith(".txt"))
                .filter(condition)
                .collect(Collectors.toList());
    }
}

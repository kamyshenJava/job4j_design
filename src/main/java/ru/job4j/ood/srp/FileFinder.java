package ru.job4j.ood.srp;

import ru.job4j.collection.list.List;

import java.nio.file.Path;

public interface FileFinder {
    void validate(Path path);

    List<Path> find(Path dir);

    void print(List<Path> result);
}

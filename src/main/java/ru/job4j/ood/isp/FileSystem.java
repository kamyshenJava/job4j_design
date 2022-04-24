package ru.job4j.ood.isp;

import java.nio.file.Path;
import java.util.List;

public interface FileSystem {

    List<Path> findByName(String name);

    boolean create(String name, Path destination);

    boolean delete(Path name);

    boolean copy(Path name, Path destination);
}

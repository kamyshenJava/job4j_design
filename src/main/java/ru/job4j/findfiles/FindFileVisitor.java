package ru.job4j.findfiles;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FindFileVisitor extends SimpleFileVisitor<Path> {

    private PathMatcher pathMatcher;
    private List<String> resultingPaths = new ArrayList<>();

    public FindFileVisitor(PathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
    }

    public List<String> getResultingPaths() {
        return resultingPaths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (pathMatcher.matches(file)) {
            resultingPaths.add(file.toString());
        }
        return super.visitFile(file, attrs);
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException e) {
        return FileVisitResult.SKIP_SUBTREE;
    }
}

package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(Files.newOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream in = new BufferedInputStream(Files.newInputStream(source))) {
                    zip.write(in.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String[] validateArgs(String[] args) {
        ArgsName argsNames = ArgsName.of(args);
        String dir = argsNames.get("d");
        String exclude = argsNames.get("e");
        String output = argsNames.get("o");
        if (args.length != 3 || dir == null || exclude == null || output == null
                || !Files.isDirectory(Path.of(dir))) {
            throw new IllegalArgumentException();
        }
        return new String[]{dir, exclude, output};
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        String[] vArgs =  zip.validateArgs(args);
        List<Path> filteredPaths = Search.search(Path.of(vArgs[0]),
                p -> !p.toString().endsWith(vArgs[1])).stream()
                .map(x -> Path.of(vArgs[0]).relativize(x))
                .collect(Collectors.toList());
        zip.packFiles(filteredPaths, Path.of(vArgs[2]));
    }
}

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
                putNextEntryAndThenWrite(zip, source);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(Path source, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(Files.newOutputStream(target)))) {
            putNextEntryAndThenWrite(zip, source);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void putNextEntryAndThenWrite(ZipOutputStream zip, Path source) throws IOException {
        zip.putNextEntry(new ZipEntry(source.toString()));
        try (BufferedInputStream in = new BufferedInputStream(Files.newInputStream(source))) {
            zip.write(in.readAllBytes());
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsNames = ArgsName.of(args);
        String dir = argsNames.get("d");
        String exclude = argsNames.get("e");
        String output = argsNames.get("o");
        if (args.length != 3 || dir == null || exclude == null || output == null
                || !Files.isDirectory(Path.of(dir))) {
            throw new IllegalArgumentException();
        }
        List<Path> filteredPaths = Search.search(Path.of(dir),
                p -> !p.toString().endsWith(exclude)).stream()
                .map(x -> Path.of(dir).relativize(x))
                .collect(Collectors.toList());
        Zip zip = new Zip();
        zip.packFiles(filteredPaths, Path.of(output));
    }
}

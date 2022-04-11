package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    public String getCachingDir() {
        return cachingDir;
    }

    @Override
    protected String load(String key) {
        String rsl = "";
        SoftReference<String> data = cache.get(key);
        if (data != null) {
            rsl = data.get();
            System.out.println("The file has been read from cache");
        } else {
            Path absPath = Path.of(cachingDir).resolve(key);
            if (!Files.exists(absPath)) {
                throw new IllegalArgumentException();
            }
            try (BufferedReader br = new BufferedReader(new FileReader(absPath.toString()))) {
                rsl = br.lines().collect(Collectors.joining(System.lineSeparator()));
                cache.put(key, new SoftReference<>(rsl));
                System.out.println("The file has been cashed.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rsl;
    }
}

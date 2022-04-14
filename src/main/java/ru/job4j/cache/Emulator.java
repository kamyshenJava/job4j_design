package ru.job4j.cache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Emulator {

    private static final int LOAD_INTO_CACHE = 1;
    private static final int GET_FROM_CACHE = 2;
    private static final int TERMINATE = 3;
    private DirFileCache cache;

    public Emulator(DirFileCache cache) {
        this.cache = cache;
    }

    public void handle() {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            int choice = menu(scanner);
            if (choice == LOAD_INTO_CACHE) {
                String fileName = getFileName(scanner);
                cache.put(fileName, cache.load(fileName));
                System.out.println("Файл загружен в кэш и доступен по ключю: " + fileName);
            }
            if (choice == GET_FROM_CACHE) {
                String fileName = getFileName(scanner);
                System.out.println(cache.get(fileName));
            }
            if (choice == TERMINATE) {
                run = false;
                System.out.println("Выход из программы");
            }
        }
    }

    private int menu(Scanner scanner) {
        System.out.println("Что бы вы хотели сделать?");
        System.out.println("1 — загрузить содержимое файла в кэш,");
        System.out.println("2 — получить содержимое файла из кэша/самого файла,");
        System.out.println("3 — выйти.");
        int choice = Integer.parseInt(scanner.nextLine());
        while (!(choice == 1 || choice == 2 || choice == 3)) {
            System.out.println("Вы указали неправильный номер. Выберите 1, 2 или 3.");
            choice = Integer.parseInt(scanner.nextLine());
        }
        return choice;
    }

    private String getFileName(Scanner scanner) {
        String fileName = "";
        System.out.println("Укажите название файла");
        boolean run = true;
        while (run) {
            fileName = scanner.nextLine();
            Path absPath = Path.of(cache.getCachingDir()).resolve(fileName);
            if (Files.exists(absPath)) {
                run = false;
            } else {
                System.out.println("Неправильное имя файла. Попробуйте снова.");
            }
        }
        return fileName;
    }

    private static String getDir() {
        System.out.println("Укажите директорию с файлами");
        Scanner scanner = new Scanner(System.in);
        String rsl = scanner.nextLine();
        while (!Files.isDirectory(Path.of(rsl))) {
            System.out.println("Вы указали неправильную директорию. Попробуйте снова.");
            rsl = scanner.nextLine();
        }
        return rsl;
    }

    public static void main(String[] args) {
        DirFileCache dirFileCache = new DirFileCache(getDir());
        new Emulator(dirFileCache).handle();
    }
}

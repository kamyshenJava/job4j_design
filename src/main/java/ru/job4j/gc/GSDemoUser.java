package ru.job4j.gc;

public class GSDemoUser {

    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %dkb%n", freeMemory / 1024);
        System.out.printf("Total: %dkb%n ", totalMemory / 1024);
        System.out.printf("Max: %dkb%n ", maxMemory / 1024);
    }

    public static void main(String[] args) {
        info();
        for (int i = 0; i < 3836; i++) {
            new User(i, "N" + i);
        }
        info();
    }
}

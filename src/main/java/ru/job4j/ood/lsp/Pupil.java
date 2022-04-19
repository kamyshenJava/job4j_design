package ru.job4j.ood.lsp;

public class Pupil {

    String name;
    int age;

    public Pupil(String name, int age) {
        if (age < 5 || age > 18) {
            throw new IllegalArgumentException("Wrong age");
        }
        this.name = name;
        this.age = age;
    }
}

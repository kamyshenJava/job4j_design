package ru.job4j.ood.lsp;

public class Student extends Pupil {

    public Student(String name, int age) {
        super(name, age);
        validateAge(age);
    }

    public void validateAge(int age) {
        if (this.age < 16 || this.age > 30) {
            throw new IllegalArgumentException("Wrong age");
        }
    }
}

package ru.job4j.serialization.json;

public class Person {
    private String name;
    private String phoneNumber;

    public Person(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Person{"
                + "name='" + name + '\''
                + ", phoneNumber='" + phoneNumber + '\'' + '}';
    }
}

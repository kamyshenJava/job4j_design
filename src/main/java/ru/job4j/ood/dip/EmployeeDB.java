package ru.job4j.ood.dip;

import java.util.HashMap;
import java.util.Map;

public class EmployeeDB {

    private Map<String, Person> employees = new HashMap<>();

    public boolean add(Person person) {
        String name = person.getName();
        boolean rsl = !employees.containsKey(name);
        if (rsl) {
            employees.put(name, person);
        }
        return rsl;
    }
}

package ru.job4j.design.srp;

import java.util.Comparator;
import java.util.function.Predicate;

public class ReportForHR implements Report {

    private Store store;

    public ReportForHR(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        Comparator<Employee> comparator = Comparator.comparing(Employee::getSalary).reversed();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        store.findBy(filter).stream()
                .sorted(comparator)
                .forEach(em -> text.append(em.getName()).append(";")
                                .append(em.getSalary()).append(";")
                                .append(System.lineSeparator()));
        return text.toString();
    }
}

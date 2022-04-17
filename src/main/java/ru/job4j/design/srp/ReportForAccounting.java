package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportForAccounting implements Report {

    private Store store;
    private double exchangeRate;

    public ReportForAccounting(Store store, double exchangeRate) {
        this.store = store;
        this.exchangeRate = exchangeRate;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary; SalaryUSD;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(employee.getSalary() / exchangeRate).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}

package ru.job4j.ood.ocp;

public class ReportStoreUsage {

    private ReportStore store;

    public ReportStoreUsage(ReportStore store) {
        this.store = store;
    }

    public ReportStore findById(int id) {
        return new ReportStore();
    }
}

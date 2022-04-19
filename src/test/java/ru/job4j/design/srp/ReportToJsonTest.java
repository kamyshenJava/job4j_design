package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ReportToJsonTest {

    @Test
    public void whenGeneratedJson() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report report = new ReportToJson(store);
        final Gson gson = new GsonBuilder().create();
        String nowJson = gson.toJson(now);
        String expected = "[{\"name\":\"Ivan\",\"hired\":"
                + nowJson
                + ",\"fired\":"
                + nowJson
                + ",\"salary\":100.0}]";
        assertThat(report.generate(x -> true), is(expected));

    }
}
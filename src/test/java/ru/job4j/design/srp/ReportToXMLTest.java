package ru.job4j.design.srp;

import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ReportToXMLTest {

    @Test
    public void whenGeneratedXml() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        OffsetDateTime nowFormatted = ((GregorianCalendar) now).toZonedDateTime().toOffsetDateTime();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report report = new ReportToXML(store);
        StringBuilder expected = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<employees>\n"
                        + "    <employee>\n"
                        + "        <fired>")
                .append(nowFormatted)
                .append("</fired>\n"
                        + "        <hired>")
                .append(nowFormatted)
                .append("</hired>\n"
                        + "        <name>Ivan</name>\n"
                        + "        <salary>100.0</salary>\n"
                        + "    </employee>\n"
                        + "</employees>" + "\n");
        assertThat(report.generate(x -> true), is(expected.toString()));
    }
}
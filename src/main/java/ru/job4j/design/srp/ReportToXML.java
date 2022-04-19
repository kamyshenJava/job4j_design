package ru.job4j.design.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

public class ReportToXML implements Report {

    public ReportToXML(Store store) {
        this.store = store;
    }

    private Store store;

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        String xml = "";
        try {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(new Employees(employees), writer);
                xml = writer.getBuffer().toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }

    @XmlRootElement(name = "employees")
    public static class Employees {

        private List<Employee> employees;

        public Employees() {
        }

        public Employees(List<Employee> employees) {
            this.employees = employees;
        }

        @XmlElement(name = "employee")
        public List<Employee> getEmployees() {
            return employees;
        }

        public void setEmployees(List<Employee> employees) {
            this.employees = employees;
        }
    }
}

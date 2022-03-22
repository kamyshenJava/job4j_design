package ru.job4j.serialization.xml;


import javax.xml.bind.*;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "computer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Computer {

    @XmlAttribute
    private boolean isLaptop;

    @XmlAttribute
    private int displaySize;

    @XmlAttribute
    private String manufacturer;
    private Processor processor;

    @XmlElementWrapper(name = "disks")
    @XmlElement(name = "disk")
    private String[] disks;

    public Computer() {
    }

    public Computer(boolean isLaptop, int displaySize, String manufacturer, Processor processor, String... disks) {
        this.isLaptop = isLaptop;
        this.displaySize = displaySize;
        this.manufacturer = manufacturer;
        this.processor = processor;
        this.disks = disks;
    }

    @Override
    public String toString() {
        return "Computer{" + "isLaptop=" + isLaptop + ", displaySize=" + displaySize
                + ", manufacturer='" + manufacturer + '\'' + ", processor=" + processor
                + ", disks=" + Arrays.toString(disks) + '}';
    }

    public static void main(String[] args) throws JAXBException, IOException {
        Computer computer = new Computer(true, 17, "Asus",
                new Processor("Intel", "i9"), "SSD1Tb", "HDD1Tb");

        JAXBContext context = JAXBContext.newInstance(Computer.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(computer, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Computer result = (Computer) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}

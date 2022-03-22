package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "processor")
public class Processor {

    @XmlAttribute
    private String manufacturer;

    @XmlAttribute
    private String name;

    public Processor() {
    }

    public Processor(String manufacturer, String name) {
        this.manufacturer = manufacturer;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Processor{" + "manufacturer='" + manufacturer + '\'' + ", name='" + name + '\'' + '}';
    }
}

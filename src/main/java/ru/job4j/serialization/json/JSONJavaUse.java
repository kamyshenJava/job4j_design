package ru.job4j.serialization.json;

import org.json.JSONObject;

public class JSONJavaUse {
    public static void main(String[] args) {
        Person john = new Person("John", "9560123");
        PlaneTicket ticket = new PlaneTicket(true, 50000.5,
                "963852741-01", john, new String[]{"LED", "SVO", "DOH"});

        JSONObject jsObject = new JSONObject(ticket);
        System.out.println(jsObject);
    }
}

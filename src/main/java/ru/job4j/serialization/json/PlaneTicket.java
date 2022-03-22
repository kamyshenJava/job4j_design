package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class PlaneTicket {
    private boolean isPaid;
    private double price;
    private String number;
    private Person passenger;
    private String[] route;

    public PlaneTicket(boolean isPaid, double price,
                       String number, Person passenger, String[] route) {
        this.isPaid = isPaid;
        this.price = price;
        this.number = number;
        this.passenger = passenger;
        this.route = route;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public double getPrice() {
        return price;
    }

    public String getNumber() {
        return number;
    }

    public Person getPassenger() {
        return passenger;
    }

    public String[] getRoute() {
        return route;
    }

    @Override
    public String toString() {
        return "PlaneTicket{"
                + "isPaid=" + isPaid
                + ", price=" + price
                + ", number='" + number + '\''
                + ", passenger=" + passenger
                + ", route=" + Arrays.toString(route)
                + '}';
    }

    public static void main(String[] args) {
        Person john = new Person("John", "9560123");
        PlaneTicket ticket = new PlaneTicket(true, 50000.5,
                "963852741-01", john, new String[]{"LED", "SVO", "DOH"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(ticket));

        final String newTicketJson = "{"
                + "\"isPaid\":true,"
                + "\"price\":50000.5,"
                + "\"number\":\"963852741-01\","
                + "\"passenger\":"
                + "{"
                + "\"name\":\"John\",\"phoneNumber\":\"9560123\""
                + "},"
                + "\"route\":"
                + "[\"LED\",\"SVO\",\"DOH\"]"
                + "}";

        PlaneTicket ticketFromJson = gson.fromJson(newTicketJson, PlaneTicket.class);
        System.out.println(ticketFromJson);
    }
}

package ru.job4j.ood.ocp;

public class EmailSender {

    private String address;

    public EmailSender(String address) {
        this.address = address;
    }

    public void send(String text) {
        EmailFormatter emailFormatter = new EmailFormatter();
        emailFormatter.formatter(text);
    }
}

package ru.job4j.ood.srp;

public interface EmailSender {
    void send(String msg);

    String formatEmail(String text);
}

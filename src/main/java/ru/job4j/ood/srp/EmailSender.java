package ru.job4j.ood.srp;

import java.sql.Connection;
import java.util.function.Predicate;

public interface EmailSender {
    void send(String msg);

    String formatEmail(String text);

    String getAddress(Connection connection, Predicate<String> filter);
}

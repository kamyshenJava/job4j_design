package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int age = 33;
        byte weight = 70;
        short height = 180;
        long capacity = 1000000;
        float price = 500.5F;
        double fare = 50.5;
        boolean isValid = true;
        char symbol = '0';
        LOG.debug("The weirdest info : age - {}, weight : {},"
                        + " height : {}, capacity : {}, price : {},"
                        + " fare : {}, isValid : {}, symbol : {}",
                age, weight, height, capacity, price, fare, isValid, symbol);
    }
}

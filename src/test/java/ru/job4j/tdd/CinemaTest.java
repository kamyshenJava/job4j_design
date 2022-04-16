package ru.job4j.tdd;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CinemaTest {

    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, session, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        Session session2 = new Session3D();
        Session session3 = new Session3D();
        cinema.add(session);
        cinema.add(session2);
        cinema.add(session3);
        List<Session> sessions = cinema.find(ses -> true);
        assertThat(sessions, is(Arrays.asList(session, session2, session3)));
    }

    @Test
    public void whenBuySameSeatThenNull() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, session, 1, 1, date);
        Ticket ticket2 = cinema.buy(account, session, 1, 1, date);
        assertThat(ticket2, is(nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenBuyAndNotEnoughMoneyInAccountThenException() {
        Account account = null;
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, session, 1, 1, date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenBuyAndSessionNotExistThenException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Session session = null;
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, session, 1, 1, date);
    }
}
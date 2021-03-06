package ru.job4j.tdd;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CinemaTest {

    @Ignore
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

    @Ignore
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

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenBuySameSeatThenException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, session, 1, 1, date);
        Ticket ticket2 = cinema.buy(account, session, 1, 1, date);
    }

    @Ignore
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
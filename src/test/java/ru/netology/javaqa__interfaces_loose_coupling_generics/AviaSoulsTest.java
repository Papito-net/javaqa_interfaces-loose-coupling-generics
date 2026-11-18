package ru.netology.javaqa__interfaces_loose_coupling_generics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;


public class AviaSoulsTest {

    @Test
    public void shouldReturnEmptyArrayWhenNoTicketsFound() {
        AviaSouls manager = new AviaSouls();
        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket ticket1 = new Ticket("MOW", "ROV", 4000, 10, 12);
        manager.add(ticket1);

        Ticket[] expected = {};
        Ticket[] actual = manager.searchAndSortBy("ROV", "GSV", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchSingleTicket() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("MOW", "ROV", 4000, 10, 12);
        Ticket ticket2 = new Ticket("MOW", "KRR", 3000, 11, 13);

        manager.add(ticket1);
        manager.add(ticket2);

        Ticket[] expected = {ticket2};
        Ticket[] actual = manager.search("MOW", "KRR");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindAndSortTicketsByPrice() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("MOW", "ROV", 4000, 10, 12);
        Ticket ticket2 = new Ticket("MOW", "ROV", 2000, 14, 16);
        Ticket ticket3 = new Ticket("MOW", "KRR", 3000, 11, 13);
        Ticket ticket4 = new Ticket("MOW", "ROV", 7000, 18, 20);
        Ticket ticket5 = new Ticket("MOW", "ROV", 2000, 21, 23);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);

        Ticket[] expected = {ticket2, ticket5, ticket1, ticket4};
        Ticket[] actual = manager.search("MOW", "ROV");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindNothingWhenNoTicketsMatch() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("MOW", "ROV", 4000, 10, 12);
        Ticket ticket2 = new Ticket("MOW", "KRR", 3000, 11, 13);

        manager.add(ticket1);
        manager.add(ticket2);

        Ticket[] expected = {};
        Ticket[] actual = manager.search("ROV", "GSV");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyArrayWhenManagerIsEmpty() {
        AviaSouls manager = new AviaSouls();

        Ticket[] expected = {};
        Ticket[] actual = manager.search("MOW", "ROV");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAndSortByFlightTime() {
        AviaSouls manager = new AviaSouls();
        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket ticket1 = new Ticket("MOW", "ROV", 2000, 10, 15);
        Ticket ticket2 = new Ticket("MOW", "ROV", 7000, 14, 16);
        Ticket ticket3 = new Ticket("MOW", "KRR", 3000, 11, 12);
        Ticket ticket4 = new Ticket("MOW", "ROV", 4000, 18, 21);
        Ticket ticket5 = new Ticket("MOW", "ROV", 5000, 20, 22);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);


        Ticket[] expected = {ticket2, ticket5, ticket4, ticket1}; // Сортировка по возрастанию времени в пути
        Ticket[] actual = manager.searchAndSortBy("MOW", "ROV", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAndSortBySingleTicket() {
        AviaSouls manager = new AviaSouls();
        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket ticket1 = new Ticket("MOW", "ROV", 4000, 10, 12);
        Ticket ticket2 = new Ticket("MOW", "KRR", 3000, 11, 13);

        manager.add(ticket1);
        manager.add(ticket2);

        Ticket[] expected = {ticket2};
        Ticket[] actual = manager.searchAndSortBy("MOW", "KRR", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }
}


class TicketTest {

    @Test
    public void shouldBeLessWhenPriceIsLower() {
        Ticket ticket1 = new Ticket("MOW", "ROV", 2000, 10, 12);
        Ticket ticket2 = new Ticket("MOW", "ROV", 4000, 14, 16);

        int actual = ticket1.compareTo(ticket2);

        Assertions.assertTrue(actual < 0);
    }

    @Test
    public void shouldBeGreaterWhenPriceIsHigher() {
        Ticket ticket1 = new Ticket("MOW", "ROV", 6000, 10, 12);
        Ticket ticket2 = new Ticket("MOW", "ROV", 4000, 14, 16);

        int actual = ticket1.compareTo(ticket2);

        Assertions.assertTrue(actual > 0);
    }

    @Test
    public void shouldBeEqualWhenPricesMatch() {
        Ticket ticket1 = new Ticket("MOW", "ROV", 4000, 10, 12);
        Ticket ticket2 = new Ticket("MOW", "KRR", 4000, 14, 16);

        int actual = ticket1.compareTo(ticket2);

        Assertions.assertEquals(0, actual);
    }
}


class TicketTimeComparatorTest {

    @Test
    public void shouldReturnNegativeWhenFirstTicketIsFaster() {
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket ticket1 = new Ticket("MOW", "ROV", 4000, 10, 12);
        Ticket ticket2 = new Ticket("MOW", "ROV", 2000, 14, 18);

        int actual = comparator.compare(ticket1, ticket2);

        Assertions.assertTrue(actual < 0);
    }

    @Test
    public void shouldReturnPositiveWhenFirstTicketIsSlower() {
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket ticket1 = new Ticket("MOW", "ROV", 2000, 10, 15);
        Ticket ticket2 = new Ticket("MOW", "ROV", 4000, 14, 16);

        int actual = comparator.compare(ticket1, ticket2);

        Assertions.assertTrue(actual > 0);
    }

    @Test
    public void shouldReturnZeroWhenFlightTimeIsEqual() {
        TicketTimeComparator comparator = new TicketTimeComparator();
        // Время полёта: 13 - 10 = 3 часа
        Ticket ticket1 = new Ticket("MOW", "ROV", 4000, 10, 13);
        // Время полёта: 17 - 14 = 3 часа
        Ticket ticket2 = new Ticket("MOW", "KRR", 2000, 14, 17);

        int actual = comparator.compare(ticket1, ticket2);

        Assertions.assertEquals(0, actual); // Время в пути одинаковое
    }

    @Test
    public void shouldSortArrayByFlightTime() {
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket ticket1 = new Ticket("MOW", "ROV", 2000, 10, 15); // 5 часов
        Ticket ticket2 = new Ticket("MOW", "ROV", 7000, 14, 16); // 2 часа
        Ticket ticket3 = new Ticket("MOW", "ROV", 4000, 18, 21); // 3 часа

        Ticket[] tickets = {ticket1, ticket2, ticket3};
        Arrays.sort(tickets, comparator);

        Ticket[] expected = {ticket2, ticket3, ticket1};
        Assertions.assertArrayEquals(expected, tickets);
    }
}
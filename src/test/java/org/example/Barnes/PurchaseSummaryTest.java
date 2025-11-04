package org.example.Barnes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PurchaseSummaryTest {

    private PurchaseSummary purchaseSummary;

    @BeforeEach
    void setUp() {
        purchaseSummary = new PurchaseSummary();
    }

    @Test
    void testInitialState() {
        assertThat(purchaseSummary.getTotalPrice()).isEqualTo(0);
        assertThat(purchaseSummary.getUnavailable()).isEmpty();
    }

    @Test
    void testAddToTotalPrice() {
        purchaseSummary.addToTotalPrice(100);
        assertThat(purchaseSummary.getTotalPrice()).isEqualTo(100);
    }

    @Test
    void testAddMultiplePrices() {
        purchaseSummary.addToTotalPrice(50);
        purchaseSummary.addToTotalPrice(75);
        purchaseSummary.addToTotalPrice(25);
        assertThat(purchaseSummary.getTotalPrice()).isEqualTo(150);
    }

    @Test
    void testAddUnavailableBook() {
        Book book = new Book("1234567890", 20, 5);
        purchaseSummary.addUnavailable(book, 3);
        Map<Book, Integer> unavailable = purchaseSummary.getUnavailable();
        assertThat(unavailable).hasSize(1);
        assertThat(unavailable.get(book)).isEqualTo(3);
    }

    @Test
    void testAddMultipleUnavailableBooks() {
        Book book1 = new Book("1234567890", 20, 5);
        Book book2 = new Book("0987654321", 30, 3);
        purchaseSummary.addUnavailable(book1, 2);
        purchaseSummary.addUnavailable(book2, 1);
        Map<Book, Integer> unavailable = purchaseSummary.getUnavailable();
        assertThat(unavailable).hasSize(2);
        assertThat(unavailable.get(book1)).isEqualTo(2);
        assertThat(unavailable.get(book2)).isEqualTo(1);
    }

    @Test
    void testUnavailableMapIsUnmodifiable() {
        Book book = new Book("1234567890", 20, 5);
        purchaseSummary.addUnavailable(book, 3);
        Map<Book, Integer> unavailable = purchaseSummary.getUnavailable();
        assertThat(unavailable).isUnmodifiable();
    }
}


package org.example.Barnes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BarnesAndNobleTest {

    private BookDatabase bookDatabase;
    private BuyBookProcess buyBookProcess;
    private BarnesAndNoble barnesAndNoble;

    @BeforeEach
    void setUp() {
        bookDatabase = new SimpleBookDatabase();
        buyBookProcess = new SimpleBuyBookProcess();
        barnesAndNoble = new BarnesAndNoble(bookDatabase, buyBookProcess);
    }

    @Test
    void testGetPriceForCartWithSingleBook() {
        Map<String, Integer> order = new HashMap<>();
        order.put("1234567890", 3);

        PurchaseSummary summary = barnesAndNoble.getPriceForCart(order);

        assertThat(summary).isNotNull();
        assertThat(summary.getTotalPrice()).isEqualTo(75);
        assertThat(summary.getUnavailable()).isEmpty();
    }

    @Test
    void testGetPriceForCartWithMultipleBooks() {
        Map<String, Integer> order = new HashMap<>();
        order.put("1234567890", 2);
        order.put("0987654321", 3);

        PurchaseSummary summary = barnesAndNoble.getPriceForCart(order);

        assertThat(summary).isNotNull();
        assertThat(summary.getTotalPrice()).isEqualTo(140);
        assertThat(summary.getUnavailable()).isEmpty();
    }

    @Test
    void testGetPriceForCartWithInsufficientQuantity() {
        Map<String, Integer> order = new HashMap<>();
        order.put("1234567890", 10);

        PurchaseSummary summary = barnesAndNoble.getPriceForCart(order);

        assertThat(summary).isNotNull();
        assertThat(summary.getTotalPrice()).isEqualTo(125);
        assertThat(summary.getUnavailable()).hasSize(1);
    }

    @Test
    void testGetPriceForCartWithNullOrder() {
        PurchaseSummary summary = barnesAndNoble.getPriceForCart(null);
        assertThat(summary).isNull();
    }

    @Test
    void testGetPriceForCartWithEmptyOrder() {
        Map<String, Integer> order = new HashMap<>();
        PurchaseSummary summary = barnesAndNoble.getPriceForCart(order);

        assertThat(summary).isNotNull();
        assertThat(summary.getTotalPrice()).isEqualTo(0);
        assertThat(summary.getUnavailable()).isEmpty();
    }

    private static class SimpleBookDatabase implements BookDatabase {
        private final Map<String, Book> books = new HashMap<>();

        SimpleBookDatabase() {
            books.put("1234567890", new Book("1234567890", 25, 10));
            books.put("0987654321", new Book("0987654321", 30, 5));
        }

        @Override
        public Book findByISBN(String ISBN) {
            return books.get(ISBN);
        }
    }

    private static class SimpleBuyBookProcess implements BuyBookProcess {
        @Override
        public void buyBook(Book book, int amount) {
        }
    }
}


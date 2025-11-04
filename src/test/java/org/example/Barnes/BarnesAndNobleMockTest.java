package org.example.Barnes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BarnesAndNobleMockTest {

    @Mock
    private BookDatabase bookDatabase;

    @Mock
    private BuyBookProcess buyBookProcess;

    private BarnesAndNoble barnesAndNoble;

    @BeforeEach
    void setUp() {
        barnesAndNoble = new BarnesAndNoble(bookDatabase, buyBookProcess);
    }

    @Test
    void testGetPriceForCartWithSingleBook() {
        Book book = new Book("1234567890", 25, 10);
        Map<String, Integer> order = new HashMap<>();
        order.put("1234567890", 3);

        when(bookDatabase.findByISBN("1234567890")).thenReturn(book);

        PurchaseSummary summary = barnesAndNoble.getPriceForCart(order);

        assertThat(summary).isNotNull();
        assertThat(summary.getTotalPrice()).isEqualTo(75);
        assertThat(summary.getUnavailable()).isEmpty();
        verify(bookDatabase).findByISBN("1234567890");
        verify(buyBookProcess).buyBook(book, 3);
    }

    @Test
    void testGetPriceForCartWithMultipleBooks() {
        Book book1 = new Book("1234567890", 25, 10);
        Book book2 = new Book("0987654321", 30, 5);
        Map<String, Integer> order = new HashMap<>();
        order.put("1234567890", 2);
        order.put("0987654321", 3);

        when(bookDatabase.findByISBN("1234567890")).thenReturn(book1);
        when(bookDatabase.findByISBN("0987654321")).thenReturn(book2);

        PurchaseSummary summary = barnesAndNoble.getPriceForCart(order);

        assertThat(summary).isNotNull();
        assertThat(summary.getTotalPrice()).isEqualTo(140);
        assertThat(summary.getUnavailable()).isEmpty();
        verify(bookDatabase).findByISBN("1234567890");
        verify(bookDatabase).findByISBN("0987654321");
        verify(buyBookProcess).buyBook(book1, 2);
        verify(buyBookProcess).buyBook(book2, 3);
    }

    @Test
    void testGetPriceForCartWithInsufficientQuantity() {
        Book book = new Book("1234567890", 25, 5);
        Map<String, Integer> order = new HashMap<>();
        order.put("1234567890", 10);

        when(bookDatabase.findByISBN("1234567890")).thenReturn(book);

        PurchaseSummary summary = barnesAndNoble.getPriceForCart(order);

        assertThat(summary).isNotNull();
        assertThat(summary.getTotalPrice()).isEqualTo(125);
        assertThat(summary.getUnavailable()).hasSize(1);
        assertThat(summary.getUnavailable().get(book)).isEqualTo(5);
        verify(bookDatabase).findByISBN("1234567890");
        verify(buyBookProcess).buyBook(book, 5);
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
}


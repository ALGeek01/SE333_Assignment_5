package org.example.Barnes;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookTest {

    @Test
    void testConstructorAndGetters() {
        Book book = new Book("1234567890", 25, 10);
        assertThat(book.getPrice()).isEqualTo(25);
        assertThat(book.getQuantity()).isEqualTo(10);
    }

    @Test
    void testEqualsWithSameISBN() {
        Book book1 = new Book("1234567890", 25, 10);
        Book book2 = new Book("1234567890", 30, 5);
        assertThat(book1).isEqualTo(book2);
    }

    @Test
    void testEqualsWithDifferentISBN() {
        Book book1 = new Book("1234567890", 25, 10);
        Book book2 = new Book("0987654321", 25, 10);
        assertThat(book1).isNotEqualTo(book2);
    }

    @Test
    void testEqualsWithNull() {
        Book book = new Book("1234567890", 25, 10);
        assertThat(book).isNotEqualTo(null);
    }

    @Test
    void testEqualsWithDifferentClass() {
        Book book = new Book("1234567890", 25, 10);
        assertThat(book).isNotEqualTo("not a book");
    }

    @Test
    void testHashCode() {
        Book book1 = new Book("1234567890", 25, 10);
        Book book2 = new Book("1234567890", 30, 5);
        assertThat(book1.hashCode()).isEqualTo(book2.hashCode());
    }

    @Test
    void testHashCodeWithDifferentISBN() {
        Book book1 = new Book("1234567890", 25, 10);
        Book book2 = new Book("0987654321", 25, 10);
        assertThat(book1.hashCode()).isNotEqualTo(book2.hashCode());
    }
}


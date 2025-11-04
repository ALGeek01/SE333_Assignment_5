package org.example.Amazon.Cost;

import org.example.Amazon.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ExtraCostForElectronicsTest {

    private ExtraCostForElectronics extraCost;

    @BeforeEach
    void setUp() {
        extraCost = new ExtraCostForElectronics();
    }

    @Test
    void testPriceForEmptyCart() {
        List<Item> emptyCart = new ArrayList<>();
        double price = extraCost.priceToAggregate(emptyCart);
        assertThat(price).isEqualTo(0.0);
    }

    @Test
    void testPriceWithNoElectronics() {
        List<Item> cart = new ArrayList<>();
        cart.add(new Item(ItemType.OTHER, "Book", 2, 10.0));
        cart.add(new Item(ItemType.OTHER, "Clothing", 1, 20.0));
        double price = extraCost.priceToAggregate(cart);
        assertThat(price).isEqualTo(0.0);
    }

    @Test
    void testPriceWithSingleElectronic() {
        List<Item> cart = new ArrayList<>();
        cart.add(new Item(ItemType.ELECTRONIC, "Laptop", 1, 1000.0));
        double price = extraCost.priceToAggregate(cart);
        assertThat(price).isEqualTo(7.50);
    }

    @Test
    void testPriceWithMultipleElectronics() {
        List<Item> cart = new ArrayList<>();
        cart.add(new Item(ItemType.ELECTRONIC, "Laptop", 1, 1000.0));
        cart.add(new Item(ItemType.ELECTRONIC, "Phone", 1, 500.0));
        double price = extraCost.priceToAggregate(cart);
        assertThat(price).isEqualTo(7.50);
    }

    @Test
    void testPriceWithMixedItemsIncludingElectronics() {
        List<Item> cart = new ArrayList<>();
        cart.add(new Item(ItemType.OTHER, "Book", 2, 10.0));
        cart.add(new Item(ItemType.ELECTRONIC, "Tablet", 1, 300.0));
        cart.add(new Item(ItemType.OTHER, "Clothing", 1, 20.0));
        double price = extraCost.priceToAggregate(cart);
        assertThat(price).isEqualTo(7.50);
    }
}


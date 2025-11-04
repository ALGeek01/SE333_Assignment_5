package org.example.Amazon.Cost;

import org.example.Amazon.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RegularCostTest {

    private RegularCost regularCost;

    @BeforeEach
    void setUp() {
        regularCost = new RegularCost();
    }

    @Test
    void testPriceForEmptyCart() {
        List<Item> emptyCart = new ArrayList<>();
        double price = regularCost.priceToAggregate(emptyCart);
        assertThat(price).isEqualTo(0.0);
    }

    @Test
    void testPriceForSingleItem() {
        List<Item> cart = new ArrayList<>();
        cart.add(new Item(ItemType.OTHER, "Book", 2, 10.0));
        double price = regularCost.priceToAggregate(cart);
        assertThat(price).isEqualTo(20.0);
    }

    @Test
    void testPriceForMultipleItems() {
        List<Item> cart = new ArrayList<>();
        cart.add(new Item(ItemType.OTHER, "Item1", 2, 10.0));
        cart.add(new Item(ItemType.ELECTRONIC, "Item2", 3, 15.0));
        cart.add(new Item(ItemType.OTHER, "Item3", 1, 5.0));
        double price = regularCost.priceToAggregate(cart);
        assertThat(price).isEqualTo(20.0 + 45.0 + 5.0);
    }

    @Test
    void testPriceWithZeroQuantity() {
        List<Item> cart = new ArrayList<>();
        cart.add(new Item(ItemType.OTHER, "Item1", 0, 10.0));
        double price = regularCost.priceToAggregate(cart);
        assertThat(price).isEqualTo(0.0);
    }

    @Test
    void testPriceWithDecimalPrice() {
        List<Item> cart = new ArrayList<>();
        cart.add(new Item(ItemType.OTHER, "Item1", 2, 12.50));
        double price = regularCost.priceToAggregate(cart);
        assertThat(price).isEqualTo(25.0);
    }
}


package org.example.Amazon.Cost;

import org.example.Amazon.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeliveryPriceTest {

    private DeliveryPrice deliveryPrice;

    @BeforeEach
    void setUp() {
        deliveryPrice = new DeliveryPrice();
    }

    @Test
    void testPriceForEmptyCart() {
        List<Item> emptyCart = new ArrayList<>();
        double price = deliveryPrice.priceToAggregate(emptyCart);
        assertThat(price).isEqualTo(0.0);
    }

    @Test
    void testPriceForOneItem() {
        List<Item> cart = new ArrayList<>();
        cart.add(new Item(ItemType.OTHER, "Book", 1, 10.0));
        double price = deliveryPrice.priceToAggregate(cart);
        assertThat(price).isEqualTo(5.0);
    }

    @Test
    void testPriceForThreeItems() {
        List<Item> cart = new ArrayList<>();
        cart.add(new Item(ItemType.OTHER, "Item1", 1, 10.0));
        cart.add(new Item(ItemType.OTHER, "Item2", 1, 20.0));
        cart.add(new Item(ItemType.OTHER, "Item3", 1, 30.0));
        double price = deliveryPrice.priceToAggregate(cart);
        assertThat(price).isEqualTo(5.0);
    }

    @Test
    void testPriceForFourItems() {
        List<Item> cart = new ArrayList<>();
        cart.add(new Item(ItemType.OTHER, "Item1", 1, 10.0));
        cart.add(new Item(ItemType.OTHER, "Item2", 1, 20.0));
        cart.add(new Item(ItemType.OTHER, "Item3", 1, 30.0));
        cart.add(new Item(ItemType.OTHER, "Item4", 1, 40.0));
        double price = deliveryPrice.priceToAggregate(cart);
        assertThat(price).isEqualTo(12.5);
    }

    @Test
    void testPriceForTenItems() {
        List<Item> cart = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            cart.add(new Item(ItemType.OTHER, "Item" + i, 1, 10.0 * i));
        }
        double price = deliveryPrice.priceToAggregate(cart);
        assertThat(price).isEqualTo(12.5);
    }

    @Test
    void testPriceForMoreThanTenItems() {
        List<Item> cart = new ArrayList<>();
        for (int i = 1; i <= 11; i++) {
            cart.add(new Item(ItemType.OTHER, "Item" + i, 1, 10.0 * i));
        }
        double price = deliveryPrice.priceToAggregate(cart);
        assertThat(price).isEqualTo(20.0);
    }
}


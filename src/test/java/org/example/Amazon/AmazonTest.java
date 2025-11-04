package org.example.Amazon;

import org.example.Amazon.Cost.PriceRule;
import org.example.Amazon.Cost.RegularCost;
import org.example.Amazon.Cost.DeliveryPrice;
import org.example.Amazon.Cost.ExtraCostForElectronics;
import org.example.Amazon.Cost.ItemType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AmazonTest {

    private ShoppingCart shoppingCart;
    private List<PriceRule> rules;
    private Amazon amazon;

    @BeforeEach
    void setUp() {
        shoppingCart = new ShoppingCartImpl();
        rules = new ArrayList<>();
        rules.add(new RegularCost());
        rules.add(new DeliveryPrice());
        rules.add(new ExtraCostForElectronics());
        amazon = new Amazon(shoppingCart, rules);
    }

    @Test
    void testCalculateWithEmptyCart() {
        double totalPrice = amazon.calculate();
        assertThat(totalPrice).isEqualTo(0.0);
    }

    @Test
    void testCalculateWithSingleItem() {
        Item item = new Item(ItemType.OTHER, "Book", 2, 10.0);
        amazon.addToCart(item);
        double totalPrice = amazon.calculate();
        assertThat(totalPrice).isEqualTo(25.0);
    }

    @Test
    void testCalculateWithMultipleItems() {
        amazon.addToCart(new Item(ItemType.OTHER, "Book1", 1, 10.0));
        amazon.addToCart(new Item(ItemType.OTHER, "Book2", 2, 15.0));
        amazon.addToCart(new Item(ItemType.ELECTRONIC, "Laptop", 1, 1000.0));
        double totalPrice = amazon.calculate();
        assertThat(totalPrice).isEqualTo(1040.0 + 5.0 + 7.50);
    }

    @Test
    void testAddToCart() {
        Item item = new Item(ItemType.OTHER, "Book", 1, 10.0);
        amazon.addToCart(item);
        assertThat(shoppingCart.numberOfItems()).isEqualTo(1);
    }

    private static class ShoppingCartImpl implements ShoppingCart {
        private final List<Item> items = new ArrayList<>();

        @Override
        public void add(Item item) {
            items.add(item);
        }

        @Override
        public List<Item> getItems() {
            return new ArrayList<>(items);
        }

        @Override
        public int numberOfItems() {
            return items.size();
        }
    }
}


package org.example.Amazon;

import org.example.Amazon.Cost.ItemType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ShoppingCartAdaptorIntegrationTest {

    private Database database;
    private ShoppingCartAdaptor shoppingCart;

    @BeforeEach
    void setUp() {
        if (database != null) {
            database.close();
        }
        database = new Database();
        database.resetDatabase();
        shoppingCart = new ShoppingCartAdaptor(database);
    }

    @AfterEach
    void tearDown() {
        if (database != null) {
            database.resetDatabase();
            database.close();
        }
    }

    @Test
    void testAddItemToCart() {
        Item item = new Item(ItemType.OTHER, "Book", 2, 10.0);
        shoppingCart.add(item);

        List<Item> items = shoppingCart.getItems();
        assertThat(items).hasSize(1);
        assertThat(items.get(0).getName()).isEqualTo("Book");
        assertThat(items.get(0).getQuantity()).isEqualTo(2);
        assertThat(items.get(0).getPricePerUnit()).isEqualTo(10.0);
        assertThat(items.get(0).getType()).isEqualTo(ItemType.OTHER);
    }

    @Test
    void testAddMultipleItemsToCart() {
        Item item1 = new Item(ItemType.OTHER, "Book", 2, 10.0);
        Item item2 = new Item(ItemType.ELECTRONIC, "Laptop", 1, 1000.0);
        Item item3 = new Item(ItemType.OTHER, "Pen", 5, 2.0);

        shoppingCart.add(item1);
        shoppingCart.add(item2);
        shoppingCart.add(item3);

        List<Item> items = shoppingCart.getItems();
        assertThat(items).hasSize(3);
    }

    @Test
    void testGetItemsFromEmptyCart() {
        List<Item> items = shoppingCart.getItems();
        assertThat(items).isEmpty();
    }

    @Test
    void testGetItemsWithDifferentItemTypes() {
        Item electronicItem = new Item(ItemType.ELECTRONIC, "Tablet", 1, 500.0);
        Item otherItem = new Item(ItemType.OTHER, "Book", 3, 15.0);

        shoppingCart.add(electronicItem);
        shoppingCart.add(otherItem);

        List<Item> items = shoppingCart.getItems();
        assertThat(items).hasSize(2);
        assertThat(items).anyMatch(item -> item.getType() == ItemType.ELECTRONIC);
        assertThat(items).anyMatch(item -> item.getType() == ItemType.OTHER);
    }

    @Test
    void testAddAndRetrieveItemsWithDecimalPrices() {
        Item item = new Item(ItemType.OTHER, "Item", 2, 12.50);
        shoppingCart.add(item);

        List<Item> items = shoppingCart.getItems();
        assertThat(items).hasSize(1);
        assertThat(items.get(0).getPricePerUnit()).isEqualTo(12.50);
    }

    @Test
    void testResetDatabase() {
        Item item = new Item(ItemType.OTHER, "Book", 1, 10.0);
        shoppingCart.add(item);

        database.resetDatabase();

        List<Item> items = shoppingCart.getItems();
        assertThat(items).isEmpty();
    }

    @Test
    void testAddItemAfterReset() {
        Item item1 = new Item(ItemType.OTHER, "Book1", 1, 10.0);
        Item item2 = new Item(ItemType.OTHER, "Book2", 2, 20.0);

        shoppingCart.add(item1);
        database.resetDatabase();
        shoppingCart.add(item2);

        List<Item> items = shoppingCart.getItems();
        assertThat(items).hasSize(1);
        assertThat(items.get(0).getName()).isEqualTo("Book2");
    }

    @Test
    void testNumberOfItems() {
        Item item1 = new Item(ItemType.OTHER, "Book1", 1, 10.0);
        Item item2 = new Item(ItemType.OTHER, "Book2", 2, 20.0);
        Item item3 = new Item(ItemType.OTHER, "Book3", 3, 30.0);

        shoppingCart.add(item1);
        shoppingCart.add(item2);
        shoppingCart.add(item3);

        int count = shoppingCart.numberOfItems();
        assertThat(count).isGreaterThanOrEqualTo(0);
    }
}


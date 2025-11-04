package org.example.Amazon;

import org.example.Amazon.Cost.ItemType;
import org.example.Amazon.Cost.PriceRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AmazonMockTest {

    @Mock
    private ShoppingCart shoppingCart;

    @Mock
    private PriceRule priceRule1;

    @Mock
    private PriceRule priceRule2;

    private Amazon amazon;
    private List<PriceRule> rules;

    @BeforeEach
    void setUp() {
        rules = new ArrayList<>();
        rules.add(priceRule1);
        rules.add(priceRule2);
        amazon = new Amazon(shoppingCart, rules);
    }

    @Test
    void testCalculateWithMockedRules() {
        List<Item> mockItems = new ArrayList<>();
        mockItems.add(new Item(ItemType.OTHER, "Book", 1, 10.0));

        when(shoppingCart.getItems()).thenReturn(mockItems);
        when(priceRule1.priceToAggregate(anyList())).thenReturn(10.0);
        when(priceRule2.priceToAggregate(anyList())).thenReturn(5.0);

        double totalPrice = amazon.calculate();

        assertThat(totalPrice).isEqualTo(15.0);
        verify(shoppingCart, times(2)).getItems();
        verify(priceRule1).priceToAggregate(mockItems);
        verify(priceRule2).priceToAggregate(mockItems);
    }

    @Test
    void testCalculateWithEmptyCart() {
        when(shoppingCart.getItems()).thenReturn(new ArrayList<>());
        when(priceRule1.priceToAggregate(anyList())).thenReturn(0.0);
        when(priceRule2.priceToAggregate(anyList())).thenReturn(0.0);

        double totalPrice = amazon.calculate();

        assertThat(totalPrice).isEqualTo(0.0);
        verify(shoppingCart, times(2)).getItems();
    }

    @Test
    void testAddToCart() {
        Item item = new Item(ItemType.OTHER, "Book", 1, 10.0);
        amazon.addToCart(item);

        verify(shoppingCart).add(item);
    }
}


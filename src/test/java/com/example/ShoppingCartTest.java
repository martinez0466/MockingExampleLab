package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ShoppingCartTest {
    ShoppingCart shoppingCart = new ShoppingCart();

    @Test
    void createShoppingCartList() {
        ShoppingCart shoppingCart = new ShoppingCart();
        assertThat(shoppingCart.getItemCount()).isEqualTo(0);
    }

    @Test
    void newShoppingCartIsEmpty() {
        assertThat(shoppingCart.getItemCount()).isEqualTo(0);
    }

    @Test
    void addItemToShoppingCart() {
        shoppingCart.addItem("Item1", 1, 10.0);
        assertThat(shoppingCart.getItemCount()).isEqualTo(1);
    }

    @DisplayName("Remove item from shoppingCart")
    @Test
    void testRemoveItem() {
        shoppingCart.addItem("Item1", 1, 10.0);
        shoppingCart.removeItem("Item1");
        assertThat(shoppingCart.getItemCount()).isEqualTo(0);
    }

    @DisplayName("Update Quantity of item")
    @Test
    void updateQuantity() {
        shoppingCart.addItem("Item1", 1, 10.0);
        shoppingCart.updateQuantity("Item1", 3);
        assertThat(shoppingCart.getQuantity("Item1")).isEqualTo(3);
    }

    @DisplayName("Calculate total price of items")
    @Test
    void calculateTotalPrice() {
        shoppingCart.addItem("Item1", 2, 10.0);
        shoppingCart.addItem("Item2", 3, 5.0);
        assertThat(shoppingCart.calculateTotalPrice()).isEqualTo(35.0);
    }

    @DisplayName("Apply discount to an item")
    @Test
    void applyDiscount() {
        shoppingCart.addItem("Item1", 1, 10.0);
        shoppingCart.applyDiscount("Item1", 10);
        assertThat(shoppingCart.calculateTotalPrice()).isEqualTo(9.0);
    }

    @DisplayName("Remove non-existing item from shopping cart")
    @Test
    void removeNonExistingItem() {
        shoppingCart.removeItem("NonExistingItem");
        assertThat(shoppingCart.getItemCount()).isEqualTo(0);
    }

    @Test
    void getReturnsAddedString() {
        shoppingCart.addItem("Hello", 1, 10.0);
        assertThat(shoppingCart.get(0)).isEqualTo("Hello");
    }

    @Test
    void getReturnsAnotherAddedString() {
        shoppingCart.addItem("World", 1, 10.0);
        assertThat(shoppingCart.get(0)).isEqualTo("World");
    }

    @Test
    void addTwoStringsAndReturnFirstUsingIndex() {
        shoppingCart.addItem("Hello", 1, 10.0);
        shoppingCart.addItem("World", 1, 10.0);
        assertThat(shoppingCart.get(0)).isEqualTo("Hello");
        assertThat(shoppingCart.get(1)).isEqualTo("World");
    }
}
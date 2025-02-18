package com.example;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addItem(String item, int quantity, double price) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        for (CartItem cartItem : items) {
            if (cartItem.getName().equals(item)) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(item, quantity, price));
    }

    public int getItemCount() {
        return items.size();
    }

    public void removeItem(String item) {
        items.removeIf(cartItem -> cartItem.getName().equals(item));
    }

    public String get(int index) {
        if (index < 0 || index >= items.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return items.get(index).getName();
    }

    public void updateQuantity(String item, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative: " + quantity);
        }
        for (CartItem cartItem : items) {
            if (cartItem.getName().equals(item)) {
                cartItem.setQuantity(quantity); // Ändrat från att addera till att ersätta
                return;
            }
        }
        throw new IllegalArgumentException("Item not found: " + item);
    }

    public double calculateTotalPrice() {
        return items.stream()
                .mapToDouble(item -> item.getQuantity() * item.getPrice())
                .sum();
    }

    public void applyDiscount(String item, double discountPercentage) {
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Discount must be between 0 and 100.");
        }
        for (CartItem cartItem : items) {
            if (cartItem.getName().equals(item)) {
                double discountedPrice = cartItem.getPrice() * (1 - discountPercentage / 100);
                cartItem.setPrice(Math.max(discountedPrice, 0)); // Se till att priset aldrig blir negativt
                return;
            }
        }
        throw new IllegalArgumentException("Item not found: " + item);
    }

    public int getQuantity(String item) {
        for (CartItem cartItem : items) {
            if (cartItem.getName().equals(item)) {
                return cartItem.getQuantity();
            }
        }
        return 0;
    }
}

package com.retailstore.model;

import com.retailstore.enums.ItemType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Set;

class ItemTest {

    private final Validator validator;

    public ItemTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testItemConstructorValidInput() {
        // Arrange
        String itemId = "item1";
        String name = "Item Name";
        int quantity = 1;
        ItemType type = ItemType.GROCERIES;
        BigDecimal totalPrice = BigDecimal.valueOf(10.0);

        // Act
        Item item = new Item(itemId, name, quantity, type, totalPrice);

        // Assert
        assertNotNull(item);
        assertEquals(itemId, item.getItemId());
        assertEquals(name, item.getName());
        assertEquals(quantity, item.getQuantity());
        assertEquals(type, item.getType());
        assertEquals(totalPrice, item.getTotalPrice());
    }

    @Test
    void testItemConstructorWithNullItemId() {
        // Arrange
        String name = "Item Name";
        int quantity = 1;
        ItemType type = ItemType.GROCERIES;
        BigDecimal totalPrice = BigDecimal.valueOf(10.0);

        // Act
        Item item = new Item(null, name, quantity, type, totalPrice);

        // Assert
        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("ItemId Cannot be empty")));
    }

    @Test
    void testItemConstructorWithEmptyItemId() {
        // Arrange
        String itemId = "";
        String name = "Item Name";
        int quantity = 1;
        ItemType type = ItemType.GROCERIES;
        BigDecimal totalPrice = BigDecimal.valueOf(10.0);

        // Act
        Item item = new Item(itemId, name, quantity, type, totalPrice);

        // Assert
        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("ItemId Cannot be empty")));
    }

    @Test
    void testItemConstructorWithNullName() {
        // Arrange
        String itemId = "item1";
        int quantity = 1;
        ItemType type = ItemType.GROCERIES;
        BigDecimal totalPrice = BigDecimal.valueOf(10.0);

        // Act
        Item item = new Item(itemId, null, quantity, type, totalPrice);

        // Assert
        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Item Name Cannot be empty")));
    }

    @Test
    void testItemConstructorWithEmptyName() {
        // Arrange
        String itemId = "item1";
        String name = "";
        int quantity = 1;
        ItemType type = ItemType.GROCERIES;
        BigDecimal totalPrice = BigDecimal.valueOf(10.0);

        // Act
        Item item = new Item(itemId, name, quantity, type, totalPrice);

        // Assert
        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Item Name Cannot be empty")));
    }

    @Test
    void testItemConstructorWithNegativeQuantity() {
        // Arrange
        String itemId = "item1";
        String name = "Item Name";
        int quantity = -1;
        ItemType type = ItemType.GROCERIES;
        BigDecimal totalPrice = BigDecimal.valueOf(10.0);

        // Act
        Item item = new Item(itemId, name, quantity, type, totalPrice);

        // Assert
        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Quantity must be at least 1")));
    }

    @Test
    void testItemConstructorWithZeroQuantity() {
        // Arrange
        String itemId = "item1";
        String name = "Item Name";
        int quantity = 0;
        ItemType type = ItemType.GROCERIES;
        BigDecimal totalPrice = BigDecimal.valueOf(10.0);

        // Act
        Item item = new Item(itemId, name, quantity, type, totalPrice);

        // Assert
        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Quantity must be at least 1")));
    }

    @Test
    void testItemConstructorWithNullType() {
        // Arrange
        String itemId = "item1";
        String name = "Item Name";
        int quantity = 1;
        BigDecimal totalPrice = BigDecimal.valueOf(10.0);

        // Act
        Item item = new Item(itemId, name, quantity, null, totalPrice);

        // Assert
        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Item Type Cannot be Empty")));
    }

    @Test
    void testItemConstructorWithNegativePrice() {
        // Arrange
        String itemId = "item1";
        String name = "Item Name";
        int quantity = 1;
        ItemType type = ItemType.GROCERIES;
        BigDecimal totalPrice = BigDecimal.valueOf(0.0);

        // Act
        Item item = new Item(itemId, name, quantity, type, totalPrice);

        // Assert
        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Price should be positive")));
    }

    @Test
    void testItemConstructorWithZeroPrice() {
        // Arrange
        String itemId = "item1";
        String name = "Item Name";
        int quantity = 1;
        ItemType type = ItemType.GROCERIES;
        BigDecimal totalPrice = BigDecimal.valueOf(0.0);

        // Act
        Item item = new Item(itemId, name, quantity, type, totalPrice);

        // Assert
        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Price should be positive")));
    }
}


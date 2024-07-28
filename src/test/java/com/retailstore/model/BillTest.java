package com.retailstore.model;

import com.retailstore.enums.ItemType;
import com.retailstore.enums.UserType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BillTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testBillCreationWithValidData() {
        // Arrange
        User user = new User("user1", "User Name", UserType.CUSTOMER, LocalDate.now().minusYears(1));
        Item item = new Item("item1", "Item Name", 1, ItemType.NON_GROCERIES, BigDecimal.valueOf(100.0));
        Bill bill = new Bill("bill1", user, Collections.singletonList(item));

        // Act
        Set<ConstraintViolation<Bill>> violations = validator.validate(bill);

        // Assert
        assertTrue(violations.isEmpty(), "There should be no constraint violations for valid data");
        assertEquals(bill.getBillId(),bill.getBillId());
        assertEquals(bill.getUser(),user);
        assertEquals(bill.getItems().get(0),item);

    }

    @Test
     void testBillCreationWithBlankBillId() {
        // Arrange
        User user = new User("user1", "User Name", UserType.CUSTOMER, LocalDate.now().minusYears(1));
        Item item = new Item("item1", "Item Name", 1, ItemType.NON_GROCERIES, BigDecimal.valueOf(100.0));

        // Act
        Bill bill = new Bill("", user, Collections.singletonList(item));
        Set<ConstraintViolation<Bill>> violations = validator.validate(bill);

        // Assert
        assertFalse(violations.isEmpty(), "There should be constraint violations for blank bill ID");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Bill ID cannot be empty")));

    }

    @Test
     void testBillCreationWithNullUser() {
        // Arrange
        Item item = new Item("item1", "Item Name", 1, ItemType.NON_GROCERIES, BigDecimal.valueOf(100.0));

        // Act
        Bill bill = new Bill("bill1", null, Collections.singletonList(item));
        Set<ConstraintViolation<Bill>> violations = validator.validate(bill);

        // Assert
        assertFalse(violations.isEmpty(), "There should be constraint violations for null user");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("User Cannot be null")));
    }


    @Test
     void testBillCreationWithEmptyItems() {
        // Arrange
        User user = new User("user1", "User Name", UserType.CUSTOMER, LocalDate.now().minusYears(1));

        // Act
        Bill bill = new Bill("bill1", user, Collections.emptyList());
        Set<ConstraintViolation<Bill>> violations = validator.validate(bill);

        // Assert
        assertFalse(violations.isEmpty(), "There should be constraint violations for empty items list");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Add at least one item")));
    }
}


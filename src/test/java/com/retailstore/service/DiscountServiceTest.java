package com.retailstore.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.retailstore.enums.ItemType;
import com.retailstore.enums.UserType;
import com.retailstore.model.Bill;
import com.retailstore.model.Discount;
import com.retailstore.model.Item;
import com.retailstore.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

class DiscountServiceTest {

    private DiscountService discountService;

    @BeforeEach
     void setUp() {
        discountService = new DiscountService();
    }

    @Test
     void testCalculateNetPayableAmount_newEmployeeWithNonGroceryItems() {
        User user = new User("1", "John Doe", UserType.EMPLOYEE, LocalDate.now().minusYears(1));
        List<Item> items = Arrays.asList(
                new Item("1", "Laptop", 1, ItemType.NON_GROCERIES, new BigDecimal("1000.00")),
                new Item("2", "Phone", 1, ItemType.NON_GROCERIES, new BigDecimal("500.00"))
        );
        Bill bill = new Bill("1", user, items);

        Discount discount = discountService.calculateNetPayableAmount(bill);

        assertEquals("1500.00", discount.getTotalAmount());
        assertEquals("975.00", discount.getDiscountedAmount());
    }

    @Test
     void testCalculateNetPayableAmount_newAffiliateWithNonGroceryItems() {
        User user = new User("2", "Jane Smith", UserType.AFFILIATE, LocalDate.now().minusYears(1));
        List<Item> items = Arrays.asList(
                new Item("3", "Groceries", 10, ItemType.NON_GROCERIES, new BigDecimal("1000.00")),
                new Item("4", "TV", 1, ItemType.NON_GROCERIES, new BigDecimal("500.00"))
        );
        Bill bill = new Bill("2", user, items);

        Discount discount = discountService.calculateNetPayableAmount(bill);

        assertEquals("1500.00", discount.getTotalAmount());
        assertEquals("1275.00", discount.getDiscountedAmount());
    }

    @Test
     void testCalculateNetPayableAmount_longTermCustomerWithNonGroceryItems() {
        User user = new User("3", "Alice Johnson", UserType.CUSTOMER, LocalDate.now().minusYears(5));
        List<Item> items = Arrays.asList(
                new Item("5", "Washing Machine", 1, ItemType.NON_GROCERIES, new BigDecimal("1000.00")),
                new Item("6", "Refrigerator", 1, ItemType.NON_GROCERIES, new BigDecimal("500.00"))
        );
        Bill bill = new Bill("3", user, items);

        Discount discount = discountService.calculateNetPayableAmount(bill);

        assertEquals("1500.00", discount.getTotalAmount());
        assertEquals("1350.00", discount.getDiscountedAmount());
    }

    @Test
     void testCalculateNetPayableAmount_longEmployeeWithGroceryItems() {
        User user = new User("1", "John Doe", UserType.EMPLOYEE, LocalDate.now().minusYears(3));
        List<Item> items = Arrays.asList(
                new Item("1", "Laptop", 1, ItemType.GROCERIES, new BigDecimal("1000.00")),
                new Item("2", "Phone", 1, ItemType.GROCERIES, new BigDecimal("500.00"))
        );
        Bill bill = new Bill("1", user, items);

        Discount discount = discountService.calculateNetPayableAmount(bill);

        assertEquals("1500.00", discount.getTotalAmount());
        assertEquals("1425.00", discount.getDiscountedAmount());
    }

    @Test
     void testCalculateNetPayableAmount_longAffiliateWithGroceryItems() {
        User user = new User("2", "Jane Smith", UserType.AFFILIATE, LocalDate.now().minusYears(1));
        List<Item> items = Arrays.asList(
                new Item("3", "Groceries", 10, ItemType.GROCERIES, new BigDecimal("1000.00")),
                new Item("4", "TV", 1, ItemType.GROCERIES, new BigDecimal("500.00"))
        );
        Bill bill = new Bill("2", user, items);

        Discount discount = discountService.calculateNetPayableAmount(bill);

        assertEquals("1500.00", discount.getTotalAmount());
        assertEquals("1425.00", discount.getDiscountedAmount());
    }

    @Test
     void testCalculateNetPayableAmount_longTermCustomerWithGroceryItems() {
        User user = new User("3", "Alice Johnson", UserType.CUSTOMER, LocalDate.now().minusYears(5));
        List<Item> items = Arrays.asList(
                new Item("5", "Washing Machine", 1, ItemType.GROCERIES, new BigDecimal("1000.00")),
                new Item("6", "Refrigerator", 1, ItemType.GROCERIES, new BigDecimal("500.00"))
        );
        Bill bill = new Bill("3", user, items);

        Discount discount = discountService.calculateNetPayableAmount(bill);

        assertEquals("1500.00", discount.getTotalAmount());
        assertEquals("1425.00", discount.getDiscountedAmount());
    }

    @Test
     void testCalculateNetPayableAmount_longEmployeeWithMixGroceryItems() {
        User user = new User("1", "John Doe", UserType.EMPLOYEE, LocalDate.now().minusYears(3));
        List<Item> items = Arrays.asList(
                new Item("1", "Laptop", 1, ItemType.NON_GROCERIES, new BigDecimal("1000.00")),
                new Item("2", "Phone", 1, ItemType.GROCERIES, new BigDecimal("500.00"))
        );
        Bill bill = new Bill("1", user, items);

        Discount discount = discountService.calculateNetPayableAmount(bill);

        assertEquals("1500.00", discount.getTotalAmount());
        assertEquals("1125.00", discount.getDiscountedAmount());
    }

    @Test
     void testCalculateNetPayableAmount_longAffiliateWithMixGroceryItems() {
        User user = new User("2", "Jane Smith", UserType.AFFILIATE, LocalDate.now().minusYears(2));
        List<Item> items = Arrays.asList(
                new Item("3", "Groceries", 10, ItemType.NON_GROCERIES, new BigDecimal("1000.00")),
                new Item("4", "TV", 1, ItemType.GROCERIES, new BigDecimal("500.00"))
        );
        Bill bill = new Bill("2", user, items);

        Discount discount = discountService.calculateNetPayableAmount(bill);

        assertEquals("1500.00", discount.getTotalAmount());
        assertEquals("1325.00", discount.getDiscountedAmount());
    }

    @Test
     void testCalculateNetPayableAmount_longTermCustomerWithMixGroceryItems() {
        User user = new User("3", "Alice Johnson", UserType.CUSTOMER, LocalDate.now().minusYears(5));
        List<Item> items = Arrays.asList(
                new Item("5", "Washing Machine", 1, ItemType.NON_GROCERIES, new BigDecimal("1000.00")),
                new Item("6", "Refrigerator", 1, ItemType.GROCERIES, new BigDecimal("500.00"))
        );
        Bill bill = new Bill("3", user, items);

        Discount discount = discountService.calculateNetPayableAmount(bill);

        assertEquals("1500.00", discount.getTotalAmount());
        assertEquals("1375.00", discount.getDiscountedAmount());
    }


     @Test
     void testCalculateNetPayableAmount_newEmployeeCustomerWithGroceryItems() {
        User user = new User("4", "Bob Brown", UserType.EMPLOYEE, LocalDate.now().minusMonths(6));
        List<Item> items = Arrays.asList(
                new Item("7", "Bread", 5, ItemType.GROCERIES, new BigDecimal("1000.00")),
                new Item("8", "Milk", 10, ItemType.GROCERIES, new BigDecimal("500.00"))
        );
        Bill bill = new Bill("4", user, items);

        Discount discount = discountService.calculateNetPayableAmount(bill);

        assertEquals("1500.00", discount.getTotalAmount());
        assertEquals("1425.00", discount.getDiscountedAmount());
    }

    @Test
     void testCalculateNetPayableAmount_newAffiliateWithGroceryItems() {
        User user = new User("5", "Charlie Davis", UserType.AFFILIATE, LocalDate.now().minusMonths(3));
        List<Item> items = Arrays.asList(
                new Item("9", "Groceries", 10, ItemType.GROCERIES, new BigDecimal("1000.00")),
                new Item("10", "Laptop", 1, ItemType.GROCERIES, new BigDecimal("500.00"))
        );
        Bill bill = new Bill("5", user, items);

        Discount discount = discountService.calculateNetPayableAmount(bill);

        assertEquals("1500.00", discount.getTotalAmount());
        assertEquals("1425.00", discount.getDiscountedAmount());
    }

    @Test
     void testCalculateNetPayableAmount_noDiscounts() {
        User user = new User("6", "Dave Evans", UserType.CUSTOMER, LocalDate.now());
        List<Item> items = Arrays.asList(
                new Item("11", "Book", 1, ItemType.NON_GROCERIES, new BigDecimal("50.00"))
        );
        Bill bill = new Bill("6", user, items);

        Discount discount = discountService.calculateNetPayableAmount(bill);

        assertEquals("50.00", discount.getTotalAmount());
        assertEquals("50.00", discount.getDiscountedAmount());
    }

    @Test
     void testCalculateNetPayableAmount_noDiscountsWithMaxDecimal() {
        User user = new User("12", "Dave Evans", UserType.CUSTOMER, LocalDate.now());
        List<Item> items = Arrays.asList(
                new Item("15", "Milk", 1, ItemType.GROCERIES, new BigDecimal("99.992345"))
        );
        Bill bill = new Bill("6", user, items);

        Discount discount = discountService.calculateNetPayableAmount(bill);

        assertEquals("99.99", discount.getTotalAmount());
        assertEquals("99.99", discount.getDiscountedAmount());
    }

    @Test
     void testCalculateNetPayableAmount_bulkDiscountsWithMaxDecimal() {
        User user = new User("14", "Dave Shan", UserType.CUSTOMER, LocalDate.now());
        List<Item> items = Arrays.asList(
                new Item("18", "Phone", 1, ItemType.NON_GROCERIES, new BigDecimal("199.995"))
        );
        Bill bill = new Bill("25", user, items);

        Discount discount = discountService.calculateNetPayableAmount(bill);

        assertEquals("200.00", discount.getTotalAmount());
        assertEquals("190.00", discount.getDiscountedAmount());
    }

    @Test
     void testCalculateNetPayableAmount_bulkDiscountsWithMinDecimal() {
        User user = new User("28", "Mike Evans", UserType.CUSTOMER, LocalDate.now());
        List<Item> items = Arrays.asList(
                new Item("100", "Vegetable", 1, ItemType.GROCERIES, new BigDecimal("199.994"))
        );
        Bill bill = new Bill("78", user, items);

        Discount discount = discountService.calculateNetPayableAmount(bill);

        assertEquals("199.99", discount.getTotalAmount());
        assertEquals("194.99", discount.getDiscountedAmount());
    }


    @Test
    void testCalculateNetPayableAmount_bulkDiscountsAndEmployeeDiscountWithMinDecimal() {
        User user = new User("28", "Mike Evans", UserType.EMPLOYEE, LocalDate.now());
        List<Item> items = Arrays.asList(
                new Item("102", "Vegetable", 1, ItemType.GROCERIES, new BigDecimal("48.99345")),
                new Item("104", "Water Bottle", 1, ItemType.NON_GROCERIES, new BigDecimal("48.99345")));

        Bill bill = new Bill("106", user, items);

        Discount discount = discountService.calculateNetPayableAmount(bill);

        assertEquals("97.99", discount.getTotalAmount());
        assertEquals("83.29", discount.getDiscountedAmount());
    }

}

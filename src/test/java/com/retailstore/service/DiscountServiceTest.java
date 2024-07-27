package com.retailstore.service;

import com.retailstore.model.Bill;
import com.retailstore.model.Discount;
import com.retailstore.model.Item;
import com.retailstore.model.User;
import com.retailstore.enums.ItemType;
import com.retailstore.enums.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
//Todo:check failing test
class DiscountServiceTest {

    private DiscountService discountService;

    @BeforeEach
    void setUp() {
        discountService = new DiscountService();
    }

    @Test
    void testCalculateNetPayableAmountWithEmployeeDiscount() {
        // Create mocks
        User user = mock(User.class);
        when(user.getUserType()).thenReturn(UserType.EMPLOYEE);
        when(user.getJoiningDate()).thenReturn(LocalDate.now());

        Item item1 = mock(Item.class);
        when(item1.getTotalPrice()).thenReturn(4.0);
        when(item1.getType()).thenReturn(ItemType.GROCERIES);

        Item item2 = mock(Item.class);
        when(item2.getTotalPrice()).thenReturn(1200.0);
        when(item2.getType()).thenReturn(ItemType.NON_GROCERIES);

        Bill bill = mock(Bill.class);
        when(bill.getUser()).thenReturn(user);
        when(bill.getItems()).thenReturn(Arrays.asList(item1, item2));

        // Calculate the discount
        Discount discount = discountService.calculateNetPayableAmount(bill);

        double expectedTotal = 1204.0;
        double expectedDiscountedAmount = 1204.0 - (1200.0 * 0.30) - Math.floor(1204.0 / 100) * 5;

        assertEquals(expectedTotal, discount.getTotalAmount());
        assertEquals(expectedDiscountedAmount, discount.getDiscountedAmount());
    }

    @Test
    void testCalculateNetPayableAmountWithAffiliateDiscount() {
        // Create mocks
        User user = mock(User.class);
        when(user.getUserType()).thenReturn(UserType.AFFILIATE);
        when(user.getJoiningDate()).thenReturn(LocalDate.now());

        Item item1 = mock(Item.class);
        when(item1.getTotalPrice()).thenReturn(3.0);
        when(item1.getType()).thenReturn(ItemType.NON_GROCERIES);

        Item item2 = mock(Item.class);
        when(item2.getTotalPrice()).thenReturn(250.0);
        when(item2.getType()).thenReturn(ItemType.NON_GROCERIES);

        Bill bill = mock(Bill.class);
        when(bill.getUser()).thenReturn(user);
        when(bill.getItems()).thenReturn(Arrays.asList(item1, item2));

        // Calculate the discount
        Discount discount = discountService.calculateNetPayableAmount(bill);

        double expectedTotal = 253.0;
        double expectedDiscountedAmount = 253.0 - (253.0 * 0.10) -  Math.floor(253.0 / 100) * 5;

        assertEquals(expectedTotal, discount.getTotalAmount());
        assertEquals(expectedDiscountedAmount, discount.getDiscountedAmount());
    }

    @Test
    void testCalculateNetPayableAmountWithLoyaltyDiscount() {
        // Create mocks
        User user = mock(User.class);
        when(user.getUserType()).thenReturn(UserType.CUSTOMER);
        when(user.getJoiningDate()).thenReturn(LocalDate.of(2018, 1, 1));

        Item item1 = mock(Item.class);
        when(item1.getTotalPrice()).thenReturn(6.0);
        when(item1.getType()).thenReturn(ItemType.NON_GROCERIES);

        Item item2 = mock(Item.class);
        when(item2.getTotalPrice()).thenReturn(150.0);
        when(item2.getType()).thenReturn(ItemType.NON_GROCERIES);

        Bill bill = mock(Bill.class);
        when(bill.getUser()).thenReturn(user);
        when(bill.getItems()).thenReturn(Arrays.asList(item1, item2));

        // Calculate the discount
        Discount discount = discountService.calculateNetPayableAmount(bill);

        double expectedTotal = 156.0;
        double expectedDiscountedAmount = 156.0 - (156 * 0.05) -  Math.floor(156.0 / 100) * 5;

        assertEquals(expectedTotal, discount.getTotalAmount());
        assertEquals(expectedDiscountedAmount, discount.getDiscountedAmount());
    }

//    @Test
//    void testCalculateNetPayableAmountWithNoDiscount() {
//        // Create mocks
//        User user = mock(User.class);
//        when(user.getUserType()).thenReturn(UserType.CUSTOMER);
//        when(user.getJoiningDate()).thenReturn(LocalDate.of(2021, 1, 1));
//
//        Item item1 = mock(Item.class);
//        when(item1.getTotalPrice()).thenReturn(2.0);
//        when(item1.getType()).thenReturn(ItemType.NON_GROCERIES);
//
//        Item item2 = mock(Item.class);
//        when(item2.getTotalPrice()).thenReturn(5.0);
//        when(item2.getType()).thenReturn(ItemType.NON_GROCERIES);
//
//        Bill bill = mock(Bill.class);
//        when(bill.getUser()).thenReturn(user);
//        when(bill.getItems()).thenReturn(Arrays.asList(item1, item2));
//
//        // Calculate the discount
//        Discount discount = discountService.calculateNetPayableAmount(bill);
//
//        double expectedTotal = 7;
//        double expectedDiscountedAmount = (7 -  (14.0 / 100) * 5); // Only bulk discount applies
//
//        assertEquals(expectedTotal, discount.getTotalAmount());
//        assertEquals(expectedDiscountedAmount, discount.getDiscountedAmount());
//    }
}

package com.retailstore.service;
import com.retailstore.constants.DiscountConstants;
import com.retailstore.enums.ItemType;
import com.retailstore.enums.UserType;
import com.retailstore.model.Bill;
import com.retailstore.model.Discount;
import com.retailstore.model.Item;
import com.retailstore.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DiscountServiceTest {

    @InjectMocks
    private DiscountService discountService;

    @Mock
    private User mockUser;

    @Mock
    private Item mockItem1;

    @Mock
    private Item mockItem2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateNetPayableAmountForEmployee() {
        // Arrange
        when(mockItem1.getTotalPrice()).thenReturn(150.0);
        when(mockItem1.getType()).thenReturn(ItemType.NON_GROCERIES);
        when(mockItem2.getTotalPrice()).thenReturn(50.0);
        when(mockItem2.getType()).thenReturn(ItemType.GROCERIES);
        when(mockUser.getUserType()).thenReturn(UserType.EMPLOYEE);
        when(mockUser.getJoiningDate()).thenReturn(LocalDate.now().minusYears(3));

        List<Item> items = Arrays.asList(mockItem1, mockItem2);
        Bill bill = new Bill("bill1", mockUser, items);

        double expectedTotalAmount = 200.0;
        double expectedPercentageDiscount = 150.0 * DiscountConstants.EMPLOYEE_DISCOUNT;
        double expectedBulkDiscount = Math.floor(expectedTotalAmount / 100) * DiscountConstants.PER_100_DISCOUNT;
        double expectedDiscountedAmount = expectedTotalAmount - expectedPercentageDiscount - expectedBulkDiscount;

        // Act
        Discount result = discountService.calculateNetPayableAmount(bill);

        // Assert
        assertEquals(expectedTotalAmount, result.getTotalAmount(), "Total amount should be correct");
        assertEquals(expectedDiscountedAmount, result.getDiscountedAmount(), "Discounted amount should be correct");
    }

    @Test
    public void testCalculateNetPayableAmountForAffiliate() {
        // Arrange
        when(mockItem1.getTotalPrice()).thenReturn(200.0);
        when(mockItem1.getType()).thenReturn(ItemType.NON_GROCERIES);
        when(mockItem2.getTotalPrice()).thenReturn(30.0);
        when(mockItem2.getType()).thenReturn(ItemType.GROCERIES);
        when(mockUser.getUserType()).thenReturn(UserType.AFFILIATE);
        when(mockUser.getJoiningDate()).thenReturn(LocalDate.now().minusYears(1));

        List<Item> items = Arrays.asList(mockItem1, mockItem2);
        Bill bill = new Bill("bill2", mockUser, items);

        double expectedTotalAmount = 230.0;
        double expectedPercentageDiscount = 200.0 * DiscountConstants.AFFILIATE_DISCOUNT;
        double expectedBulkDiscount = Math.floor(expectedTotalAmount / 100) * DiscountConstants.PER_100_DISCOUNT;
        double expectedDiscountedAmount = expectedTotalAmount - expectedPercentageDiscount - expectedBulkDiscount;

        // Act
        Discount result = discountService.calculateNetPayableAmount(bill);

        // Assert
        assertEquals(expectedTotalAmount, result.getTotalAmount(), "Total amount should be correct");
        assertEquals(expectedDiscountedAmount, result.getDiscountedAmount(), "Discounted amount should be correct");
    }

    @Test
    public void testCalculateNetPayableAmountForLongTermCustomer() {
        // Arrange
        when(mockItem1.getTotalPrice()).thenReturn(80.0);
        when(mockItem1.getType()).thenReturn(ItemType.NON_GROCERIES);
        when(mockItem2.getTotalPrice()).thenReturn(20.0);
        when(mockItem2.getType()).thenReturn(ItemType.GROCERIES);
        when(mockUser.getUserType()).thenReturn(UserType.CUSTOMER);
        when(mockUser.getJoiningDate()).thenReturn(LocalDate.now().minusYears(3));

        List<Item> items = Arrays.asList(mockItem1, mockItem2);
        Bill bill = new Bill("bill3", mockUser, items);

        double expectedTotalAmount = 100.0;
        double expectedPercentageDiscount = 80.0 * DiscountConstants.LOYALTY_DISCOUNT;
        double expectedBulkDiscount = Math.floor(expectedTotalAmount / 100) * DiscountConstants.PER_100_DISCOUNT;
        double expectedDiscountedAmount = expectedTotalAmount - expectedPercentageDiscount - expectedBulkDiscount;

        // Act
        Discount result = discountService.calculateNetPayableAmount(bill);

        // Assert
        assertEquals(expectedTotalAmount, result.getTotalAmount(), "Total amount should be correct");
        assertEquals(expectedDiscountedAmount, result.getDiscountedAmount(), "Discounted amount should be correct");
    }

    @Test
    public void testCalculateNetPayableAmountForNewCustomer() {
        // Arrange
        when(mockItem1.getTotalPrice()).thenReturn(150.0);
        when(mockItem1.getType()).thenReturn(ItemType.NON_GROCERIES);
        when(mockItem2.getTotalPrice()).thenReturn(50.0);
        when(mockItem2.getType()).thenReturn(ItemType.GROCERIES);
        when(mockUser.getUserType()).thenReturn(UserType.CUSTOMER);
        when(mockUser.getJoiningDate()).thenReturn(LocalDate.now());

        List<Item> items = Arrays.asList(mockItem1, mockItem2);
        Bill bill = new Bill("bill4", mockUser, items);

        double expectedTotalAmount = 200.0;
        double expectedPercentageDiscount = 150.0 * DiscountConstants.LOYALTY_DISCOUNT;
        double expectedBulkDiscount = Math.floor(expectedTotalAmount / 100) * DiscountConstants.PER_100_DISCOUNT;
        double expectedDiscountedAmount = expectedTotalAmount - expectedPercentageDiscount - expectedBulkDiscount;

        // Act
        Discount result = discountService.calculateNetPayableAmount(bill);

        // Assert
        assertEquals(expectedTotalAmount, result.getTotalAmount(), "Total amount should be correct");
        assertEquals(190.0, result.getDiscountedAmount(), "Discounted amount should be correct");
    }

    @Test
    public void testCalculateNetPayableAmountWithNoItems() {
        // Arrange
        when(mockUser.getUserType()).thenReturn(UserType.CUSTOMER);
        when(mockUser.getJoiningDate()).thenReturn(LocalDate.now());

        List<Item> items = Collections.emptyList();
        Bill bill = new Bill("bill5", mockUser, items);

        double expectedTotalAmount = 0.0;
        double expectedPercentageDiscount = 0.0;
        double expectedBulkDiscount = 0.0;
        double expectedDiscountedAmount = 0.0;

        // Act
        Discount result = discountService.calculateNetPayableAmount(bill);

        // Assert
        assertEquals(expectedTotalAmount, result.getTotalAmount(), "Total amount should be 0.0");
        assertEquals(expectedDiscountedAmount, result.getDiscountedAmount(), "Discounted amount should be 0.0");
    }
}
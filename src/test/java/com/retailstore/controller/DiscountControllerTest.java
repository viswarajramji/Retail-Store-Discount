package com.retailstore.controller;

import com.retailstore.enums.ItemType;
import com.retailstore.enums.UserType;
import com.retailstore.model.Bill;
import com.retailstore.model.Discount;
import com.retailstore.model.Item;
import com.retailstore.model.User;
import com.retailstore.service.DiscountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DiscountControllerTest {

    @Mock
    private DiscountServiceImpl discountService;

    @InjectMocks
    private DiscountController discountController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateNetPayableAmount() {

        User user = mock(User.class);
        when(user.getUserType()).thenReturn(UserType.EMPLOYEE);
        when(user.getJoiningDate()).thenReturn(LocalDate.now());

        Item item1 = mock(Item.class);
        when(item1.getTotalPrice()).thenReturn(BigDecimal.valueOf(100.0));
        when(item1.getType()).thenReturn(ItemType.GROCERIES);

        Item item2 = mock(Item.class);
        when(item2.getTotalPrice()).thenReturn(BigDecimal.valueOf(200.0));
        when(item2.getType()).thenReturn(ItemType.NON_GROCERIES);

        Bill bill = mock(Bill.class);
        when(bill.getUser()).thenReturn(user);
        when(bill.getItems()).thenReturn(Arrays.asList(item1, item2));

        Discount discount = new Discount("300.00","225.00");
        when(discountService.calculateNetPayableAmount(bill)).thenReturn(discount);

        ResponseEntity<Discount> response = discountController.calculateNetPayableAmount(bill);

        // Verify the results
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(discount.getTotalAmount(), response.getBody().getTotalAmount());
        assertEquals(discount.getDiscountedAmount(), response.getBody().getDiscountedAmount());
        verify(discountService, times(1)).calculateNetPayableAmount(bill);
    }
}


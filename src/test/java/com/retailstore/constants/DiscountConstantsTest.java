package com.retailstore.constants;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountConstantsTest {

    @Test
     void testEmployeeDiscount() {
        assertEquals(0.30, DiscountConstants.EMPLOYEE_DISCOUNT, "Employee discount should be 0.30");
    }

    @Test
    void testAffiliateDiscount() {
        assertEquals(0.10, DiscountConstants.AFFILIATE_DISCOUNT, "Affiliate discount should be 0.10");
    }

    @Test
     void testLoyaltyDiscount() {
        assertEquals(0.05, DiscountConstants.LOYALTY_DISCOUNT, "Loyalty discount should be 0.05");
    }

    @Test
     void testPer100Discount() {
        assertEquals(5.00, DiscountConstants.PER_100_DISCOUNT, "Per 100 discount should be 5.00");
    }
}


package com.retailstore.service;

import com.retailstore.model.Bill;
import com.retailstore.model.Discount;

public interface DiscountService {
     Discount calculateNetPayableAmount(Bill bill);
}

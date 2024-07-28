package com.retailstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Discount {
    private final String totalAmount;
    private final String discountedAmount;
}

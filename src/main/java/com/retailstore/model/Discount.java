package com.retailstore.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Discount {
    private final String totalAmount;
    private final String discountedAmount;
}

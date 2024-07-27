package com.retailstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Discount {
    private final  double totalAmount;
    private final double discountedAmount;
}

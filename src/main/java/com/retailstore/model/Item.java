package com.retailstore.model;

import com.retailstore.enums.ItemType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Item {
    @NotBlank(message = "ItemId Cannot be empty")
    private final String itemId;

    @NotBlank(message = "Item Name Cannot be empty")
    private final String name;

    @Min(value = 1, message = "Quantity must be at least 1")
    private final int quantity;

    @NotNull(message = "Item Type Cannot be Empty")
    private final ItemType type;

    @Positive(message = "Price should be positive")
    private final BigDecimal totalPrice;
}

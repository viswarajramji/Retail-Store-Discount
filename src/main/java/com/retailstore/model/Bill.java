package com.retailstore.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class Bill {

    @NotBlank(message = "Bill ID cannot be empty")
    private final String billId;

    @NotNull(message = "User Cannot be null")
    @Valid
    private final User user;

    @Size(min = 1 , message = "Add at least one item")
    @NotNull
    @Valid
    private final List<Item> items;

    public Bill(String billId, User user, List<Item> items) {
        this.billId = billId;
        this.user = user;
        this.items = Collections.unmodifiableList(items); //[ Ensure immutability of the list - to handle null if nul is passed as null]

    }

}

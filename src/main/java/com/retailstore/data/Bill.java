package com.retailstore.data;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @NotBlank(message = "Bill ID cannot be empty")
    private String billId;

    @NotNull(message = "User Cannot be null")
    @Valid
    private User user;

    @Size(min = 1,message = "Add atleast one item")
    @NotNull
    @Valid
    private List<Item> items;
}
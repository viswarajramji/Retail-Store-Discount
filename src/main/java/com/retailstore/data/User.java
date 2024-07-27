package com.retailstore.data;

import com.retailstore.enums.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NotBlank(message = "UserId Cannot be Empty")
    private String userId;

    @NotBlank(message = "UserName Cannot be Empty")
    private String userName;

    @NotNull(message = "UserType Cannot be Blank")
    private UserType userType;

    @NotNull(message = "JoiningDate Cannot be Blank")
    private LocalDate joiningDate;
}

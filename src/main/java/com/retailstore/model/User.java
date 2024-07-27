package com.retailstore.model;

import com.retailstore.enums.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class User {

    @NotBlank(message = "UserId Cannot be Empty")
    private final String userId;

    @NotBlank(message = "UserName Cannot be Empty")
    private final String userName;

    @NotNull(message = "UserType Cannot be Blank")
    private final UserType userType;

    @NotNull(message = "JoiningDate Cannot be Blank")
    private final LocalDate joiningDate;

}

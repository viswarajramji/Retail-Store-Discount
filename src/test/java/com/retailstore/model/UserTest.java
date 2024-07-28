package com.retailstore.model;
import com.retailstore.enums.UserType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testUserValidation_Success() {
        String userId = "user123";
        String userName = "John Doe";
        UserType userType = UserType.EMPLOYEE;
        LocalDate joiningDate = LocalDate.now();

        User user = new User(userId, userName, userType, joiningDate);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertTrue(violations.isEmpty(), "There should be no violations");
        assertNotNull(user);
        assertEquals(userId, user.getUserId());
        assertEquals(userName, user.getUserName());
        assertEquals(userType, user.getUserType());
        assertEquals(joiningDate, user.getJoiningDate());
    }

    @Test
     void testUserValidation_UserIdBlank() {
        User user = new User("", "John Doe", UserType.EMPLOYEE, LocalDate.now());

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertFalse(violations.isEmpty(), "There should be violations");
        assertEquals(1, violations.size());
        assertEquals("UserId Cannot be Empty", violations.iterator().next().getMessage());
    }

    @Test
     void testUserValidation_UserNameBlank() {
        User user = new User("user123", "", UserType.EMPLOYEE, LocalDate.now());

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertFalse(violations.isEmpty(), "There should be violations");
        assertEquals(1, violations.size());
        assertEquals("UserName Cannot be Empty", violations.iterator().next().getMessage());
    }

    @Test
     void testUserValidation_UserTypeNull() {
        User user = new User("user123", "John Doe", null, LocalDate.now());

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertFalse(violations.isEmpty(), "There should be violations");
        assertEquals(1, violations.size());
        assertEquals("UserType Cannot be Blank", violations.iterator().next().getMessage());
    }

    @Test
     void testUserValidation_JoiningDateNull() {
        User user = new User("user123", "John Doe", UserType.EMPLOYEE, null);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertFalse(violations.isEmpty(), "There should be violations");
        assertEquals(1, violations.size());
        assertEquals("JoiningDate cannot be Blank", violations.iterator().next().getMessage());
    }

    @Test
     void testUserValidation_JoiningDateFuture() {
        User user = new User("user123", "John Doe", UserType.EMPLOYEE, LocalDate.now().plusDays(1));

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertFalse(violations.isEmpty(), "There should be violations");
        assertEquals(1, violations.size());
        assertEquals("The date must be in the past or present", violations.iterator().next().getMessage());
    }
}

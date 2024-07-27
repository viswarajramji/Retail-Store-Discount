package com.retailstore.model;

import com.retailstore.enums.UserType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private final Validator validator;

    public UserTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testUserConstructorValidInput() {
        // Arrange
        String userId = "user1";
        String userName = "John Doe";
        UserType userType = UserType.EMPLOYEE;
        LocalDate joiningDate = LocalDate.now().minusYears(3);

        // Act
        User user = new User(userId, userName, userType, joiningDate);

        // Assert
        assertNotNull(user);
        assertEquals(userId, user.getUserId());
        assertEquals(userName, user.getUserName());
        assertEquals(userType, user.getUserType());
        assertEquals(joiningDate, user.getJoiningDate());
    }

    @Test
    void testUserConstructorWithNullUserId() {
        // Arrange
        String userName = "John Doe";
        UserType userType = UserType.EMPLOYEE;
        LocalDate joiningDate = LocalDate.now().minusYears(3);

        // Act
        User user = new User(null, userName, userType, joiningDate);

        // Assert
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("UserId Cannot be Empty")));
    }

    @Test
    void testUserConstructorWithEmptyUserId() {
        // Arrange
        String userId = "";
        String userName = "John Doe";
        UserType userType = UserType.EMPLOYEE;
        LocalDate joiningDate = LocalDate.now().minusYears(3);

        // Act
        User user = new User(userId, userName, userType, joiningDate);

        // Assert
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("UserId Cannot be Empty")));
    }

    @Test
    void testUserConstructorWithNullUserName() {
        // Arrange
        String userId = "user1";
        UserType userType = UserType.EMPLOYEE;
        LocalDate joiningDate = LocalDate.now().minusYears(3);

        // Act
        User user = new User(userId, null, userType, joiningDate);

        // Assert
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("UserName Cannot be Empty")));
    }

    @Test
    void testUserConstructorWithEmptyUserName() {
        // Arrange
        String userId = "user1";
        String userName = "";
        UserType userType = UserType.EMPLOYEE;
        LocalDate joiningDate = LocalDate.now().minusYears(3);

        // Act
        User user = new User(userId, userName, userType, joiningDate);

        // Assert
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("UserName Cannot be Empty")));
    }

    @Test
    void testUserConstructorWithNullUserType() {
        // Arrange
        String userId = "user1";
        String userName = "John Doe";
        LocalDate joiningDate = LocalDate.now().minusYears(3);

        // Act
        User user = new User(userId, userName, null, joiningDate);

        // Assert
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("UserType Cannot be Blank")));
    }

    @Test
    void testUserConstructorWithNullJoiningDate() {
        // Arrange
        String userId = "user1";
        String userName = "John Doe";
        UserType userType = UserType.EMPLOYEE;

        // Act
        User user = new User(userId, userName, userType, null);

        // Assert
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("JoiningDate Cannot be Blank")));
    }
}


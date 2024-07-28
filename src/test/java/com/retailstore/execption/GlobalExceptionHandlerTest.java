package com.retailstore.execption;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    void testHandleValidationExceptions() {
        // Arrange
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("object", "field", "error message");

        // Create a real instance of MethodArgumentNotValidException with the mocked BindingResult
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);

        // Configure the mock BindingResult
        when(bindingResult.getAllErrors()).thenReturn(Collections.singletonList(fieldError));

        // Act
        ResponseEntity<Map<String, String>> responseEntity = globalExceptionHandler.handleValidationExceptions(ex);

        // Assert
        Map<String, String> expectedErrors = new HashMap<>();
        expectedErrors.put("field", "error message");

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(expectedErrors, responseEntity.getBody());
    }
}

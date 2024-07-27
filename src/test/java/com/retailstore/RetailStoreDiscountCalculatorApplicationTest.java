package com.retailstore;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RetailStoreDiscountCalculatorApplicationTest {

    @Test
    void whenLoadApplication_thenSuccess() {
        // Using MockedStatic to mock the static method SpringApplication.run
        try (MockedStatic<SpringApplication> mockedStatic = Mockito.mockStatic(SpringApplication.class)) {
            // Set up the expectation
            mockedStatic.when(() -> SpringApplication.run(RetailStoreDiscountCalculatorApplication.class, new String[] {}))
                    .thenReturn(null);

            // Call the main method
            RetailStoreDiscountCalculatorApplication.main(new String[] {});

            // Verify that SpringApplication.run was called
            mockedStatic.verify(() -> SpringApplication.run(RetailStoreDiscountCalculatorApplication.class, new String[] {}));

            // Additional assertion to ensure that the main method doesn't throw an exception
            // No need to assert that the main method itself does not throw an exception
        }
    }
}

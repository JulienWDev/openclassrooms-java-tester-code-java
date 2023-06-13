package com.openclassrooms.testing;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    void shouldAddTwoPositiveNumbers(){
        // ARRANGE
        int a = 2;
        int b = 3;

        Calculator calculator = new Calculator();

        // ACT
        int sum = calculator.add(a, b);

        // ASSERT
        assertEquals(5, sum);
    }

    @Test
    void shouldMultiplyTwoPositiveNumbers(){
        // ARRANGE
        int a = 2;
        int b = 3;

        Calculator calculator = new Calculator();

        // ACT
        int actual = calculator.multiply(a, b);

        // ASSERT
        assertEquals(6, actual);
    }

}

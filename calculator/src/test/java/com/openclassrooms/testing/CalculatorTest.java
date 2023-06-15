package com.openclassrooms.testing;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    private Calculator calculatorUnderTest;

    private static Instant startedAt;

    @BeforeEach
    private void initCalculator(){
        calculatorUnderTest = new Calculator();
        System.out.println("Appel avant chaque test");
    }

    @AfterEach
    private void undefCalculator(){
        calculatorUnderTest = null;
        System.out.println("Appel après chaque test");
    }

    @BeforeAll
    public static void initStartingTime() {
        System.out.println("Appel avant tous les tests");
        startedAt = Instant.now();
    }

    @AfterAll
    public static void showTestDuration() {
        System.out.println("Appel après tous les tests");
        Instant endedAt = Instant.now();
        long duration = Duration.between(startedAt, endedAt).toMillis();
        System.out.println(MessageFormat.format("Durée des tests: {0} ms", duration));
    }

    @Test
    void shouldAddTwoPositiveNumbers(){
        // ARRANGE
        int a = 2;
        int b = 3;

        // ACT
        int sum = calculatorUnderTest.add(a, b);

        // ASSERT
        assertThat(sum).isEqualTo(5);
    }

    @Test
    void shouldMultiplyTwoPositiveNumbers(){
        // ARRANGE
        int a = 2;
        int b = 3;

        // ACT
        int actual = calculatorUnderTest.multiply(a, b);

        // ASSERT
        assertThat(actual).isEqualTo(6);
    }

    @ParameterizedTest(name = "{0} x 0 doit être égal à 0")
    @ValueSource(ints = {1, 2 , 42, 1001, 5089})
    public void shouldReturnZeroIfTheMultiplierIsZero(int arg){
        // ARRANGE - nothing to do

        // ACT
        int actualResult = calculatorUnderTest.multiply(arg, 0);

        // ASSERT
        assertEquals(0, actualResult);
    }


    @ParameterizedTest(name = "{0} + {1} doit être égal à {2}")
    @CsvSource({"1,1,2", "2,3,5", "42,57,99"})
    public void shouldReturnTheAdditionOfMultipleIntegers(int arg1, int arg2, int expectedResult) {
        // ARRANGE - nothing to do

        // ACT
        int actualResult = calculatorUnderTest.add(arg1, arg2);

        // ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @Timeout(1)
    void longCalculationShouldComputeInLessThanOneSecond() throws CalculationTimeoutException {
        calculatorUnderTest.longCalculation();
    }

    @Test
    public void listDigits_shouldReturnsTheListOfDigits_ofPositiveInteger(){
        // GIVEN
        int number = 95897;

        // WHEN
        Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);

        // THEN
//        Set<Integer> expectedDigits = Stream.of(5,7,8,9).collect(Collectors.toSet());
//        assertEquals(expectedDigits, actualDigits);
        assertThat(actualDigits).containsExactlyInAnyOrder(5,7,8,9);

    }

    @Test
    public void listDigits_shouldReturnsTheListOfDigits_ofNegativeInteger(){
        // GIVEN
        int number = -124432;

        // WHEN
        Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);

        // THEN
        assertThat(actualDigits).containsExactlyInAnyOrder(1,2,3,4);

    }

    @Test
    public void listDigits_shouldReturnsTheListOfZero_ofZero(){
        // GIVEN
        int number = 0;

        // WHEN
        Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);

        // THEN
        assertThat(actualDigits).containsExactlyInAnyOrder(0);

    }
}

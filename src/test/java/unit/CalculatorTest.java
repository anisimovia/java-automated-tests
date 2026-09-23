package unit;

import com.aqa.course.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для калькулятора.
 */
public class CalculatorTest {

    Calculator calculator;

    @BeforeEach
    public void newCalculator() {
        this.calculator = new Calculator();
    }

    // sum(int, int)
    @Nested
    @DisplayName("sum(int, int)")
    class SumTwoIntegersTest {

        @ParameterizedTest(name = "{0} + {1} = {2}")
        @CsvSource({
                // Позитивные
                "1, 2, 3",
                "100, 200, 300",
                // Отрицательные
                "-10, -20, -30",
                "-12, 6, -6",
                "5, -5, 0",
                // С нулём
                "0, 0, 0",
                "0, 5, 5",
                "5, 0, 5",
                // Граничные значения int
                "2147483647, 0, 2147483647",     // Integer.MAX_VALUE
                "-2147483648, 0, -2147483648",   // Integer.MIN_VALUE
                "2147483646, 1, 2147483647"      // MAX_VALUE - 1 + 1
        })
        @DisplayName("Позитивные и граничные случаи")
        public void sumTest(int firstValue, int secondValue, int expectedResult) {
            int actualResult = calculator.sum(firstValue, secondValue);
            assertEquals(expectedResult, actualResult,
                    () -> "we expected " + expectedResult + ", but was " + actualResult);
        }

        @Test
        @DisplayName("Переполнение int: MAX_VALUE + 1 = MIN_VALUE")
        public void sumOverflowTest() {
            int actual = calculator.sum(Integer.MAX_VALUE, 1);
            assertEquals(Integer.MIN_VALUE, actual,
                    "Переполнение int должно циклически вернуться к MIN_VALUE");
        }

        @Test
        @DisplayName("Переполнение int: MIN_VALUE - 1 = MAX_VALUE")
        public void sumUnderflowTest() {
            int actual = calculator.sum(Integer.MIN_VALUE, -1);
            assertEquals(Integer.MAX_VALUE, actual,
                    "Переполнение int снизу должно вернуть MAX_VALUE");
        }
    }

    // sum(List<Integer>)
    @Nested
    @DisplayName("sum(List<Integer>)")
    class SumListTest {

        private static Stream<Arguments> testsDataForSumOfCollectionValues() {
            return Stream.of(
                    // Позитивные
                    Arguments.of(Arrays.asList(1, 2, 3), 6L),
                    Arguments.of(Arrays.asList(10, -2, 3), 11L),
                    Arguments.of(Arrays.asList(100, 200, 300), 600L),
                    // Отрицательные
                    Arguments.of(Arrays.asList(-1, -2, -3), -6L),
                    Arguments.of(Arrays.asList(-10, 5, -5), -10L),
                    // Смешанные
                    Arguments.of(Arrays.asList(0, 0, 0), 0L),
                    Arguments.of(Arrays.asList(1, 0, -1), 0L),
                    // Граничные
                    Arguments.of(Collections.emptyList(), 0L),
                    Arguments.of(Collections.singletonList(42), 42L),
                    Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 55L)
            );
        }

        @ParameterizedTest(name = "{0} -> {1}")
        @MethodSource("testsDataForSumOfCollectionValues")
        @DisplayName("Позитивные, граничные и смешанные случаи")
        public void sumOfCollectionValuesTest(List<Integer> numbers, long expectedResult) {
            long actualResult = calculator.sum(numbers);
            assertEquals(expectedResult, actualResult,
                    () -> "we expected " + expectedResult + ", but was " + actualResult);
        }

        @Test
        @DisplayName("NPE при передаче null вместо списка")
        public void sumOfCollectionWithNullListThrowsNpe() {
            assertThrows(NullPointerException.class,
                    () -> calculator.sum((List<Integer>) null));
        }

        @Test
        @DisplayName("NPE при наличии null внутри списка")
        public void sumOfCollectionWithNullElementThrowsNpe() {
            assertThrows(NullPointerException.class,
                    () -> calculator.sum(Arrays.asList(1, null, 3)));
        }
    }

    // multiple(List<Integer>)
    @Nested
    @DisplayName("multiple(List<Integer>)")
    class MultipleTest {

        private static Stream<Arguments> testsDataForMultiple() {
            return Stream.of(
                    // Позитивные
                    Arguments.of(Arrays.asList(1, 2, 3, 4), 24L),
                    Arguments.of(Arrays.asList(5, 5), 25L),
                    Arguments.of(Arrays.asList(2, 3, 4), 24L),
                    Arguments.of(Arrays.asList(10, 10, 10), 1000L),
                    // С нулём
                    Arguments.of(Arrays.asList(2, 0, 5), 0L),
                    Arguments.of(Arrays.asList(0, 0, 0), 0L),
                    // Отрицательные (чётное число минусов → плюс)
                    Arguments.of(Arrays.asList(-1, 2, -3), 6L),
                    Arguments.of(Arrays.asList(-2, -3), 6L),
                    Arguments.of(Arrays.asList(-2, 3), -6L),   // нечётное число минусов → минус
                    // Граничные
                    Arguments.of(Collections.emptyList(), 1L),   // identity = 1
                    Arguments.of(Collections.singletonList(7), 7L),
                    Arguments.of(Arrays.asList(1, 1, 1, 1), 1L)
            );
        }

        @ParameterizedTest(name = "{0} -> {1}")
        @MethodSource("testsDataForMultiple")
        @DisplayName("Позитивные, граничные и случаи с нулём/минусами")
        public void multipleTest(List<Integer> numbers, long expectedResult) {
            long actualResult = calculator.multiple(numbers);
            assertEquals(expectedResult, actualResult,
                    () -> "we expected " + expectedResult + ", but was " + actualResult);
        }

        @Test
        @DisplayName("NPE при null-списке")
        public void multipleWithNullListThrowsNpe() {
            assertThrows(NullPointerException.class,
                    () -> calculator.multiple(null));
        }

        @Test
        @DisplayName("NPE при наличии null внутри списка")
        public void multipleWithNullElementThrowsNpe() {
            assertThrows(NullPointerException.class,
                    () -> calculator.multiple(Arrays.asList(1, null, 3)));
        }
    }

    // divide(int, int)
    @Nested
    @DisplayName("divide(int, int)")
    class DivideTest {

        @ParameterizedTest(name = "{0} / {1} = {2}")
        @CsvSource({
                // Точное деление
                "10, 2, 5.0",
                "9, 3, 3.0",
                "-10, 2, -5.0",
                "10, -2, -5.0",
                "-10, -2, 5.0",
                "0, 5, 0.0",
                // Дробный результат (баг исправлен)
                "10, 4, 2.5",
                "1, 3, 0.333333",
                "5, 2, 2.5",
                "-7, 2, -3.5",
                "22, 7, 3.142857",
                // Граничные
                "1, 1, 1.0",
                "-1, 1, -1.0"
        })
        @DisplayName("Позитивные и дробные случаи")
        public void divideTest(int dividend, int divisor, double expectedResult) {
            double actualResult = calculator.divide(dividend, divisor);
            assertEquals(expectedResult, actualResult, 0.0001,
                    () -> "we expected " + expectedResult + ", but was " + actualResult);
        }

        @Test
        @DisplayName("Деление на ноль: ArithmeticException")
        public void divideByZeroThrows() {
            assertThrows(ArithmeticException.class,
                    () -> calculator.divide(5, 0));
        }

        @Test
        @DisplayName("Деление нуля на ноль: ArithmeticException")
        public void divideZeroByZeroThrows() {
            assertThrows(ArithmeticException.class,
                    () -> calculator.divide(0, 0));
        }
    }

    // pow(int, int)
    @Nested
    @DisplayName("pow(int, int)")
    class PowTest {

        @ParameterizedTest(name = "{0}^{1} = {2}")
        @CsvSource({
                // Степень 0
                "2, 0, 1",
                "5, 0, 1",
                "0, 0, 1",       // 0^0 = 1 по соглашению
                "-3, 0, 1",
                // Степень 1
                "2, 1, 2",
                "5, 1, 5",
                "0, 1, 0",
                "-3, 1, -3",
                // Позитивные степени
                "2, 2, 4",
                "2, 3, 8",
                "2, 4, 16",
                "2, 10, 1024",
                "3, 3, 27",
                "5, 2, 25",
                "10, 3, 1000",
                // Отрицательное основание
                "-2, 2, 4",
                "-2, 3, -8",
                "-3, 3, -27"
        })
        @DisplayName("Позитивные и граничные случаи")
        public void powTest(int base, int power, long expectedResult) {
            long actualResult = calculator.pow(base, power);
            assertEquals(expectedResult, actualResult,
                    () -> base + "^" + power + " should be " + expectedResult);
        }

        @Test
        @DisplayName("Переполнение long: 2^63 = Long.MIN_VALUE")
        public void powOverflowTest() {
            long actual = calculator.pow(2, 63);
            assertEquals(Long.MIN_VALUE, actual,
                    "2^63 переполняет long и даёт MIN_VALUE");
        }
    }
}
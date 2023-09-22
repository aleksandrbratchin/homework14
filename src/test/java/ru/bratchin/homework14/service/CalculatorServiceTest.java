package ru.bratchin.homework14.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class CalculatorServiceTest {

    private final CalculatorService service = new CalculatorService();

    @ParameterizedTest(name = "plus[{index}]: param1 = {0} param2 = {1} expected = {2}")
    @CsvSource(value = {
            "1, 1, 2",
            "15, -1, 14",
            "3, 0, 3"
    })
    void plus(int param1, int param2, int expected) {
        int result = service.plus(param1, param2);

        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "minus[{index}]: param1 = {0} param2 = {1} expected = {2}")
    @CsvSource(value = {
            "1, 1, 0",
            "15, -1, 16",
            "3, 0, 3"
    })
    void minus(int param1, int param2, int expected) {
        int result = service.minus(param1, param2);

        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "multiply[{index}]: param1 = {0} param2 = {1} expected = {2}")
    @CsvSource(value = {
            "1, 1, 1",
            "15, -1, -15",
            "3, 0, 0"
    })
    void multiply(int param1, int param2, int expected) {
        int result = service.multiply(param1, param2);

        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "divide[{index}]: param1 = {0} param2 = {1} expected = {2}")
    @CsvSource(value = {
            "1, 1, 1",
            "15, -1, -15",
            "3, 2, 1"
    })
    void divide(int param1, int param2, int expected) {
        int result = service.divide(param1, param2);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void divideByZero() {
        Throwable thrown = catchThrowable(() -> service.divide(0, 0));

        assertThat(thrown).isInstanceOf(ArithmeticException.class);
    }


}
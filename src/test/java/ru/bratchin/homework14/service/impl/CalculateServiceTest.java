package ru.bratchin.homework14.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.bratchin.homework14.service.CalculateService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class CalculateServiceTest {

    private final CalculateService<Integer> service = new CalculateIntegerService();

    @ParameterizedTest(name = "plus[{index}]: param1 = {0} param2 = {1} expected = {2}")
    @CsvSource(value = {
            "1, 1, 2",
            "15, -1, 14",
            "3, 0, 3"
    })
    void plus(Integer param1, Integer param2, Integer expected) {
        Integer result = service.plus(param1, param2);

        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "minus[{index}]: param1 = {0} param2 = {1} expected = {2}")
    @CsvSource(value = {
            "1, 1, 0",
            "15, -1, 16",
            "3, 0, 3"
    })
    void minus(Integer param1, Integer param2, Integer expected) {
        Integer result = service.minus(param1, param2);

        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "multiply[{index}]: param1 = {0} param2 = {1} expected = {2}")
    @CsvSource(value = {
            "1, 1, 1",
            "15, -1, -15",
            "3, 0, 0"
    })
    void multiply(Integer param1, Integer param2, Integer expected) {
        Integer result = service.multiply(param1, param2);

        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "divide[{index}]: param1 = {0} param2 = {1} expected = {2}")
    @CsvSource(value = {
            "1, 1, 1",
            "15, -1, -15",
            "3, 2, 1"
    })
    void divide(Integer param1, Integer param2, Integer expected) {
        Integer result = service.divide(param1, param2);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void divideByZero() {
        Throwable thrown = catchThrowable(() -> service.divide(0, 0));

        assertThat(thrown).isInstanceOf(ArithmeticException.class);
    }


}
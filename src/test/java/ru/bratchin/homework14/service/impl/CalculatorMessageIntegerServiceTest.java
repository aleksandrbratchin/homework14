package ru.bratchin.homework14.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.bratchin.homework14.service.CalculatorMessageService;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorMessageIntegerServiceTest {

    private final CalculatorMessageIntegerService service =
            new CalculatorMessageIntegerService(new CalculateIntegerService());

    @Test
    void welcome() {
        String result = service.welcome();

        assertThat(result.toLowerCase())
                .contains("добро пожаловать");
    }

    @Nested
    class SuccessParams {
        @ParameterizedTest(name = "plus[{index}]: param1 = {0} param2 = {1} expected = {2}")
        @CsvSource(value = {
                "1, 1, 2",
                "15, -1, 14",
                "3, 0, 3",
                "0, 0, 0"
        })
        void plus(String param1, String param2, String expected) {
            String result = service.plus(param1, param2);

            assertThat(result)
                    .startsWith(param1 + " + " + param2)
                    .contains(expected);
        }

        @ParameterizedTest(name = "minus[{index}]: param1 = {0} param2 = {1} expected = {2}")
        @CsvSource(value = {
                "3, 53, -50",
                "15, -1, 16",
                "3, 0, 3",
                "0, 0, 0"
        })
        void minus(String param1, String param2, String expected) {
            String result = service.minus(param1, param2);

            assertThat(result)
                    .startsWith(param1 + " - " + param2)
                    .contains(expected);
        }

        @ParameterizedTest(name = "multiply[{index}]: param1 = {0} param2 = {1} expected = {2}")
        @CsvSource(value = {
                "1, 1, 1",
                "15, -1, -15",
                "3, 0, 0",
                "0, 0, 0"
        })
        void multiply(String param1, String param2, String expected) {
            String result = service.multiply(param1, param2);

            assertThat(result)
                    .startsWith(param1 + " * " + param2)
                    .contains(expected);
        }

        @ParameterizedTest(name = "divide[{index}]: param1 = {0} param2 = {1} expected = {2}")
        @CsvSource(value = {
                "1, 1, 1",
                "15, -1, -15",
                "3, 2, 1"
        })
        void divide(String param1, String param2, String expected) {
            String result = service.divide(param1, param2);

            assertThat(result)
                    .startsWith(param1 + " / " + param2)
                    .contains(expected);
        }
    }

    @Nested
    class ErrorParams {

        private static String errorParamMessage;

        @BeforeAll
        public static void setup() throws NoSuchFieldException, IllegalAccessException {
            Field field = CalculatorMessageService.class.getDeclaredField("NFEMessage");
            field.setAccessible(true);
            errorParamMessage = String.valueOf(field.get(null));
        }

        @ParameterizedTest(name = "plus[{index}]: param1 = {0} param2 = {1} expected = {2}")
        @CsvSource(value = {
                ", ",
                "1f, -1",
                "1, -1d"
        })
        void plus(String param1, String param2) {
            String result = service.plus(param1, param2);

            assertThat(result).isEqualTo(errorParamMessage);
        }

        @ParameterizedTest(name = "minus[{index}]: param1 = {0} param2 = {1} expected = {2}")
        @CsvSource(value = {
                ", ",
                "1f, -1",
                "1, -1d"
        })
        void minus(String param1, String param2) {
            String result = service.minus(param1, param2);

            assertThat(result).isEqualTo(errorParamMessage);
        }

        @ParameterizedTest(name = "multiply[{index}]: param1 = {0} param2 = {1} expected = {2}")
        @CsvSource(value = {
                ", ",
                "1f, -1",
                "1, -1d"
        })
        void multiply(String param1, String param2) {
            String result = service.multiply(param1, param2);

            assertThat(result).isEqualTo(errorParamMessage);
        }

        @ParameterizedTest(name = "divide[{index}]: param1 = {0} param2 = {1} expected = {2}")
        @CsvSource(value = {
                ", ",
                "1f, -1",
                "1, -1d"
        })
        void divide(String param1, String param2) {
            String result = service.divide(param1, param2);

            assertThat(result).isEqualTo(errorParamMessage);
        }

        @Test
        void divideByZero() {
            String result = service.divide("13", "0");

            assertThat(result).contains("0");
        }

    }

}
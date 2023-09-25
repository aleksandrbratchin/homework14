package ru.bratchin.homework14.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import ru.bratchin.homework14.service.impl.CalculateIntegerService;
import ru.bratchin.homework14.service.impl.CalculatorMessageServiceImpl;

import java.lang.reflect.Field;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalculatorController.class)
//Достать из контекста сервис
@Import({CalculatorMessageServiceImpl.class, CalculateIntegerService.class})
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void welcome() throws Exception {
        mockMvc.perform(get("/calculator"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "Добро пожаловать в калькулятор!"
                ));
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
        void plus(String param1, String param2, String result) throws Exception {
            mockMvc.perform(get("/calculator/plus?num1=" + param1 + "&num2=" + param2))
                    .andExpect(status().isOk())
                    .andExpectAll(
                            content().string(
                                    containsString(param1 + " + " + param2)
                            ),
                            content().string(
                                    endsWith(result)
                            )
                    );
        }

        @ParameterizedTest(name = "minus[{index}]: param1 = {0} param2 = {1} expected = {2}")
        @CsvSource(value = {
                "3, 53, -50",
                "15, -1, 16",
                "3, 0, 3",
                "0, 0, 0"
        })
        void minus(String param1, String param2, String result) throws Exception {
            mockMvc.perform(get("/calculator/minus?num1=" + param1 + "&num2=" + param2))
                    .andExpect(status().isOk())
                    .andExpectAll(
                            content().string(
                                    containsString(param1 + " - " + param2)
                            ),
                            content().string(
                                    endsWith(result)
                            )
                    );
        }

        @ParameterizedTest(name = "multiply[{index}]: param1 = {0} param2 = {1} expected = {2}")
        @CsvSource(value = {
                "1, 1, 1",
                "15, -1, -15",
                "3, 0, 0",
                "0, 0, 0"
        })
        void multiply(String param1, String param2, String result) throws Exception {
            mockMvc.perform(get("/calculator/multiply?num1=" + param1 + "&num2=" + param2))
                    .andExpect(status().isOk())
                    .andExpectAll(
                            content().string(
                                    containsString(param1 + " * " + param2)
                            ),
                            content().string(
                                    endsWith(result)
                            )
                    );
        }

        @ParameterizedTest(name = "divide[{index}]: param1 = {0} param2 = {1} expected = {2}")
        @CsvSource(value = {
                "1, 1, 1",
                "15, -1, -15",
                "3, 2, 1"
        })
        void divide(String param1, String param2, String result) throws Exception {
            mockMvc.perform(get("/calculator/divide?num1=" + param1 + "&num2=" + param2))
                    .andExpect(status().isOk())
                    .andExpectAll(
                            content().string(
                                    containsString(param1 + " / " + param2)
                            ),
                            content().string(
                                    endsWith(result)
                            )
                    );
        }
    }


    @Nested
    class ErrorParams {

        private static String errorParamMessage;

        @BeforeAll
        public static void setup() throws NoSuchFieldException, IllegalAccessException {
            Field field = CalculatorMessageServiceImpl.class.getDeclaredField("NFEMessage");
            field.setAccessible(true);
            errorParamMessage = String.valueOf(field.get(null));
        }

        @ParameterizedTest(name = "plus[{index}]: param1 = {0} param2 = {1} expected = {2}")
        @CsvSource(value = {
                ", ",
                "1f, -1",
                "1, -1d"
        })
        void plus(String param1, String param2) throws Exception {
            mockMvc.perform(get("/calculator/plus?num1=" + param1 + "&num2=" + param2))
                    .andExpect(status().isOk())
                    .andExpectAll(
                            content().string(
                                    errorParamMessage
                            )
                    );
        }

        @ParameterizedTest(name = "minus[{index}]: param1 = {0} param2 = {1} expected = {2}")
        @CsvSource(value = {
                ", ",
                "1f, -1",
                "1, -1d"
        })
        void minus(String param1, String param2) throws Exception {
            mockMvc.perform(get("/calculator/minus?num1=" + param1 + "&num2=" + param2))
                    .andExpect(status().isOk())
                    .andExpectAll(
                            content().string(
                                    errorParamMessage
                            )
                    );
        }

        @ParameterizedTest(name = "multiply[{index}]: param1 = {0} param2 = {1} expected = {2}")
        @CsvSource(value = {
                ", ",
                "1f, -1",
                "1, -1d"
        })
        void multiply(String param1, String param2) throws Exception {
            mockMvc.perform(get("/calculator/multiply?num1=" + param1 + "&num2=" + param2))
                    .andExpect(status().isOk())
                    .andExpectAll(
                            content().string(
                                    errorParamMessage
                            )
                    );
        }

        @ParameterizedTest(name = "divide[{index}]: param1 = {0} param2 = {1} expected = {2}")
        @CsvSource(value = {
                ", ",
                "1f, -1",
                "1, -1d"
        })
        void divide(String param1, String param2) throws Exception {
            mockMvc.perform(get("/calculator/divide?num1=" + param1 + "&num2=" + param2))
                    .andExpect(status().isOk())
                    .andExpectAll(
                            content().string(
                                    errorParamMessage
                            )
                    );
        }

        @Test
        void divideByZero() throws Exception {
            mockMvc.perform(get("/calculator/divide?num1=0&num2=0"))
                    .andExpect(status().isOk())
                    .andExpectAll(
                            content().string(
                                    containsString("Деление на 0")
                            )
                    );
        }


    }


}
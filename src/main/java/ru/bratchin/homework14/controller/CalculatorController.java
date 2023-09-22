package ru.bratchin.homework14.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bratchin.homework14.service.CalculatorService;

@RestController
@RequestMapping("/calculator")
@Validated
public class CalculatorController {

    private final CalculatorService service;

    private static final String NFEMessage = "Неверные параметры!";
    private static final String formatMessage = "%d %c %d = %d";

    public CalculatorController(CalculatorService service) {
        this.service = service;
    }

    @GetMapping
    public String welcome() {
        return "Добро пожаловать в калькулятор!";
    }

    @GetMapping("/plus")
    public String plus(
            @RequestParam(name = "num1") String a,
            @RequestParam(name = "num2") String b
    ) {
        try {
            int param1 = Integer.parseInt(a);
            int param2 = Integer.parseInt(b);
            return String.format(formatMessage, param1, '+', param2, service.plus(param1, param2));
        } catch (NumberFormatException e) {
            return NFEMessage;
        }
    }

    @GetMapping("/minus")
    public String minus(
            @RequestParam(name = "num1") String a,
            @RequestParam(name = "num2") String b
    ) {
        try {
            int param1 = Integer.parseInt(a);
            int param2 = Integer.parseInt(b);
            return String.format(formatMessage, param1, '-', param2, service.minus(param1, param2));
        } catch (NumberFormatException e) {
            return NFEMessage;
        }
    }

    @GetMapping("/multiply")
    public String multiply(
            @RequestParam(name = "num1") String a,
            @RequestParam(name = "num2") String b
    ) {
        try {
            int param1 = Integer.parseInt(a);
            int param2 = Integer.parseInt(b);
            return String.format(formatMessage, param1, '*', param2, service.multiply(param1, param2));
        } catch (NumberFormatException e) {
            return NFEMessage;
        }
    }

    @GetMapping("/divide")
    public String divide(
            @RequestParam(name = "num1") String a,
            @RequestParam(name = "num2") String b
    ) {
        try {
            int param1 = Integer.parseInt(a);
            int param2 = Integer.parseInt(b);
            return String.format(formatMessage, param1, '/', param2, service.divide(param1, param2));
        } catch (NumberFormatException e) {
            return NFEMessage;
        } catch (ArithmeticException e) {
            return "Ошибка! Деление на 0!";
        }
    }

}

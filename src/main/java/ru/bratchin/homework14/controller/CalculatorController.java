package ru.bratchin.homework14.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bratchin.homework14.service.CalculatorMessageService;
import ru.bratchin.homework14.service.impl.CalculatorMessageIntegerService;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    private final CalculatorMessageService<?> service;

    public CalculatorController(CalculatorMessageIntegerService service) {
        this.service = service;
    }

    @GetMapping
    public String welcome() {
        return service.welcome();
    }

    @GetMapping("/plus")
    public String plus(
            @RequestParam(name = "num1") String a,
            @RequestParam(name = "num2") String b
    ) {
        return service.plus(a, b);
    }

    @GetMapping("/minus")
    public String minus(
            @RequestParam(name = "num1") String a,
            @RequestParam(name = "num2") String b
    ) {
        return service.minus(a, b);
    }

    @GetMapping("/multiply")
    public String multiply(
            @RequestParam(name = "num1") String a,
            @RequestParam(name = "num2") String b
    ) {
        return service.multiply(a, b);
    }

    @GetMapping("/divide")
    public String divide(
            @RequestParam(name = "num1") String a,
            @RequestParam(name = "num2") String b
    ) {
        return service.divide(a, b);
    }

}

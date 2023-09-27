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

    private final CalculatorMessageService<?> messageService;

    public CalculatorController(CalculatorMessageIntegerService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String welcome() {
        return messageService.welcome();
    }

    @GetMapping("/plus")
    public String plus(
            @RequestParam(name = "num1") String a,
            @RequestParam(name = "num2") String b
    ) {
        return messageService.plus(a, b);
    }

    @GetMapping("/minus")
    public String minus(
            @RequestParam(name = "num1") String a,
            @RequestParam(name = "num2") String b
    ) {
        return messageService.minus(a, b);
    }

    @GetMapping("/multiply")
    public String multiply(
            @RequestParam(name = "num1") String a,
            @RequestParam(name = "num2") String b
    ) {
        return messageService.multiply(a, b);
    }

    @GetMapping("/divide")
    public String divide(
            @RequestParam(name = "num1") String a,
            @RequestParam(name = "num2") String b
    ) {
        return messageService.divide(a, b);
    }

}

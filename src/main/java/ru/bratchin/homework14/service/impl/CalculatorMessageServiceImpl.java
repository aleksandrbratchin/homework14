package ru.bratchin.homework14.service.impl;

import org.springframework.stereotype.Service;
import ru.bratchin.homework14.service.CalculateService;
import ru.bratchin.homework14.service.CalculatorMessageService;

@Service
public class CalculatorMessageServiceImpl implements CalculatorMessageService {

    private static final String NFEMessage = "Неверные параметры!";
    private static final String formatMessage = "%d %c %d = %d";

    private final CalculateService<Integer> service;

    public CalculatorMessageServiceImpl(CalculateService<Integer> service) {
        this.service = service;
    }


    @Override
    public String welcome() {
        return "Добро пожаловать в калькулятор!";
    }

    @Override
    public String plus(String a, String b) {
        try {
            int param1 = Integer.parseInt(a);
            int param2 = Integer.parseInt(b);
            return String.format(formatMessage, param1, '+', param2, service.plus(param1, param2));
        } catch (NumberFormatException e) {
            return NFEMessage;
        }
    }

    @Override
    public String minus(String a, String b) {
        try {
            int param1 = Integer.parseInt(a);
            int param2 = Integer.parseInt(b);
            return String.format(formatMessage, param1, '-', param2, service.minus(param1, param2));
        } catch (NumberFormatException e) {
            return NFEMessage;
        }
    }

    @Override
    public String multiply(String a, String b) {
        try {
            int param1 = Integer.parseInt(a);
            int param2 = Integer.parseInt(b);
            return String.format(formatMessage, param1, '*', param2, service.multiply(param1, param2));
        } catch (NumberFormatException e) {
            return NFEMessage;
        }
    }

    @Override
    public String divide(String a, String b) {
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

package ru.bratchin.homework14.service;


public abstract class CalculatorMessageService<T extends Number> {
    protected final CalculateService<T> service;

    protected static final String NFEMessage = "Неверные параметры!";

    public CalculatorMessageService(CalculateService<T> service) {
        this.service = service;
    }

    public String welcome() {
        return "Добро пожаловать в калькулятор!";
    }

    public String plus(String a, String b) {
        try {
            T[] params = convert(a, b);
            return params[0] + " + " + params[1] + " = " + service.plus(params[0], params[1]);
        } catch (NumberFormatException e) {
            return NFEMessage;
        }
    }

    public String minus(String a, String b) {
        try {
            T[] params = convert(a, b);
            return params[0] + " - " + params[1] + " = " + service.minus(params[0], params[1]);
        } catch (NumberFormatException e) {
            return NFEMessage;
        }
    }

    public String multiply(String a, String b) {
        try {
            T[] params = convert(a, b);
            return params[0] + " * " + params[1] + " = " + service.multiply(params[0], params[1]);
        } catch (NumberFormatException e) {
            return NFEMessage;
        }
    }

    public String divide(String a, String b) {
        try {
            T[] params = convert(a, b);
            return params[0] + " / " + params[1] + " = " + service.divide(params[0], params[1]);
        } catch (NumberFormatException e) {
            return NFEMessage;
        } catch (ArithmeticException e) {
            return "Ошибка! Деление на 0!";
        }
    }

    protected abstract T[] convert(String a, String b);
}

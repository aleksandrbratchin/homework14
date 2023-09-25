package ru.bratchin.homework14.service.impl;

import org.springframework.stereotype.Service;
import ru.bratchin.homework14.service.CalculateService;

@Service
public class CalculateIntegerService implements CalculateService<Integer> {
    @Override
    public Integer plus(Integer a, Integer b) {
        return a + b;
    }
    @Override
    public Integer minus(Integer a, Integer b) {
        return a - b;
    }
    @Override
    public Integer multiply(Integer a, Integer b) {
        return a * b;
    }
    @Override
    public Integer divide(Integer a, Integer b) {
        if (b == 0) throw new ArithmeticException();
        return a / b;
    }

}

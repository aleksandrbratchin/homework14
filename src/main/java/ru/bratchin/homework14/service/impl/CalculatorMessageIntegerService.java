package ru.bratchin.homework14.service.impl;

import org.springframework.stereotype.Service;
import ru.bratchin.homework14.service.CalculateService;
import ru.bratchin.homework14.service.CalculatorMessageService;

@Service
public class CalculatorMessageIntegerService extends CalculatorMessageService<Integer> {
    
    public CalculatorMessageIntegerService(CalculateService<Integer> calculateService) {
        super(calculateService);
    }

    @Override
    protected Integer[] convert(String a, String b) {
        return new Integer[]{
                Integer.parseInt(a),
                Integer.parseInt(b)
        };
    }

}

package ru.bratchin.homework14.service;

public interface CalculateService<T extends Number> {

    T plus(T a, T b);

    T minus(T a, T b);

    T multiply(T a, T b);

    T divide(T a, T b);

}

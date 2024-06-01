package com.example.sample;

public class Calculator {

    public Long calculate(Long num1, String operator, Long num2) {
        return switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            default -> throw new InvalidOperatorException();
        };
    }
}

package com.example.sample;

public class SampleApplication {

    public static void main(String[] args) throws InvalidOperatorException{
        CalculationRequest calculationRequest =
                new CalculationRequestReader().read();
        long answer = new Calculator().calculate(
                calculationRequest.getNum1(),
                calculationRequest.getOperator(),
                calculationRequest.getNum2());

        System.out.println(answer);

    }
}

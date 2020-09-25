package com.epam.mentoring.java8.task3;

/**
 * @author Kaikenov Adilhan
 **/
public class Task3App {

    public static void main(String[] args) {

        String calculate = calculate((operand1, operand2, operand3) ->
                String.format("Result = %.2f", ((operand1 + operand2) * operand3)), 2, 3, 2);
        System.out.println(calculate);
    }

    private static String calculate(ThreeFunction<Double, Double, Double, String> threeFunction, double firstOperand, double secondOperand, double thirdOperand) {
        return threeFunction.apply(firstOperand, secondOperand, thirdOperand);
    }
}

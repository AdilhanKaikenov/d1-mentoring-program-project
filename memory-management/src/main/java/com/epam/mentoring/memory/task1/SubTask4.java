package com.epam.mentoring.memory.task1;

/**
 * @author Kaikenov Adilhan
 **/
public class SubTask4 {

//        4. java.lang.StackOverflowError. Use recursive methods. Don’t tune stack size.
    public static void main(String[] args) {
        calcFactorial(-10);
    }

    private static int calcFactorial(int number) {
        return number == 1 ? 1 : number * calcFactorial(number - 1);
    }
}

package edu.synergy.practice.examples;

import java.util.Locale;

/** Four arithmetic operations with an explicit zero-denominator guard. */
public final class Calculator {
    private Calculator() {}
    public static double calculate(double a, double b, char operator) {
        if (!Double.isFinite(a) || !Double.isFinite(b)) {
            throw new IllegalArgumentException("Arguments must be finite");
        }
        double result = switch (operator) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0.0) {
                    throw new IllegalArgumentException("Division by zero is forbidden");
                }
                yield a / b;
            }
            default -> throw new IllegalArgumentException("Unknown operator: " + operator);
        };
        if (!Double.isFinite(result)) {
            throw new ArithmeticException("Result is not finite");
        }
        return result;
    }
    public static void main(String[] args) {
        for (char operation : new char[]{'+','-','*','/'}) {
            System.out.printf(Locale.ROOT, "10 %c 5 = %.2f%n", operation, calculate(10,5,operation));
        }
    }
}
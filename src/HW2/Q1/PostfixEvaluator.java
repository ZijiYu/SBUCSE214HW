package HW2.Q1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

public class PostfixEvaluator implements Evaluator {

    @Override
    public double evaluate(String expressionString) {
        Stack<BigDecimal> stack = new Stack<>();
        String[] tokens = expressionString.split(" ");

        for (String token : tokens) {
            if (Operator.isOperator(token)) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Invalid expression");
                }
                BigDecimal num2 = stack.pop();
                BigDecimal num1 = stack.pop();
                BigDecimal result = calculate(num1, num2, token);
                stack.push(result);
            } else {
                stack.push(new BigDecimal(token));
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression");
        }

        return stack.pop().setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private BigDecimal calculate(BigDecimal num1, BigDecimal num2, String operator) {
        switch (operator) {
            case "+":
                return num1.add(num2);
            case "-":
                return num1.subtract(num2);
            case "*":
                return num1.multiply(num2);
            case "/":
                if (num2.compareTo(BigDecimal.ZERO) == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return num1.divide(num2, 10, RoundingMode.HALF_UP);
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }
}

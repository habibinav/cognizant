import java.util.HashMap;
import java.util.Map;

public class FinancialForecasting {

    public static double calculateFutureValue(double presentValue, double growthRate, int periods) {
        if (periods == 0) return presentValue;
        return calculateFutureValue(presentValue * (1 + growthRate), growthRate, periods - 1);
    }

    public static double calculateFutureValueMemoized(double presentValue, double growthRate,
                                                       int periods, Map<Integer, Double> memo) {
        if (periods == 0) return presentValue;
        if (memo.containsKey(periods)) return memo.get(periods);

        double result = calculateFutureValueMemoized(presentValue * (1 + growthRate),
                                                      growthRate, periods - 1, memo);
        memo.put(periods, result);
        return result;
    }

    public static double calculateFutureValueIterative(double presentValue, double growthRate, int periods) {
        double value = presentValue;
        for (int i = 0; i < periods; i++) {
            value *= (1 + growthRate);
        }
        return value;
    }

    public static void main(String[] args) {
        System.out.println("=== Financial Forecasting Tool ===\n");

        System.out.println("--- Scenario 1: Basic Investment ---");
        System.out.println("Present Value: $1,000 | Growth Rate: 5% | Periods: 10 years");
        System.out.printf("Recursive:  $%.2f%n", calculateFutureValue(1000, 0.05, 10));
        System.out.printf("Memoized:   $%.2f%n", calculateFutureValueMemoized(1000, 0.05, 10, new HashMap<>()));
        System.out.printf("Iterative:  $%.2f%n", calculateFutureValueIterative(1000, 0.05, 10));

        System.out.println("\n--- Scenario 2: High-Growth Startup ---");
        System.out.println("Present Value: $50,000 | Growth Rate: 20% | Periods: 5 years");
        System.out.printf("Recursive:  $%.2f%n", calculateFutureValue(50000, 0.20, 5));
        System.out.printf("Iterative:  $%.2f%n", calculateFutureValueIterative(50000, 0.20, 5));

        System.out.println("\n--- Scenario 3: Retirement Savings ---");
        System.out.println("Present Value: $10,000 | Growth Rate: 7% | Periods: 30 years");
        System.out.printf("Recursive:  $%.2f%n", calculateFutureValue(10000, 0.07, 30));
        System.out.printf("Iterative:  $%.2f%n", calculateFutureValueIterative(10000, 0.07, 30));

        System.out.println("\n--- Performance Comparison ---");
        int periods = 5000;
        double pv = 1000, rate = 0.05;

        long start = System.nanoTime();
        calculateFutureValueIterative(pv, rate, periods);
        long iterativeTime = System.nanoTime() - start;

        start = System.nanoTime();
        Map<Integer, Double> memo = new HashMap<>();
        calculateFutureValueMemoized(pv, rate, periods, memo);
        long memoizedTime = System.nanoTime() - start;

        System.out.println("Periods: " + periods);
        System.out.println("Iterative time: " + iterativeTime + " ns");
        System.out.println("Memoized time:  " + memoizedTime + " ns");

        System.out.println("\n=== Time Complexity Analysis ===");
        System.out.println("Method     | Time  | Space | Stack Overflow Risk");
        System.out.println("-----------|-------|-------|--------------------");
        System.out.println("Recursive  | O(n)  | O(n)  | Yes (deep recursion)");
        System.out.println("Memoized   | O(n)  | O(n)  | Yes, but faster on repeat calls");
        System.out.println("Iterative  | O(n)  | O(1)  | No");
        System.out.println("\nOptimization: Memoization caches intermediate results to avoid");
        System.out.println("redundant computation. The iterative approach eliminates stack");
        System.out.println("overhead entirely and is safest for large period values.");
    }
}

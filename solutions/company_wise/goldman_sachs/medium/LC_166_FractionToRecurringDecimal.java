package solutions.company_wise.goldman_sachs.medium;

import java.util.HashMap;
import java.util.Map;

import common.Solution;

public class LC_166_FractionToRecurringDecimal implements Solution {

  // ✅ Optimized Long Division Approach with HashMap (O(d) time, O(d) space)
  public String fractionToDecimal(int numerator, int denominator) {
    if (numerator == 0)
      return "0";

    StringBuilder result = new StringBuilder();

    // Handle negative sign
    if (numerator < 0 ^ denominator < 0) {
      result.append("-");
    }

    // Convert to long to avoid overflow
    long num = Math.abs((long) numerator);
    long denom = Math.abs((long) denominator);

    // Compute integer part
    result.append(num / denom);
    long remainder = num % denom;

    // If no decimal part, return result
    if (remainder == 0) {
      return result.toString();
    }

    // Compute decimal part
    result.append(".");
    Map<Long, Integer> remainderMap = new HashMap<>();

    while (remainder != 0) {
      // If remainder repeats, enclose repeating part in parentheses
      if (remainderMap.containsKey(remainder)) {
        result.insert(remainderMap.get(remainder), "(");
        result.append(")");
        return result.toString();
      }

      // Store remainder's position in the decimal string
      remainderMap.put(remainder, result.length());
      remainder *= 10;
      result.append(remainder / denom);
      remainder %= denom;
    }

    return result.toString();
  }

  @Override
  public void run() {
    // 🔹 Test Case 1: Terminating Decimal
    int num1 = 1, denom1 = 2;
    System.out.println("Input: " + num1 + "/" + denom1);
    System.out.println("Output: " + fractionToDecimal(num1, denom1)); // Expected: "0.5"

    // 🔹 Test Case 2: Repeating Decimal
    int num2 = 2, denom2 = 3;
    System.out.println("\nInput: " + num2 + "/" + denom2);
    System.out.println("Output: " + fractionToDecimal(num2, denom2)); // Expected: "0.(6)"

    // 🔹 Test Case 3: Long Repeating Decimal
    int num3 = 22, denom3 = 7;
    System.out.println("\nInput: " + num3 + "/" + denom3);
    System.out.println("Output: " + fractionToDecimal(num3, denom3)); // Expected: "3.(142857)"

    // 🔹 Test Case 4: Negative Number
    int num4 = -1, denom4 = 2;
    System.out.println("\nInput: " + num4 + "/" + denom4);
    System.out.println("Output: " + fractionToDecimal(num4, denom4)); // Expected: "-0.5"

    // 🔹 Test Case 5: Another Repeating Decimal
    int num5 = 4, denom5 = 333;
    System.out.println("\nInput: " + num5 + "/" + denom5);
    System.out.println("Output: " + fractionToDecimal(num5, denom5)); // Expected: "0.(012)"
  }
}
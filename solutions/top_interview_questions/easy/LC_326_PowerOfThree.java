package solutions.top_interview_questions.easy;

import common.Solution;

public class LC_326_PowerOfThree implements Solution {

    // ✅ Optimized Approach 1: Using Modulo Division (O(log₃ N) time, O(1) space)
    public boolean isPowerOfThreeModulo(int n) {
        if (n < 1)
            return false;
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    // ✅ Optimized Approach 2: Using Largest Power of 3 in Integer Range (O(1) time,
    // O(1) space)
    public boolean isPowerOfThreeOptimized(int n) {
        return n > 0 && 1162261467 % n == 0; // 1162261467 = 3^19 (largest power of 3 in int range)
    }

    @Override
    public void run() {
        // 🔹 Test Case 1
        int num1 = 27;
        System.out.println("Number: " + num1);
        System.out.println("Is Power of Three (Modulo Division)? " + isPowerOfThreeModulo(num1)); // Expected: true
        System.out.println("Is Power of Three (Optimized)? " + isPowerOfThreeOptimized(num1)); // Expected: true

        // 🔹 Test Case 2
        int num2 = 20;
        System.out.println("\nNumber: " + num2);
        System.out.println("Is Power of Three (Modulo Division)? " + isPowerOfThreeModulo(num2)); // Expected: false
        System.out.println("Is Power of Three (Optimized)? " + isPowerOfThreeOptimized(num2)); // Expected: false

        // 🔹 Test Case 3
        int num3 = 1;
        System.out.println("\nNumber: " + num3);
        System.out.println("Is Power of Three (Modulo Division)? " + isPowerOfThreeModulo(num3)); // Expected: true
        System.out.println("Is Power of Three (Optimized)? " + isPowerOfThreeOptimized(num3)); // Expected: true

        // 🔹 Test Case 4 (Edge Case)
        int num4 = -27;
        System.out.println("\nNumber: " + num4);
        System.out.println("Is Power of Three (Modulo Division)? " + isPowerOfThreeModulo(num4)); // Expected: false
        System.out.println("Is Power of Three (Optimized)? " + isPowerOfThreeOptimized(num4)); // Expected: false
    }
}
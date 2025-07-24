/*
 * LeetCode Problem 412: Fizz Buzz
 * URL: https://leetcode.com/problems/fizz-buzz/
 * Difficulty: Easy
 *
 * Approaches:
 * 1. Simple If-Else Conditions - O(n) time, O(1) space
 * 2. String Concatenation - O(n) time, O(1) space  
 * 3. HashMap Approach - O(n) time, O(1) space
 *
 * Time Complexity:
 * - All approaches: O(n) - single pass from 1 to n
 *
 * Space Complexity:
 * - All approaches: O(1) - not counting output array
 */

package solutions.top_interview_questions.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.Solution;

public class LC_412_FizzBuzz implements Solution {

    // Approach 1: Simple If-Else Conditions (Most Common)
    public List<String> fizzBuzzSimple(int n) {
        List<String> result = new ArrayList<>();
        
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {  // Divisible by both 3 and 5
                result.add("FizzBuzz");
            } else if (i % 3 == 0) {  // Divisible by 3
                result.add("Fizz");
            } else if (i % 5 == 0) {  // Divisible by 5
                result.add("Buzz");
            } else {
                result.add(String.valueOf(i));
            }
        }
        
        return result;
    }

    // Approach 2: String Concatenation (More Extensible)
    public List<String> fizzBuzzConcatenation(int n) {
        List<String> result = new ArrayList<>();
        
        for (int i = 1; i <= n; i++) {
            String current = "";
            
            if (i % 3 == 0) {
                current += "Fizz";
            }
            if (i % 5 == 0) {
                current += "Buzz";
            }
            if (current.isEmpty()) {
                current = String.valueOf(i);
            }
            
            result.add(current);
        }
        
        return result;
    }

    // Approach 3: HashMap Approach (Most Extensible)
    public List<String> fizzBuzzHashMap(int n) {
        List<String> result = new ArrayList<>();
        
        // Define mappings for extensibility
        Map<Integer, String> fizzBuzzMap = new HashMap<>();
        fizzBuzzMap.put(3, "Fizz");
        fizzBuzzMap.put(5, "Buzz");
        
        for (int i = 1; i <= n; i++) {
            String current = "";
            
            for (Map.Entry<Integer, String> entry : fizzBuzzMap.entrySet()) {
                if (i % entry.getKey() == 0) {
                    current += entry.getValue();
                }
            }
            
            if (current.isEmpty()) {
                current = String.valueOf(i);
            }
            
            result.add(current);
        }
        
        return result;
    }

    // Approach 4: Ternary Operator (Concise)
    public List<String> fizzBuzzTernary(int n) {
        List<String> result = new ArrayList<>();
        
        for (int i = 1; i <= n; i++) {
            result.add(
                i % 15 == 0 ? "FizzBuzz" :
                i % 3 == 0 ? "Fizz" :
                i % 5 == 0 ? "Buzz" :
                String.valueOf(i)
            );
        }
        
        return result;
    }

    @Override
    public void run() {
        int n = 15;
        
        System.out.println("Test Case: n = " + n);
        System.out.println("Expected: [1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, FizzBuzz]");
        System.out.println();
        
        // Test all approaches
        List<String> result1 = fizzBuzzSimple(n);
        System.out.println("Simple If-Else: " + result1);
        
        List<String> result2 = fizzBuzzConcatenation(n);
        System.out.println("String Concatenation: " + result2);
        
        List<String> result3 = fizzBuzzHashMap(n);
        System.out.println("HashMap Approach: " + result3);
        
        List<String> result4 = fizzBuzzTernary(n);
        System.out.println("Ternary Operator: " + result4);
        System.out.println();
        
        // Test smaller case
        int n2 = 5;
        System.out.println("Test Case: n = " + n2);
        List<String> result5 = fizzBuzzSimple(n2);
        System.out.println("Result: " + result5); // Expected: [1, 2, Fizz, 4, Buzz]
        System.out.println();
        
        // Test edge case
        int n3 = 1;
        System.out.println("Test Case: n = " + n3);
        List<String> result6 = fizzBuzzSimple(n3);
        System.out.println("Result: " + result6); // Expected: [1]
    }
} 
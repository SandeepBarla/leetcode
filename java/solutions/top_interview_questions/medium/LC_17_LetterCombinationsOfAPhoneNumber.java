/*
 * LeetCode Problem 17: Letter Combinations of a Phone Number
 * URL: https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * Difficulty: Medium
 *
 * Approaches:
 * 1. Backtracking/DFS - O(3^n * 4^m) time, O(3^n * 4^m) space
 * 2. Iterative BFS - O(3^n * 4^m) time, O(3^n * 4^m) space
 * 3. Queue-based Level Processing - O(3^n * 4^m) time, O(3^n * 4^m) space
 *
 * Time Complexity:
 * - All approaches: O(3^n * 4^m) where n=digits with 3 letters, m=digits with 4 letters
 *
 * Space Complexity:
 * - All approaches: O(3^n * 4^m) - storing all combinations
 */

package solutions.top_interview_questions.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import common.Solution;

public class LC_17_LetterCombinationsOfAPhoneNumber implements Solution {
    
    // Mapping for phone number to letters
    private static final Map<Character, String> PHONE_MAP = new HashMap<>();
    static {
        PHONE_MAP.put('2', "abc");
        PHONE_MAP.put('3', "def");
        PHONE_MAP.put('4', "ghi");
        PHONE_MAP.put('5', "jkl");
        PHONE_MAP.put('6', "mno");
        PHONE_MAP.put('7', "pqrs");
        PHONE_MAP.put('8', "tuv");
        PHONE_MAP.put('9', "wxyz");
    }

    // Approach 1: Backtracking/DFS (Optimal)
    public List<String> letterCombinationsBacktracking(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        
        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }
    
    private void backtrack(List<String> result, StringBuilder current, String digits, int index) {
        // Base case: we've processed all digits
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }
        
        // Get letters for current digit
        String letters = PHONE_MAP.get(digits.charAt(index));
        
        // Try each letter for current digit
        for (char letter : letters.toCharArray()) {
            current.append(letter);
            backtrack(result, current, digits, index + 1);
            current.deleteCharAt(current.length() - 1); // backtrack
        }
    }

    // Approach 2: Iterative BFS
    public List<String> letterCombinationsIterative(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        
        result.add(""); // Start with empty string
        
        for (char digit : digits.toCharArray()) {
            List<String> temp = new ArrayList<>();
            String letters = PHONE_MAP.get(digit);
            
            for (String combination : result) {
                for (char letter : letters.toCharArray()) {
                    temp.add(combination + letter);
                }
            }
            
            result = temp;
        }
        
        return result;
    }

    // Approach 3: Queue-based Level Processing
    public List<String> letterCombinationsQueue(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        
        Queue<String> queue = new LinkedList<>();
        queue.offer("");
        
        for (char digit : digits.toCharArray()) {
            String letters = PHONE_MAP.get(digit);
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                
                for (char letter : letters.toCharArray()) {
                    queue.offer(current + letter);
                }
            }
        }
        
        result.addAll(queue);
        return result;
    }

    // Approach 4: Recursive without global state
    public List<String> letterCombinationsRecursive(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        
        return generateCombinations(digits, 0);
    }
    
    private List<String> generateCombinations(String digits, int index) {
        List<String> result = new ArrayList<>();
        
        // Base case
        if (index == digits.length()) {
            result.add("");
            return result;
        }
        
        // Get combinations for remaining digits
        List<String> subCombinations = generateCombinations(digits, index + 1);
        
        // Add current digit's letters to each sub-combination
        String letters = PHONE_MAP.get(digits.charAt(index));
        for (char letter : letters.toCharArray()) {
            for (String subCombination : subCombinations) {
                result.add(letter + subCombination);
            }
        }
        
        return result;
    }

    @Override
    public void run() {
        // Test Case 1: Basic case with 2 digits
        String digits1 = "23";
        System.out.println("Test Case 1: \"" + digits1 + "\"");
        System.out.println("Backtracking: " + letterCombinationsBacktracking(digits1));
        // Expected: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
        System.out.println("Iterative: " + letterCombinationsIterative(digits1));
        System.out.println("Queue-based: " + letterCombinationsQueue(digits1));
        System.out.println("Recursive: " + letterCombinationsRecursive(digits1));
        System.out.println();

        // Test Case 2: Empty string
        String digits2 = "";
        System.out.println("Test Case 2: \"" + digits2 + "\" (empty)");
        System.out.println("Backtracking: " + letterCombinationsBacktracking(digits2)); // Expected: []
        System.out.println("Iterative: " + letterCombinationsIterative(digits2)); // Expected: []
        System.out.println("Queue-based: " + letterCombinationsQueue(digits2)); // Expected: []
        System.out.println("Recursive: " + letterCombinationsRecursive(digits2)); // Expected: []
        System.out.println();

        // Test Case 3: Single digit
        String digits3 = "2";
        System.out.println("Test Case 3: \"" + digits3 + "\"");
        System.out.println("Backtracking: " + letterCombinationsBacktracking(digits3)); // Expected: ["a","b","c"]
        System.out.println("Iterative: " + letterCombinationsIterative(digits3)); // Expected: ["a","b","c"]
        System.out.println("Queue-based: " + letterCombinationsQueue(digits3)); // Expected: ["a","b","c"]
        System.out.println("Recursive: " + letterCombinationsRecursive(digits3)); // Expected: ["a","b","c"]
        System.out.println();

        // Test Case 4: Digit with 4 letters (7 and 9)
        String digits4 = "79";
        System.out.println("Test Case 4: \"" + digits4 + "\" (4-letter digits)");
        List<String> result4 = letterCombinationsBacktracking(digits4);
        System.out.println("Backtracking (" + result4.size() + " combinations): " + result4);
        // Expected: 16 combinations (4 * 4)
        
        List<String> iterative4 = letterCombinationsIterative(digits4);
        System.out.println("Iterative (" + iterative4.size() + " combinations): " + iterative4);
        System.out.println();

        // Test Case 5: Longer input (performance test)
        String digits5 = "234";
        System.out.println("Test Case 5: \"" + digits5 + "\" (performance test)");
        
        long start = System.currentTimeMillis();
        List<String> result5 = letterCombinationsBacktracking(digits5);
        long time = System.currentTimeMillis() - start;
        System.out.println("Backtracking: " + result5.size() + " combinations in " + time + "ms");
        // Expected: 27 combinations (3 * 3 * 3)
        
        start = System.currentTimeMillis();
        List<String> iterative5 = letterCombinationsIterative(digits5);
        time = System.currentTimeMillis() - start;
        System.out.println("Iterative: " + iterative5.size() + " combinations in " + time + "ms");
        
        // Show first few combinations
        System.out.println("First 10 combinations: " + result5.subList(0, Math.min(10, result5.size())));
        System.out.println();

        // Test Case 6: Mixed digits
        String digits6 = "237";
        System.out.println("Test Case 6: \"" + digits6 + "\" (mixed 3 and 4 letter digits)");
        List<String> result6 = letterCombinationsBacktracking(digits6);
        System.out.println("Total combinations: " + result6.size()); // Expected: 36 (3 * 3 * 4)
        System.out.println("Sample: " + result6.subList(0, Math.min(8, result6.size())));
    }
} 
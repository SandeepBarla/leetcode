/*
 * LeetCode Problem: 875. Koko Eating Bananas
 * URL: https://leetcode.com/problems/koko-eating-bananas/
 * Difficulty: Medium
 * 
 * Problem Description:
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. 
 * The guards have gone and will come back in h hours.
 * 
 * Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile 
 * of bananas and eats k bananas from that pile. If the pile has less than k bananas, 
 * she eats all of them for that hour and will not eat any more bananas during that hour.
 * 
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the 
 * guards come back. Return the minimum integer k such that she can eat all the bananas within h hours.
 * 
 * Example 1:
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 * Explanation: If Koko eats at speed 4:
 * - Hour 1: Eat 3 bananas from pile 1 (3 bananas left in pile 1)
 * - Hour 2: Eat 3 bananas from pile 2 (3 bananas left in pile 2)
 * - Hour 3: Eat 3 bananas from pile 2 (0 bananas left in pile 2)
 * - Hour 4: Eat 4 bananas from pile 3 (3 bananas left in pile 3)
 * - Hour 5: Eat 3 bananas from pile 3 (0 bananas left in pile 3)
 * - Hour 6: Eat 4 bananas from pile 4 (7 bananas left in pile 4)
 * - Hour 7: Eat 4 bananas from pile 4 (3 bananas left in pile 4)
 * - Hour 8: Eat 3 bananas from pile 4 (0 bananas left in pile 4)
 * 
 * Example 2:
 * Input: piles = [30,11,23,4,20], h = 5
 * Output: 30
 * Explanation: Koko must eat at speed 30 to finish all bananas in 5 hours.
 * 
 * Example 3:
 * Input: piles = [30,11,23,4,20], h = 6
 * Output: 23
 * 
 * Constraints:
 * - 1 <= piles.length <= 10^4
 * - piles.length <= h <= 10^9
 * - 1 <= piles[i] <= 10^9
 */

package solutions.neetcode_150.medium;
import java.util.Arrays;

import common.Solution;

public class LC_875_KokoEatingBananas implements Solution {
    
    /**
     * Approach 1: Binary Search (Optimized Original)
     * 
     * Algorithm:
     * 1. Binary search on the answer space: eating speed k from 1 to max(piles)
     * 2. For each candidate speed, calculate total hours needed using ceiling division
     * 3. If hours > h, need faster speed (search right half)
     * 4. If hours <= h, this speed works, try to find slower speed (search left half)
     * 5. Return the minimum valid speed found
     * 
     * Time Complexity: O(n * log(max(piles))) - binary search * time to calculate hours
     * Space Complexity: O(1) - only using constant extra space
     */
    public int minEatingSpeedBinarySearch(int[] piles, int h) {
        if (piles == null || piles.length == 0 || h < piles.length) {
            return -1; // Invalid input
        }
        
        int left = 1;
        int right = getMaxPile(piles);
        int result = right;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long hoursNeeded = calculateHours(piles, mid);
            
            if (hoursNeeded <= h) {
                result = mid; // Valid speed, try to find slower one
                right = mid - 1;
            } else {
                left = mid + 1; // Need faster speed
            }
        }
        
        return result;
    }
    
    /**
     * Approach 2: Binary Search with Sorting (Original Enhanced)
     * 
     * Algorithm:
     * 1. Sort the piles array first (though not necessary for correctness)
     * 2. Apply binary search similar to approach 1
     * 3. Early termination when hours exceed h during calculation
     * 
     * Time Complexity: O(n log n + n * log(max(piles))) - sorting + binary search
     * Space Complexity: O(1) - if sorting in place, O(n) if creating new array
     */
    public int minEatingSpeedSorted(int[] piles, int h) {
        if (piles == null || piles.length == 0 || h < piles.length) {
            return -1;
        }
        
        // Create a copy to avoid modifying original array
        int[] sortedPiles = Arrays.copyOf(piles, piles.length);
        Arrays.sort(sortedPiles);
        
        int left = 1;
        int right = sortedPiles[sortedPiles.length - 1];
        int result = right;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long hoursNeeded = calculateHoursEarlyTermination(sortedPiles, mid, h);
            
            if (hoursNeeded <= h) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return result;
    }
    
    /**
     * Approach 3: Linear Search (Brute Force)
     * 
     * Algorithm:
     * 1. Try eating speeds from 1 to max(piles) linearly
     * 2. For each speed, calculate total hours needed
     * 3. Return the first speed that allows finishing within h hours
     * 
     * Time Complexity: O(n * max(piles)) - can be very slow for large max values
     * Space Complexity: O(1) - only using constant extra space
     * Note: For educational purposes; not recommended for large inputs
     */
    public int minEatingSpeedLinear(int[] piles, int h) {
        if (piles == null || piles.length == 0 || h < piles.length) {
            return -1;
        }
        
        int maxPile = getMaxPile(piles);
        
        for (int speed = 1; speed <= maxPile; speed++) {
            long hoursNeeded = calculateHours(piles, speed);
            if (hoursNeeded <= h) {
                return speed;
            }
        }
        
        return maxPile; // Fallback (should not reach here with valid input)
    }
    
    /**
     * Approach 4: Binary Search with Optimized Bounds
     * 
     * Algorithm:
     * 1. Calculate more intelligent bounds for binary search
     * 2. Lower bound: ceil(total_bananas / h) - minimum theoretical speed
     * 3. Upper bound: max(piles) - worst case one pile per hour
     * 4. Apply binary search within optimized range
     * 
     * Time Complexity: O(n * log(total_bananas / h)) - potentially much faster
     * Space Complexity: O(1) - only using constant extra space
     */
    public int minEatingSpeedOptimized(int[] piles, int h) {
        if (piles == null || piles.length == 0 || h < piles.length) {
            return -1;
        }
        
        long totalBananas = 0;
        int maxPile = 0;
        for (int pile : piles) {
            totalBananas += pile;
            maxPile = Math.max(maxPile, pile);
        }
        
        // Optimized bounds
        int left = (int) Math.max(1, (totalBananas + h - 1) / h); // Ceiling division
        int right = maxPile;
        int result = right;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long hoursNeeded = calculateHours(piles, mid);
            
            if (hoursNeeded <= h) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return result;
    }
    
    /**
     * Helper method to calculate total hours needed for a given eating speed
     */
    private long calculateHours(int[] piles, int speed) {
        long totalHours = 0;
        for (int pile : piles) {
            totalHours += (pile + speed - 1) / speed; // Ceiling division
        }
        return totalHours;
    }
    
    /**
     * Helper method to calculate hours with early termination
     */
    private long calculateHoursEarlyTermination(int[] piles, int speed, int maxHours) {
        long totalHours = 0;
        for (int pile : piles) {
            totalHours += (pile + speed - 1) / speed;
            if (totalHours > maxHours) {
                return totalHours; // Early termination
            }
        }
        return totalHours;
    }
    
    /**
     * Helper method to find maximum pile size
     */
    private int getMaxPile(int[] piles) {
        int max = piles[0];
        for (int pile : piles) {
            max = Math.max(max, pile);
        }
        return max;
    }
    
    @Override
    public void run() {
        // Test Case 1: Example from problem
        int[] piles1 = {3, 6, 7, 11};
        int h1 = 8;
        System.out.println("Test Case 1:");
        System.out.println("Piles: " + Arrays.toString(piles1));
        System.out.println("Hours: " + h1);
        System.out.println("Expected: 4");
        System.out.println("Binary Search: " + minEatingSpeedBinarySearch(piles1, h1));
        System.out.println("Sorted Binary Search: " + minEatingSpeedSorted(piles1, h1));
        System.out.println("Linear Search: " + minEatingSpeedLinear(piles1, h1));
        System.out.println("Optimized Binary Search: " + minEatingSpeedOptimized(piles1, h1));
        System.out.println();
        
        // Test Case 2: Example from problem
        int[] piles2 = {30, 11, 23, 4, 20};
        int h2 = 5;
        System.out.println("Test Case 2:");
        System.out.println("Piles: " + Arrays.toString(piles2));
        System.out.println("Hours: " + h2);
        System.out.println("Expected: 30");
        System.out.println("Binary Search: " + minEatingSpeedBinarySearch(piles2, h2));
        System.out.println("Sorted Binary Search: " + minEatingSpeedSorted(piles2, h2));
        System.out.println("Linear Search: " + minEatingSpeedLinear(piles2, h2));
        System.out.println("Optimized Binary Search: " + minEatingSpeedOptimized(piles2, h2));
        System.out.println();
        
        // Test Case 3: Example from problem
        int[] piles3 = {30, 11, 23, 4, 20};
        int h3 = 6;
        System.out.println("Test Case 3:");
        System.out.println("Piles: " + Arrays.toString(piles3));
        System.out.println("Hours: " + h3);
        System.out.println("Expected: 23");
        System.out.println("Binary Search: " + minEatingSpeedBinarySearch(piles3, h3));
        System.out.println("Sorted Binary Search: " + minEatingSpeedSorted(piles3, h3));
        System.out.println("Linear Search: " + minEatingSpeedLinear(piles3, h3));
        System.out.println("Optimized Binary Search: " + minEatingSpeedOptimized(piles3, h3));
        System.out.println();
        
        // Test Case 4: Single pile
        int[] piles4 = {1000000000};
        int h4 = 2;
        System.out.println("Test Case 4:");
        System.out.println("Piles: " + Arrays.toString(piles4));
        System.out.println("Hours: " + h4);
        System.out.println("Expected: 500000000");
        System.out.println("Binary Search: " + minEatingSpeedBinarySearch(piles4, h4));
        System.out.println("Sorted Binary Search: " + minEatingSpeedSorted(piles4, h4));
        // Skip linear search for large numbers to avoid timeout
        System.out.println("Linear Search: [Skipped - would be too slow]");
        System.out.println("Optimized Binary Search: " + minEatingSpeedOptimized(piles4, h4));
        System.out.println();
        
        // Test Case 5: Minimum hours (h = piles.length)
        int[] piles5 = {1, 1, 1, 1000000000};
        int h5 = 4;
        System.out.println("Test Case 5:");
        System.out.println("Piles: " + Arrays.toString(piles5));
        System.out.println("Hours: " + h5);
        System.out.println("Expected: 1000000000 (must eat largest pile in 1 hour)");
        System.out.println("Binary Search: " + minEatingSpeedBinarySearch(piles5, h5));
        System.out.println("Sorted Binary Search: " + minEatingSpeedSorted(piles5, h5));
        System.out.println("Linear Search: [Skipped - would be too slow]");
        System.out.println("Optimized Binary Search: " + minEatingSpeedOptimized(piles5, h5));
        System.out.println();
        
        // Test Case 6: Many extra hours
        int[] piles6 = {5, 5, 5, 5};
        int h6 = 20;
        System.out.println("Test Case 6:");
        System.out.println("Piles: " + Arrays.toString(piles6));
        System.out.println("Hours: " + h6);
        System.out.println("Expected: 1 (can eat very slowly)");
        System.out.println("Binary Search: " + minEatingSpeedBinarySearch(piles6, h6));
        System.out.println("Sorted Binary Search: " + minEatingSpeedSorted(piles6, h6));
        System.out.println("Linear Search: " + minEatingSpeedLinear(piles6, h6));
        System.out.println("Optimized Binary Search: " + minEatingSpeedOptimized(piles6, h6));
        System.out.println();
        
        // Test Case 7: Edge case - equal piles and hours
        int[] piles7 = {10, 10, 10};
        int h7 = 3;
        System.out.println("Test Case 7:");
        System.out.println("Piles: " + Arrays.toString(piles7));
        System.out.println("Hours: " + h7);
        System.out.println("Expected: 10 (eat one pile per hour)");
        System.out.println("Binary Search: " + minEatingSpeedBinarySearch(piles7, h7));
        System.out.println("Sorted Binary Search: " + minEatingSpeedSorted(piles7, h7));
        System.out.println("Linear Search: " + minEatingSpeedLinear(piles7, h7));
        System.out.println("Optimized Binary Search: " + minEatingSpeedOptimized(piles7, h7));
    }
} 
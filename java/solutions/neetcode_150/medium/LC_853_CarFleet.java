/*
 * LeetCode Problem: 853. Car Fleet
 * URL: https://leetcode.com/problems/car-fleet/
 * Difficulty: Medium
 * 
 * Problem Description:
 * There are n cars at given miles away from the starting mile 0, traveling to reach the mile target.
 * You are given two integer arrays position and speed, both of length n, where position[i] is the 
 * starting mile of the ith car and speed[i] is the speed of the ith car in miles per hour.
 * 
 * A car fleet is a non-empty set of cars driving at the same position and same speed. Note that a 
 * single car is also a car fleet.
 * 
 * If a car catches up to a car fleet at the mile target, it will still be considered as one car fleet.
 * 
 * Return the number of car fleets that will arrive at the destination.
 * 
 * Example 1:
 * Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
 * Output: 3
 * Explanation:
 * The cars starting at 10 (speed 2) and 8 (speed 4) become a fleet, meeting each other at 12. 
 * The fleet forms at target.
 * The car starting at 0 (speed 1) does not catch up to any other car, so it is a fleet by itself.
 * The cars starting at 5 (speed 1) and 3 (speed 3) become a fleet, meeting each other at 6. 
 * The fleet moves as one until it reaches target.
 * 
 * Example 2:
 * Input: target = 10, position = [3], speed = [3]
 * Output: 1
 * Explanation: There is only one car, hence there is only one fleet.
 * 
 * Example 3:
 * Input: target = 100, position = [0,2,4], speed = [4,2,1]
 * Output: 1
 * Explanation:
 * The cars starting at 0, 2, and 4 join, at one or the other mile marks, into a single fleet.
 * 
 * Constraints:
 * - n == position.length == speed.length
 * - 1 <= n <= 10^5
 * - 0 < target <= 10^6
 * - 0 <= position[i] < target
 * - All the values of position are unique.
 * - 0 < speed[i] <= 10^6
 */

package solutions.neetcode_150.medium;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import common.Solution;

public class LC_853_CarFleet implements Solution {
    
    /**
     * Approach 1: Greedy with Sorting (Optimized Original)
     * 
     * Algorithm:
     * 1. Create pairs of (position, speed) and sort by position in descending order
     * 2. For each car from closest to target to farthest, calculate time to reach target
     * 3. If current car takes more time than previous maximum, it forms a new fleet
     * 4. Otherwise, it joins the existing fleet (catches up before or at target)
     * 
     * Time Complexity: O(n log n) - due to sorting
     * Space Complexity: O(n) - for storing car pairs
     */
    public int carFleetGreedy(int target, int[] position, int[] speed) {
        int n = position.length;
        if (n <= 1) return n;
        
        // Create array of [position, speed] pairs
        int[][] cars = new int[n][2];
        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }
        
        // Sort by position in descending order (closest to target first)
        Arrays.sort(cars, (a, b) -> Integer.compare(b[0], a[0]));
        
        int fleets = 0;
        double maxTime = 0;
        
        // Process each car from closest to farthest
        for (int i = 0; i < n; i++) {
            double timeToTarget = (double)(target - cars[i][0]) / cars[i][1];
            
            // If this car takes longer than any previous car, it forms a new fleet
            if (timeToTarget > maxTime) {
                fleets++;
                maxTime = timeToTarget;
            }
            // Otherwise, it catches up and joins the existing fleet
        }
        
        return fleets;
    }
    
    /**
     * Approach 2: Stack-based Solution
     * 
     * Algorithm:
     * 1. Sort cars by position (closest to target first)
     * 2. Use stack to keep track of time for each fleet
     * 3. For each car, calculate time to reach target
     * 4. Pop from stack while current time <= stack top (car catches up)
     * 5. Push current time to stack (new fleet or continuation)
     * 
     * Time Complexity: O(n log n) - due to sorting
     * Space Complexity: O(n) - for stack and car pairs
     */
    public int carFleetStack(int target, int[] position, int[] speed) {
        int n = position.length;
        if (n <= 1) return n;
        
        // Create and sort car pairs
        int[][] cars = new int[n][2];
        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }
        Arrays.sort(cars, (a, b) -> Integer.compare(b[0], a[0]));
        
        Stack<Double> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            double timeToTarget = (double)(target - cars[i][0]) / cars[i][1];
            
            // If this car takes longer time, it forms a new fleet
            // If it takes same or less time, it joins existing fleet (don't push)
            if (stack.isEmpty() || timeToTarget > stack.peek()) {
                stack.push(timeToTarget);
            }
        }
        
        return stack.size();
    }
    
    /**
     * Approach 3: Map-based Solution with Time Calculation
     * 
     * Algorithm:
     * 1. Create a map from position to time to reach target
     * 2. Sort positions in descending order
     * 3. Iterate through sorted positions, counting fleets
     * 4. A new fleet is formed when current time > previous maximum time
     * 
     * Time Complexity: O(n log n) - due to sorting
     * Space Complexity: O(n) - for map and sorted positions
     */
    public int carFleetMap(int target, int[] position, int[] speed) {
        int n = position.length;
        if (n <= 1) return n;
        
        // Map position to time to reach target
        Map<Integer, Double> positionToTime = new HashMap<>();
        for (int i = 0; i < n; i++) {
            double time = (double)(target - position[i]) / speed[i];
            positionToTime.put(position[i], time);
        }
        
        // Sort positions in descending order
        Integer[] sortedPositions = new Integer[n];
        for (int i = 0; i < n; i++) {
            sortedPositions[i] = position[i];
        }
        Arrays.sort(sortedPositions, Collections.reverseOrder());
        
        int fleets = 0;
        double maxTime = 0;
        
        for (int pos : sortedPositions) {
            double time = positionToTime.get(pos);
            if (time > maxTime) {
                fleets++;
                maxTime = time;
            }
        }
        
        return fleets;
    }
    
    /**
     * Approach 4: Array Index Sorting (Memory Optimized)
     * 
     * Algorithm:
     * 1. Create array of indices and sort by position (descending)
     * 2. Iterate through sorted indices, calculating time on the fly
     * 3. Count fleets based on time comparison
     * 
     * Time Complexity: O(n log n) - due to sorting
     * Space Complexity: O(n) - for indices array
     */
    public int carFleetIndexSort(int target, int[] position, int[] speed) {
        int n = position.length;
        if (n <= 1) return n;
        
        // Create array of indices
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        
        // Sort indices by position in descending order
        Arrays.sort(indices, (i, j) -> Integer.compare(position[j], position[i]));
        
        int fleets = 0;
        double maxTime = 0;
        
        for (int idx : indices) {
            double time = (double)(target - position[idx]) / speed[idx];
            if (time > maxTime) {
                fleets++;
                maxTime = time;
            }
        }
        
        return fleets;
    }
    
    @Override
    public void run() {
        // Test Case 1: Example from problem
        int target1 = 12;
        int[] position1 = {10, 8, 0, 5, 3};
        int[] speed1 = {2, 4, 1, 1, 3};
        System.out.println("Test Case 1:");
        System.out.println("Target: " + target1);
        System.out.println("Position: " + Arrays.toString(position1));
        System.out.println("Speed: " + Arrays.toString(speed1));
        System.out.println("Expected: 3");
        System.out.println("Greedy: " + carFleetGreedy(target1, position1, speed1));
        System.out.println("Stack: " + carFleetStack(target1, position1, speed1));
        System.out.println("Map: " + carFleetMap(target1, position1, speed1));
        System.out.println("Index Sort: " + carFleetIndexSort(target1, position1, speed1));
        System.out.println();
        
        // Test Case 2: Single car
        int target2 = 10;
        int[] position2 = {3};
        int[] speed2 = {3};
        System.out.println("Test Case 2:");
        System.out.println("Target: " + target2);
        System.out.println("Position: " + Arrays.toString(position2));
        System.out.println("Speed: " + Arrays.toString(speed2));
        System.out.println("Expected: 1");
        System.out.println("Greedy: " + carFleetGreedy(target2, position2, speed2));
        System.out.println("Stack: " + carFleetStack(target2, position2, speed2));
        System.out.println("Map: " + carFleetMap(target2, position2, speed2));
        System.out.println("Index Sort: " + carFleetIndexSort(target2, position2, speed2));
        System.out.println();
        
        // Test Case 3: All cars form one fleet
        int target3 = 100;
        int[] position3 = {0, 2, 4};
        int[] speed3 = {4, 2, 1};
        System.out.println("Test Case 3:");
        System.out.println("Target: " + target3);
        System.out.println("Position: " + Arrays.toString(position3));
        System.out.println("Speed: " + Arrays.toString(speed3));
        System.out.println("Expected: 1");
        System.out.println("Greedy: " + carFleetGreedy(target3, position3, speed3));
        System.out.println("Stack: " + carFleetStack(target3, position3, speed3));
        System.out.println("Map: " + carFleetMap(target3, position3, speed3));
        System.out.println("Index Sort: " + carFleetIndexSort(target3, position3, speed3));
        System.out.println();
        
        // Test Case 4: Edge case - all cars at same position would be invalid,
        // so test cars that don't catch up
        int target4 = 20;
        int[] position4 = {6, 8};
        int[] speed4 = {1, 2};
        System.out.println("Test Case 4:");
        System.out.println("Target: " + target4);
        System.out.println("Position: " + Arrays.toString(position4));
        System.out.println("Speed: " + Arrays.toString(speed4));
        System.out.println("Expected: 2");
        System.out.println("Greedy: " + carFleetGreedy(target4, position4, speed4));
        System.out.println("Stack: " + carFleetStack(target4, position4, speed4));
        System.out.println("Map: " + carFleetMap(target4, position4, speed4));
        System.out.println("Index Sort: " + carFleetIndexSort(target4, position4, speed4));
        System.out.println();
        
        // Test Case 5: Complex scenario
        int target5 = 15;
        int[] position5 = {0, 3, 6, 9, 12};
        int[] speed5 = {1, 1, 1, 1, 1};
        System.out.println("Test Case 5:");
        System.out.println("Target: " + target5);
        System.out.println("Position: " + Arrays.toString(position5));
        System.out.println("Speed: " + Arrays.toString(speed5));
        System.out.println("Expected: 5 (all same speed, no catching up)");
        System.out.println("Greedy: " + carFleetGreedy(target5, position5, speed5));
        System.out.println("Stack: " + carFleetStack(target5, position5, speed5));
        System.out.println("Map: " + carFleetMap(target5, position5, speed5));
        System.out.println("Index Sort: " + carFleetIndexSort(target5, position5, speed5));
    }
} 
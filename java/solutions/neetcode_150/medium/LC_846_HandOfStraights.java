package solutions.neetcode_150.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import common.Solution;

/**
 * LeetCode 846: Hand of Straights
 * URL: https://leetcode.com/problems/hand-of-straights/
 * Difficulty: Medium
 * 
 * Problem:
 * Alice has some number of cards and she wants to rearrange the cards into groups 
 * so that each group is of size groupSize and consists of groupSize consecutive cards.
 * Given an integer array hand where hand[i] is the value written on the ith card 
 * and an integer groupSize, return true if she can rearrange the cards, or false otherwise.
 * 
 * Example:
 * Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
 * Output: true
 * Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
 * 
 * Approach 1: HashMap + Sorted Keys (Your Original Approach)
 * - Create frequency map of all cards
 * - Sort unique cards to process in order
 * - For each card, try to form consecutive groups
 * - Time Complexity: O(n log n) due to sorting
 * - Space Complexity: O(n) for map and keys list
 * 
 * Approach 2: TreeMap (Optimized)
 * - Use TreeMap to maintain sorted order automatically
 * - Process cards in natural order without explicit sorting
 * - Time Complexity: O(n log n) due to TreeMap operations
 * - Space Complexity: O(n) for map only
 * 
 * Approach 3: PriorityQueue/MinHeap
 * - Use min heap to always get the smallest available card
 * - More intuitive for some, but similar complexity
 * - Time Complexity: O(n log n)
 * - Space Complexity: O(n)
 */
public class LC_846_HandOfStraights implements Solution {

    /**
     * Approach 1: HashMap + Sorted Keys (Original approach)
     * Creates frequency map and sorts keys for processing consecutive groups
     */
    public boolean isNStraightHandHashMap(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0) return false;
        
        Map<Integer, Integer> freq = new HashMap<>();
        List<Integer> keys = new ArrayList<>();

        // Build frequency map and collect unique cards
        for (int card : hand) {
            if (freq.containsKey(card)) {
                freq.put(card, freq.get(card) + 1);
            } else {
                freq.put(card, 1);
                keys.add(card);
            }
        }

        // Sort keys to process in ascending order
        Collections.sort(keys);
        
        // Try to form groups starting from each card
        for (int card : keys) {
            int count = freq.get(card);
            if (count == 0) continue; // Already used in earlier groups

            // Try to form 'count' groups starting from 'card'
            for (int i = 0; i < groupSize; i++) {
                int next = card + i;
                if (freq.getOrDefault(next, 0) < count) return false;
                freq.put(next, freq.get(next) - count);
            }
        }

        return true;
    }

    /**
     * Approach 2: TreeMap (Cleaner implementation)
     * Uses TreeMap to maintain sorted order automatically
     */
    public boolean isNStraightHandTreeMap(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0) return false;
        
        TreeMap<Integer, Integer> freq = new TreeMap<>();
        
        // Build frequency map (TreeMap keeps keys sorted)
        for (int card : hand) {
            freq.put(card, freq.getOrDefault(card, 0) + 1);
        }
        
        // Process cards in sorted order
        while (!freq.isEmpty()) {
            int startCard = freq.firstKey(); // Get smallest card
            int count = freq.get(startCard);
            
            // Try to form 'count' groups starting from 'startCard'
            for (int i = 0; i < groupSize; i++) {
                int currentCard = startCard + i;
                if (!freq.containsKey(currentCard) || freq.get(currentCard) < count) {
                    return false;
                }
                
                freq.put(currentCard, freq.get(currentCard) - count);
                if (freq.get(currentCard) == 0) {
                    freq.remove(currentCard);
                }
            }
        }
        
        return true;
    }

    /**
     * Approach 3: PriorityQueue/MinHeap
     * Uses min heap to process cards in ascending order
     */
    public boolean isNStraightHandMinHeap(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0) return false;
        
        Map<Integer, Integer> freq = new HashMap<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        // Build frequency map
        for (int card : hand) {
            if (!freq.containsKey(card)) {
                freq.put(card, 0);
                minHeap.offer(card);
            }
            freq.put(card, freq.get(card) + 1);
        }
        
        // Process cards using min heap
        while (!minHeap.isEmpty()) {
            int startCard = minHeap.peek();
            int count = freq.get(startCard);
            
            // Try to form 'count' groups starting from 'startCard'
            for (int i = 0; i < groupSize; i++) {
                int currentCard = startCard + i;
                if (!freq.containsKey(currentCard) || freq.get(currentCard) < count) {
                    return false;
                }
                
                freq.put(currentCard, freq.get(currentCard) - count);
                if (freq.get(currentCard) == 0) {
                    // Remove from heap if frequency becomes 0
                    minHeap.remove(currentCard);
                }
            }
        }
        
        return true;
    }

    @Override
    public void run() {
        System.out.println("Testing LC_846_HandOfStraights...");
        
        // Test case 1: Valid consecutive groups
        int[] hand1 = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        int groupSize1 = 3;
        System.out.println("Test 1 - hand: [1,2,3,6,2,3,4,7,8], groupSize: 3");
        
        boolean result1a = isNStraightHandHashMap(hand1, groupSize1);
        boolean result1b = isNStraightHandTreeMap(hand1, groupSize1);
        boolean result1c = isNStraightHandMinHeap(hand1, groupSize1);
        
        System.out.println("Expected: true");
        System.out.println("HashMap approach: " + result1a + " " + (result1a ? "✓" : "✗"));
        System.out.println("TreeMap approach: " + result1b + " " + (result1b ? "✓" : "✗"));
        System.out.println("MinHeap approach: " + result1c + " " + (result1c ? "✓" : "✗"));
        System.out.println();
        
        // Test case 2: Invalid - cannot form consecutive groups
        int[] hand2 = {1, 2, 3, 4, 5};
        int groupSize2 = 4;
        System.out.println("Test 2 - hand: [1,2,3,4,5], groupSize: 4");
        
        boolean result2a = isNStraightHandHashMap(hand2, groupSize2);
        boolean result2b = isNStraightHandTreeMap(hand2, groupSize2);
        boolean result2c = isNStraightHandMinHeap(hand2, groupSize2);
        
        System.out.println("Expected: false");
        System.out.println("HashMap approach: " + result2a + " " + (result2a == false ? "✓" : "✗"));
        System.out.println("TreeMap approach: " + result2b + " " + (result2b == false ? "✓" : "✗"));
        System.out.println("MinHeap approach: " + result2c + " " + (result2c == false ? "✓" : "✗"));
        System.out.println();
        
        // Test case 3: Edge case - single group
        int[] hand3 = {1, 2, 3};
        int groupSize3 = 3;
        System.out.println("Test 3 - hand: [1,2,3], groupSize: 3");
        
        boolean result3a = isNStraightHandHashMap(hand3, groupSize3);
        boolean result3b = isNStraightHandTreeMap(hand3, groupSize3);
        boolean result3c = isNStraightHandMinHeap(hand3, groupSize3);
        
        System.out.println("Expected: true");
        System.out.println("HashMap approach: " + result3a + " " + (result3a ? "✓" : "✗"));
        System.out.println("TreeMap approach: " + result3b + " " + (result3b ? "✓" : "✗"));
        System.out.println("MinHeap approach: " + result3c + " " + (result3c ? "✓" : "✗"));
        System.out.println();
        
        // Test case 4: Invalid - length not divisible by groupSize
        int[] hand4 = {1, 2, 3, 4, 5};
        int groupSize4 = 3;
        System.out.println("Test 4 - hand: [1,2,3,4,5], groupSize: 3");
        
        boolean result4a = isNStraightHandHashMap(hand4, groupSize4);
        boolean result4b = isNStraightHandTreeMap(hand4, groupSize4);
        boolean result4c = isNStraightHandMinHeap(hand4, groupSize4);
        
        System.out.println("Expected: false (length not divisible)");
        System.out.println("HashMap approach: " + result4a + " " + (result4a == false ? "✓" : "✗"));
        System.out.println("TreeMap approach: " + result4b + " " + (result4b == false ? "✓" : "✗"));
        System.out.println("MinHeap approach: " + result4c + " " + (result4c == false ? "✓" : "✗"));
        System.out.println();
        
        // Test case 5: Duplicate cards forming multiple groups
        int[] hand5 = {1, 1, 2, 2, 3, 3};
        int groupSize5 = 3;
        System.out.println("Test 5 - hand: [1,1,2,2,3,3], groupSize: 3");
        
        boolean result5a = isNStraightHandHashMap(hand5, groupSize5);
        boolean result5b = isNStraightHandTreeMap(hand5, groupSize5);
        boolean result5c = isNStraightHandMinHeap(hand5, groupSize5);
        
        System.out.println("Expected: true (two groups of [1,2,3])");
        System.out.println("HashMap approach: " + result5a + " " + (result5a ? "✓" : "✗"));
        System.out.println("TreeMap approach: " + result5b + " " + (result5b ? "✓" : "✗"));
        System.out.println("MinHeap approach: " + result5c + " " + (result5c ? "✓" : "✗"));
    }
} 
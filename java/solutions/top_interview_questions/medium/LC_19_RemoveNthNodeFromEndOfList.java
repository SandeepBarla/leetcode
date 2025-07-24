/*
 * LeetCode Problem 19: Remove Nth Node From End of List
 * URL: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 * Difficulty: Medium
 *
 * Approaches:
 * 1. Two Pointers (One Pass) - O(n) time, O(1) space
 * 2. Two Pass Approach - O(n) time, O(1) space
 * 3. Stack Approach - O(n) time, O(n) space
 * 4. Recursion - O(n) time, O(n) space
 *
 * Time Complexity:
 * - All approaches: O(n) - traverse the list
 *
 * Space Complexity:
 * - Approach 1,2: O(1) - only using pointers
 * - Approach 3: O(n) - stack storage
 * - Approach 4: O(n) - recursion call stack
 */

package solutions.top_interview_questions.medium;

import java.util.Stack;

import common.LinkedListUtils;
import common.ListNode;
import common.Solution;

public class LC_19_RemoveNthNodeFromEndOfList implements Solution {

    // Approach 1: Two Pointers (One Pass) - Optimal
    public ListNode removeNthFromEndTwoPointers(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode first = dummy;
        ListNode second = dummy;
        
        // Move first pointer n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }
        
        // Move both pointers until first reaches end
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        
        // Remove the nth node from end
        second.next = second.next.next;
        
        return dummy.next;
    }

    // Approach 2: Two Pass Approach
    public ListNode removeNthFromEndTwoPass(ListNode head, int n) {
        // First pass: calculate length
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }
        
        // Edge case: removing the first node
        if (length == n) {
            return head.next;
        }
        
        // Second pass: find the node before the one to remove
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        current = dummy;
        
        for (int i = 0; i < length - n; i++) {
            current = current.next;
        }
        
        // Remove the nth node from end
        current.next = current.next.next;
        
        return dummy.next;
    }

    // Approach 3: Stack Approach
    public ListNode removeNthFromEndStack(ListNode head, int n) {
        Stack<ListNode> stack = new Stack<>();
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // Push all nodes to stack
        ListNode current = dummy;
        while (current != null) {
            stack.push(current);
            current = current.next;
        }
        
        // Pop n+1 nodes (nth node from end and its previous node)
        for (int i = 0; i <= n; i++) {
            current = stack.pop();
        }
        
        // Remove the nth node from end
        current.next = current.next.next;
        
        return dummy.next;
    }

    // Approach 4: Recursion
    public ListNode removeNthFromEndRecursion(ListNode head, int n) {
        int[] count = new int[1]; // Use array to pass by reference
        ListNode result = removeNthFromEndHelper(head, n, count);
        return result;
    }
    
    private ListNode removeNthFromEndHelper(ListNode head, int n, int[] count) {
        if (head == null) {
            return null;
        }
        
        head.next = removeNthFromEndHelper(head.next, n, count);
        count[0]++;
        
        // If this is the nth node from end, skip it
        if (count[0] == n) {
            return head.next;
        }
        
        return head;
    }

    // Helper method to create test lists
    private ListNode createList(int[] values) {
        if (values.length == 0) return null;
        
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }
        
        return head;
    }

    @Override
    public void run() {
        // Test Case 1: Regular case [1,2,3,4,5], n=2
        int[] values1 = {1, 2, 3, 4, 5};
        int n1 = 2;
        
        System.out.println("Test Case 1: Remove " + n1 + "th node from end");
        LinkedListUtils.printList("Original", createList(values1));
        
        ListNode result1_1 = removeNthFromEndTwoPointers(createList(values1), n1);
        LinkedListUtils.printList("Two Pointers", result1_1); // Expected: [1,2,3,5]
        
        ListNode result1_2 = removeNthFromEndTwoPass(createList(values1), n1);
        LinkedListUtils.printList("Two Pass", result1_2); // Expected: [1,2,3,5]
        
        ListNode result1_3 = removeNthFromEndStack(createList(values1), n1);
        LinkedListUtils.printList("Stack", result1_3); // Expected: [1,2,3,5]
        
        ListNode result1_4 = removeNthFromEndRecursion(createList(values1), n1);
        LinkedListUtils.printList("Recursion", result1_4); // Expected: [1,2,3,5]
        System.out.println();

        // Test Case 2: Remove first node [1], n=1
        int[] values2 = {1};
        int n2 = 1;
        
        System.out.println("Test Case 2: Remove " + n2 + "th node from single element list");
        LinkedListUtils.printList("Original", createList(values2));
        
        ListNode result2_1 = removeNthFromEndTwoPointers(createList(values2), n2);
        LinkedListUtils.printList("Two Pointers", result2_1); // Expected: []
        
        ListNode result2_2 = removeNthFromEndTwoPass(createList(values2), n2);
        LinkedListUtils.printList("Two Pass", result2_2); // Expected: []
        System.out.println();

        // Test Case 3: Remove first node from longer list [1,2], n=2
        int[] values3 = {1, 2};
        int n3 = 2;
        
        System.out.println("Test Case 3: Remove " + n3 + "th node (first node) from two-element list");
        LinkedListUtils.printList("Original", createList(values3));
        
        ListNode result3_1 = removeNthFromEndTwoPointers(createList(values3), n3);
        LinkedListUtils.printList("Two Pointers", result3_1); // Expected: [2]
        
        ListNode result3_2 = removeNthFromEndTwoPass(createList(values3), n3);
        LinkedListUtils.printList("Two Pass", result3_2); // Expected: [2]
        System.out.println();

        // Test Case 4: Remove last node [1,2,3], n=1
        int[] values4 = {1, 2, 3};
        int n4 = 1;
        
        System.out.println("Test Case 4: Remove " + n4 + "th node (last node)");
        LinkedListUtils.printList("Original", createList(values4));
        
        ListNode result4_1 = removeNthFromEndTwoPointers(createList(values4), n4);
        LinkedListUtils.printList("Two Pointers", result4_1); // Expected: [1,2]
        
        ListNode result4_2 = removeNthFromEndStack(createList(values4), n4);
        LinkedListUtils.printList("Stack", result4_2); // Expected: [1,2]
        
        ListNode result4_3 = removeNthFromEndRecursion(createList(values4), n4);
        LinkedListUtils.printList("Recursion", result4_3); // Expected: [1,2]
        System.out.println();

        // Test Case 5: Performance test with longer list
        int[] values5 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int n5 = 5;
        
        System.out.println("Test Case 5: Performance test - Remove " + n5 + "th node from 10-element list");
        LinkedListUtils.printList("Original", createList(values5));
        
        long start = System.currentTimeMillis();
        ListNode result5_1 = removeNthFromEndTwoPointers(createList(values5), n5);
        long time1 = System.currentTimeMillis() - start;
        LinkedListUtils.printList("Two Pointers", result5_1); // Expected: [1,2,3,4,5,7,8,9,10]
        System.out.println("Two Pointers time: " + time1 + "ms");
        
        start = System.currentTimeMillis();
        ListNode result5_2 = removeNthFromEndTwoPass(createList(values5), n5);
        long time2 = System.currentTimeMillis() - start;
        LinkedListUtils.printList("Two Pass", result5_2); // Expected: [1,2,3,4,5,7,8,9,10]
        System.out.println("Two Pass time: " + time2 + "ms");
        
        start = System.currentTimeMillis();
        ListNode result5_3 = removeNthFromEndStack(createList(values5), n5);
        long time3 = System.currentTimeMillis() - start;
        LinkedListUtils.printList("Stack", result5_3); // Expected: [1,2,3,4,5,7,8,9,10]
        System.out.println("Stack time: " + time3 + "ms");
        
        start = System.currentTimeMillis();
        ListNode result5_4 = removeNthFromEndRecursion(createList(values5), n5);
        long time4 = System.currentTimeMillis() - start;
        LinkedListUtils.printList("Recursion", result5_4); // Expected: [1,2,3,4,5,7,8,9,10]
        System.out.println("Recursion time: " + time4 + "ms");

        // Test Case 6: Remove middle node [1,2,3,4,5], n=3
        int[] values6 = {1, 2, 3, 4, 5};
        int n6 = 3;
        
        System.out.println("\nTest Case 6: Remove " + n6 + "th node (middle) from 5-element list");
        LinkedListUtils.printList("Original", createList(values6));
        
        ListNode result6 = removeNthFromEndTwoPointers(createList(values6), n6);
        LinkedListUtils.printList("Result", result6); // Expected: [1,2,4,5]
    }
} 
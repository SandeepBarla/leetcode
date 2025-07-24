/*
 * LeetCode Problem 160: Intersection of Two Linked Lists
 * URL: https://leetcode.com/problems/intersection-of-two-linked-lists/
 * Difficulty: Easy
 *
 * Approaches:
 * 1. Two Pointers (Optimal) - O(m + n) time, O(1) space
 * 2. HashSet Approach - O(m + n) time, O(m) space
 * 3. Length Difference Approach - O(m + n) time, O(1) space
 *
 * Time Complexity:
 * - Approach 1: O(m + n) - traverse both lists once or twice
 * - Approach 2: O(m + n) - traverse first list to build set, second to check
 * - Approach 3: O(m + n) - two passes to find lengths, one pass to find intersection
 *
 * Space Complexity:
 * - Approach 1: O(1) - only using pointers
 * - Approach 2: O(m) - HashSet storing nodes from first list
 * - Approach 3: O(1) - only using variables and pointers
 */

package solutions.top_interview_questions.easy;

import java.util.HashSet;
import java.util.Set;

import common.ListNode;
import common.Solution;

public class LC_160_IntersectionOfTwoLinkedLists implements Solution {

    // Approach 1: Two Pointers (Optimal) - Elegant solution
    public ListNode getIntersectionNodeTwoPointers(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        
        ListNode pointerA = headA;
        ListNode pointerB = headB;
        
        // When pointer reaches end, redirect to other list's head
        // They will meet at intersection or both reach null
        while (pointerA != pointerB) {
            pointerA = (pointerA == null) ? headB : pointerA.next;
            pointerB = (pointerB == null) ? headA : pointerB.next;
        }
        
        return pointerA; // Either intersection node or null
    }

    // Approach 2: HashSet Approach
    public ListNode getIntersectionNodeHashSet(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        
        Set<ListNode> visitedNodes = new HashSet<>();
        
        // Add all nodes from first list to set
        ListNode current = headA;
        while (current != null) {
            visitedNodes.add(current);
            current = current.next;
        }
        
        // Check nodes in second list
        current = headB;
        while (current != null) {
            if (visitedNodes.contains(current)) {
                return current; // Found intersection
            }
            current = current.next;
        }
        
        return null; // No intersection
    }

    // Approach 3: Length Difference Approach
    public ListNode getIntersectionNodeLengthDiff(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        
        // Calculate lengths of both lists
        int lengthA = getLength(headA);
        int lengthB = getLength(headB);
        
        // Align starting positions
        ListNode pointerA = headA;
        ListNode pointerB = headB;
        
        if (lengthA > lengthB) {
            // Advance pointer A by difference
            for (int i = 0; i < lengthA - lengthB; i++) {
                pointerA = pointerA.next;
            }
        } else {
            // Advance pointer B by difference
            for (int i = 0; i < lengthB - lengthA; i++) {
                pointerB = pointerB.next;
            }
        }
        
        // Now traverse together until intersection or end
        while (pointerA != null && pointerB != null) {
            if (pointerA == pointerB) {
                return pointerA;
            }
            pointerA = pointerA.next;
            pointerB = pointerB.next;
        }
        
        return null;
    }
    
    private int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    @Override
    public void run() {
        // Test Case 1: Lists with intersection
        // List A: 4 -> 1 -> 8 -> 4 -> 5
        // List B: 5 -> 6 -> 1 -> 8 -> 4 -> 5 (intersection at node with value 8)
        ListNode intersectionNode = new ListNode(8);
        intersectionNode.next = new ListNode(4);
        intersectionNode.next.next = new ListNode(5);
        
        ListNode headA1 = new ListNode(4);
        headA1.next = new ListNode(1);
        headA1.next.next = intersectionNode;
        
        ListNode headB1 = new ListNode(5);
        headB1.next = new ListNode(6);
        headB1.next.next = new ListNode(1);
        headB1.next.next.next = intersectionNode;
        
        System.out.println("Test Case 1 (With intersection):");
        ListNode result1_1 = getIntersectionNodeTwoPointers(headA1, headB1);
        System.out.println("Two Pointers: " + (result1_1 != null ? result1_1.val : "null")); // Expected: 8
        
        ListNode result1_2 = getIntersectionNodeHashSet(headA1, headB1);
        System.out.println("HashSet: " + (result1_2 != null ? result1_2.val : "null")); // Expected: 8
        
        ListNode result1_3 = getIntersectionNodeLengthDiff(headA1, headB1);
        System.out.println("Length Diff: " + (result1_3 != null ? result1_3.val : "null")); // Expected: 8
        System.out.println();

        // Test Case 2: Lists without intersection
        ListNode headA2 = new ListNode(2);
        headA2.next = new ListNode(6);
        headA2.next.next = new ListNode(4);
        
        ListNode headB2 = new ListNode(1);
        headB2.next = new ListNode(5);
        
        System.out.println("Test Case 2 (No intersection):");
        ListNode result2_1 = getIntersectionNodeTwoPointers(headA2, headB2);
        System.out.println("Two Pointers: " + (result2_1 != null ? result2_1.val : "null")); // Expected: null
        
        ListNode result2_2 = getIntersectionNodeHashSet(headA2, headB2);
        System.out.println("HashSet: " + (result2_2 != null ? result2_2.val : "null")); // Expected: null
        
        ListNode result2_3 = getIntersectionNodeLengthDiff(headA2, headB2);
        System.out.println("Length Diff: " + (result2_3 != null ? result2_3.val : "null")); // Expected: null
        System.out.println();

        // Test Case 3: One empty list
        System.out.println("Test Case 3 (One empty list):");
        ListNode result3 = getIntersectionNodeTwoPointers(headA1, null);
        System.out.println("Two Pointers: " + (result3 != null ? result3.val : "null")); // Expected: null
    }
} 
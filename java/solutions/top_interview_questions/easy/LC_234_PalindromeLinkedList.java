/*
 * LeetCode Problem 234: Palindrome Linked List
 * URL: https://leetcode.com/problems/palindrome-linked-list/
 * Difficulty: Easy
 *
 * Approaches:
 * 1. Reverse Second Half (Optimal) - O(n) time, O(1) space
 * 2. ArrayList + Two Pointers - O(n) time, O(n) space
 * 3. Recursion - O(n) time, O(n) space
 *
 * Time Complexity:
 * - Approach 1: O(n) - traverse list twice (find middle + reverse & compare)
 * - Approach 2: O(n) - convert to array then two pointers
 * - Approach 3: O(n) - recursive calls for each node
 *
 * Space Complexity:
 * - Approach 1: O(1) - only using pointers, modifies list
 * - Approach 2: O(n) - ArrayList storage
 * - Approach 3: O(n) - recursion call stack
 */

package solutions.top_interview_questions.easy;

import java.util.ArrayList;
import java.util.List;

import common.LinkedListUtils;
import common.ListNode;
import common.Solution;

public class LC_234_PalindromeLinkedList implements Solution {

    // Approach 1: Reverse Second Half (Optimal)
    public boolean isPalindromeReverseHalf(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        // Find middle of the linked list
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Reverse second half
        ListNode secondHalf = reverseList(slow.next);
        ListNode firstHalf = head;
        
        // Compare first and reversed second half
        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        
        return true;
    }
    
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        
        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }
        
        return prev;
    }

    // Approach 2: ArrayList + Two Pointers
    public boolean isPalindromeArrayList(ListNode head) {
        List<Integer> values = new ArrayList<>();
        
        // Convert linked list to ArrayList
        ListNode current = head;
        while (current != null) {
            values.add(current.val);
            current = current.next;
        }
        
        // Check palindrome using two pointers
        int left = 0;
        int right = values.size() - 1;
        
        while (left < right) {
            if (!values.get(left).equals(values.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }

    // Approach 3: Recursion
    private ListNode frontPointer;
    
    public boolean isPalindromeRecursion(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }
    
    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            if (!recursivelyCheck(currentNode.next)) {
                return false;
            }
            if (currentNode.val != frontPointer.val) {
                return false;
            }
            frontPointer = frontPointer.next;
        }
        return true;
    }

    // Helper method to clone a linked list
    private ListNode cloneList(ListNode head) {
        if (head == null) return null;
        ListNode newHead = new ListNode(head.val);
        ListNode current = newHead;
        head = head.next;
        while (head != null) {
            current.next = new ListNode(head.val);
            current = current.next;
            head = head.next;
        }
        return newHead;
    }

    @Override
    public void run() {
        // Test Case 1: Palindrome list [1, 2, 2, 1]
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(2);
        head1.next.next.next = new ListNode(1);
        
        System.out.println("Test Case 1: [1, 2, 2, 1]");
        LinkedListUtils.printList("List", head1);
        System.out.println("Reverse Half: " + isPalindromeReverseHalf(cloneList(head1))); // Expected: true
        System.out.println("ArrayList: " + isPalindromeArrayList(cloneList(head1))); // Expected: true
        System.out.println("Recursion: " + isPalindromeRecursion(cloneList(head1))); // Expected: true
        System.out.println();

        // Test Case 2: Not palindrome [1, 2]
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        
        System.out.println("Test Case 2: [1, 2]");
        LinkedListUtils.printList("List", head2);
        System.out.println("Reverse Half: " + isPalindromeReverseHalf(cloneList(head2))); // Expected: false
        System.out.println("ArrayList: " + isPalindromeArrayList(cloneList(head2))); // Expected: false
        System.out.println("Recursion: " + isPalindromeRecursion(cloneList(head2))); // Expected: false
        System.out.println();

        // Test Case 3: Single node [1]
        ListNode head3 = new ListNode(1);
        
        System.out.println("Test Case 3: [1]");
        LinkedListUtils.printList("List", head3);
        System.out.println("Reverse Half: " + isPalindromeReverseHalf(cloneList(head3))); // Expected: true
        System.out.println("ArrayList: " + isPalindromeArrayList(cloneList(head3))); // Expected: true
        System.out.println("Recursion: " + isPalindromeRecursion(cloneList(head3))); // Expected: true
        System.out.println();

        // Test Case 4: Odd length palindrome [1, 2, 3, 2, 1]
        ListNode head4 = new ListNode(1);
        head4.next = new ListNode(2);
        head4.next.next = new ListNode(3);
        head4.next.next.next = new ListNode(2);
        head4.next.next.next.next = new ListNode(1);
        
        System.out.println("Test Case 4: [1, 2, 3, 2, 1]");
        LinkedListUtils.printList("List", head4);
        System.out.println("Reverse Half: " + isPalindromeReverseHalf(cloneList(head4))); // Expected: true
        System.out.println("ArrayList: " + isPalindromeArrayList(cloneList(head4))); // Expected: true
        System.out.println("Recursion: " + isPalindromeRecursion(cloneList(head4))); // Expected: true
    }
} 
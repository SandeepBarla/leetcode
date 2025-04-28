/*
 * LeetCode Problem 206: Reverse Linked List
 * URL: https://leetcode.com/problems/reverse-linked-list/
 * Difficulty: Easy
 *
 * Approach: Iterative Reversal
 * - Use three pointers: prev, curr, and next.
 * - Reverse the direction of each link one by one.
 * - At the end, prev will point to the new head.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
package solutions.top_interview_questions.easy;

import common.LinkedListUtils;
import common.ListNode;
import common.Solution;

public class LC_206_ReverseLinkedList implements Solution {

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode next;

        while (curr != null) {
            next = curr.next; // Save next node
            curr.next = prev; // Reverse the link
            prev = curr; // Move prev to current
            curr = next; // Move curr to next
        }

        return prev;
    }

    @Override
    public void run() {
        int[] input = { 1, 2, 3, 4, 5 };

        ListNode head = LinkedListUtils.createLinkedList(input);
        LinkedListUtils.printList("Original List", head);

        ListNode reversedHead = reverseList(head);
        LinkedListUtils.printList("Reversed List", reversedHead);
    }
}
/*
 * LeetCode Problem 141: Linked List Cycle
 * URL: https://leetcode.com/problems/linked-list-cycle/
 * Difficulty: Easy
 *
 * Approach: Floyd’s Tortoise and Hare (Two Pointers)
 * - Use a slow and fast pointer.
 * - Move slow by one step, fast by two steps.
 * - If there's a cycle, the fast pointer will meet the slow pointer.
 * - If fast reaches the end, the list has no cycle.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
package solutions.top_interview_questions.easy;

import common.LinkedListUtils;
import common.ListNode;
import common.Solution;

public class LC_141_LinkedListCycle implements Solution {

    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast)
                return true;
        }

        return false;
    }

    @Override
    public void run() {
        int[] withCycle = { 3, 2, 0, -4 };
        int[] withoutCycle = { 1, 2, 3, 4 };

        // Create a cycle at position 1 (0-based)
        ListNode cyclic = LinkedListUtils.createCyclicLinkedList(withCycle, 1);
        System.out.println("Cycle Detected (should be true): " + hasCycle(cyclic));

        ListNode nonCyclic = LinkedListUtils.createCyclicLinkedList(withoutCycle, -1);
        LinkedListUtils.printList("Non-cyclic List", nonCyclic);
        System.out.println("Cycle Detected (should be false): " + hasCycle(nonCyclic));
    }
}
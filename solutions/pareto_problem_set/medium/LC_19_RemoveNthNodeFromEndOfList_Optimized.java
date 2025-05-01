/*
 * LeetCode Problem 19: Remove Nth Node From End of List
 * URL: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 * Difficulty: Medium
 *
 * Approach: One-Pass Using Two Pointers
 * - Use a dummy node before head to simplify edge cases.
 * - Move the fast pointer n steps ahead.
 * - Then move both fast and slow pointers until fast reaches the end.
 * - The slow pointer will be just before the node to delete.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
package solutions.pareto_problem_set.medium;

import common.LinkedListUtils;
import common.ListNode;
import common.Solution;

public class LC_19_RemoveNthNodeFromEndOfList_Optimized implements Solution {

  public ListNode removeNthFromEnd(ListNode head, int n) {
    // Add dummy node before head to simplify head removal case
    ListNode dummy = new ListNode(0);
    dummy.next = head;

    ListNode fast = dummy;
    ListNode slow = dummy;

    // Move fast n steps ahead
    for (int i = 0; i < n; i++) {
      fast = fast.next;
    }

    // Move both fast and slow until fast reaches the end
    while (fast.next != null) {
      fast = fast.next;
      slow = slow.next;
    }

    // Remove the nth node from the end
    slow.next = slow.next.next;

    return dummy.next;
  }

  @Override
  public void run() {
    int[] input = { 1, 2, 3, 4, 5 };
    int n = 2;

    ListNode head = LinkedListUtils.createLinkedList(input);
    LinkedListUtils.printList("Original List", head);

    ListNode updated = removeNthFromEnd(head, n);
    LinkedListUtils.printList("After Removing " + n + "th Node From End", updated);
  }
}
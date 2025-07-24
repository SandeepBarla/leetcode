/*
 * LeetCode Problem 21: Merge Two Sorted Lists
 * URL: https://leetcode.com/problems/merge-two-sorted-lists/
 * Difficulty: Easy
 *
 * Approach: Iterative Merge
 * - Compare the two list heads.
 * - Attach the smaller node to the result list.
 * - Move the pointer forward from the list whose node was used.
 * - At the end, attach the remaining part of the non-empty list.
 *
 * Time Complexity: O(n + m) where n = length of list1, m = length of list2
 * Space Complexity: O(1) (in-place merging)
 */
package solutions.top_interview_questions.easy;

import common.LinkedListUtils;
import common.ListNode;
import common.Solution;

public class LC_21_MergeTwoSortedLists implements Solution {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(0); // Dummy node
        ListNode node = head;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                node.next = list1;
                list1 = list1.next;
            } else {
                node.next = list2;
                list2 = list2.next;
            }
            node = node.next;
        }

        // Attach the remaining part
        if (list1 != null)
            node.next = list1;
        if (list2 != null)
            node.next = list2;

        return head.next;
    }

    @Override
    public void run() {
        int[] arr1 = { 1, 2, 4 };
        int[] arr2 = { 1, 3, 4 };

        ListNode list1 = LinkedListUtils.createLinkedList(arr1);
        ListNode list2 = LinkedListUtils.createLinkedList(arr2);

        LinkedListUtils.printList("List 1", list1);
        LinkedListUtils.printList("List 2", list2);

        ListNode mergedList = mergeTwoLists(list1, list2);
        LinkedListUtils.printList("Merged List", mergedList);
    }
}
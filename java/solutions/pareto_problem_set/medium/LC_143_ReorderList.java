package solutions.pareto_problem_set.medium;

import common.LinkedListUtils;
import common.ListNode;
import common.Solution;

public class LC_143_ReorderList implements Solution {
  public void reorderList(ListNode head) {
    if (head == null || head.next == null)
      return;

    // Step 1: Find the middle using slow & fast pointers
    ListNode fast = head, slow = head;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }

    // Step 2: Reverse the second half of the linked list
    ListNode prev = null, curr = slow.next;
    slow.next = null; // Cut the list into two halves
    while (curr != null) {
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }

    // Step 3: Merge the first and reversed second half
    ListNode first = head, second = prev;
    while (first != null && second != null) {
      ListNode temp1 = first.next, temp2 = second.next;
      first.next = second;
      second.next = temp1;
      first = temp1;
      second = temp2;
    }
  }

  @Override
  public void run() {
    // 🔹 Test Case 1: Odd-length List
    ListNode head1 = LinkedListUtils.createLinkedList(new int[] { 1, 2, 3, 4, 5 });
    System.out.println("Before Reordering:");
    LinkedListUtils.printList("Input", head1);
    reorderList(head1);
    System.out.println("After Reordering:");
    LinkedListUtils.printList("Output", head1);

    // 🔹 Test Case 2: Even-length List
    ListNode head2 = LinkedListUtils.createLinkedList(new int[] { 1, 2, 3, 4 });
    System.out.println("Before Reordering:");
    LinkedListUtils.printList("Input", head2);
    reorderList(head2);
    System.out.println("After Reordering:");
    LinkedListUtils.printList("Output", head2);

    // 🔹 Test Case 3: Two elements
    ListNode head3 = LinkedListUtils.createLinkedList(new int[] { 1, 2 });
    System.out.println("Before Reordering:");
    LinkedListUtils.printList("Input", head3);
    reorderList(head3);
    System.out.println("After Reordering:");
    LinkedListUtils.printList("Output", head3);

    // 🔹 Test Case 4: Single element
    ListNode head4 = LinkedListUtils.createLinkedList(new int[] { 1 });
    System.out.println("Before Reordering:");
    LinkedListUtils.printList("Input", head4);
    reorderList(head4);
    System.out.println("After Reordering:");
    LinkedListUtils.printList("Output", head4);
  }
}
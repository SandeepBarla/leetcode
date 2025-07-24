package solutions.top_interview_questions.hard;

import java.util.PriorityQueue;

import common.LinkedListUtils;
import common.ListNode;
import common.Solution;

public class LC_23_MergeKSortedLists implements Solution {

  // 🔹 Approach 1: Brute Force (Sequential Merging) (O(N k^2))
  public ListNode mergeKListsBruteForce(ListNode[] lists) {
    if (lists == null || lists.length == 0)
      return null;
    if (lists.length == 1)
      return lists[0];

    // Step 1: Merge the first two lists
    ListNode res = mergeTwoLists(lists[0], lists[1]);

    // Step 2: Iteratively merge with remaining lists
    for (int i = 2; i < lists.length; i++) {
      res = mergeTwoLists(res, lists[i]);
    }

    return res;
  }

  // 🔹 Approach 2: Min Heap (O(N log k))
  public ListNode mergeKListsUsingMinHeap(ListNode[] lists) {
    if (lists == null || lists.length == 0)
      return null;

    // Min Heap: Sort by node value
    PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

    // Step 1: Add the head of each list to the heap
    for (ListNode list : lists) {
      if (list != null) {
        minHeap.offer(list);
      }
    }

    // Step 2: Create a dummy node to form the merged list
    ListNode dummy = new ListNode(0);
    ListNode curr = dummy;

    // Step 3: Process elements from the heap
    while (!minHeap.isEmpty()) {
      ListNode node = minHeap.poll(); // Extract the smallest node
      curr.next = node;
      curr = curr.next;

      // If the extracted node has a next, add it to the heap
      if (node.next != null) {
        minHeap.offer(node.next);
      }
    }

    return dummy.next;
  }

  // 🔹 Approach 3: Divide and Conquer (O(N log k)) ✅ Best Approach
  public ListNode mergeKListsDivideAndConquer(ListNode[] lists) {
    if (lists == null || lists.length == 0)
      return null;
    return mergeLists(lists, 0, lists.length - 1);
  }

  // Recursively merge lists in a balanced way
  private ListNode mergeLists(ListNode[] lists, int left, int right) {
    if (left == right)
      return lists[left]; // Base case: Only one list
    int mid = left + (right - left) / 2;
    ListNode leftMerged = mergeLists(lists, left, mid);
    ListNode rightMerged = mergeLists(lists, mid + 1, right);
    return mergeTwoLists(leftMerged, rightMerged); // Merge two halves
  }

  // 🔹 Helper function to merge two sorted linked lists (O(N))
  private ListNode mergeTwoLists(ListNode nodeA, ListNode nodeB) {
    ListNode dummy = new ListNode(0);
    ListNode temp = dummy;

    while (nodeA != null && nodeB != null) {
      if (nodeA.val < nodeB.val) {
        temp.next = nodeA;
        nodeA = nodeA.next;
      } else {
        temp.next = nodeB;
        nodeB = nodeB.next;
      }
      temp = temp.next;
    }

    if (nodeA != null)
      temp.next = nodeA;
    if (nodeB != null)
      temp.next = nodeB;

    return dummy.next;
  }

  @Override
  public void run() {
    // 🔹 Test Case 1
    ListNode[] lists1 = {
        LinkedListUtils.createLinkedList(new int[] { 1, 4, 5 }),
        LinkedListUtils.createLinkedList(new int[] { 1, 3, 4 }),
        LinkedListUtils.createLinkedList(new int[] { 2, 6 })
    };

    for (int i = 0; i < lists1.length; i++) {
      LinkedListUtils.printList("InputList1[" + i + "]", lists1[i]);
    }

    System.out.println("Merged List (Brute Force):");
    LinkedListUtils.printList("Output", mergeKListsBruteForce(lists1));

    ListNode[] lists2 = {
        LinkedListUtils.createLinkedList(new int[] { 1, 4, 5 }),
        LinkedListUtils.createLinkedList(new int[] { 1, 3, 4 }),
        LinkedListUtils.createLinkedList(new int[] { 2, 6 })
    };

    System.out.println("Merged List (Min Heap):");
    LinkedListUtils.printList("Output", mergeKListsUsingMinHeap(lists2));

    ListNode[] lists3 = {
        LinkedListUtils.createLinkedList(new int[] { 1, 4, 5 }),
        LinkedListUtils.createLinkedList(new int[] { 1, 3, 4 }),
        LinkedListUtils.createLinkedList(new int[] { 2, 6 })
    };

    System.out.println("Merged List (Divide & Conquer):");
    LinkedListUtils.printList("Output", mergeKListsDivideAndConquer(lists3));

    // 🔹 Test Case 2
    ListNode[] lists4 = {
        LinkedListUtils.createLinkedList(new int[] { 2, 3, 5 }),
        LinkedListUtils.createLinkedList(new int[] { 1, 6, 7 }),
        LinkedListUtils.createLinkedList(new int[] { 0, 8, 9 })
    };

    for (int i = 0; i < lists4.length; i++) {
      LinkedListUtils.printList("InputList2[" + i + "]", lists4[i]);
    }

    System.out.println("Merged List (Brute Force):");
    LinkedListUtils.printList("Output", mergeKListsBruteForce(lists4));

    ListNode[] lists5 = {
        LinkedListUtils.createLinkedList(new int[] { 2, 3, 5 }),
        LinkedListUtils.createLinkedList(new int[] { 1, 6, 7 }),
        LinkedListUtils.createLinkedList(new int[] { 0, 8, 9 })
    };

    System.out.println("Merged List (Min Heap):");
    LinkedListUtils.printList("Output", mergeKListsUsingMinHeap(lists5));

    ListNode[] lists6 = {
        LinkedListUtils.createLinkedList(new int[] { 2, 3, 5 }),
        LinkedListUtils.createLinkedList(new int[] { 1, 6, 7 }),
        LinkedListUtils.createLinkedList(new int[] { 0, 8, 9 })
    };

    System.out.println("Merged List (Divide & Conquer):");
    LinkedListUtils.printList("Output", mergeKListsDivideAndConquer(lists6));
  }
}
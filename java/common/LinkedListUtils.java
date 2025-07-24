package common;

// Handles all linked list operations
public class LinkedListUtils {

    // Convert an array into a linked list
    public static ListNode createLinkedList(int[] nums) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        for (int num : nums) {
            current.next = new ListNode(num);
            current = current.next;
        }
        return dummyHead.next;
    }

    // Print a linked list with a label
    public static void printList(String label, ListNode head) {
        System.out.print(label + ": "); // Print the label first
        if (head == null) {
            System.out.println("Empty List");
            return;
        }
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) {
                System.out.print(" -> ");
            }
            head = head.next;
        }
        System.out.println();
    }

    // Create a linked list with a cycle (returns the head)
    // cyclePos: index to connect tail to (0-based), or -1 if no cycle
    public static ListNode createCyclicLinkedList(int[] nums, int cyclePos) {
        if (nums.length == 0)
            return null;

        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        ListNode cycleNode = null;

        for (int i = 0; i < nums.length; i++) {
            current.next = new ListNode(nums[i]);
            current = current.next;
            if (i == cyclePos) {
                cycleNode = current;
            }
        }

        if (cyclePos != -1) {
            current.next = cycleNode;
        }

        return dummyHead.next;
    }
}
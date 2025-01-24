package common;

public class ListNode {
    public int val;
    public ListNode next;

    // Default constructor (empty node)
    public ListNode() {}

    // Constructor for a single node
    public ListNode(int val) {
        this.val = val;
    }

    // Constructor for a node with a reference to next
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
// Maintaing two pointers with distance n between them
// T.C = O(n); n is length linkedlist;
// S.C = O(1)
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
        // iterate till nth node from the beginning
        for(int i=0; i<n; i++) fast = fast.next;
        // if the node is null, it means first node has to be removed
        if(fast==null) return head.next;
        // now iterate both slow and fast pointers at same time, to keep the n nodes difference between them 
        while(fast.next!=null){
            fast = fast.next;
            slow = slow.next;
        }
        // at this point slow.next is the node that need to be removed
        slow.next = slow.next.next;
        return head;
    }
}

// Calculating the length of the list
// T.C = O(n); n is length linkedlist;
// S.C = O(1)
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
        int count = 0;
        int length = 0;

        // calculate the length of the list
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            count++;
        }
        if(fast!=null) length = (2*count)+1;
        else length = 2*count;

        // calculate which node from the beginning has to be removed
        slow = head;
        count = length - n +1;
        
        int i=1;
        ListNode prev = null;
        // iterate till that node
        while(i!=count){
            prev = slow;
            slow = slow.next;
            i++;
        }

        // remove the node
        if(prev!=null) prev.next = slow.next;
        else return slow.next;
        return head;
        
    }
}
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
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0); // create a dumm head to return the actual head at last
        ListNode current = dummyHead;
        int carry = 0;
        // loop through until both l1 and l2 are empty
        while(l1!=null || l2!=null){
            int val1 = l1!=null ? l1.val : 0; // set value as 0 if l1 is empty
            int val2 = l2!=null ? l2.val : 0; // set value as 0 if l2 is empty
            int val = val1+val2+carry;
            carry = val/10; // carry will be 1 if val is greater than 10
            current.next = new ListNode(val%10); // create a new node with the value and add it to current list
            current = current.next;
            // go to next nodes
            if(l1!=null) l1=l1.next;
            if(l2!=null) l2=l2.next;
        }
        // add a new node if there is a left over carry
        if(carry==1){
            current.next = new ListNode(1);
        }
        // return the next value as head is dummy
        return dummyHead.next;
    }
}
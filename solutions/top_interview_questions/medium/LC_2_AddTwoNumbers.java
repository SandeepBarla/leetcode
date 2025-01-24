package solutions.top_interview_questions.medium;

import common.LinkedListUtils;
import common.ListNode;
import common.Solution;

// T.C = O(n); n is length of largest linkedlist
// S.C = O(n); n is length of largest linkedlist
public class LC_2_AddTwoNumbers implements Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0); // create a dummy head to return the actual head at last
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

    @Override
    public void run() {    
        // Use LinkedListUtils to create input linked lists
        int[] list1 = {9, 9, 9};
        int[] list2 = {9, 9, 9};
        
        ListNode l1 = LinkedListUtils.createLinkedList(list1);
        ListNode l2 = LinkedListUtils.createLinkedList(list2);
    
        // Print input lists with labels
        LinkedListUtils.printList("Input List 1", l1);
        LinkedListUtils.printList("Input List 2", l2);
    
        // Compute result
        ListNode result = addTwoNumbers(l1, l2);
    
        // Print output list
        LinkedListUtils.printList("Output", result);
    }
}
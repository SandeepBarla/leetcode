/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

// Using two pointers
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        //if a & b have different len, then we will stop the loop after second iteration
        while(nodeA!=nodeB){
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            nodeA = nodeA==null ? headB : nodeA.next;
            nodeB = nodeB==null ? headA : nodeB.next;
        }
        return nodeA; 
    }
}

// Finding the difference in length between two linkedlists and checking for match from there 
 public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode temp = headA;
        int m=0; // length of first linkedlist
        int n=0; // length of second linkedlist
        while(temp!=null){
            temp=temp.next;
            m++;
        }
        temp = headB;
        while(temp!=null){
            temp=temp.next;
            n++;
        }
        ListNode nodeA;
        ListNode nodeB;
        int diff;
        if(m>n){
            diff = m-n;
            nodeA = headA;
            while(diff>0){ // if listA is bigger skip those first few elements in listA
                nodeA = nodeA.next;
                diff--;
            }
            nodeB = headB;
        }else{
            diff = n-m;
            nodeB = headB;
            while(diff>0){ // if listB is bigger skip those first few elements in listB
                nodeB = nodeB.next;
                diff--;
            }
            nodeA = headA; 
        }

        while(nodeA!=null && nodeA!=nodeB){ // now start comparing nodes of listA and listB 
            nodeA = nodeA.next;
            nodeB = nodeB.next;
        }

        return nodeA;
    }
}


// Using HashSet
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode temp = headA;
        HashSet<ListNode> set = new HashSet<ListNode>();
        while(temp!=null){
            set.add(temp); // store all the nodes of linked list A in hashset
            temp=temp.next;
        }
        temp = headB;

        // loop through each node of linked list B and see if it exists in hashset
        while(temp!=null && !set.contains(temp)){
            temp=temp.next;
        }
        return temp;
        
    }
}
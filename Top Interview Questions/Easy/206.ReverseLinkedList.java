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

// Iterative approach
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        ListNode next;
        while(current!=null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }
}

class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null) return null;
        Stack<ListNode> stack = new Stack<>();
        ListNode node = head;
        // push all the elements from the head of list to stack
        while(node!=null){
            stack.push(node);
            node = node.next;
        }
        // set the latest element of stack as head of list
        head = stack.pop();
        node = head;
        // loop through until all the elements of stack were popped
        while(!stack.isEmpty()){
            node.next = stack.pop();
            node = node.next;
        }
        node.next = null;
        return head;
    }
}
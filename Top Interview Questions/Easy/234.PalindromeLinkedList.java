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

// Using pointers
class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode slow = head;
        ListNode fast = head;
        // reverse the linked list till middle element
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            curr.next = prev;
            prev = curr;
            curr = slow;
        }
        if(fast!=null) slow = slow.next; // skip middle element if total nodes are odd
        // start comparing reversed list and from mid of original linkedlist
        while(slow!=null){
            if(slow.val!=prev.val) return false;
            slow = slow.next;
            prev = prev.next;
        }
        return true;

    }
}

// Using Stack
class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode node = head;
        Stack<ListNode> stack = new Stack<>();
        // push all the nodes to stack
        while(node!=null){
            stack.push(node);
            node = node.next;
        }
        int i=0;
        node = head;
        int n = stack.size();
        // start comparing latest element in stack and head of the linkedlist
        while(i<n/2){
            if(node.val!=stack.pop().val) return false;
            node = node.next;
            i++;
        }
        return true;
    }
}
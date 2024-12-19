/**
 * Definition for singly-linked list.
 * class ListNode {
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
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast!=null && fast.next!=null){ // don't need to check for slow, as fast covers that already
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast) return true; // if there is a loop, slow and fast will meet eventually
        }
        return false;
    }
}

 // Using HashSet
public class Solution {
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<ListNode>();
        ListNode temp = head;
        while(temp!=null && !set.contains(temp)){
            set.add(temp);
            temp = temp.next;
        }
        return !(temp==null); // temp will be null, when there is no loop
    }
}
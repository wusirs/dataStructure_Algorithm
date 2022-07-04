package y_leetcode;

import y_leetcode.node.ListNode;

/**
 * 快慢指针
 * https://leetcode-cn.com/problems/linked-list-cycle/
 */
public class _141_环形链表 {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;
        ListNode fast = head.next.next ;
        ListNode slow = head.next;
        while (fast != null && fast.next != null){
            if (fast == slow)
                return true;
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}




package y_leetcode;

import y_leetcode.node.ListNode;

/**
 * 141. 环形链表
 * <a href="https://leetcode-cn.com/problems/linked-list-cycle/">141. 环形链表</a>
 */
public class LinkedListCycle {
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




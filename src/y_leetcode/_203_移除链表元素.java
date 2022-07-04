package y_leetcode;

import y_leetcode.node.ListNode;

/**
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 */
public class _203_移除链表元素 {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null || head.next == null)
            return head;

        ListNode pre = head;
        ListNode next = pre.next;
        while (pre != null){

        }

        return head;
    }
}

package y_leetcode;

import y_leetcode.node.ListNode;

/**
 * 203. 移除链表元素
 * <a href="https://leetcode-cn.com/problems/remove-linked-list-elements/">203. 移除链表元素</a>
 */
public class RemoveLinkedListElements {
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

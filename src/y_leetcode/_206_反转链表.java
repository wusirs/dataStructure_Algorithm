package y_leetcode;


import y_leetcode.node.ListNode;

/**
 * https://leetcode.cn/problems/reverse-linked-list/
 */
public class _206_反转链表 {

    /**
     * 递归
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newNode = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newNode;
    }


    /**
     * 非递归（迭代）
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        ListNode newHead = null;
        while (head != null){
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }
}

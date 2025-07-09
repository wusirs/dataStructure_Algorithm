package y_leetcode;


import y_leetcode.node.ListNode;

/**
 * 206. 反转链表
 * <a href="https://leetcode.cn/problems/reverse-linked-list/">206. 反转链表</a>
 */
public class ReverseLinkedList {

    /**
     * 递归
     * @param head 链表头
     * @return {@link ListNode}
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
     * @param head 链表头
     * @return {@link ListNode}
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

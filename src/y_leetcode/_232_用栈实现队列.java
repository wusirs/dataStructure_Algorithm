package y_leetcode;

import java.util.Stack;

/**
 * 232. 用栈实现队列
 * https://leetcode.cn/problems/implement-queue-using-stacks/
 */
public class _232_用栈实现队列 {
    private Stack<Integer> inStack = new Stack<>();
    private Stack<Integer> outStack = new Stack<>();

    public _232_用栈实现队列() {

    }

    /**
     * 入队
     * @param x
     */
    public void push(int x) {
        inStack.push(x);
    }

    /**
     * 出队
     * @return
     */
    public int pop() {
        trans();

        return outStack.pop();
    }

    private void trans() {
        if (outStack.isEmpty()){
            while (!inStack.isEmpty())
                outStack.push(inStack.pop());
        }
    }

    /**
     * 获取队头元素
     * @return
     */
    public int peek() {
        trans();

        return outStack.peek();
    }

    /**
     * 清空
     * @return
     */
    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }
}

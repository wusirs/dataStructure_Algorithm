package c_stack;

import java.util.Stack;

public class Y_Test {
    public static void main(String[] args) {
        A_Stack<Integer> stack = new A_Stack<>();
        stack.push(11);
        stack.push(22);
        stack.push(33);
        stack.push(44);
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}

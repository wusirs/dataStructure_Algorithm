package y_leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Stack;

/**
 * 20. 有效的括号
 * https://leetcode.cn/problems/valid-parentheses/solution/
 * 思路：逐个把左括号放入栈，当遇到右括号弹出栈顶元素与其匹配，完全匹配完则是有效的括号
 */
public class _20_有效的括号 {
    @Test
    public void test(){
        System.out.println(isValid1("[]{}"));
    }

    private static HashMap<Character, Character> map = new HashMap<>();

    static {
        map.put('(',')');
        map.put('[',']');
        map.put('{','}');
    }

    public boolean isValid1(String s) {
        int len = s.length();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)){
                stack.push(c);
            }else {
                if (stack.isEmpty()){
                    return false;
                }
                if (c != map.get(stack.pop()))
                    return false;
            }
        }

        return stack.isEmpty();
    }

    public boolean isValid(String s) {
        int len = s.length();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '[' || c == '('){
                stack.push(c);
            }else {
                if (stack.isEmpty()){
                    return false;
                }
                Character left = stack.pop();
                if (!equal(left, c)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }



    public boolean equal(Character left, Character right){
        if (left == '(' && right != ')')
            return false;
        if (left == '[' && right != ']')
            return false;
        if (left == '{' && right != '}')
            return false;
        return true;
    }
}

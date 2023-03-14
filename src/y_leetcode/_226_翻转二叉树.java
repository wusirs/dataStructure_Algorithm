package y_leetcode;

import y_leetcode.node.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/invert-binary-tree/
 */
public class _226_翻转二叉树 {

    /**
     * 前序遍历实现
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    /**
     * 后序遍历实现
     * @param root
     * @return
     */
    public TreeNode invertTree01(TreeNode root) {
        if (root == null) {
            return root;
        }

        invertTree01(root.left);
        invertTree01(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;
    }

    /**
     * 中遍历实现
     * @param root
     * @return
     */
    public TreeNode invertTree02(TreeNode root) {
        if (root == null) {
            return root;
        }
        invertTree02(root.left);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // 因为左右子树交换了,所以还应该遍历左子树
        invertTree02(root.left);
        return root;
    }

    /**
     * 层序历实现
     * @param root
     * @return
     */
    public TreeNode invertTree03(TreeNode root) {
        if (root == null) {
            return root;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();

            TreeNode temp = poll.left;
            poll.left = poll.right;
            poll.right = temp;

            if (poll.left != null){
                queue.offer(poll.left);
            }
            if (poll.right != null){
                queue.offer(poll.right);
            }
        }
        return root;
    }
}

package e_binary_tree;

import z_util.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("all")
public class A_BinaryTree<E> implements BinaryTreeInfo {
    protected int size;

    protected Node<E> root;

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public void clear() {
        this.root = null;
    }

    public void preorderTraversal() {
        preorderTraversal(root, null);
    }

    public void preorderTraversal(B_BST.Visitor<E> visitor) {
        preorderTraversal(root, visitor);
    }

    public void preorderTraversal(Node<E> node, B_BST.Visitor<E> visitor) {
        if (node == null) {
            return;
        }
        if (visitor != null && visitor.stop) {
            return;
        }
        if (visitor == null) {
            System.out.print(node.element + ", ");
        } else {
            visitor.stop = visitor.visit(node.element);
        }

        preorderTraversal(node.left, visitor);
        preorderTraversal(node.right, visitor);
    }


    public void inorderTraversal() {
        inorderTraversal(root, null);
    }

    public void inorderTraversal(B_BST.Visitor<E> visitor) {
        inorderTraversal(root, visitor);
    }

    public void inorderTraversal(Node<E> node, B_BST.Visitor<E> visitor) {
        if (node == null) {
            return;
        }
        if (visitor != null && visitor.stop) {
            return;
        }
        inorderTraversal(node.left, visitor);
        if (visitor == null) {
            System.out.print(node.element + ", ");
        } else {
            if (visitor != null && visitor.stop) {
                return;
            }
            visitor.stop = visitor.visit(node.element);
        }
        inorderTraversal(node.right, visitor);
    }


    public void postorderTraversal() {
        postorderTraversal(root, null);
    }

    public void postorderTraversal(B_BST.Visitor<E> visitor) {
        postorderTraversal(root, visitor);
    }

    public void postorderTraversal(Node<E> node, B_BST.Visitor<E> visitor) {
        if (node == null) {
            return;
        }

        if (visitor != null && visitor.stop) {
            return;
        }
        postorderTraversal(node.left, visitor);
        postorderTraversal(node.right, visitor);
        if (visitor == null) {
            System.out.print(node.element + ", ");
        } else {
            if (visitor != null && visitor.stop) {
                return;
            }
            visitor.stop = visitor.visit(node.element);
        }
    }


    /**
     * 层序遍历
     */
    public void levelOrderTraversal() {
        levelOrderTraversal(null);
    }

    public void levelOrderTraversal(B_BST.Visitor<E> visitor) {
        if (root == null) {
            return;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            if (visitor == null) {
                System.out.print(poll.element + ", ");
            } else {
                if (visitor.stop) {
                    return;
                }
                visitor.stop = visitor.visit(poll.element);
            }
            if (poll.left != null) {
                queue.offer(poll.left);
            }

            if (poll.right != null) {
                queue.offer(poll.right);
            }
        }
    }

    /**
     * 利用层序遍历判断一棵树是不是完全二叉树
     * 思路:
     * 如果树为空,返回false
     * 如果树不为空,开始层序遍历二叉树
     * 如果node.left != null && node.right != null,将node.left,node.right按顺序入队
     * 如果node.left == null && node.right != null,返回false
     * 如果node.left !=  null && node.right == null或者node.left == null && node.right == null
     * 那么接下来所有的节点都应该是叶子节点,如果接下来的遍历中出现了不是叶子结点的节点则返回false
     *
     * @return
     */
    public boolean isComplete() {
        if (root == null) {
            return false;
        }
        boolean leaf = false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            if (leaf && !poll.isLeaf()) {
                return false;
            }
            if (poll.left != null) {
                queue.offer(poll.left);
            } else if (poll.right != null) {
                //如果node.left == null && node.right != null,返回false
                return false;
            }

            if (poll.right != null) {
                //如果node.left != null && node.right != null,将node.left,node.right按顺序入队
                queue.offer(poll.right);
            } else {
//          如果node.left !=  null && node.right == null或者node.left == null && node.right == null
//          那么接下来所有的节点都应该是叶子节点,如果接下来的遍历中出现了不是叶子结点的节点则返回false
                leaf = true;
            }
        }
        return true;
    }

    /**
     * 和上面的isComplete效果一样,逻辑判断有区别
     * @param visitor
     */
//    public boolean isComplete() {
//        if (root == null) {
//            return false;
//        }
//        boolean leaf = false;
//        Queue<Node<E>> queue = new LinkedList<>();
//        queue.offer(root);
//        while (!queue.isEmpty()) {
//            Node<E> poll = queue.poll();
//            if (leaf && !poll.isLeaf()){
//                return false;
//            }
//            if (poll.hasTwoChildren()) {
//                queue.offer(poll.left);
//                queue.offer(poll.right);
//            } else if (poll.left == null && poll.right != null) {
//                return false;
//            } else {
//                if (poll.left != null) {
//                    queue.offer(poll.left);
//                }
//                leaf = true;
//            }
//        }
//        return true;
//    }


    /**
     * Returns the
     * 利用递归计算二叉树的高度
     *
     * @return
     */
    public int height() {
        return height(root);
    }

    public int height(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public int height2() {
        return height2(root);
    }

    /**
     * 利用层序遍历计算二叉树的高度
     *
     * @param node
     * @return
     */
    public int height2(Node<E> node) {
        if (node == null) {
            return 0;
        }
        // 树的高度
        int height = 0;
        // 存储着每一层的元素的数量
        int levelSize = 1;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            levelSize--; // 没访问队列中的一个节点,这一层的节点数就减一

            if (poll.left != null) {
                queue.offer(poll.left);
            }

            if (poll.right != null) {
                queue.offer(poll.right);
            }

            if (levelSize == 0) {
// 当levelSize减到零的时候就意味着即将要访问下一层,同时下一层的所有节点也都添加到队列中
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return node;
    }

    /**
     * 前驱节点:中序遍历的前一个节点
     *
     * @param node
     * @return
     */
    protected Node<E> predecessor(Node<E> node) {
        if (node == null) {
            return null;
        }
        Node<E> preNode = null;

        // 前驱节点在左子树当中(left.right.right.right.right...)
        if (node.left != null) {
            preNode = node.left;
            while (preNode.right != null) {
                preNode = preNode.right;
            }
            return preNode;
        }

        // 一直往上找父节点(a),直到找到父节点(a)是父节点(a)的父节点的左子节点为止
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        return node.parent;
    }

    /**
     * 后继节点:中序遍历的后一个节点
     *
     * @param node
     * @return
     */
    protected Node<E> successor(Node<E> node) {
        if (node == null) {
            return null;
        }
        Node<E> postNode = null;

        // 前驱节点在左子树当中(right.left.left.left...)
        if (node.right != null) {
            postNode = node.right;
            while (postNode.left != null) {
                postNode = postNode.left;
            }
            return postNode;
        }

        // 一直往上找父节点(a),直到找到父节点(a)是父节点(a)的父节点的右子节点为止
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }


    protected static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;

        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        public boolean isLeftChild(){
            return parent != null && this == parent.left;
        }

        public boolean isRightChild(){
            return parent != null && this == parent.right;
        }


        /**
         * @return 返回兄弟节点
         */
        public Node<E> sibling(){
            if (isLeftChild()){
                return parent.right;
            }

            if (isRightChild()){
                return parent.left;
            }

            return null;
        }

        @Override
        public String toString() {
//            Node<E> myNode = ((Node<E>) this);
            String parentStr = this.parent != null ? this.parent.element.toString() : "null";
            return this.element + "_p(" + parentStr + ")";
        }
    }

    public static abstract class Visitor<E> {
        boolean stop;

        abstract boolean visit(E element);
    }
}

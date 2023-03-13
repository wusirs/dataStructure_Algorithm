package e_binary_tree;


import z_util.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class A_BinarySearchTree<E> implements BinaryTreeInfo {
    private int size;

    private Node<E> root;

    private Comparator<E> comparator;

    public A_BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public A_BinarySearchTree() {
        this(null);
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public void clear() {

    }

    public void add(E element) {
        elementNotNullCheck(element);

        // 添加第一个节点
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }

        // 添加字节点不是第一个
        Node<E> node = root;
        Node<E> parent = root;
        int cmp = 0;
        while (node != null) {
            cmp = compare(element, node.element);
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                node.element = element;
                return;
            }
        }

        Node<E> nowNode = new Node<>(element, parent);
        if (cmp > 0) {
            parent.right = nowNode;
        }

        if (cmp < 0) {
            parent.left = nowNode;
        }

        size++;
    }

    public E remove() {
        return null;
    }

    public boolean contains(E element) {
        return false;
    }

    /**
     * 返回值等于0，则相等
     * 返回值大于0，则 e1 > e2
     * 返回值小于0，则 e1 < e2
     *
     * @param e1
     * @param e2
     * @return
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
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
        Node<E> myNode = ((Node<E>) node);
        String parentStr = myNode.parent != null ? myNode.parent.element.toString() : "null";
        return myNode.element + "_" + parentStr;
    }

    public void preorderTraversal() {
        preorderTraversal(root, null);
    }

    public void preorderTraversal(Visitor<E> visitor) {
        preorderTraversal(root, visitor);
    }

    public void preorderTraversal(Node<E> node, Visitor<E> visitor) {
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

    public void inorderTraversal(Visitor<E> visitor) {
        inorderTraversal(root, visitor);
    }

    public void inorderTraversal(Node<E> node, Visitor<E> visitor) {
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

    public void postorderTraversal(Visitor<E> visitor) {
        postorderTraversal(root, visitor);
    }

    public void postorderTraversal(Node<E> node, Visitor<E> visitor) {
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
            if (leaf && !poll.isLeaf()){
                return false;
            }
            if (poll.left != null){
                queue.offer(poll.left);
            }else if(poll.right != null){
                //如果node.left == null && node.right != null,返回false
                return false;
            }

            if (poll.right != null){
                //如果node.left != null && node.right != null,将node.left,node.right按顺序入队
                queue.offer(poll.right);
            }else {
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



    public void levelOrderTraversal(Visitor<E> visitor) {
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

    private static class Node<E> {
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
    }

    public static abstract class Visitor<E> {
        boolean stop;

        abstract boolean visit(E element);
    }
}

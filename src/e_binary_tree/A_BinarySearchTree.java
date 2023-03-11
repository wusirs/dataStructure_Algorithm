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

    public boolean isComplete() {
        if (root == null) {
            return false;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) {
                return false;
            }

            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) { // node.left == null && node.right != null
                return false;
            }

            if (node.right != null) {
                queue.offer(node.right);
            } else { // node.right == null
                leaf = true;
            }
        }

        return true;
    }


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
                if (visitor.stop){
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

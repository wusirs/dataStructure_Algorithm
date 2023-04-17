package e_binary_tree;

import java.util.Comparator;

/**
 * RedBlackTree
 * @author heisenberg
 */


//@SuppressWarnings("all")
public class D_RBTree<E> extends B_BST<E> {
    public static final boolean RED = false;
    public static final boolean BLACK = true;

    public D_RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    public D_RBTree() {
        this(null);
    }

    /**
     * 染色方法
     * @param node 要染色的节点
     * @param color 颜色
     * @return 返回染色后的节点
     */
    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) {
            return node;
        }
        ((RBNode<E>)node).color = color;
        return node;
    }

    /**
     *
     * @param node 要染色的节点
     * @return 返回染色后的节点
     */
    private Node<E> red(Node<E> node){
        return color(node, RED);
    }

    /**
     *
     * @param node 要染色的节点
     * @return 返回染色后的节点
     */
    private Node<E> black(Node<E> node){
        return color(node, BLACK);
    }

    /**
     * @param node 需要判断颜色的节点
     * @return 返回判断结果
     */
    private boolean colorOf(Node<E> node){
        return node == null ? BLACK : ((RBNode<E>)node).color;
    }

    /**
     *
     * @param node 需要判断颜色的节点
     * @return 返回判断结果
     */
    private boolean isBlack(Node<E> node){
        return colorOf(node) == BLACK;
    }

    /**
     *
     * @param node 需要判断颜色的节点
     * @return 返回判断结果
     */
    private boolean isRed(Node<E> node){
        return colorOf(node) == RED;
    }

    private static class RBNode<E> extends Node<E>{
        boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }
    }
}

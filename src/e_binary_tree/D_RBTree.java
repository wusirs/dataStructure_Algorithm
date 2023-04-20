package e_binary_tree;

import java.util.Comparator;

/**
 * RedBlackTree
 * @author heisenberg
 */


//@SuppressWarnings("all")
public class D_RBTree<E> extends B_BBST<E> {
    public static final boolean RED = false;
    public static final boolean BLACK = true;

    public D_RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    public D_RBTree() {
        this(null);
    }


    /**
     * 添加之后修复红黑树的性质
     * @param node 添加的节点
     */
    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;

        // 添加的是根节点
        if (parent == null) {
            black(node);
            return;
        }

        // 情况1：添加的节点父节点是黑色不需要任何处理
        if (isBlack(parent)) {
            return;
        }

        // uncle 节点
        Node<E> uncle = parent.sibling();
        // 祖父节点
        Node<E> grand = red(parent.parent);
        if (isRed(uncle)){
            // 情况2：叔父节点是红色，添加节点会导致【上溢】
            black(parent);
            black(uncle);
            // 祖父节点当作新添加的节点，把祖父节点染成红色，递归调用afterAdd方法
            afterAdd(grand);
            return;
        }
        // 情况3：叔父节点不是红色，添加节点不会上溢，但是要进行旋转
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                black(parent);
            }else{ // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        }else {
            if (node.isLeftChild()) { // RL
                black(node);
                rotateRight(parent);
            }else{ // RR
                black(parent);
            }
            rotateLeft(grand);
        }
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

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<E>(element, parent);
    }

    private static class RBNode<E> extends Node<E>{
        boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String  str = "";
            if (color == RED){
                str = "R_";
            }
            return str + element.toString();
        }
    }
}

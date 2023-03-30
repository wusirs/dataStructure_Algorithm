package e_binary_tree;

import java.util.Comparator;

/**
 * 平衡因子(Balance Factor):某节点的左右子树的高度差
 * AVLTree特点:
 * 每个节点的平衡因子只可能是1,0,-1(绝对值<=1,如果超过1,称之为"失衡");
 * 每个节点的左右子树高度差不超过1;
 * 搜索,添加,删除的时间复杂度是O(logn)
 * @param <E>
 */
public class C_AVLTree<E> extends B_BST<E>{
    public C_AVLTree() {
        this(null);
    }

    public C_AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * LL - 右旋(单旋):
     * g.left = p.right
     * p.right = g
     * 让p成为这棵子树的根节点,仍然是一棵二叉搜索树
     */

    @Override
    protected void afterAdd(Node<E> node) {
        // 从新添加的结点开始,一路往上找,直到找到第一个失衡的结点开始
        while ((node = node.parent) != null){
            if (isBalanced(node)){
                // TODO 更新高度
                updateHeight(node);
            }else {
                // TODO 恢复平衡
                restoreBalance(node);
                // 整棵树恢复平衡
                break;
            }
        }
    }

    /**
     * 恢复平衡
     * @param grand 高度最低的那个不平衡节点
     */
    private void restoreBalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>)grand).tallerChild();
        Node<E> node = ((AVLNode<E>)parent).tallerChild();

        if (parent.isLeftChild()){// L
            if (node.isLeftChild()){ // LL
                rotateRight(grand);
            }else{// LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        }else{ // R
            if (node.isLeftChild()){ // RL
                rotateRight(parent);
                rotateLeft(grand);
            }else{// RR
                rotateLeft(grand);
            }
        }
    }

    /**
     * 左旋
     * @param grand
     */
    private void rotateLeft(Node<E> grand){
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;

        afterRotate(grand, parent, child);
    }

    /**
     * 右旋
     * @param grand
     */
    private void rotateRight(Node<E> grand){
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;

        afterRotate(grand, parent, child);
    }

    /**
     * 左右旋转公用代码
     * @param grand
     * @param parent
     * @param child
     */
    private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        // 让parent成为这颗子树根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()){
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        }else{ // grand是根节点的情况
            root = parent;
        }

        //更新child的parent
        if (child != null){
            child.parent = grand;
        }

        // 更新grand的parent
        grand.parent = parent;

        updateHeight(grand);
        updateHeight(parent);
    }

    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }

    private void updateHeight(Node<E> node){
        ((AVLNode<E>) node).updateHeight();
    }

    private static class AVLNode<E> extends Node<E>{
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode)right).height;
            return leftHeight - rightHeight;
        }

        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode)right).height;
            this.height = 1 + Math.max(leftHeight, rightHeight);
        }

        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode)right).height;
            if (leftHeight > rightHeight) {
                return left;
            }

            if (leftHeight < rightHeight) {
                return right;
            }

            // 如果左右子树一样高的话,那么就返回同向的那个节点(也就是this这个节点是其父节点的左节点那么就返回左子节点,反之就返回右子节点)
            return isLeftChild() ? left : right;
        }

        @Override
        public String toString() {
            String parentStr = this.parent != null ? this.parent.element.toString() : "null";
            return this.element + "_p(" + parentStr + ")_h(" + this.height + ")";
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

//    @Override
//    public Object string(Object node) {
//        Node<E> myNode = ((Node<E>) node);
//        String parentStr = myNode.parent != null ? myNode.parent.element.toString() : "null";
//        return myNode.element + "_p(" + parentStr + ")_h(" + ((AVLNode<E>)node).height + ")";
//    }
}


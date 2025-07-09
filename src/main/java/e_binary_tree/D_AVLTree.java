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
public class D_AVLTree<E> extends B_BBST<E>{
    public D_AVLTree() {
        this(null);
    }

    public D_AVLTree(Comparator<E> comparator) {
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
//                restoreBalance(node);
                restoreBalance2(node);
                // 整棵树恢复平衡
                break;
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null){
            if (isBalanced(node)){
                // TODO 更新高度
                updateHeight(node);
            }else {
                // TODO 恢复平衡
                restoreBalance2(node);
            }
        }
    }

    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand, parent, child);
        // 更新高度
        updateHeight(grand);
        updateHeight(parent);
    }

    @Override
    protected void rotate(Node<E> childRoot, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f) {
        super.rotate(childRoot, b, c, d, e, f);

        // 更新高度
        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }

    /**
     * 恢复平衡
     * @param grand 高度最低的那个不平衡节点
     */
    @SuppressWarnings("unused")
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
     * 恢复平衡,统一旋转操作
     * @param grand 高度最低的那个不平衡节点
     */
    private void restoreBalance2(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>)grand).tallerChild();
        Node<E> node = ((AVLNode<E>)parent).tallerChild();

        // 详见AVL旋转统一.png 图片
        if (parent.isLeftChild()){// L
            if (node.isLeftChild()){ // LL
                rotate(grand, node, node.right, parent, parent.right, grand);
            }else{// LR
                rotate(grand, parent, node.left, node, node.right, grand);
            }
        }else{ // R
            if (node.isLeftChild()){ // RL
                rotate(grand, grand, node.left, node, node.right, parent);
            }else{// RR
                rotate(grand, grand, parent.left, parent, node.left, node);
            }
        }
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
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            return leftHeight - rightHeight;
        }

        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            this.height = 1 + Math.max(leftHeight, rightHeight);
        }

        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
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

    /*@Override
    public Object string(Object node) {
        Node<E> myNode = ((Node<E>) node);
        String parentStr = myNode.parent != null ? myNode.parent.element.toString() : "null";
        return myNode.element + "_p(" + parentStr + ")_h(" + ((AVLNode<E>)node).height + ")";
    }*/
}


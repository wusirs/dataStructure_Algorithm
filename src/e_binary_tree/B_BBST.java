package e_binary_tree;

import java.util.Comparator;

public class B_BBST<E> extends C_BST<E> {
    public B_BBST() {
        this(null);
    }

    public B_BBST(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * 左旋
     * @param grand 失衡的节点
     */
    protected void rotateLeft(Node<E> grand){
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;

        afterRotate(grand, parent, child);
    }

    /**
     * 右旋
     * @param grand 失衡的节点
     */
    protected void rotateRight(Node<E> grand){
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;

        afterRotate(grand, parent, child);
    }

    /**
     * 左右旋转公用代码
     * @param grand 失衡的节点
     * @param parent
     * @param child
     */
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
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
    }

    /**
     *  注意a和g节点可以不处理,因为a和g的节点相对位置没有发生变化
     * @param childRoot 子树根节点
     * @param b
     * @param c
     * @param d
     * @param e
     * @param f
     */
    protected void rotate(
            Node<E> childRoot, // 子树根节点
            Node<E> b, Node<E> c,
            Node<E> d,
            Node<E> e, Node<E> f){
        // 让d成为这棵子树的根节点
        d.parent = childRoot.parent;
        if (childRoot.isLeftChild()){
            childRoot.parent.left = d;
        }else if(childRoot.isRightChild()){
            childRoot.parent.right = d;
        }else{
            root = d;
        }

        //  b - c
        b.right = c;
        if(c != null){
            c.parent = b;
        }

        // e - f
        f.left = e;
        if (e != null){
            e.parent = f;
        }

        // b - d - f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
    }

}

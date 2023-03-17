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

    /**
     * <1>.删除叶子节点:叶子节点置为null,如果这个二叉树就一个节点,也就是根节点root就是叶子节点,那么直接root=null
     * <2>.删除度为1的节点:如果要删除的这个节点(a)度为1,那么直接用a的父节点指向a节点的子节点(度为1所以就一个子节点),
     * 然后让a的子节点的parent指向a的父节点;
     * 如果这个度为1的节点是根节点,那么直接root指向root的子节点,再让这个子节点的parent为null即可;
     * <3>.删除度为2的节点:先用前驱或者后继节点覆盖原节点的值,再删除相应的前驱或者后继节点,又因为这个度为2的
     *
     * note:如果一个节点的度为2,那么它的前驱和后继节点的度只可能是1或0
     * @return
     */
    public void remove(E element) {
        node(element);
    }

    private void remove(Node node){
        if (node == null) {
            return;
        }

        size --;
        if (node.hasTwoChildren()){ // 度为2的节点
            // 找后继
            Node successor = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.element = successor.element;
            // 删除后继节点,只需要把后继节点赋值给node,后面判断删除这个successor节点,这个后继节点的度必然是1或0
            node = successor;
        }
        // 删除node节点,能来到这个位置node的度必然是1或0
        Node replacement = node.left != null ? node.left : node.right;
        if (replacement != null){// node是度为1的节点
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left或right的指向
            if (node.parent == null){ // node是度为1的节点并且是根节点
                this.root = replacement;
            }else { // node是度为1的节点并且不是根节点
                if (node == node.parent.left){
                    node.parent.left = replacement;
                }else {// node == node.parent.right
                    node.parent.right = replacement;
                }
            }
        }else if (node.parent == null){// node是叶子节点并且是根节点
            this.root = null;
        }else { // node是叶子节点,但不是根节点
            if (node == node.parent.left){
                node.parent.left = null;
            }else { // node == node.parent.right
                node.parent.right = null;
            }
        }
    }

    private Node<E> node(E element){
        Node<E> node = root;
        while (node != null){
            int cmp = compare(element, node.element);
            if (cmp == 0) {
                return node;
            }
            if (cmp > 0){
                node = node.right;
            }else {
                node = node.left;
            }
        }
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

    /**
     * 前驱节点:中序遍历的前一个节点
     * @param node
     * @return
     */
    private Node<E> predecessor(Node<E> node){
        if (node == null){
            return null;
        }
        Node<E> preNode = null;

        // 前驱节点在左子树当中(left.right.right.right.right...)
        if (node.left != null){
            preNode = node.left;
            while (preNode.right != null){
                preNode = preNode.right;
            }
            return preNode;
        }

        // 一直往上找父节点(a),直到找到父节点(a)是父节点(a)的父节点的左子节点为止
        while (node.parent != null && node == node.parent.left){
            node = node.parent;
        }
        return  node.parent;
    }

    /**
     * 后继节点:中序遍历的后一个节点
     * @param node
     * @return
     */
    private Node<E> successor(Node<E> node){
        if (node == null){
            return null;
        }
        Node<E> postNode = null;

        // 前驱节点在左子树当中(right.left.left.left...)
        if (node.right != null){
            postNode = node.right;
            while (postNode.left != null){
                postNode = postNode.left;
            }
            return postNode;
        }

        // 一直往上找父节点(a),直到找到父节点(a)是父节点(a)的父节点的右子节点为止
        while (node.parent != null && node == node.parent.right){
            node = node.parent;
        }
        return  node.parent;
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

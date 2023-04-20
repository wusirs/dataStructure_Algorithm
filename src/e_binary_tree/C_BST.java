package e_binary_tree;


import java.util.Comparator;

public class C_BST<E> extends A_BinaryTree<E> {
    private final Comparator<E> comparator;

    public C_BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public C_BST() {
        this(null);
    }

    public void add(E element) {
        elementNotNullCheck(element);

        // 添加第一个节点
        if (root == null) {
            root = createNode(element, null);
            size++;

            // TODO 新添加节点之后的处理
            afterAdd(root);
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

        Node<E> newNode = createNode(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        }

        if (cmp < 0) {
            parent.left = newNode;
        }
        size++;
        // TODO 新添加节点之后的处理
        afterAdd(newNode);
    }

    /**
     * 添加node之后的调整,
     * @param node 添加的节点
     */
    protected void afterAdd(Node<E> node) {

    }

    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element,parent);
    }

    /**
     * <1>.删除叶子节点:叶子节点置为null,如果这个二叉树就一个节点,也就是根节点root就是叶子节点,那么直接root=null<br/>
     * <2>.删除度为1的节点:如果要删除的这个节点(a)度为1,那么直接用a的父节点指向a节点的子节点(度为1所以就一个子节点),
     * 然后让a的子节点的parent指向a的父节点;
     * 如果这个度为1的节点是根节点,那么直接root指向root的子节点,再让这个子节点的parent为null即可;<br/>
     * <3>.删除度为2的节点:先用前驱或者后继节点覆盖原节点的值,再删除相应的前驱或者后继节点,又因为这个度为2的节点的前驱或后继的度
     * 只可能是1或者0,所以接下来就变成了删除这个度为2的节点的前驱或者后继节点.删除度为1或0的节点可以参考<1><2><br/>
     * <p>
     * note:如果一个节点的度为2,那么它的前驱和后继节点的度只可能是1或0
     *
     */
    public void remove(E element) {
        remove(node(element));
    }

    private void remove(Node<E> node) {
        if (node == null) {
            return;
        }

        size--;
        if (node.hasTwoChildren()) { // 度为2的节点
            // 找后继
            Node<E> successor = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.element = successor.element;
            // 删除后继节点,只需要把后继节点赋值给node,后面判断删除这个successor节点,这个后继节点的度必然是1或0
            node = successor;
        }

        // 删除node节点,能来到这个位置node的度必然是1或0
        Node<E> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) {// node是度为1的节点
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left或right的指向
            if (node.parent == null) { // node是度为1的节点并且是根节点
                this.root = replacement;
            } else { // node是度为1的节点并且不是根节点
                if (node == node.parent.left) {
                    node.parent.left = replacement;
                } else {// node == node.parent.right
                    node.parent.right = replacement;
                }
            }

            // 真正被删除的节点
            afterRemove(node);
        } else if (node.parent == null) {// node是叶子节点并且是根节点
            this.root = null;

            // 真正被删除的节点
            afterRemove(node);
        } else { // node是叶子节点,但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else { // node == node.parent.right
                node.parent.right = null;
            }

            // 真正被删除的节点
            afterRemove(node);
        }
    }

    /**
     * 删除node之后的调整
     * @param node 被删除的节点
     */
    protected void afterRemove(Node<E> node) {
//        do nothing
    }

    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) {
                return node;
            }
            if (cmp > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    public boolean contains(E element) {
        return node(element) != null;
    }

    /**
     * 返回值等于0，则相等
     * 返回值大于0，则 e1 > e2
     * 返回值小于0，则 e1 < e2
     *
     * @param e1 元素1
     * @param e2 元素2
     * @return 返回比较结果
     */
    @SuppressWarnings("unchecked")
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
}

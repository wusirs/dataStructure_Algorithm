package b_link;

@SuppressWarnings("all")
public class F_CircleLinkedList<E> extends _B_AbstractList<E> {
    private Node<E> first;
    private Node<E> last;
    private Node<E> current;

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (prev != null)
                sb.append(prev.element);

            sb.append("_").append(element).append("_");

            if (next != null)
                sb.append(next.element);

            return sb.toString();
        }
    }

    /**
     * GC Root对象有哪些
     * 虚拟机栈 -----栈帧中的本地 变量表中引用的对象
     * 本地方法栈 -----即一般说的 Native方法引用的对象
     * 方法区中 类静态属性引用的对象
     * 方法区中 常量引用的对象
     */
    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
        ;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }


    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E oldElement = node.element;
        node.element = element;
        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        if (size == index) {
            Node<E> oldLast = last;
            last = new Node<>(oldLast, element, first);
            if (oldLast == null) { // 添加第一个元素
                first = last;
                first.next = first;
                first.prev = first;
            } else {
                oldLast.next = last;
                first.prev = last;
            }
        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node<>(prev, element, next);
            next.prev = node;

            prev.next = node;
            // 往0这个位置插入
            if (next == first) { // index == 0
                first = node;
            }
        }


        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        return remove(node(index));
    }

    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    private Node<E> node(int index) {
        rangeCheck(index);

        if (index < (size >> 1)) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    public void reset(){
        current = first;
    }

    public E next(){
        if (current == null)
            return null;
        current = current.next;
        return current.element;
    }

    private E remove(Node<E> node){
        if (size == 1) {
            first = last = null;
        } else {
            Node<E> prev = node.prev;
            Node<E> next = node.next;
            prev.next = next;
            next.prev = prev;

            if (node == first)   // index == 0
                first = next;

            if (node == last)  // index == size - 1
                last = prev;

        }
        size--;
        return node.element;
    }

    public E remove(){
        if (current == null)
            return null;
        Node<E> next = current.next;
        E element = remove(current);
        if (size == 0)
            current = null;
        else
            current = next;
        return element;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size = ").append(size).append(", [");
        Node<E> node = this.first;
        for (int i = 0; i < size; i++) {
            if (i != 0) stringBuilder.append(", ");
            stringBuilder.append(node);
            node = node.next;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}

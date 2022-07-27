package b_dataStructure;

@SuppressWarnings(value = {"unchecked", "rawtypes", "all"})
public class A_ArrayList<E> extends _B_AbstractList<E> {
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 存放数据
     */
    private E[] elements;

    /**
     * 有参构造
     *
     * @param capacity
     */
    public A_ArrayList(int capacity) {
        capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
        elements = (E[]) new Object[capacity];
    }

    /**
     * 无参构造
     */
    public A_ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 清除所有元素
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }


    /**
     * 获取index位置的元素
     *
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    /**
     * 设置index位置的元素
     *
     * @param index
     * @param element
     * @return 原来的元素ֵ
     */
    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    /**
     * 在index位置插入一个元素
     * 复杂度 ： 最好 : O(1), 最坏 : O(n), 平均 : O(n)
     *
     * @param index
     * @param element
     */
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        ensureCapacity(size + 1);
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }

//        System.arraycopy(elementData, index, elementData, index + 1,
//                size - index);
        elements[index] = element;
        size++;
    }

    /**
     * 删除index位置的元素
     * 复杂度 ： 最好 : O(1), 最坏 : O(n), 平均 : O(n)
     *
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        rangeCheck(index);
        E old = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        elements[--size] = null;

        trim();

        return old;
    }

    /**
     * 数组的缩容，在remove时可以缩容
     */
    private void trim() {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity >> 1;
        if (size >= newCapacity || oldCapacity <= DEFAULT_CAPACITY){
            return;
        }
        // 缩容
        E[] newElements = (E[])new Object[newCapacity];
        for (int i = 0; i < size; i ++){
            newElements[i] = elements[i];
        }
        elements = newElements;

        System.out.println(oldCapacity + "缩容为" + newCapacity);
    }

    public void remove(E element) {
        remove(indexOf(element));
    }

    /**
     * 查看元素的索引
     *
     * @param element
     * @return
     */
    @Override
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 保证要有capacity的容量
     *
     * @param capacity
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) {
            return;
        }
        // 新容量是旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
        System.out.println(oldCapacity + "扩容为" + newCapacity);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("size = ").append(size).append(", [");

        for (int i = 0; i < size; i++) {
            if (i != 0) stringBuilder.append(", ");
            stringBuilder.append(elements[i]);
//            if(i != (size - 1)){
//                stringBuilder.append(", ");
//            }
        }
        stringBuilder.append("]");
//        return Arrays.toString(elements);
        return stringBuilder.toString();
    }
}

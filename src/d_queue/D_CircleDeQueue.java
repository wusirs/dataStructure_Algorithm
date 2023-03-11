package d_queue;

import b_link.D_LinkedList;

/**
 * 循环双端队列
 */
public class D_CircleDeQueue<E> {
    private int size;

    private int front;

    private E[] elements;

    public static final int DEFAULT_CAPACITY = 10;

    public D_CircleDeQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public void clear(){
        for (int i = 0; i < elements.length; i++) {
            elements[index(i)] = null;
        }

        // 上面还要用到，所以放到后面
        front = 0;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 从尾部入队
     * @param element
     */
    public void enQueueRear(E element) {
        ensureCapacity(size + 1);
        elements[index(size)] = element;
        size ++;
    }

    /**
     * 从头部出队
     * @return
     */
    public E deQueueFront() {
        E element = elements[front];
        elements[front] = null;
        front = index(1);
        size --;

        return element;
    }

    /**
     * 从头部入队
     * @param element
     */
    public void enQueueFront(E element) {
        ensureCapacity(size + 1);
        front = index(-1);
        elements[front] = element;
        size ++;
    }

    /**
     * 从尾部出队
     */
    public E deQueueRear(){
        int rearIndex = index(size - 1);
        E element = elements[rearIndex];
        elements[rearIndex] = null;
        size --;
        return element;
    }

    public E front() {
        return elements[front];
    }

    public E rear() {
        return elements[index(size - 1)];
    }

    private int index(int index){
        index += front;
        // 避免负数出现
        if (index < 0){
           return index + elements.length;
        }
        return index - (index >= elements.length ? elements.length : 0);
    }

    private void ensureCapacity(int capacity){
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) {
            return;
        }

        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i ++){
            newElements[i] = elements[index(i)];
        }
        elements = newElements;
        front = 0;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("capacity=").append(elements.length)
                .append(" size=").append(size).append(" front=").append(front)
                .append(",[");
        for (int i = 0; i < elements.length; i++) {
            if (i != 0){
                stringBuilder.append(", ");
            }
            stringBuilder.append(elements[i]);
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}

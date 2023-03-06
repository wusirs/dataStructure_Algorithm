package d_queue;

/**
 * 循环队列
 */
@SuppressWarnings("unchecked")
public class C_CircleQueue<E> {
    private int size;

    private int front;

    private E[] elements;

    public static final int DEFAULT_CAPACITY = 10;

    public C_CircleQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public void clear(){
        for (int i = 0; i < elements.length; i++) {
            elements[index(i)] = null;
        }
        // 上面还要用到，所以放到后面
        size = 0;
        front = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int index(int index){
        index += front;
//        return index % elements.length;
//        return index >= elements.length ? index - elements.length : index;
        // 注意这种判断方法要求index>0 , length > 0, n > 2 * elements.length
        return index - (index >= elements.length ? elements.length : 0);
    }

    private void ensureCapacity(int capacity){
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i ++){
            newElements[i] = elements[index(i)];
        }
        elements = newElements;
        front = 0;
    }

    public void enQueue(E element) {
        ensureCapacity(size + 1);
        elements[index(size)] = element;
        size ++;
    }

    public E deQueue() {
        E frontElement = elements[this.front];
        elements[front] =null;
        front = index(1);
        size --;
        return frontElement;
    }

    public E front() {
        return elements[front];
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

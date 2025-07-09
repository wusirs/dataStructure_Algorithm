package d_queue;

import b_link.D_LinkedList;

public class B_Deque<E> {
    private D_LinkedList<E> linkedList = new D_LinkedList<E>();

    public void clear(){
        linkedList.clear();
    }

    public int size() {
        return linkedList.size();
    }

    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    public void enQueueRear(E element) {
        linkedList.add(element);
    }

    public E deQueueFront() {
        return linkedList.remove(0);
    }

    public void enQueueFront(E element) {
        linkedList.add(0, element);
    }

    public E deQueueRear(){
        return linkedList.remove(linkedList.size() - 1);
    }

    public E front() {
        return linkedList.get(0);
    }

    public E rear() {
        return linkedList.get(linkedList.size() - 1);
    }
}

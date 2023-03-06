package d_queue;

import b_link.D_LinkedList;
import b_link._A_List;

public class A_Queue<E> {
    private _A_List<E> linkedList = new D_LinkedList<>();


    public void clear(){
        linkedList.clear();
    }

    public int size() {
        return linkedList.size();
    }

    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    public void enQueue(E element) {
        linkedList.add(element);
    }

    public E deQueue() {
        return linkedList.remove(0);
    }

    public E front() {
        return linkedList.get(0);
    }

}

package c_stack;

import b_link.A_ArrayList;

public class A_Stack<E> {
    private A_ArrayList<E> list = new A_ArrayList<>();
    public void push(E element){
        list.add(element);
    }

    public E pop(){
        return list.remove(list.size() - 1);
    }

    public E top(){
        return list.get(list.size() - 1);
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void clear(){
        list.clear();
    }
}

package d_queue;

import org.junit.Test;

import javax.annotation.Resource;
import java.util.LinkedList;

public class Z_Test {
    public static void main(String[] args) {
        A_Queue<Integer> queue = new A_Queue<>();

        queue.enQueue(11);
        queue.enQueue(22);
        queue.enQueue(33);
        queue.enQueue(44);

        while (!queue.isEmpty()){
            System.out.println(queue.deQueue());
        }

        LinkedList a;
    }

    @Test
    public void test(){
        B_Deque<Integer> queue = new B_Deque<>();

        queue.enQueueFront(11);
        queue.enQueueFront(22);
        queue.enQueueRear(33);
        queue.enQueueRear(44);

        while (!queue.isEmpty()){
            System.out.println(queue.deQueueFront());
        }
    }
}

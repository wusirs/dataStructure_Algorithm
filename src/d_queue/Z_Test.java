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


    @Test
    public void circleQueueTest(){
        C_CircleQueue<Integer> queue = new C_CircleQueue<>();
        for (int i = 0; i < 10; i ++){
            queue.enQueue(i);
        }
        for (int i = 0; i < 5; i++) {
            queue.deQueue();
        }

        for (int i = 15; i < 23; i++) {
            queue.enQueue(i);
        }

        System.out.println(queue);
        while (!queue.isEmpty()){
            System.out.println(queue.deQueue());
        }
    }


    @Test
    public void circleDeQueueTest(){
        D_CircleDeQueue<Integer> queue = new D_CircleDeQueue<>();
        /** 头 5 4 3 2 1 100 101 102 103 104 105 尾
         第一次扩容 扩5位
         头 5 4 3 2 1 100 101 102 103 104 null null null null null 尾
         第一次扩容继续插入数据
         头 5 4 3 2 1 100 101 102 103 104 105 106 8 7 6 尾
         第一次扩容 扩7位
         8 7 6 5 4 3 2 1 100 101 102 103 104 105 106 null null null null null null null
         第二次扩容继续插入数据
         8 7 6 5 4 3 2 1 100 101 102 103 104 105 106 107 108 109 null null 10 9
         */
        for (int i = 0; i < 10; i ++){
            queue.enQueueFront(i + 1);
            queue.enQueueRear(i + 100);
        }
//        System.out.println(queue);
        for (int i = 0; i < 5; i++) {
            queue.deQueueFront();
            queue.deQueueRear();
        }
        queue.enQueueFront(11);
        queue.enQueueFront(12);
        System.out.println(queue);
        while (!queue.isEmpty()){
            System.out.println(queue.deQueueFront());
        }
    }
}

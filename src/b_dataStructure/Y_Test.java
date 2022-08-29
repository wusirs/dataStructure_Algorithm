package b_dataStructure;

import b_dataStructure.z_Objects.Person;
import org.junit.Test;
import z_util.Asserts;

public class Y_Test {
    @Test
    public void A_ArrayList_Test() {
        A_ArrayList<Person> arrayList = new A_ArrayList();
        arrayList.add(new Person(1, "Jack"));
        arrayList.add(new Person(3, "James"));
        arrayList.add(new Person(4, "Rose"));
        arrayList.add(new Person(5, "Mick"));
        arrayList.clear();

        // 提醒JVM进行垃圾回收
        System.gc();
    }


    @Test
    public void A_ArrayList_Test02() {
        A_ArrayList<Object> arrayList = new A_ArrayList();
        arrayList.add(19);
        arrayList.add(12);
        arrayList.add(13);
        arrayList.add(15);
        arrayList.remove(0);
        System.out.println(arrayList);
//        System.out.println(arrayList.indexOf(12));
    }

    @Test
    public void A_ArrayList_Test03() {
        A_ArrayList<Object> arrayList = new A_ArrayList();
        for (int i = 0; i < 50; i++) {
            arrayList.add(i);
        }

        for (int i = 0; i < 50; i++) {
            arrayList.remove(0);
        }

        System.out.println(arrayList);
    }

    @Test
    public void B_LinkList_Test01() {
        _A_List list = new B_SingleLinkList();
        list.add(20);
        list.add(0, 10);
        list.add(30);
        list.add(list.size(), 40);
        list.remove(1);
        System.out.println(list);
    }

    @Test
    public void B_LinkList_Test02() {
        _A_List list = new C_SingleLinkList();
        list.add(20);
        list.add(0, 10);
        list.add(30);
        list.add(list.size(), 40);
        list.remove(1);
        System.out.println(list);
    }

    @Test
    public void D_LinkedList_Test01(){
        D_LinkedList<Integer> list = new D_LinkedList();
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);

        list.add(0, 55);
        list.add(2, 66);
        list.add(list.size(), 77);

        list.remove(0);
        list.remove(2);
        list.remove(list.size() - 1);

        System.out.println(list);

        Asserts.test(list.indexOf(44) == 3);
        Asserts.test(list.indexOf(22) == A_ArrayList.ELEMENT_NOT_FOUND);
        Asserts.test(list.contains(33));
        Asserts.test(list.get(0) == 11);
        Asserts.test(list.get(1) == 66);
        Asserts.test(list.get(list.size() - 1) == 44);
    }


    @Test
    public void E_SingleCircleLinkedList_Test01(){
        E_SingleCircleLinkedList<Integer> list = new E_SingleCircleLinkedList();
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);

        list.add(0, 55);
        list.add(2, 66);
        list.add(list.size(), 77);

        System.out.println(list);

        list.remove(0);
        list.remove(2);
        list.remove(list.size() - 1);

        System.out.println(list);

        Asserts.test(list.indexOf(44) == 3);
        Asserts.test(list.indexOf(22) == A_ArrayList.ELEMENT_NOT_FOUND);
        Asserts.test(list.contains(33));
        Asserts.test(list.get(0) == 11);
        Asserts.test(list.get(1) == 66);
        Asserts.test(list.get(list.size() - 1) == 44);
    }

    @Test
    public void test01(){
        int gcb = gcb(24, 32);
        System.out.println(gcb);
        System.out.println(gcb1(36, 24));
        System.out.println(gcb2(36, 24));

        System.out.println(lcm(5, 8));
    }



    // 这里可以不判断a，b谁大
    // 最大公约数
    private int gcb(int a, int b){
        if (b == 0)
            return a;
        return gcb(b, a % b);
    }

    // 最小公倍数
    private int lcm(int a, int b){
        return a * b / gcb(a, b);
    }

    private int gcb1(int a, int b){
        while (a != b){
            if (a > b)
                a = a - b;
            else
                b = b - a;

        }

        return a;
    }

    private int gcb2(int a, int b){
        if(a < b){
            int temp = a;
            a = b;
            b = temp;
        }

        for (int i = b;;i--){
            if (a % i == 0 && b % i == 0)
                return i;
        }
    }
}

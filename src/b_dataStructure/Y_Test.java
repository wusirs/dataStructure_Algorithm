package b_dataStructure;

import b_dataStructure.z_Objects.Person;
import org.junit.Test;

public class Y_Test {
    @Test
    public void A_ArrayList_Test(){
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
    public void A_ArrayList_Test02(){
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
    public void B_LinkList_Test03(){
        _A_List list = new B_LinkList();
        list.add(20);
        list.add(0,10);
        list.add(30);
        list.add(list.size(), 40);
        list.remove(1);
        System.out.println(list);
    }

    @Test
    public void B_LinkList_Test04(){
        _A_List list = new C_LinkList();
        list.add(20);
        list.add(0,10);
        list.add(30);
        list.add(list.size(), 40);
        list.remove(1);
        System.out.println(list);
    }
}

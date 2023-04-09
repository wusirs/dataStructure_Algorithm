package zt_test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ATest {
    public static void main(String[] args) {
        LinkedList<A> list = new LinkedList<>();
        list.add(new A("公司"));
        list.add(new A("班组"));
        list.add(new A("工区"));
        List<A> collect =
                list.stream().sorted(Comparator.comparing(A::getName).reversed()).
                        collect(Collectors.toList());
        System.out.println(collect);
    }


}


class A {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public A(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "zt_test.A{" +
                "name='" + name + '\'' +
                '}';
    }
}


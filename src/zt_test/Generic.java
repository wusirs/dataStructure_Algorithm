package zt_test;

import java.util.ArrayList;
import java.util.List;

public class Generic {
    public static void main(String[] args) {
        ArrayList<Cat> cats = new ArrayList<>();
        cats.add(new Cat());
        printer(cats);
        Dog printer = printer(new Dog(), new Cat());
    }
    public static <T,V> T printer(T t, V v) {
        System.out.println(t);
        System.out.println(v);
        return t;
    }

    public static void printer(List<? extends Animal> animals) {
        System.out.println(animals);
    }

}

class Animal{

}

class Cat extends Animal{

}

class Dog extends Animal{

}
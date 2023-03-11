package e_binary_tree;

import org.junit.Test;
import w_bean.Person;
import z_util.file.Files;
import z_util.printer.BinaryTrees;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author heisenberg
 */
public class Z_BinarySearchTreeTest {
    @Test
    public void BinarySearchTreeTest_01() {
        Integer data[] = new Integer[]{7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
        A_BinarySearchTree<Integer> binarySearchTreeA = new A_BinarySearchTree<Integer>();
        for (int i = 0; i < data.length; i++) {
            binarySearchTreeA.add(data[i]);
        }

        BinaryTrees.println(binarySearchTreeA);
    }

    @Test
    public void BinarySearchTreeTest_02() {
        Integer data[] = new Integer[]{7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
        A_BinarySearchTree<Person> binarySearchTree =
                new A_BinarySearchTree<Person>(new Comparator<Person>() {
                    @Override
                    public int compare(Person p1, Person p2) {
                        return p1.getAge() - p2.getAge();
                    }
                });

        for (int i = 0; i < data.length; i++) {
            binarySearchTree.add(new Person(data[i]));
        }

        String s = BinaryTrees.printString(binarySearchTree);
        Files.writeToFile("D:/test/1.txt", s + "\n", true);
    }


    @Test
    public void BinarySearchTreeTest_03() {
        A_BinarySearchTree<Person> binarySearchTree =
                new A_BinarySearchTree<Person>(new Comparator<Person>() {
                    @Override
                    public int compare(Person p1, Person p2) {
                        return p1.getAge() - p2.getAge();
                    }
                });

        for (int i = 0; i < 30; i++) {
            binarySearchTree.add(new Person((int) (Math.random() * 100)));
        }

        String s = BinaryTrees.printString(binarySearchTree);
        Files.writeToFile("D:/test/1.txt", s + "\n", true);
    }


    // 前序遍历

    @Test
    public void preorderTraversalTest() {
        Integer data[] = new Integer[]{7, 4, 2, 1, 3, 5, 9, 8, 11, 10, 12};
        A_BinarySearchTree<Person> binarySearchTree =
                new A_BinarySearchTree<Person>(new Comparator<Person>() {
                    @Override
                    public int compare(Person p1, Person p2) {
                        return p1.getAge() - p2.getAge();
                    }
                });

        for (int i = 0; i < data.length; i++) {
            binarySearchTree.add(new Person(data[i]));
        }
//        binarySearchTree.preorderTraversal(new A_BinarySearchTree.Visitor<Person>() {
//            @Override
//            boolean visit(Person element) {
//                System.out.print("_" + element.toString());
//                if (element.getAge() == 4){
//                    return true;
//                }
//                return false;
//            }
//        });

        binarySearchTree.preorderTraversal();
    }

    @Test
    public void inorderTraversalTest() {
        Integer data[] = new Integer[]{7, 4, 2, 1, 3, 5, 9, 8, 11, 10, 12};
        A_BinarySearchTree<Person> binarySearchTree =
                new A_BinarySearchTree<Person>(new Comparator<Person>() {
                    @Override
                    public int compare(Person p1, Person p2) {
                        return p1.getAge() - p2.getAge();
                    }
                });

        for (int i = 0; i < data.length; i++) {
            binarySearchTree.add(new Person(data[i]));
        }

//        binarySearchTree.levelOrderTraversal(new A_BinarySearchTree.Visitor<Person>() {
//            @Override
//            public void visit(Person element) {
//                System.out.print(element.toString() + ", ");
//            }
//        });

//        binarySearchTree.levelOrderTraversal((element) -> System.out.print(element.toString() +
//        ", "));
        binarySearchTree.levelOrderTraversal();
    }
}

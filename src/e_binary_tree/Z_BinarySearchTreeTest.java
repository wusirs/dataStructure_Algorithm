package e_binary_tree;

import org.junit.Test;
import w_bean.Person;
import z_util.file.Files;
import z_util.printer.BinaryTrees;

import java.util.Comparator;

/**
 * @author heisenberg
 */
public class Z_BinarySearchTreeTest {
    @Test
    public void BinarySearchTreeTest_01() {
        Integer data[] = new Integer[]{7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
        B_BST<Integer> binarySearchTreeA = new B_BST<Integer>();
        for (int i = 0; i < data.length; i++) {
            binarySearchTreeA.add(data[i]);
        }

        BinaryTrees.println(binarySearchTreeA);
    }

    @Test
    public void BinarySearchTreeTest_02() {
        Integer data[] = new Integer[]{7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
        B_BST<Person> binarySearchTree =
                new B_BST<Person>(new Comparator<Person>() {
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
        B_BST<Person> binarySearchTree =
                new B_BST<Person>(new Comparator<Person>() {
                    @Override
                    public int compare(Person p1, Person p2) {
                        return p1.getAge() - p2.getAge();
                    }
                });

        for (int i = 0; i < 30; i++) {
            binarySearchTree.add(new Person((int) (Math.random() * 100)));
        }

        BinaryTrees.println(binarySearchTree);
//        String s = BinaryTrees.printString(binarySearchTree);
//        Files.writeToFile("D:/test/1.txt", s + "\n", true);
        System.out.println(binarySearchTree.height2());
        System.out.println(binarySearchTree.isComplete());
    }


    // 前序遍历

    @Test
    public void preorderTraversalTest() {
        Integer data[] = new Integer[]{7, 4, 2, 1, 3, 5, 9, 8, 11, 10, 12};
        B_BST<Person> binarySearchTree =
                new B_BST<Person>(new Comparator<Person>() {
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
        B_BST<Person> binarySearchTree =
                new B_BST<Person>(new Comparator<Person>() {
                    @Override
                    public int compare(Person p1, Person p2) {
                        return p1.getAge() - p2.getAge();
                    }
                });

        for (int i = 0; i < data.length; i++) {
            binarySearchTree.add(new Person(data[i]));
        }

        /*binarySearchTree.levelOrderTraversal(new A_BinarySearchTree.Visitor<Person>() {
            @Override
            public boolean visit(Person element) {
                System.out.print(element.toString() + ", ");
                if (element.getAge() == 4){
                    return true;
                }
                return false;
            }
        });*/
        binarySearchTree.levelOrderTraversal();
        System.out.println(binarySearchTree.height());
    }

    @Test
    public void isCompleteTreeTest() {
        Integer data[] = new Integer[]{7, 4, 9, 2, 5};
        B_BST<Person> binarySearchTree = new B_BST<Person>();
        for (int i = 0; i < data.length; i++) {
            binarySearchTree.add(new Person(data[i]));
        }
        BinaryTrees.println(binarySearchTree);
        System.out.println(binarySearchTree.isComplete());
    }

    @Test
    public void removeTest(){
        Integer data[] = new Integer[]{7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
        B_BST<Integer> binarySearchTree = new B_BST<Integer>();
        for (int i = 0; i < data.length; i++) {
            binarySearchTree.add(data[i]);
        }
        BinaryTrees.println(binarySearchTree);
//        binarySearchTree.remove(1);
//        binarySearchTree.remove(3);
//        binarySearchTree.remove(12);
//        binarySearchTree.remove(5);
        binarySearchTree.remove(7);
        BinaryTrees.println(binarySearchTree);
    }


    @Test
    public void AVLTreeTest(){
        Integer data[] = new Integer[]{7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
        C_AVLTree<Integer> avlTree = new C_AVLTree<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });
        for (int i = 0; i < data.length; i++) {
            avlTree.add(data[i]);
        }
        BinaryTrees.println(avlTree);
    }
}

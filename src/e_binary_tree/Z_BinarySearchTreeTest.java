package e_binary_tree;

import org.junit.Test;
import w_bean.Person;
import z_util.TimeTool;
import z_util.file.Files;
import z_util.printer.BinaryTrees;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author heisenberg
 */
@SuppressWarnings("all")
public class Z_BinarySearchTreeTest {
    @Test
    public void BinarySearchTreeTest_01() {
        Integer data[] = new Integer[]{7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
        C_BST<Integer> binarySearchTreeA = new C_BST<Integer>();
        for (int i = 0; i < data.length; i++) {
            binarySearchTreeA.add(data[i]);
        }

        BinaryTrees.println(binarySearchTreeA);
    }

    @Test
    public void BinarySearchTreeTest_02() {
        Integer data[] = new Integer[]{7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
        C_BST<Person> binarySearchTree =
                new C_BST<Person>(new Comparator<Person>() {
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
        C_BST<Person> binarySearchTree =
                new C_BST<Person>(new Comparator<Person>() {
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
        C_BST<Person> binarySearchTree =
                new C_BST<Person>(new Comparator<Person>() {
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
        C_BST<Person> binarySearchTree =
                new C_BST<Person>(new Comparator<Person>() {
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
        C_BST<Person> binarySearchTree = new C_BST<Person>();
        for (int i = 0; i < data.length; i++) {
            binarySearchTree.add(new Person(data[i]));
        }
        BinaryTrees.println(binarySearchTree);
        System.out.println(binarySearchTree.isComplete());
    }

    @Test
    public void removeTest() {
        Integer data[] = new Integer[]{7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
        C_BST<Integer> binarySearchTree = new C_BST<Integer>();
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
    public void AVLTreeTest() {
        Integer data[] = new Integer[]{85, 19, 69, 3, 7, 99, 95, 2, 1, 70, 44, 58, 11, 21, 14, 93
                , 57, 4, 56};
        D_AVLTree<Integer> avlTree = new D_AVLTree<Integer>();
        for (int i = 0; i < data.length; i++) {
            avlTree.add(data[i]);
            BinaryTrees.println(avlTree);
            System.out.println("--------------------------------");
        }
//        BinaryTrees.println(avlTree);
    }


    @Test
    public void AVLTreeRemoveTest() {
        Integer data[] = new Integer[]{67, 52, 92, 96, 53, 95, 13, 63, 34, 82, 76, 54, 9, 68, 39};
        D_AVLTree<Integer> avlTree = new D_AVLTree<Integer>();
        for (int i = 0; i < data.length; i++) {
            avlTree.add(data[i]);
        }

        for (int i = 0; i < data.length; i++) {
            avlTree.remove(data[i]);
            BinaryTrees.println(avlTree);
            System.out.println("________________________________________________________________");
        }
        BinaryTrees.println(avlTree);
    }

    @Test
    public void AVLTreeRemoveTest02() {
        List<Integer> data = new ArrayList<Integer>();
        for (int i = 0; i < 100_0000; i++) {
            data.add((int) (Math.random() * 100_0000));
        }

        // 可以对如下代码测消耗时间,比较BST 树和AVL 树的添加,搜索,删除耗时
        C_BST<Integer> bst = new C_BST<Integer>();
        TimeTool.check("BST二叉树添加", () -> {
            for (int i = 0; i < data.size(); i++) {
                bst.add(data.get(i));
            }
        });

        TimeTool.check("BST二叉树查找", () -> {
            for (int i = 0; i < data.size(); i++) {
                bst.contains(data.get(i));
            }
        });

        TimeTool.check("BST二叉树删除", () -> {
            for (int i = 0; i < data.size(); i++) {
                bst.remove(data.get(i));
            }
        });

        D_AVLTree<Integer> avlTree = new D_AVLTree<Integer>();
        TimeTool.check("AVL二叉树添加", () -> {
            for (int i = 0; i < data.size(); i++) {
                avlTree.add(data.get(i));
            }
        });

        TimeTool.check("AVL二叉树查找", () -> {
            for (int i = 0; i < data.size(); i++) {
                avlTree.contains(data.get(i));
            }
        });

        TimeTool.check("AVL二叉树删除", () -> {
            for (int i = 0; i < data.size(); i++) {
                avlTree.remove(data.get(i));
            }
        });

        BinaryTrees.println(avlTree);
    }

    @Test
    public void RBTTest() {
        Integer data[] = new Integer[]{55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50};
        D_RBTree<Integer> rb = new D_RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rb.add(data[i]);
            System.out.println("【" + data[i] + "】");
            BinaryTrees.println(rb);
            System.out.println("________________________________________________________________");
        }

        BinaryTrees.println(rb);
    }
}

package Tree;

import java.util.Arrays;

/**
 * Created by Usiel on 21.02.2016.
 */
public class Tree {
    public static void main(String[] args) {
        BinaryTreeNode t = new BinaryTreeNode(11, null);
        t.insert(15);
        t.insert(90);
        t.insert(10);
        t.insert(9);
        t.insert(8);
        t.insert(1);
        t.print();
        t.balance();
        t.print();
    }
}

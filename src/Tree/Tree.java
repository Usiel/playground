package Tree;

/**
 * Created by Usiel on 21.02.2016.
 */
public class Tree {
    public static void main(String[] args) {
        BinaryTreeNode t = new BinaryTreeNode(4);
        t.insert(2);
        t.insert(3);
        t.insert(5);
        t.insert(9);
        t.insert(1);
        t.insert(0);
        t.print();
        System.out.println(t.find(1));
        t.remove(4);
        System.out.println(t.find(1));
        t.print();
    }
}

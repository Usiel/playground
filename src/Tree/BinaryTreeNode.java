package Tree;

/**
 * Created by Usiel on 21.02.2016.
 */
public class BinaryTreeNode {
    public int key;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public BinaryTreeNode(int key) {
        this.key = key;
    }

    public void remove(int key) {
        BinaryTreeNode n = this.find(key);
        if (n.right == null && n.left == null) {
            // is leaf
            n = null;
        } else {
            if (n.right != null) {
                this.key = n.right.key;
                n.right.remove(n.right.key);
            } else {
                this.key = n.left.key;
                n.left.remove(n.left.key);
            }
        }
    }

    public void insert(int value) {
        if (value < key) {
            if (left == null) {
                left = new BinaryTreeNode(value);
            } else {
                left.insert(value);
            }
        } else {
            if (right == null) {
                right = new BinaryTreeNode(value);
            } else {
                right.insert(value);
            }
        }
    }

    public BinaryTreeNode find(int key) {
        if (this.key == key) {
            return this;
        } else {
            if (key < this.key) {
                if (left == null) {
                    return null;
                } else {
                    return left.find(key);
                }
            } else {
                if (right == null) {
                    return null;
                } else {
                    return right.find(key);
                }
            }
        }
    }

    public void print() {
        System.out.println(this.key);
        if (left != null)
            left.print();
        if (right != null)
            right.print();
    }
}

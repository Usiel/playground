package Tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

/**
 * Created by Usiel on 21.02.2016.
 */
public class BinaryTreeNode {
    public int key;
    public BinaryTreeNode left;
    public BinaryTreeNode right;
    public BinaryTreeNode parent;

    public BinaryTreeNode(int key, BinaryTreeNode parent) {
        this.key = key;
        this.parent = parent;
    }

    public void remove(int key) {
        BinaryTreeNode n = this.find(key);

        if (n.right == null && n.left == null) {
            // is leaf
            if (n.parent != null) {
                if (n.parent.right != null && n.parent.right.key == key) {
                    n.parent.right = null;
                } else {
                    n.parent.left = null;
                }
            }
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

    public int size() {
        return 1 + (left == null ? 0 : left.size()) + (right == null ? 0 : right.size());
    }

    public Stack<BinaryTreeNode> getNodesInOrder() {
        Stack<BinaryTreeNode> stack = new Stack<>();
        if (right != null) {
            stack.addAll(right.getNodesInOrder());
        }
        stack.add(this);
        if (left != null) {
            stack.addAll(left.getNodesInOrder());
        }
        return stack;
    }

    public int[] toSortedArray() {
        return toArray(getNodesInOrder());
    }

    private int[] toArray(Stack<BinaryTreeNode> stack) {
        int[] a = new int[size()];
        int i = 0;
        while (!stack.empty()) {
            a[i++] = stack.pop().key;
        }

        return a;
    }

    public int[] toLevelOrderedArray() {
        return toArray(getNodesInLevelOrder());
    }

    private Stack<BinaryTreeNode> getNodesInLevelOrder() {
        //bfs
        Deque<BinaryTreeNode> queue = new ArrayDeque<>(size()/2);
        Deque<BinaryTreeNode> result = new ArrayDeque<>(size());
        Stack<BinaryTreeNode> stack = new Stack<>();
        queue.push(this);
        while (!queue.isEmpty()) {
            BinaryTreeNode c = queue.poll();
            result.add(c);
            if (c.left != null) {
                queue.add(c.left);
            }
            if (c.right != null) {
                queue.add(c.right);
            }
        }

        while (!result.isEmpty()) {
            stack.push(result.pollLast());
        }

        return stack;
    }

    public int[] toArray() {
        return toArray(getNodesPreOrder());
    }

    private BinaryTreeNode min() {
        BinaryTreeNode node = this;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private BinaryTreeNode copy(BinaryTreeNode tree) {
        return tree;
    }

    public void invert() {
        BinaryTreeNode node = left;
        left = right;
        right = node;
        if (right != null) {
            right.invert();
        }
        if (left != null) {
            left.invert();
        }
    }

    public void insert(int value) {
        if (value < key) {
            if (left == null) {
                left = new BinaryTreeNode(value, this);
            } else {
                left.insert(value);
            }
        } else {
            if (right == null) {
                right = new BinaryTreeNode(value, this);
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
        System.out.println("Tree of size " + size());
        print(1);
    }

    public void print(int level) {
        System.out.println("LVL " + level + " - " + this.key);
        if (left != null)
            left.print(level+1);
        if (right != null)
            right.print(level+1);
    }

    public Stack<BinaryTreeNode> getNodesPreOrder() {
        Stack<BinaryTreeNode> stack = new Stack<>();
        if (right != null) {
            stack.addAll(right.getNodesPreOrder());
        }
        if (left != null) {
            stack.addAll(left.getNodesPreOrder());
        }
        stack.push(this);

        return stack;
    }

    public void balance() {
        if (left != null) {
            left.balance();
        }
        if (right != null) {
            right.balance();
        }
        if (right == null ^ left == null && parent != null) {
            int parentKey = parent.key;
            parent.key = this.key;
            if (parent.left == this) {
                parent.left = null;
            } else {
                parent.right = null;
            }
            parent.insert(parentKey);
            parent.insert(right, left);
        }
    }

    private void insert(BinaryTreeNode right, BinaryTreeNode left) {
        if (right != null) {
            this.insert(right.key);
            this.insert(right.right, right.left);
        }
        if (left != null) {
            this.insert(left.key);
            this.insert(left.right, left.left);
        }
    }
}

package model.tree;

import ucr.lab.model.tree.BTreeNode;
import ucr.lab.model.tree.Tree;
import ucr.lab.model.tree.TreeException;
import ucr.lab.utility.Equals;

import java.util.Random;

public class BTree<T extends Comparable<T>> implements Tree<T> {
    private BTreeNode<T> root;

    public BTree() {
        this.root = null;
    }

    public BTree(BTreeNode<T> root) {
        this.root = root;
    }

    @Override
    public int size() throws ucr.lab.model.tree.TreeException {
        if (isEmpty()) {
            throw new ucr.lab.model.tree.TreeException("Binary Tree is empty");
        }
        return size(root);
    }

    private int size(BTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean contains(T element) throws ucr.lab.model.tree.TreeException {
        if (isEmpty()) {
            throw new ucr.lab.model.tree.TreeException("Binary Tree is empty");
        }
        return binarySearch(root, element);
    }

    private boolean binarySearch(BTreeNode<T> node, T element) {
        if (node == null) {
            return false;
        }
        if (Equals.equals(node.data, element)) {
            return true;
        }
        return binarySearch(node.left, element) || binarySearch(node.right, element);
    }

    @Override
    public void add(T element) {
        this.root = add(root, element, "root");
    }

    private BTreeNode<T> add(BTreeNode<T> node, T element, String path) {
        if (node == null) {
            node = new BTreeNode<>(element, path);
        } else {
            int value = new Random().nextInt(10);
            if (value % 2 == 0)
                node.left = add(node.left, element, path + "/left");
            else
                node.right = add(node.right, element, path + "/right");
        }
        return node;
    }

    @Override
    public void remove(T element) throws ucr.lab.model.tree.TreeException {
        if (isEmpty()) {
            throw new ucr.lab.model.tree.TreeException("Binary Tree is empty");
        }
        root = remove(root, element);
    }

    private BTreeNode<T> remove(BTreeNode<T> node, T element) {
        if (node == null) return null;

        if (Equals.equals(node.data, element)) {
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                T replacementValue = getMin(node.right);
                node.data = replacementValue;
                node.right = remove(node.right, replacementValue);
            }
        } else {
            node.left = remove(node.left, element);
            node.right = remove(node.right, element);
        }

        return node;
    }

    private T getMin(BTreeNode<T> node) {
        T min = node.data;
        if (node.left != null) {
            T leftMin = getMin(node.left);
            if (leftMin.compareTo(min) < 0) min = leftMin;
        }
        if (node.right != null) {
            T rightMin = getMin(node.right);
            if (rightMin.compareTo(min) < 0) min = rightMin;
        }
        return min;
    }

    @Override
    public int height(T element) throws ucr.lab.model.tree.TreeException {
        if (isEmpty()) {
            throw new ucr.lab.model.tree.TreeException("Binary Tree is empty");
        }
        int h = height(root, element, 0);
        if (h == -1) throw new ucr.lab.model.tree.TreeException("Element not found in tree");
        return h;
    }

    private int height(BTreeNode<T> node, T element, int depth) {
        if (node == null) return -1;
        if (Equals.equals(node.data, element)) return depth;
        int leftHeight = height(node.left, element, depth + 1);
        if (leftHeight != -1) return leftHeight;
        return height(node.right, element, depth + 1);
    }

    @Override
    public int height() throws ucr.lab.model.tree.TreeException {
        if (isEmpty()) {
            throw new ucr.lab.model.tree.TreeException("Binary Tree is empty");
        }
        return height(root) - 1;
    }

    private int height(BTreeNode<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    @Override
    public T min() throws ucr.lab.model.tree.TreeException {
        if (isEmpty()) {
            throw new ucr.lab.model.tree.TreeException("Binary Tree is empty");
        }
        return getMin(root);
    }

    @Override
    public T max() throws ucr.lab.model.tree.TreeException {
        if (isEmpty()) {
            throw new ucr.lab.model.tree.TreeException("Binary Tree is empty");
        }
        return getMax(root);
    }

    private T getMax(BTreeNode<T> node) {
        T max = node.data;
        if (node.left != null) {
            T leftMax = getMax(node.left);
            if (leftMax.compareTo(max) > 0) max = leftMax;
        }
        if (node.right != null) {
            T rightMax = getMax(node.right);
            if (rightMax.compareTo(max) > 0) max = rightMax;
        }
        return max;
    }

    @Override
    public String preOrder() throws ucr.lab.model.tree.TreeException {
        if (isEmpty()) {
            throw new ucr.lab.model.tree.TreeException("Binary Tree is empty");
        }
        return preOrder(root).trim();
    }

    private String preOrder(BTreeNode<T> node) {
        String result = "";
        if (node != null) {
            result += node.data + "(" + node.path + ") ";
            result += preOrder(node.left);
            result += preOrder(node.right);
        }
        return result;
    }

    @Override
    public String inOrder() throws ucr.lab.model.tree.TreeException {
        if (isEmpty()) {
            throw new ucr.lab.model.tree.TreeException("Binary Tree is empty");
        }
        return inOrder(root).trim();
    }

    private String inOrder(BTreeNode<T> node) {
        String result = "";
        if (node != null) {
            result += inOrder(node.left);
            result += node.data + "(" + node.path + ") ";
            result += inOrder(node.right);
        }
        return result;
    }

    @Override
    public String postOrder() throws ucr.lab.model.tree.TreeException {
        if (isEmpty()) {
            throw new ucr.lab.model.tree.TreeException("Binary Tree is empty");
        }
        return postOrder(root).trim();
    }

    private String postOrder(BTreeNode<T> node) {
        String result = "";
        if (node != null) {
            result += postOrder(node.left);
            result += postOrder(node.right);
            result += node.data + "(" + node.path + ") ";
        }
        return result;
    }

    @Override
    public String nodeHeight() throws ucr.lab.model.tree.TreeException {
        if (isEmpty()) {
            throw new ucr.lab.model.tree.TreeException("Binary Tree is empty");
        }
        return nodeHeight(root).trim();
    }

    private String nodeHeight(BTreeNode<T> node) {
        String result = "";
        if (node != null) {
            result += node.data + ":" + (height(node) - 1) + " ";
            result += nodeHeight(node.left);
            result += nodeHeight(node.right);
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        try {
            result += "Binary Tree Tour " + "\n";
            result += "InOrder: " + inOrder() + "\n";
            result += "PreOrder: " + preOrder() + "\n";
            result += "PostOrder: " + postOrder();
        } catch (TreeException e) {
            return e.getMessage();
        }
        return result;
    }
}

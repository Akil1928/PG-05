package model.tree;

import ucr.lab.model.tree.BTreeNode;
import ucr.lab.model.tree.Tree;
import ucr.lab.model.tree.TreeException;
import ucr.lab.utility.Equals;


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
        int comparison = compareElements(element, node.data);
        if (comparison == 0) { // Element found
            return true;
        } else if (comparison < 0) { // Element is smaller, go left
            return binarySearch(node.left, element);
        } else { // Element is larger, go right
            return binarySearch(node.right, element);
        }
    }

    @Override
    public void add(T element) {
        this.root = add(root, element, "root");
    }

    private BTreeNode<T> add(BTreeNode<T> node, T element, String path) {
        if (node == null) {
            node = new BTreeNode<>(element, path);
        } else {
            int comparison = compareElements(element, node.data);
            if (comparison < 0) { // Element is smaller, go left
                node.left = add(node.left, element, path + "/left");
            } else if (comparison > 0) { // Element is larger, go right
                node.right = add(node.right, element, path + "/right");
            }
            // If comparison == 0, element is equal, do nothing (no duplicates added)
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

        int comparison = compareElements(element, node.data);

        if (comparison < 0) { // Element is smaller, go left
            node.left = remove(node.left, element);
        } else if (comparison > 0) { // Element is larger, go right
            node.right = remove(node.right, element);
        } else { // Element found (comparison == 0)
            // Case 1: No child or one child
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // Case 2: Two children
            // Find the in-order successor (smallest in the right subtree)
            node.data = getMin(node.right);
            // Delete the in-order successor
            node.right = remove(node.right, node.data);
        }
        return node;
    }

    @Override
    public int height(T element) throws ucr.lab.model.tree.TreeException {
        if (isEmpty()) {
            throw new ucr.lab.model.tree.TreeException("Binary Tree is empty");
        }
        int h = height(root, element, 1); // Start depth at 1 for "number of nodes" height
        if (h == -1) throw new ucr.lab.model.tree.TreeException("Element not found in tree");
        return h;
    }

    private int height(BTreeNode<T> node, T element, int depth) {
        if (node == null) return -1;
        int comparison = compareElements(element, node.data);
        if (comparison == 0) {
            return depth;
        } else if (comparison < 0) {
            return height(node.left, element, depth + 1);
        } else {
            return height(node.right, element, depth + 1);
        }
    }

    @Override
    public int height() throws ucr.lab.model.tree.TreeException {
        if (isEmpty()) {
            throw new ucr.lab.model.tree.TreeException("Binary Tree is empty");
        }
        return height(root); // Changed to return height(root) directly
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

    private T getMin(BTreeNode<T> node) {
        if (node.left == null) {
            return node.data;
        }
        return getMin(node.left);
    }

    @Override
    public T max() throws ucr.lab.model.tree.TreeException {
        if (isEmpty()) {
            throw new ucr.lab.model.tree.TreeException("Binary Tree is empty");
        }
        return getMax(root);
    }

    private T getMax(BTreeNode<T> node) {
        if (node.right == null) {
            return node.data;
        }
        return getMax(node.right);
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
            result += node.data + ":" + (height(node) - 1) + " "; // This will now be "height as nodes - 1"
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

   // ========================= ayuda ==========================
    private boolean equals (T a, T b){
        return  a== null ? b == null: a.equals(b);
    }

    //metodo genercico de comparacion
    private int compareElements(T a, T b){
        return a.compareTo(b);
    }
}

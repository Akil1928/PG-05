package model.tree;

public class BTreeNode<T> {
    public T data;
    public String path; // El path suele ser una cadena que indica la ruta (ej: "root/left/right")
    public BTreeNode<T> left, right;

    public BTreeNode(T data) {
        this.data = data;
        this.path = "root"; // Path por defecto para la raíz
        this.left = null;
        this.right = null;
    }

    public BTreeNode(T data, String path) {
        this.data = data;
        this.path = path;
        this.left = null;
        this.right = null;
    }

    public BTreeNode(T data, BTreeNode<T> left, BTreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
